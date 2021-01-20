package chronosacaria.mcda.enchants;

import chronosacaria.mcda.enchants.enchantments.BurningEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class EnchantsRegistry {
    public static Enchantment BURNING;

    public static void init(){
        BURNING = new BurningEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }
}
