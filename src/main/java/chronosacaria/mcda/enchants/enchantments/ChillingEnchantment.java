package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ChillingEnchantment extends ArmorEnchantment {
    public ChillingEnchantment(EnchantID enchantID) {
        super(enchantID);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (attacker instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) attacker;
            e.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, level * 2 - 1));
            e.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20, level * 2 - 1));
        }
    }
}
