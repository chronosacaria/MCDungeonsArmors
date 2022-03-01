package chronosacaria.mcda.config;

import chronosacaria.mcda.Mcda;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = Mcda.MOD_ID)
public class McdaConfig extends PartitioningSerializer.GlobalData{

    @ConfigEntry.Category("mcda_enchant_and_effect_registry")
    public McdaEnableEnchantAndEffectConfig mcdaEnableEnchantAndEffectConfig = new McdaEnableEnchantAndEffectConfig();

    @ConfigEntry.Category("mcda_loot_tables")
    public McdaLootTablesConfig mcdaLootTablesConfig = new McdaLootTablesConfig();

    @ConfigEntry.Category("mcda_armor_stats")
    public McdaArmorStatsConfig mcdaArmorStatsConfig = new McdaArmorStatsConfig();

    @ConfigEntry.Category("mcda_item_drops")
    public McdaItemDropsConfig mcdaItemDropsConfig = new McdaItemDropsConfig();

    public static void init(){
        AutoConfig.register(McdaConfig.class,
                PartitioningSerializer.wrap(JanksonConfigSerializer::new));
    }
}
