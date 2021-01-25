package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.Mcda;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class SwiftfootedEnchantment extends Enchantment{
        public SwiftfootedEnchantment(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
            super(weight, type, slotTypes);
            Registry.register(Registry.ENCHANTMENT, Mcda.ID("swiftfooted"), this);
        }

        public int getMaxLevel(){
            return 3;
        }
}
