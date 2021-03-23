package chronosacaria.mcda.effects;

import chronosacaria.mcda.api.AOEHelper;
import chronosacaria.mcda.registry.EnchantsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.enchants.EnchantID.*;

public class EnchantmentEffects {

    public static final List<Item> FOOD_RESERVE_LIST = Collections.unmodifiableList(Arrays.asList(
            Items.APPLE, Items.BREAD, Items.COOKED_SALMON, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON,
            Items.COOKED_COD, Items.COOKED_COD, Items.COOKED_RABBIT, Items.COOKED_CHICKEN, Items.COOKED_BEEF,
            Items.MELON_SLICE, Items.CARROT, Items.GOLDEN_CARROT, Items.GOLDEN_APPLE, Items.BAKED_POTATO));

    public static final List<ItemStack> SURPRISE_GIFT_LIST = Collections.unmodifiableList(Arrays.asList(
            PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH),
            PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS),
            PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY)));

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
        if (!config.enableEnchantment.get(FIRE_TRAIL))
            return;

        int fireTrailLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(FIRE_TRAIL), player);
        if (fireTrailLevel == 0) return;

        BlockState blockState = Blocks.FIRE.getDefaultState();

            BlockPos placeFireTrail = blockPos.offset(player.getMovementDirection().getOpposite(), 2);
            if (player.world.getBlockState(placeFireTrail).isAir() && player.isOnGround() && !(player.isSneaking())){
                player.world.setBlockState(placeFireTrail, blockState);
            }

    }

    public static void applyFoodReserves(PlayerEntity playerEntity) {
        if (!config.enableEnchantment.get(FOOD_RESERVES))
            return;

        if (isInstantHealthPotion(playerEntity.getMainHandStack())) {
            int foodReserveLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(FOOD_RESERVES), playerEntity);

            while (foodReserveLevel > 0) {
                Item foodToDrop = FOOD_RESERVE_LIST.get(playerEntity.getRandom().nextInt(FOOD_RESERVE_LIST.size()));
                ItemEntity foodDrop = new ItemEntity(playerEntity.world, playerEntity.getX(),
                        playerEntity.getY(), playerEntity.getZ(), new ItemStack(foodToDrop));
                playerEntity.world.spawnEntity(foodDrop);
                foodReserveLevel--;
            }
        }
    }

    public static void applyHealAllies(PlayerEntity playerEntity, float amount){
        if (!config.enableEnchantment.get(HEAL_ALLIES))
            return;

        if (playerEntity.getHealth() < playerEntity.getMaxHealth()){
            int healAlliesLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(HEAL_ALLIES), playerEntity);
            float damageToHealingMultiplier = (amount * (0.25F * healAlliesLevel));

            AOEHelper.healNearbyAllies(playerEntity, damageToHealingMultiplier, 12);
        }
    }

    public static void applyPotionBarrier(PlayerEntity playerEntity) {
        if (!config.enableEnchantment.get(POTION_BARRIER))
            return;

        if (isInstantHealthPotion(playerEntity.getMainHandStack())) {
            int potionBarrierLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(POTION_BARRIER), playerEntity);
            if (potionBarrierLevel == 0) return;
            int duration = 60 + (20 * potionBarrierLevel);
            StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, duration, 3);
            playerEntity.addStatusEffect(resistance);
        }
    }

    public static void applySurpriseGift(PlayerEntity playerEntity) {
        if (!config.enableEnchantment.get(SURPRISE_GIFT))
            return;

        if (isInstantHealthPotion(playerEntity.getMainHandStack())) {
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
    }

    // Effects for ServerPlayerEntityMixin
    public static void applyCowardice(ServerPlayerEntity player) {
        if (!config.enableEnchantment.get(COWARDICE))
            return;

        if (player.getHealth() == player.getMaxHealth()) {
            int cowardiceLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(COWARDICE), player);
            if (cowardiceLevel == 0) return;
            StatusEffectInstance strengthBoost = new StatusEffectInstance(StatusEffects.STRENGTH, 42, cowardiceLevel + 1, false, false);
            player.addStatusEffect(strengthBoost);
        }
    }

    public static void applyFrenzied(ServerPlayerEntity player) {
        if (!config.enableEnchantment.get(FRENZIED))
            return;

        if (player.getHealth() <= (0.5F * player.getMaxHealth())) {
            int frenziedLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(FRENZIED), player);
            if (frenziedLevel == 0) return;

            StatusEffectInstance frenzied = new StatusEffectInstance(StatusEffects.HASTE, 40, frenziedLevel, false, false);
            player.addStatusEffect(frenzied);
        }
    }

    public static void applyDeflect(EntityHitResult hitResult, PersistentProjectileEntity projectile) {
        if (!config.enableEnchantment.get(DEFLECT))
            return;

        if (!(projectile.getOwner() instanceof LivingEntity)) return;
        if (!(hitResult.getEntity() instanceof LivingEntity)) return;

        LivingEntity victim = (LivingEntity) hitResult.getEntity();
        int deflectLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(DEFLECT), victim);
        if (deflectLevel == 0) return;

        double originalDamage = projectile.getDamage();
        double deflectChance = deflectLevel * 0.2F;
        float deflectRand = victim.getRandom().nextFloat();
        if (deflectRand <= deflectChance) {
            projectile.setDamage(originalDamage * 0.5D);
            projectile.yaw += 180.0F;
            projectile.prevYaw += 180.0F;
        }
    }

    public static void applyReckless(ServerPlayerEntity player){
        if (!config.enableEnchantment.get(RECKLESS))
            return;

        int recklessLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(RECKLESS), player);
        if (recklessLevel == 0) return;

        if (player.getHealth() >= 8) {
            player.setHealth(8f);
        }

        if (player.getHealth() <= 8){
            StatusEffectInstance reckless = new StatusEffectInstance(StatusEffects.STRENGTH, 40, recklessLevel - 1,false, false);
            player.addStatusEffect(reckless);
        }

    }

    public static void applySwiftfooted(ServerPlayerEntity player){
        if (!config.enableEnchantment.get(SWIFTFOOTED))
            return;

        if (!(player.isOnGround())){
            int swiftfootedLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(SWIFTFOOTED),player);
            if (swiftfootedLevel == 0) return;

            StatusEffectInstance swiftfooted = new StatusEffectInstance(StatusEffects.SPEED, 60, swiftfootedLevel - 1,
                    false, false);
            player.addStatusEffect(swiftfooted);
        }
    }
}
