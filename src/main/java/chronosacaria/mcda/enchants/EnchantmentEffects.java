package chronosacaria.mcda.enchants;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EnchantmentEffects {

    public static final List<Item> FOOD_RESERVES = Collections.unmodifiableList(Arrays.asList(
            Items.APPLE, Items.BREAD, Items.COOKED_SALMON, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON,
            Items.COOKED_COD, Items.COOKED_COD, Items.COOKED_RABBIT, Items.COOKED_CHICKEN, Items.COOKED_BEEF,
            Items.MELON_SLICE, Items.CARROT, Items.GOLDEN_CARROT, Items.GOLDEN_APPLE, Items.BAKED_POTATO));

    public static final List<ItemStack> SURPRISE_GIFTS = Collections.unmodifiableList(Arrays.asList(
            PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH),
            PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS),
            PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY)));

    public static void applyCowardice(ServerPlayerEntity player) {
        if (player.getHealth() == player.getMaxHealth()) {
            int cowardiceLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.COWARDICE, player);
            if (cowardiceLevel == 0) return;
            StatusEffectInstance strengthBoost = new StatusEffectInstance(StatusEffects.STRENGTH, 40, cowardiceLevel + 1);
            player.addStatusEffect(strengthBoost);
        }
    }

    public static void applyFoodReserves(PlayerEntity playerEntity) {
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(playerEntity.getMainHandStack());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
            int foodReserveLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.FOOD_RESERVES, playerEntity);

            while (foodReserveLevel > 0) {
                Item foodToDrop = FOOD_RESERVES.get(playerEntity.getRandom().nextInt(FOOD_RESERVES.size()));
                ItemEntity foodDrop = new ItemEntity(playerEntity.world, playerEntity.getX(),
                        playerEntity.getY(), playerEntity.getZ(), new ItemStack(foodToDrop));
                playerEntity.world.spawnEntity(foodDrop);
                foodReserveLevel--;
            }
        }
    }

    public static void applyPotionBarrier(PlayerEntity playerEntity) {
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(playerEntity.getMainHandStack());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
            int potionBarrierLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.POTION_BARRIER, playerEntity);
            if (potionBarrierLevel == 0) return;
            int duration = 60 + (20 * potionBarrierLevel);
            StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, duration, 3);
            playerEntity.addStatusEffect(resistance);
        }
    }

    public static void applySurpriseGift(PlayerEntity playerEntity) {
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(playerEntity.getMainHandStack());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
            int surpriseGiftLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.SURPRISE_GIFT, playerEntity);
            if (surpriseGiftLevel == 0) return;
            float surpriseGiftChance = 0.5F * surpriseGiftLevel;

            while (surpriseGiftChance > 0) {
                float surpriseGiftRand = playerEntity.getRandom().nextFloat();
                if (surpriseGiftRand <= surpriseGiftChance) {
                    ItemStack potionToDrop = SURPRISE_GIFTS.get(playerEntity.getRandom().nextInt(SURPRISE_GIFTS.size()));
                    ItemEntity surpriseGift = new ItemEntity(playerEntity.world, playerEntity.getX(),
                            playerEntity.getY(), playerEntity.getZ(), potionToDrop);
                    playerEntity.world.spawnEntity(surpriseGift);
                }
                surpriseGiftChance -= 1.0F;
            }
        }
    }

    public static void applyFrenzied(ServerPlayerEntity player) {
        if (player.getHealth() <= (0.5F * player.getMaxHealth())) {
            int frenziedLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.FRENZIED, player);
            if (frenziedLevel == 0) return;
            StatusEffectInstance frenzied = new StatusEffectInstance(StatusEffects.HASTE, 40, frenziedLevel);
            player.addStatusEffect(frenzied);
        }
    }

    public static void applyDeflect(EntityHitResult hitResult, PersistentProjectileEntity projectile) {
        if (!(projectile.getOwner() instanceof LivingEntity)) return;
        if (!(hitResult.getEntity() instanceof LivingEntity)) return;

        LivingEntity victim = (LivingEntity) hitResult.getEntity();
        int deflectLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.DEFLECT, victim);
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
}
