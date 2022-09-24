package chronosacaria.mcda.config;

import chronosacaria.mcda.effects.ArmorEffectID;
import chronosacaria.mcda.enchants.EnchantID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.EnumMap;

@Config(name = "mcda_enchant_and_effect_registry")
public class McdaEnableEnchantAndEffectConfig implements ConfigData {

    public EnumMap<EnchantID, Boolean> enableEnchantment = new EnumMap<>(EnchantID.class);
    public EnumMap<EnchantID, Boolean> enableEnchantmentForVillagerTrade = new EnumMap<>(EnchantID.class);
    public EnumMap<EnchantID, Boolean> enableEnchantmentForRandomSelection = new EnumMap<>(EnchantID.class);
    public EnumMap<ArmorEffectID, Boolean> enableArmorEffect = new EnumMap<>(ArmorEffectID.class);
    @Comment("Controlled Teleportation for Unstable Robes, default = true")
    public boolean controlledTeleportation = true;

    public McdaEnableEnchantAndEffectConfig(){
        for (EnchantID enchantID : EnchantID.values()) {
            enableEnchantment.put(enchantID, true);
            enableEnchantmentForVillagerTrade.put(enchantID, true);
            enableEnchantmentForRandomSelection.put(enchantID, true);
        }

        for (ArmorEffectID armorEffectID : ArmorEffectID.values())
            enableArmorEffect.put(armorEffectID, true);
    }
}
