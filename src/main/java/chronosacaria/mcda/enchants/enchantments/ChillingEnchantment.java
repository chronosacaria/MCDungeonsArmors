package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.Mcda;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.registry.Registry;

public class ChillingEnchantment extends Enchantment {
    public ChillingEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT, Mcda.ID("chilling"), this);
    }

    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level){
        if (attacker != null){
            if (attacker instanceof LivingEntity){
                ((LivingEntity)attacker).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20
                        , level * 2 - 1));
                ((LivingEntity)attacker).addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20
                        , level * 2 - 1));
            }
        }
    }
}
