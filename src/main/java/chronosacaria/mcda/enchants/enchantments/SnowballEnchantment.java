package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.api.ProjectileEffectHelper;
import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class SnowballEnchantment extends ArmorEnchantment {
    public SnowballEnchantment(EnchantID enchantID) {
        super(enchantID);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (attacker != null) {
            ProjectileEffectHelper.fireSnowballAtNearbyEnemy(user, 10);
        }
    }
}
