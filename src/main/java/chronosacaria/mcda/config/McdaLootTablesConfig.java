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

    @Comment("Chance to get an armor piece from chests. Default = 0.10f")
    public Float findArmorChance = 0.10f;

    @Comment("Higher Number makes Luck give you potential for extra MCDA loot. Default = 1.2f")
    public Float bonusRollsWithLuck = 1.2f;

    @Comment("Default, non-tiered armour spawn rates.")
    public EnumMap<ArmorSets, Integer> armorSpawnRates = new EnumMap<>(ArmorSets.class);

    @Comment("Chests for tiered spawning.")
    public LinkedHashMap<ItemSettingsHelper, String[]> commonLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, String[]> uncommonLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, String[]> rareLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, String[]> epicLootTables = new LinkedHashMap<>();

    @Comment("Tiered armour spawn rates.")
    public LinkedHashMap<ArmorSets, Integer> armorLootTableSpawnRates = new LinkedHashMap<>();

    public McdaLootTablesConfig(){

        armorSpawnRates.put(GHOSTLY, 5);
        armorSpawnRates.put(GHOST_KINDLER, 1);
        armorSpawnRates.put(GRIM, 5);
        armorSpawnRates.put(DARK, 10);
        armorSpawnRates.put(ROYAL, 5);
        armorSpawnRates.put(TITAN, 3);
        armorSpawnRates.put(THIEF, 10);
        armorSpawnRates.put(PLATE, 5);
        armorSpawnRates.put(FULL_METAL, 1);
        armorSpawnRates.put(SNOW, 3);
        armorSpawnRates.put(WOLF, 5);
        armorSpawnRates.put(FOX, 1);
        armorSpawnRates.put(REINFORCED_MAIL, 5);
        armorSpawnRates.put(STALWART_MAIL, 1);
        armorSpawnRates.put(SCALE_MAIL, 5);
        armorSpawnRates.put(MERCENARY, 5);
        armorSpawnRates.put(SPELUNKER, 5);
        armorSpawnRates.put(CAVE_CRAWLER, 1);
        armorSpawnRates.put(HERO, 10);
        armorSpawnRates.put(GILDED, 10);
        armorSpawnRates.put(TELEPORTATION, 10);
        armorSpawnRates.put(UNSTABLE, 1);
        armorSpawnRates.put(MYSTERY, 5);
        armorSpawnRates.put(GREEN_MYSTERY, 5);
        armorSpawnRates.put(BLUE_MYSTERY, 5);
        armorSpawnRates.put(PURPLE_MYSTERY, 5);
        armorSpawnRates.put(RED_MYSTERY, 5);
        armorSpawnRates.put(VANGUARD, 5);


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
            armorLootTableSpawnRates.put(armorSets, 10);
        }
        armorLootTableSpawnRates.replace(SPLENDID, 5);
        armorLootTableSpawnRates.replace(BEEHIVE, 5);
        armorLootTableSpawnRates.replace(HERO, 1);
        armorLootTableSpawnRates.replace(RUGGED_CLIMBING_GEAR, 5);
        armorLootTableSpawnRates.replace(TITAN, 1);
        armorLootTableSpawnRates.replace(ROYAL, 5);
        armorLootTableSpawnRates.replace(OPULENT, 5);
        armorLootTableSpawnRates.replace(GILDED, 1);
        armorLootTableSpawnRates.replace(TROUBADOUR, 0);
        armorLootTableSpawnRates.replace(EMBER, 5);
        armorLootTableSpawnRates.replace(VERDANT, 5);
        armorLootTableSpawnRates.replace(GHOST_KINDLER, 5);
        armorLootTableSpawnRates.replace(WITHER, 5);
        armorLootTableSpawnRates.replace(GOURDIAN, 5);
        armorLootTableSpawnRates.replace(CURIOUS, 5);
        armorLootTableSpawnRates.replace(ARCHER, 5);
        armorLootTableSpawnRates.replace(RENEGADE, 5);
        armorLootTableSpawnRates.replace(HUNGRY_HORROR, 1);
        armorLootTableSpawnRates.replace(RED_MYSTERY, 0);
        armorLootTableSpawnRates.replace(MYSTERY, 0);
        armorLootTableSpawnRates.replace(GREEN_MYSTERY, 0);
        armorLootTableSpawnRates.replace(BLUE_MYSTERY, 0);
        armorLootTableSpawnRates.replace(PURPLE_MYSTERY, 0);
        armorLootTableSpawnRates.replace(SHADOW_WALKER, 5);
        armorLootTableSpawnRates.replace(FROST_BITE, 5);
        armorLootTableSpawnRates.replace(GOLDEN_PIGLIN, 5);
        armorLootTableSpawnRates.replace(FULL_METAL, 5);
        armorLootTableSpawnRates.replace(CAULDRON, 5);
        armorLootTableSpawnRates.replace(STALWART_MAIL, 5);
        armorLootTableSpawnRates.replace(HIGHLAND, 5);
        armorLootTableSpawnRates.replace(STURDY_SHULKER, 5);
        armorLootTableSpawnRates.replace(FROST, 5);
        armorLootTableSpawnRates.replace(SOULDANCER, 5);
        armorLootTableSpawnRates.replace(CAVE_CRAWLER, 5);
        armorLootTableSpawnRates.replace(GLOW_SQUID, 5);
        armorLootTableSpawnRates.replace(UNSTABLE, 5);
        armorLootTableSpawnRates.replace(SPIDER, 5);
        armorLootTableSpawnRates.replace(NIMBLE_TURTLE, 5);
        armorLootTableSpawnRates.replace(BLACK_WOLF, 5);
        armorLootTableSpawnRates.replace(FOX, 1);
        armorLootTableSpawnRates.replace(ARCTIC_FOX, 1);
    }
}
