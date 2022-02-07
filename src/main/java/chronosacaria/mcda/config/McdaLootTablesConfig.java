package chronosacaria.mcda.config;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.itemhelpers.ItemSettingsHelper;
import chronosacaria.mcda.items.itemhelpers.SpawnHelper;
import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;

import static chronosacaria.mcda.items.ArmorSets.*;

@Config(name = "mcda_loot_tables")
public class McdaLootTablesConfig implements ConfigData {
    @Comment("Choose whether to use tiered loot system. Default = false")
    public LinkedHashMap<ItemSettingsHelper, Boolean> enableTieredLootTables = new LinkedHashMap<>();

    @Comment("Default, non-tiered armour spawn rates.")
    public EnumMap<SpawnHelper, Float> armorSpawnRates = new EnumMap<>(SpawnHelper.class);

    @Comment("Chests for tiered spawning.")
    public LinkedHashMap<ItemSettingsHelper, ArrayList<String>> commonLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, ArrayList<String>> uncommonLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, ArrayList<String>> rareLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, ArrayList<String>> epicLootTables = new LinkedHashMap<>();

    @Comment("Tiered armour spawn rates.")
    public LinkedHashMap<ArmorSets, Float> armorLootTableSpawnRates = new LinkedHashMap<>();

    public McdaLootTablesConfig(){

        armorSpawnRates.put(SpawnHelper.GHOSTLY, 0.05f);
        armorSpawnRates.put(SpawnHelper.GHOST_KINDLER, 0.01f);
        armorSpawnRates.put(SpawnHelper.GRIM, 0.05f);
        armorSpawnRates.put(SpawnHelper.DARK, 0.1f);
        armorSpawnRates.put(SpawnHelper.ROYAL, 0.05f);
        armorSpawnRates.put(SpawnHelper.TITAN, 0.025f);
        armorSpawnRates.put(SpawnHelper.THIEF, 0.1f);
        armorSpawnRates.put(SpawnHelper.PLATE, 0.05f);
        armorSpawnRates.put(SpawnHelper.FULL_METAL, 0.01f);
        armorSpawnRates.put(SpawnHelper.SNOW, 0.03f);
        armorSpawnRates.put(SpawnHelper.WOLF, 0.05f);
        armorSpawnRates.put(SpawnHelper.FOX, 0.01f);
        armorSpawnRates.put(SpawnHelper.REINFORCED, 0.05f);
        armorSpawnRates.put(SpawnHelper.STALWART, 0.01f);
        armorSpawnRates.put(SpawnHelper.SCALE, 0.05f);
        armorSpawnRates.put(SpawnHelper.MERCENARY, 0.05f);
        armorSpawnRates.put(SpawnHelper.SPELUNKER, 0.05f);
        armorSpawnRates.put(SpawnHelper.CAVE_CRAWLER, 0.01f);
        armorSpawnRates.put(SpawnHelper.HERO, 0.1f);
        armorSpawnRates.put(SpawnHelper.GILDED, 0.1f);
        armorSpawnRates.put(SpawnHelper.TELEPORTATION, 0.1f);
        armorSpawnRates.put(SpawnHelper.UNSTABLE, 0.01f);
        armorSpawnRates.put(SpawnHelper.MYSTERY, 0.05f);
        armorSpawnRates.put(SpawnHelper.VANGUARD, 0.05f);

        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()){
            enableTieredLootTables.put(ItemSettingsHelper.ENABLE_TIERED_LOOT_TABLES, false);
        }

        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()){
            commonLootTables.put(ItemSettingsHelper.COMMON_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/abandoned_mineshaft",
                    "minecraft:chests/shipwreck_supply",
                    "minecraft:chests/shipwreck_treasure",
                    "minecraft:chests/desert_pyramid",
                    "minecraft:chests/village/village_weaponsmith"));
        }
        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()) {
            uncommonLootTables.put(ItemSettingsHelper.UNCOMMON_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/jungle_temple",
                    "minecraft:chests/nether_bridge",
                    "minecraft:chests/bastion_bridge",
                    "minecraft:chests/bastion_other",
                    "minecraft:chests/bastion_treasure",
                    "minecraft:chests/ruined_portal"));
        }
        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()) {
            rareLootTables.put(ItemSettingsHelper.RARE_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/underwater_ruin_small",
                    "minecraft:chests/underwater_ruin_big",
                    "minecraft:chests/simple_dungeon",
                    "minecraft:chests/igloo_chest",
                    "minecraft:chests/pillager_outpost"));
        }
        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()) {
            epicLootTables.put(ItemSettingsHelper.EPIC_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/stronghold_corridor",
                    "minecraft:chests/stronghold_crossing",
                    "minecraft:chests/stronghold_library",
                    "minecraft:chests/end_city_treasure"));
        }

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
