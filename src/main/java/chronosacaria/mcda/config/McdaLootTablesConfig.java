package chronosacaria.mcda.config;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.itemhelpers.ItemSettingsHelper;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.EnumMap;
import java.util.LinkedHashMap;

import static chronosacaria.mcda.items.ArmorSets.*;

@Config(name = "mcda_loot_tables")
public class McdaLootTablesConfig implements ConfigData {
    @Comment("Choose whether to use tiered loot system. Default = false")
    public LinkedHashMap<ItemSettingsHelper, Boolean> enableTieredLootTables = new LinkedHashMap<>();

    @Comment("Default, non-tiered armour spawn rates.")
    public EnumMap<ArmorSets, Float> armorSpawnRates = new EnumMap<>(ArmorSets.class);

    @Comment("Chests for tiered spawning.")
    public LinkedHashMap<ItemSettingsHelper, String[]> commonLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, String[]> uncommonLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, String[]> rareLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, String[]> epicLootTables = new LinkedHashMap<>();

    @Comment("Tiered armour spawn rates.")
    public LinkedHashMap<ArmorSets, Float> armorLootTableSpawnRates = new LinkedHashMap<>();

    public McdaLootTablesConfig(){

        armorSpawnRates.put(GHOSTLY, 0.05f);
        armorSpawnRates.put(GHOST_KINDLER, 0.01f);
        armorSpawnRates.put(GRIM, 0.05f);
        armorSpawnRates.put(DARK, 0.1f);
        armorSpawnRates.put(ROYAL, 0.05f);
        armorSpawnRates.put(TITAN, 0.025f);
        armorSpawnRates.put(THIEF, 0.1f);
        armorSpawnRates.put(PLATE, 0.05f);
        armorSpawnRates.put(FULL_METAL, 0.01f);
        armorSpawnRates.put(SNOW, 0.03f);
        armorSpawnRates.put(WOLF, 0.05f);
        armorSpawnRates.put(FOX, 0.01f);
        armorSpawnRates.put(REINFORCED_MAIL, 0.05f);
        armorSpawnRates.put(STALWART_MAIL, 0.01f);
        armorSpawnRates.put(SCALE_MAIL, 0.05f);
        armorSpawnRates.put(MERCENARY, 0.05f);
        armorSpawnRates.put(SPELUNKER, 0.05f);
        armorSpawnRates.put(CAVE_CRAWLER, 0.01f);
        armorSpawnRates.put(HERO, 0.1f);
        armorSpawnRates.put(GILDED, 0.1f);
        armorSpawnRates.put(TELEPORTATION, 0.1f);
        armorSpawnRates.put(UNSTABLE, 0.01f);
        armorSpawnRates.put(MYSTERY, 0.05f);
        armorSpawnRates.put(GREEN_MYSTERY, 0.05f);
        armorSpawnRates.put(BLUE_MYSTERY, 0.05f);
        armorSpawnRates.put(PURPLE_MYSTERY, 0.05f);
        armorSpawnRates.put(RED_MYSTERY, 0.05f);
        armorSpawnRates.put(VANGUARD, 0.05f);


        enableTieredLootTables.put(ItemSettingsHelper.ENABLE_TIERED_LOOT_TABLES, false);

        commonLootTables.put(ItemSettingsHelper.COMMON_LOOT_TABLES, new String[] {
                "minecraft:chests/abandoned_mineshaft",
                "minecraft:chests/shipwreck_supply",
                "minecraft:chests/shipwreck_treasure",
                "minecraft:chests/desert_pyramid",
                "minecraft:chests/village/village_weaponsmith"});

        uncommonLootTables.put(ItemSettingsHelper.UNCOMMON_LOOT_TABLES, new String[] {
                "minecraft:chests/jungle_temple",
                "minecraft:chests/nether_bridge",
                "minecraft:chests/bastion_bridge",
                "minecraft:chests/bastion_other",
                "minecraft:chests/bastion_treasure",
                "minecraft:chests/ruined_portal"});

        rareLootTables.put(ItemSettingsHelper.RARE_LOOT_TABLES, new String[] {
                "minecraft:chests/underwater_ruin_small",
                "minecraft:chests/underwater_ruin_big",
                "minecraft:chests/simple_dungeon",
                "minecraft:chests/igloo_chest",
                "minecraft:chests/pillager_outpost"});

        epicLootTables.put(ItemSettingsHelper.EPIC_LOOT_TABLES, new String[] {
                "minecraft:chests/stronghold_corridor",
                "minecraft:chests/stronghold_crossing",
                "minecraft:chests/stronghold_library",
                "minecraft:chests/end_city_treasure"});

        for (ArmorSets armorSets : ArmorSets.values()){
            armorLootTableSpawnRates.put(armorSets, 0.10f);
        }
        armorLootTableSpawnRates.replace(SPLENDID, 0.05f);
        armorLootTableSpawnRates.replace(BEEHIVE, 0.05f);
        armorLootTableSpawnRates.replace(HERO, 0.01f);
        armorLootTableSpawnRates.replace(RUGGED_CLIMBING_GEAR, 0.05f);
        armorLootTableSpawnRates.replace(TITAN, 0.01f);
        armorLootTableSpawnRates.replace(ROYAL, 0.05f);
        armorLootTableSpawnRates.replace(OPULENT, 0.05f);
        armorLootTableSpawnRates.replace(GILDED, 0.01f);
        armorLootTableSpawnRates.replace(TROUBADOUR, 0.00f);
        armorLootTableSpawnRates.replace(EMBER, 0.05f);
        armorLootTableSpawnRates.replace(VERDANT, 0.05f);
        armorLootTableSpawnRates.replace(GHOST_KINDLER, 0.05f);
        armorLootTableSpawnRates.replace(WITHER, 0.05f);
        armorLootTableSpawnRates.replace(GOURDIAN, 0.05f);
        armorLootTableSpawnRates.replace(CURIOUS, 0.05f);
        armorLootTableSpawnRates.replace(ARCHER, 0.05f);
        armorLootTableSpawnRates.replace(RENEGADE, 0.05f);
        armorLootTableSpawnRates.replace(HUNGRY_HORROR, 0.01f);
        armorLootTableSpawnRates.replace(RED_MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(GREEN_MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(BLUE_MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(PURPLE_MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(SHADOW_WALKER, 0.05f);
        armorLootTableSpawnRates.replace(FROST_BITE, 0.05f);
        armorLootTableSpawnRates.replace(GOLDEN_PIGLIN, 0.05f);
        armorLootTableSpawnRates.replace(FULL_METAL, 0.05f);
        armorLootTableSpawnRates.replace(CAULDRON, 0.05f);
        armorLootTableSpawnRates.replace(STALWART_MAIL, 0.05f);
        armorLootTableSpawnRates.replace(HIGHLAND, 0.05f);
        armorLootTableSpawnRates.replace(STURDY_SHULKER, 0.05f);
        armorLootTableSpawnRates.replace(FROST, 0.05f);
        armorLootTableSpawnRates.replace(SOULDANCER, 0.05f);
        armorLootTableSpawnRates.replace(CAVE_CRAWLER, 0.05f);
        armorLootTableSpawnRates.replace(GLOW_SQUID, 0.05f);
        armorLootTableSpawnRates.replace(UNSTABLE, 0.05f);
        armorLootTableSpawnRates.replace(SPIDER, 0.05f);
        armorLootTableSpawnRates.replace(NIMBLE_TURTLE, 0.05f);
        armorLootTableSpawnRates.replace(BLACK_WOLF, 0.05f);
        armorLootTableSpawnRates.replace(FOX, 0.01f);
        armorLootTableSpawnRates.replace(ARCTIC_FOX, 0.01f);
    }
}
