package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class PotionBarrierEnchantment extends Enchantment {

    public PotionBarrierEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(EnchantID.POTION_BARRIER)
                && Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantmentForRandomSelection.get(EnchantID.POTION_BARRIER);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(EnchantID.POTION_BARRIER)
                && Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantmentForVillagerTrade.get(EnchantID.POTION_BARRIER);
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
