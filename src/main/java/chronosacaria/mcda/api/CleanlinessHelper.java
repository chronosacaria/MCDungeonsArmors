package chronosacaria.mcda.api;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class CleanlinessHelper {
    public static boolean hasArmorSet(LivingEntity livingEntity, ArmorSets armorSets){
        ItemStack headStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack footStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);

        return headStack.getItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.HEAD).asItem()
                && chestStack.getItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.CHEST).asItem()
                && legStack.getItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.LEGS).asItem()
                && footStack.getItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.FEET).asItem();
    }

    public static boolean hasRobeSet(LivingEntity livingEntity, ArmorSets armorSets){
        ItemStack chestStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);

        return chestStack.getItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.CHEST).asItem()
                && legStack.getItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.LEGS).asItem();
    }

    public static boolean hasRobeWithHatSet(LivingEntity livingEntity, ArmorSets armorSets){
        ItemStack headStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);

        return headStack.getItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.HEAD).asItem()
                && chestStack.getItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.CHEST).asItem()
                && legStack.getItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.LEGS).asItem();
    }

    public static boolean isMysteryArmor(Item item, ArmorSets armorSets) {
        return item.asItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.HEAD)
                || item.asItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.CHEST)
                || item.asItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.LEGS)
                || item.asItem() == ArmorsRegistry.armorItems.get(armorSets).get(EquipmentSlot.FEET);
    }

    public static void onTotemDeathEffects(LivingEntity livingEntity) {
        livingEntity.setHealth(1.0F);
        livingEntity.clearStatusEffects();
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 900, 1));
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        livingEntity.world.sendEntityStatus(livingEntity, (byte) 35);
    }

    public static int mcdaFindHighestDurabilityEquipment(LivingEntity livingEntity) {
        // Trackers
        int i = 0;
        float[] armorPieceDurability = {0, 0, 0, 0};

        // Store durability percents of armor
        for (ItemStack itemStack : livingEntity.getArmorItems()) {
            float k = itemStack.getMaxDamage();
            float j = k - itemStack.getDamage();
            armorPieceDurability[i] = j / k;
            i++;
        }
        // Find the highest durability armor
        i = 0;
        for (int k = 0; k < 3; k++) {
            if (armorPieceDurability[k + 1] > armorPieceDurability[i]) {
                i = k + 1;
            }
        }

        return i;
    }

    public static void mcdaRandomArmorDamage(LivingEntity livingEntity, float damagePercentage, int totalNumOfPieces, boolean missingBoots){
        Random random = new Random();
        int index = random.nextInt(totalNumOfPieces);
        if (missingBoots)
            index++;

        switch (index){
            case 0 -> mcdaDamageEquipment(livingEntity, EquipmentSlot.FEET, damagePercentage);
            case 1 -> mcdaDamageEquipment(livingEntity, EquipmentSlot.LEGS, damagePercentage);
            case 2 -> mcdaDamageEquipment(livingEntity, EquipmentSlot.CHEST, damagePercentage);
            case 3 -> mcdaDamageEquipment(livingEntity, EquipmentSlot.HEAD, damagePercentage);
        }
    }

    public static void mcdaDamageEquipment(LivingEntity livingEntity, EquipmentSlot equipSlot, float damagePercentage) {
        ItemStack armorStack = livingEntity.getEquippedStack(equipSlot);
        int k = armorStack.getMaxDamage();
        int j = k - armorStack.getDamage();
        // Necessary for proper types.
        int breakDamage = (int) (k * damagePercentage);
        boolean toBreak = j <= breakDamage;

        if (toBreak)
            armorStack.damage(j, livingEntity,
                    (entity) -> entity.sendEquipmentBreakStatus(equipSlot));
        else
            armorStack.damage(breakDamage, livingEntity,
                    (entity) -> entity.sendEquipmentBreakStatus(equipSlot));
    }

    public static boolean mcdaCooldownCheck(LivingEntity livingEntity, int ticks){
        ItemStack chestStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);

        long currentTime = livingEntity.world.getTime();

        if (chestStack.getNbt().get("time-check") == null) {
            chestStack.getOrCreateNbt().putLong("time-check", currentTime);
            return false;
        }
        long storedTime = chestStack.getNbt().getLong("time-check");

        return Math.abs(currentTime - storedTime) > ticks;
    }

    public static boolean mcdaBoundingBox(PlayerEntity playerEntity, float boxSize) {
        return playerEntity.world.getBlockCollisions(playerEntity,
                playerEntity.getBoundingBox().offset(boxSize * playerEntity.getBoundingBox().getXLength(), 0,
                        boxSize * playerEntity.getBoundingBox().getZLength())).iterator().hasNext();
    }
}
