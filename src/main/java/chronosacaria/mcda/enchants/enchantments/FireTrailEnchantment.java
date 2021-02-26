package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;

public class FireTrailEnchantment extends ArmorEnchantment {
    public FireTrailEnchantment(EnchantID enchantID) {
        super(enchantID);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

}
