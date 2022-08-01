package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;

public class RecyclerEnchantment extends Enchantment {
    public RecyclerEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        DamageSource damageSource = user.getRecentDamageSource();

        if (damageSource != null && damageSource.isProjectile()) {
            if (CleanlinessHelper.percentToOccur(10 * level)) {
                CleanlinessHelper.mcda$dropItem(user, Items.ARROW);
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(EnchantID.RECYCLER)
                && Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantmentForRandomSelection.get(EnchantID.RECYCLER);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(EnchantID.RECYCLER)
                && Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantmentForVillagerTrade.get(EnchantID.RECYCLER);
    }

    @Override
    public int getMinPower(int level) {
        return 1 + level * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 5;
    }

}
