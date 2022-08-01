package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class LuckyExplorerEnchantment extends Enchantment {

    public LuckyExplorerEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(EnchantID.LUCKY_EXPLORER)
                && Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantmentForRandomSelection.get(EnchantID.LUCKY_EXPLORER);
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(EnchantID.LUCKY_EXPLORER)
                && Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantmentForVillagerTrade.get(EnchantID.LUCKY_EXPLORER);
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
