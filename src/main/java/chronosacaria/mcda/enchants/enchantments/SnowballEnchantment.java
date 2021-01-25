package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.ProjectileEffectHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.registry.Registry;

public class SnowballEnchantment extends Enchantment{
    public SnowballEnchantment(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT, Mcda.ID("snowball"), this);
    }

    public int getMaxLevel(){
        return 1;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level){
        if (attacker != null){
            ProjectileEffectHelper.fireSnowballAtNearbyEnemy(user, 10);
        }
    }
}
