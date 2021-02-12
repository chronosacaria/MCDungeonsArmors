package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class BurningEnchantment extends ArmorEnchantment {

    public BurningEnchantment(EnchantID enchantID) {
        super(enchantID);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (attacker != null) {
            if (!attacker.isOnFire()) {
                attacker.setOnFireFor(3 * level);
            }
        }
    }
}
