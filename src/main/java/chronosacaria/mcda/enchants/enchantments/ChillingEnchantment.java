package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ChillingEnchantment extends Enchantment {
    public ChillingEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (user.getDamageTracker().getMostRecentDamage().getDamage() > 0.10) {
            if (attacker instanceof LivingEntity e) {
                e.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * level, level * 2 - 1));
                e.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20 * level, level * 2 - 1));
            }
        }
    }
    @Override
    protected boolean canAccept(Enchantment other){
        return !(other instanceof BurningEnchantment);
    }

    @Override
    public int getMinPower(int level) {
        return 1 + level * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 5;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
