package chronosacaria.mcda.effects;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.api.McdaEnchantmentHelper;
import chronosacaria.mcda.registry.EnchantsRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.enchants.EnchantID.*;

public class EnchantmentEffects {

    public static final List<Item> FOOD_RESERVE_LIST = List.of(Items.APPLE, Items.BREAD, Items.COOKED_SALMON,
            Items.COOKED_PORKCHOP, Items.COOKED_MUTTON, Items.COOKED_COD, Items.COOKED_COD, Items.COOKED_RABBIT,
            Items.COOKED_CHICKEN, Items.COOKED_BEEF, Items.MELON_SLICE, Items.CARROT, Items.GOLDEN_CARROT,
            Items.GOLDEN_APPLE, Items.BAKED_POTATO);

    public static final List<ItemStack> SURPRISE_GIFT_LIST =
            List.of(PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH),
                    PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS),
                    PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY));

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    protected static boolean isInstantHealthPotion(ItemStack itemStack) {
        boolean hasInstantHealth = false;

        for (StatusEffectInstance potionEffect : PotionUtil.getPotionEffects(itemStack)) {
            if (potionEffect.getEffectType() == StatusEffects.INSTANT_HEALTH) {
                hasInstantHealth = true;
                break;
            }
        }

        return hasInstantHealth;
    }

    // Effects for LivingEntityMixin
    public static void applyFireTrail(PlayerEntity player, BlockPos blockPos){
        int fireTrailLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(FIRE_TRAIL), player);
        if (fireTrailLevel == 0) return;

        BlockPos placeFireTrail = blockPos.offset(player.getMovementDirection().getOpposite(), 2);
        if (player.world.getBlockState(placeFireTrail).isAir() && player.isOnGround() && !(player.isSneaking()))
            player.world.setBlockState(placeFireTrail, Blocks.FIRE.getDefaultState());

    }

    public static void applyFoodReserves(PlayerEntity playerEntity) {
        if (!isInstantHealthPotion(playerEntity.getMainHandStack()))
            return;
        int foodReserveLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(FOOD_RESERVES), playerEntity);

        while (foodReserveLevel > 0) {
            Item foodToDrop = FOOD_RESERVE_LIST.get(playerEntity.getRandom().nextInt(FOOD_RESERVE_LIST.size()));
            ItemEntity foodDrop = new ItemEntity(playerEntity.world, playerEntity.getX(),
                    playerEntity.getY(), playerEntity.getZ(), new ItemStack(foodToDrop));
            playerEntity.world.spawnEntity(foodDrop);
            foodReserveLevel--;
        }
    }

    public static void applyPotionBarrier(PlayerEntity playerEntity) {
        if (!isInstantHealthPotion(playerEntity.getMainHandStack()))
            return;
        int potionBarrierLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(POTION_BARRIER), playerEntity);
        if (potionBarrierLevel != 0)
            return;
        StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, 60 + (20 * potionBarrierLevel), 3);
        playerEntity.addStatusEffect(resistance);
    }

    public static void applySurpriseGift(PlayerEntity playerEntity) {
        if (!isInstantHealthPotion(playerEntity.getMainHandStack()))
            return;
        int surpriseGiftLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(SURPRISE_GIFT), playerEntity);
        if (surpriseGiftLevel == 0) return;

        float surpriseGiftChance = 0.5F * surpriseGiftLevel;

        while (surpriseGiftChance > 0) {
            float surpriseGiftRand = playerEntity.getRandom().nextFloat();
            if (surpriseGiftRand <= surpriseGiftChance) {
                ItemStack potionToDrop = SURPRISE_GIFT_LIST.get(playerEntity.getRandom().nextInt(SURPRISE_GIFT_LIST.size()));
                ItemEntity surpriseGift = new ItemEntity(playerEntity.world, playerEntity.getX(),
                        playerEntity.getY(), playerEntity.getZ(), potionToDrop);
                playerEntity.world.spawnEntity(surpriseGift);
            }
            surpriseGiftChance -= 1.0F;
        }
    }

    public static void applyLuckyExplorer(LivingEntity livingEntity){
        World world = livingEntity.getWorld();
        if (livingEntity.isOnGround() && world.getTime() % 50 == 0) {
            int luckyExplorerLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(LUCKY_EXPLORER),
                    livingEntity);
            if (luckyExplorerLevel == 0)
                return;

            float luckyExplorerThreshold = luckyExplorerLevel * 0.10f;
            float luckyExplorerRand = livingEntity.getRandom().nextFloat();

            if (luckyExplorerRand <= luckyExplorerThreshold) {
                ItemStack feetStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);

                double currentXCoord = livingEntity.getPos().getX();
                double currentZCoord = livingEntity.getPos().getZ();

                if (feetStack.getNbt().get("x-coord") == null) {
                    feetStack.getOrCreateNbt().putDouble("x-coord", currentXCoord);
                    feetStack.getOrCreateNbt().putDouble("z-coord", currentZCoord);
                    return;
                }

                double storedXCoord = feetStack.getNbt().getDouble("x-coord");
                double storedZCoord = feetStack.getNbt().getDouble("z-coord");

                Vec3d vec3d = new Vec3d(storedXCoord, 0, storedZCoord);

                double distanceBetween = Math.sqrt(vec3d.squaredDistanceTo(currentXCoord, 0, currentZCoord));

                if (distanceBetween >= 20) {
                    ItemEntity emerald = new ItemEntity(livingEntity.world, currentXCoord,
                            livingEntity.getY(), currentZCoord, Items.EMERALD.getDefaultStack());
                    livingEntity.world.spawnEntity(emerald);

                    feetStack.getOrCreateNbt().putDouble("x-coord", currentXCoord);
                    feetStack.getOrCreateNbt().putDouble("z-coord", currentZCoord);
                }
            }
        }
    }

    public static void applyFireFocusDamage(PlayerEntity playerEntity, LivingEntity target, float amount){
        if (McdaEnchantmentHelper.hasFireAspect(playerEntity) || target.isOnFire()) {
            int fireFocusLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(FIRE_FOCUS), playerEntity);
            if (fireFocusLevel > 0) {
                float multiplier = 1 + (0.25F * fireFocusLevel);
                target.damage(DamageSource.MAGIC, amount * multiplier);
            }
        }
    }

    public static void applyPoisonFocusDamage(PlayerEntity playerEntity, LivingEntity target, float amount){
        int poisonFocusLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(POISON_FOCUS), playerEntity);
        if (poisonFocusLevel > 0) {
            float multiplier = 1 + (0.25F * poisonFocusLevel);
            target.damage(DamageSource.MAGIC, amount * multiplier);
        }
    }

    public static boolean deathBarterEffect(PlayerEntity playerEntity){

        int deathBarterLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(DEATH_BARTER), playerEntity);
        if (deathBarterLevel > 0) {
            PlayerInventory playerInventory = playerEntity.getInventory();
            int emeraldTotal = 0;
            List<Integer> emeraldSlotIndices = new ArrayList<>();
            for (int slotIndex = 0; slotIndex < playerInventory.size(); slotIndex++){
                ItemStack currentStack = playerInventory.getStack(slotIndex);
                if(currentStack.getItem() == Items.EMERALD){
                    emeraldTotal += currentStack.getCount();
                    emeraldSlotIndices.add(slotIndex);
                }
            }
            int minEmeralds = 150 / deathBarterLevel;
            if (emeraldTotal >= minEmeralds && emeraldTotal > 0) {
                for (Integer slotIndex : emeraldSlotIndices) {
                    if (minEmeralds > 0) {
                        ItemStack currEmeraldsStack = playerInventory.getStack(slotIndex);
                        int currEmeraldsCount = currEmeraldsStack.getCount();
                        int emeraldsToTake = Math.min(minEmeralds, currEmeraldsCount);
                        currEmeraldsStack.setCount(currEmeraldsCount - emeraldsToTake);
                        minEmeralds -= emeraldsToTake;
                    } else
                        break;
                }
                CleanlinessHelper.onTotemDeathEffects(playerEntity);
                return  true;
            }
        }
        return false;
    }

    // Effects for ServerPlayerEntityMixin
    public static void applyCowardice(ServerPlayerEntity player) {
        if (!config.enableEnchantment.get(COWARDICE))
            return;

        if (player.getHealth() == player.getMaxHealth()) {
            int cowardiceLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(COWARDICE), player);
            if (cowardiceLevel == 0)
                return;
            StatusEffectInstance strengthBoost = new StatusEffectInstance(StatusEffects.STRENGTH, 42,
                    cowardiceLevel - 1, false, false);
            player.addStatusEffect(strengthBoost);
        }
    }

    public static void applyFrenzied(ServerPlayerEntity player) {
        if (!config.enableEnchantment.get(FRENZIED))
            return;

        if (player.getHealth() <= (0.5F * player.getMaxHealth())) {
            int frenziedLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(FRENZIED), player);
            if (frenziedLevel == 0)
                return;
            StatusEffectInstance frenzied = new StatusEffectInstance(StatusEffects.HASTE, 40, frenziedLevel - 1, false,
                    false);
            player.addStatusEffect(frenzied);
        }
    }

    public static void applyReckless(ServerPlayerEntity player){
        if (!config.enableEnchantment.get(RECKLESS))
            return;

        int recklessLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(RECKLESS), player);
        if (recklessLevel == 0)
            return;

        float recklessHealth = player.getMaxHealth() * 0.4F;
        if (player.getHealth() >= recklessHealth)
            player.setHealth(recklessHealth);

        StatusEffectInstance reckless = new StatusEffectInstance(StatusEffects.STRENGTH, 40, recklessLevel - 1,false, false);
        player.addStatusEffect(reckless);

    }

    public static void applySwiftfooted(ServerPlayerEntity player){
        if (!config.enableEnchantment.get(SWIFTFOOTED))
            return;

        int swiftfootedLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(SWIFTFOOTED),player);
        if (swiftfootedLevel == 0)
            return;

        if (!player.isOnGround()){
            StatusEffectInstance swiftfooted = new StatusEffectInstance(StatusEffects.SPEED, 60, swiftfootedLevel - 1,
                    false, false);
            player.addStatusEffect(swiftfooted);
        }
    }
}
