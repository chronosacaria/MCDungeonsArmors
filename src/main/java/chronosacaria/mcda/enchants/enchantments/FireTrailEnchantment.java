package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.enchantment.Enchantment;

public class FireTrailEnchantment extends ArmorEnchantment {
    public FireTrailEnchantment(EnchantID enchantID) {
        super(enchantID);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean canAccept(Enchantment other){
        return !(other instanceof ChillingEnchantment);
    }

}
