package chronosacaria.mcda.enchants;

import chronosacaria.mcda.api.EnchantHelper;
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
import java.util.List;

public class EnchantmentEffects {

    public static void applyCowardice(ServerPlayerEntity player) {
        float maxHealth = player.getMaxHealth();
        float currentHeath = player.getHealth();
        if (currentHeath == maxHealth){
            if (EnchantHelper.hasEnchantment(player, EnchantsRegistry.COWARDICE)){
                int cowardiceLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.COWARDICE, player);
                StatusEffectInstance strengthBoost = new StatusEffectInstance(StatusEffects.STRENGTH, 40,
                        cowardiceLevel + 1);
                player.addStatusEffect(strengthBoost);
            }
        }
    }

    public static void applyFoodReserves(PlayerEntity playerEntity) {
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(playerEntity.getMainHandStack());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH){
            if (EnchantHelper.hasEnchantment(playerEntity, EnchantsRegistry.FOOD_RESERVES)){
                int foodReserveLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.FOOD_RESERVES,
                        playerEntity);
                List<Item> foodList = Arrays.asList(Items.APPLE, Items.BREAD, Items.COOKED_SALMON,
                        Items.COOKED_PORKCHOP, Items.COOKED_MUTTON, Items.COOKED_COD, Items.COOKED_COD,
                        Items.COOKED_RABBIT, Items.COOKED_CHICKEN, Items.COOKED_BEEF, Items.MELON_SLICE,
                        Items.CARROT, Items.GOLDEN_CARROT, Items.GOLDEN_APPLE, Items.BAKED_POTATO);

                while (foodReserveLevel > 0){
                    Item foodToDrop = foodList.get(playerEntity.getRandom().nextInt(foodList.size()));
                    ItemEntity foodDrop = new ItemEntity(playerEntity.world, playerEntity.getX(),
                            playerEntity.getY(), playerEntity.getZ(), new ItemStack(foodToDrop));
                    playerEntity.world.spawnEntity(foodDrop);
                    foodReserveLevel--;
                }
            }
        }
    }

    public static void applyPotionBarrier(PlayerEntity playerEntity) {
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(playerEntity.getMainHandStack());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
            if (EnchantHelper.hasEnchantment(playerEntity, EnchantsRegistry.POTION_BARRIER)) {
                int potionBarrierLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.POTION_BARRIER,
                        playerEntity);
                int duration = 60 + (20 * potionBarrierLevel);
                StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, duration, 3);
                playerEntity.addStatusEffect(resistance);
            }
        }
    }

    public static void applySurpriseGift(PlayerEntity playerEntity) {
        List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(playerEntity.getMainHandStack());
        if (potionEffects.isEmpty()) return;
        if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH){
            if (EnchantHelper.hasEnchantment(playerEntity, EnchantsRegistry.SURPRISE_GIFT)){
                int surpriseGiftLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.SURPRISE_GIFT,
                        playerEntity);
                float surpriseGiftChance = 0.5F * surpriseGiftLevel;

                ItemStack strengthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH);
                ItemStack speedPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS);
                ItemStack invisPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY);
                List<ItemStack> giftList = Arrays.asList(strengthPotion, speedPotion, invisPotion);

                while (surpriseGiftChance > 0){
                    float surpriseGiftRand = playerEntity.getRandom().nextFloat();
                    if (surpriseGiftRand <= surpriseGiftChance){
                        ItemStack potionToDrop = giftList.get(playerEntity.getRandom().nextInt(giftList.size()));
                        ItemEntity surpriseGift = new ItemEntity(playerEntity.world, playerEntity.getX(),
                                playerEntity.getY(), playerEntity.getZ(), potionToDrop);
                        playerEntity.world.spawnEntity(surpriseGift);
                    }
                    surpriseGiftChance -= 1.0F;
                }
            }
        }
    }

    public static void applyFrenzied(ServerPlayerEntity player) {
        float maxHealth = player.getMaxHealth();
        float currentHeath = player.getHealth();
        if (currentHeath <= (0.5F * maxHealth)){
            if (EnchantHelper.hasEnchantment(player, EnchantsRegistry.FRENZIED)){
                int cowardiceLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.FRENZIED, player);
                StatusEffectInstance frenzied = new StatusEffectInstance(StatusEffects.HASTE, 40, cowardiceLevel);
                player.addStatusEffect(frenzied);
            }
        }
    }

    public static void applyDeflect(EntityHitResult hitResult, PersistentProjectileEntity projectile) {
        if (!EnchantHelper.shooterIsLiving(projectile)) return;
        LivingEntity victim = (LivingEntity) hitResult.getEntity();
        if (EnchantHelper.hasEnchantment(victim, EnchantsRegistry.DEFLECT)){
            int deflectLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.DEFLECT, victim);
            double originalDamage = projectile.getDamage();
            double deflectChance = deflectLevel * 0.2F;
            float deflectRand = victim.getRandom().nextFloat();
            if (deflectRand <= deflectChance){
                projectile.setDamage(originalDamage * 0.5D);
                projectile.yaw += 180.0F;
                projectile.prevYaw += 180.0F;
            }
        }
    }
}
