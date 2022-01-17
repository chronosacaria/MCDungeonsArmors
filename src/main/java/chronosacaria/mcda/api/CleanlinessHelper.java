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

    public static float[] mcdaFindHighestDurabilityEquipment(LivingEntity livingEntity) {
        // Trackers
        int i = 1;
        float[] armorPieceDurability = {0, 0, 0, 0, 0};

        // Store durability percents of armor
        for (ItemStack itemStack : livingEntity.getArmorItems()) {
            float k = itemStack.getMaxDamage();
            float j = k - itemStack.getDamage();
            armorPieceDurability[i] = j / k;
            i++;
        }
        // Find the highest durability armor
        i = 0;
        for (int k = 0; k < 4; k++) {
            if (armorPieceDurability[k + 1] > armorPieceDurability[i]) {
                i = k + 1;
            }
        }
        // Durabilities are stored in the last 4 slots
        // Index of the highest durability is the first index in the array
        armorPieceDurability[0] = i;
        return armorPieceDurability;
    }

    public static void mcdaDamageEquipment(LivingEntity livingEntity, EquipmentSlot equipSlot, float damageAmount, boolean toBreak) {
        ItemStack armorStack = livingEntity.getEquippedStack(equipSlot);
        int k = armorStack.getMaxDamage();
        int j = k - armorStack.getDamage();
        // Necessary for proper types.
        damageAmount = 1 / damageAmount;
        if (toBreak)
            armorStack.damage(j, livingEntity,
                    (entity) -> entity.sendEquipmentBreakStatus(equipSlot));
        else
            armorStack.damage((k / (int) damageAmount), livingEntity,
                    (entity) -> entity.sendEquipmentBreakStatus(equipSlot));
    }

    public static boolean mcdaBoundingBox(PlayerEntity playerEntity, float boxSize) {
        return playerEntity.world.getBlockCollisions(playerEntity,
                playerEntity.getBoundingBox().offset(boxSize * playerEntity.getBoundingBox().getXLength(), 0,
                        boxSize * playerEntity.getBoundingBox().getZLength())).iterator().hasNext();
    }
}
