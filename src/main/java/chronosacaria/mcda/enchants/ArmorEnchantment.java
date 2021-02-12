package chronosacaria.mcda.enchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class ArmorEnchantment  extends Enchantment {
    public final EnchantID id;

    public ArmorEnchantment(EnchantID id) {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        this.id = id;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
