package chronosacaria.mcda.enchants;

import chronosacaria.mcda.enchants.enchantments.BurningAuraEnchantment;
import chronosacaria.mcda.enchants.enchantments.FreezingAuraEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class EnchantsRegistry {
    public static Enchantment BURNING_AURA;
    public static Enchantment FREEZING_AURA;

    public static void init(){
        BURNING_AURA = new BurningAuraEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,
                new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        FREEZING_AURA = new FreezingAuraEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,
                new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }
}
