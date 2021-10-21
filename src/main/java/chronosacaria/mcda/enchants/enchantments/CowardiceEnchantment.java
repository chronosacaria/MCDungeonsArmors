package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.enchantment.Enchantment;

public class CowardiceEnchantment extends ArmorEnchantment {
    public CowardiceEnchantment(EnchantID enchantID) {
        super(enchantID);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canAccept(Enchantment other){
        return !(other instanceof RecklessEnchantment);
    }

}
