package chronosacaria.mcda.registry;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.ItemID;
import chronosacaria.mcda.items.itemhelpers.DropHelper;
import chronosacaria.mcda.items.itemhelpers.ItemSettingsHelper;
import chronosacaria.mcda.items.itemhelpers.SpawnHelper;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

import java.util.*;

import static chronosacaria.mcda.Mcda.CONFIG;

public class LootRegistry {

    private static final LootCondition.Builder WITHOUT_SILK_TOUCH;

    public static final Collection<Identifier> BASTION_LOOT_TABLES = Set.of(LootTables.BASTION_BRIDGE_CHEST,
            LootTables.BASTION_HOGLIN_STABLE_CHEST, LootTables.BASTION_OTHER_CHEST, LootTables.BASTION_TREASURE_CHEST);

    public static final Collection<Identifier> PIGLIN_TRADING_LOOT_TABLES = Collections.singletonList(
            LootTables.PIGLIN_BARTERING_GAMEPLAY);

    public static final Collection<Identifier> NETHER_FORTRESS_LOOT_TABLES = Collections.singletonList(
            LootTables.NETHER_BRIDGE_CHEST);

    public static final Collection<Identifier> PILLAGER_TOWER_LOOT_TABLES = Set.of(LootTables.PILLAGER_OUTPOST_CHEST,
            LootTables.WOODLAND_MANSION_CHEST);

    public static final Collection<Identifier> VILLAGE_SMITH_LOOT_TABLES = List.of(LootTables.VILLAGE_TOOLSMITH_CHEST,
            LootTables.VILLAGE_WEAPONSMITH_CHEST);

    public static final Collection<Identifier> SUNKEN_SHIP_LOOT_TABLES = List.of(LootTables.SHIPWRECK_TREASURE_CHEST,
            LootTables.SHIPWRECK_SUPPLY_CHEST);

    public static final Collection<Identifier> MINESHAFT_LOOT_TABLES = Collections.singletonList(
            LootTables.ABANDONED_MINESHAFT_CHEST);

    public static final Collection<Identifier> HERO_OF_THE_VILLAGE_LOOT_TABLES = Collections.singletonList(
            LootTables.HERO_OF_THE_VILLAGE_ARMORER_GIFT_GAMEPLAY);

    public static final Collection<Identifier> STRONGHOLD_LOOT_TABLES = List.of(LootTables.STRONGHOLD_CORRIDOR_CHEST,
            LootTables.STRONGHOLD_CROSSING_CHEST, LootTables.STRONGHOLD_LIBRARY_CHEST);

    public static final ArrayList<String> COMMON_LOOT_TABLES =
            new ArrayList<>(CONFIG.mcdaLootTablesConfig.commonLootTables.get(ItemSettingsHelper.COMMON_LOOT_TABLES));
    public static final ArrayList<String> UNCOMMON_LOOT_TABLES =
            new ArrayList<>(CONFIG.mcdaLootTablesConfig.uncommonLootTables.get(ItemSettingsHelper.UNCOMMON_LOOT_TABLES));
    public static final ArrayList<String> RARE_LOOT_TABLES =
            new ArrayList<>(CONFIG.mcdaLootTablesConfig.rareLootTables.get(ItemSettingsHelper.RARE_LOOT_TABLES));
    public static final ArrayList<String> EPIC_LOOT_TABLES =
            new ArrayList<>(CONFIG.mcdaLootTablesConfig.epicLootTables.get(ItemSettingsHelper.EPIC_LOOT_TABLES));

    public static void init() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

            LootPool.Builder lootPoolBuilder;

            if (EntityType.PHANTOM.getLootTableId().equals(id) && source.isBuiltin()) {
                lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.PHANTOM_BONES),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.PHANTOM_BONES).intValue(),
                        0.35f);
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.PHANTOM_SKIN),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.PHANTOM_SKIN).intValue(),
                        0.20f);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.OCELOT.getLootTableId().equals(id) && source.isBuiltin()) {
                lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.OCELOT_PELT),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.OCELOT_PELT).intValue(),
                        0.35f);
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.OCELOT_PELT_BLACK),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.BLACK_OCELOT_PELT).intValue(),
                        0.20f);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.SKELETON.getLootTableId().equals(id) && source.isBuiltin()) {
                lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, Items.SKELETON_SKULL,
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.SKELETON_SKULL).intValue(),
                        0.20f);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.WOLF.getLootTableId().equals(id) && source.isBuiltin()) {
                lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.WOLF_PELT),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.WOLF_PELT).intValue(),
                        0.25f);
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.WOLF_PELT_BLACK),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.BLACK_WOLF_PELT).intValue(),
                        0.08f);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.FOX.getLootTableId().equals(id) && source.isBuiltin()) {
                lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.FOX_PELT),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.FOX_PELT).intValue(),
                        0.25f);
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.FOX_PELT_ARCTIC),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.ARCTIC_FOX_PELT).intValue(),
                        0.10f);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.EVOKER.getLootTableId().equals(id) && source.isBuiltin()) {
                lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).get(EquipmentSlot.HEAD),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.EVOCATION_ROBE).intValue(),
                        0.20F);
                addItemDrop(lootPoolBuilder, ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).get(EquipmentSlot.CHEST),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.EVOCATION_ROBE).intValue(),
                        0.20f);
                addItemDrop(lootPoolBuilder, ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).get(EquipmentSlot.LEGS),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.EVOCATION_ROBE).intValue(),
                        0.20f);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.GOAT.getLootTableId().equals(id) && source.isBuiltin()) {
                lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.GOAT_PELT),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.GOAT_PELT).intValue(),
                        0.50F);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (Blocks.BLUE_ICE.getLootTableId().equals(id) && source.isBuiltin()) {
                lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.FROST_CRYSTAL),
                        CONFIG.mcdaItemDropsConfig.maxDropAmounts.get(DropHelper.FROST_CRYSTAL).intValue(),
                        0.20F);
                lootPoolBuilder.conditionally(WITHOUT_SILK_TOUCH);
                tableBuilder.pool(lootPoolBuilder.build());
            }

            if (CONFIG.mcdaLootTablesConfig.enableTieredLootTables.get(ItemSettingsHelper.ENABLE_TIERED_LOOT_TABLES)) {
                for (String commonLootTable : COMMON_LOOT_TABLES) {
                    if (commonLootTable.equals(id.toString())) {
                        lootPoolBuilder = LootPool.builder();
                        addArmorSet(lootPoolBuilder, ArmorSets.BATTLE, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.BATTLE));
                        addArmorSet(lootPoolBuilder, ArmorSets.BEENEST, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.BEENEST));
                        addArmorSet(lootPoolBuilder, ArmorSets.CLIMBING_GEAR, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.CLIMBING_GEAR));
                        addArmorSet(lootPoolBuilder, ArmorSets.EVOCATION, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.EVOCATION));
                        addArmorSet(lootPoolBuilder, ArmorSets.GHOSTLY, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GHOSTLY));
                        addArmorSet(lootPoolBuilder, ArmorSets.HUNTER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.HUNTER));
                        addArmorSet(lootPoolBuilder, ArmorSets.SCALE_MAIL, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SCALE_MAIL));
                        addArmorSet(lootPoolBuilder, ArmorSets.SNOW, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SNOW));
                        addArmorSet(lootPoolBuilder, ArmorSets.SOUL_ROBE, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SOUL_ROBE));
                        addArmorSet(lootPoolBuilder, ArmorSets.SPELUNKER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SPELUNKER));
                        addArmorSet(lootPoolBuilder, ArmorSets.SQUID, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SQUID));
                        addArmorSet(lootPoolBuilder, ArmorSets.VANGUARD, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.VANGUARD));
                        addArmorSet(lootPoolBuilder, ArmorSets.WOLF, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.WOLF));
                        tableBuilder.pool(lootPoolBuilder.build());
                    }
                }
                for (String uncommonLootTable : UNCOMMON_LOOT_TABLES) {
                    if (uncommonLootTable.equals(id.toString())) {
                        lootPoolBuilder = LootPool.builder();
                        addArmorSet(lootPoolBuilder, ArmorSets.CHAMPION, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.CHAMPION));
                        addArmorSet(lootPoolBuilder, ArmorSets.EMERALD, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.EMERALD));
                        addArmorSet(lootPoolBuilder, ArmorSets.ENTERTAINER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.ENTERTAINER));
                        addArmorSet(lootPoolBuilder, ArmorSets.GOAT, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GOAT));
                        addArmorSet(lootPoolBuilder, ArmorSets.GRIM, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GRIM));
                        addArmorSet(lootPoolBuilder, ArmorSets.GUARDS, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GUARDS));
                        addArmorSet(lootPoolBuilder, ArmorSets.MERCENARY, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.MERCENARY));
                        addArmorSet(lootPoolBuilder, ArmorSets.OCELOT, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.OCELOT));
                        addArmorSet(lootPoolBuilder, ArmorSets.PHANTOM, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.PHANTOM));
                        addArmorSet(lootPoolBuilder, ArmorSets.PIGLIN, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.PIGLIN));
                        addArmorSet(lootPoolBuilder, ArmorSets.PLATE, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.PLATE));
                        addArmorSet(lootPoolBuilder, ArmorSets.REINFORCED_MAIL, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.REINFORCED_MAIL));
                        addArmorSet(lootPoolBuilder, ArmorSets.SWEET_TOOTH, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SWEET_TOOTH));
                        addArmorSet(lootPoolBuilder, ArmorSets.TELEPORTATION, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.TELEPORTATION));
                        addArmorSet(lootPoolBuilder, ArmorSets.THIEF, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.THIEF));
                        addArmorSet(lootPoolBuilder, ArmorSets.TURTLE, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.TURTLE));
                        tableBuilder.pool(lootPoolBuilder.build());
                    }
                }
                for (String rareLootTable : RARE_LOOT_TABLES) {
                    if (rareLootTable.equals(id.toString())) {
                        lootPoolBuilder = LootPool.builder();
                        addArmorSet(lootPoolBuilder, ArmorSets.SPLENDID, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SPLENDID));
                        addArmorSet(lootPoolBuilder, ArmorSets.BEEHIVE, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.BEEHIVE));
                        addArmorSet(lootPoolBuilder, ArmorSets.RUGGED_CLIMBING_GEAR, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.RUGGED_CLIMBING_GEAR));
                        addArmorSet(lootPoolBuilder, ArmorSets.DARK, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.DARK));
                        addArmorSet(lootPoolBuilder, ArmorSets.TROUBADOUR, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.TROUBADOUR));
                        addArmorSet(lootPoolBuilder, ArmorSets.EMBER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.EMBER));
                        addArmorSet(lootPoolBuilder, ArmorSets.VERDANT, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.VERDANT));
                        addArmorSet(lootPoolBuilder, ArmorSets.GHOST_KINDLER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GHOST_KINDLER));
                        addArmorSet(lootPoolBuilder, ArmorSets.WITHER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.WITHER));
                        addArmorSet(lootPoolBuilder, ArmorSets.ARCHER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.ARCHER));
                        addArmorSet(lootPoolBuilder, ArmorSets.LIVING_VINES, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.LIVING_VINES));
                        addArmorSet(lootPoolBuilder, ArmorSets.SPROUT, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SPROUT));
                        addArmorSet(lootPoolBuilder, ArmorSets.RENEGADE, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.RENEGADE));
                        addArmorSet(lootPoolBuilder, ArmorSets.GOLDEN_PIGLIN, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GOLDEN_PIGLIN));
                        addArmorSet(lootPoolBuilder, ArmorSets.STALWART_MAIL, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.STALWART_MAIL));
                        addArmorSet(lootPoolBuilder, ArmorSets.HIGHLAND, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.HIGHLAND));
                        addArmorSet(lootPoolBuilder, ArmorSets.FROST, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.FROST));
                        addArmorSet(lootPoolBuilder, ArmorSets.SOULDANCER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SOULDANCER));
                        addArmorSet(lootPoolBuilder, ArmorSets.CAVE_CRAWLER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.CAVE_CRAWLER));
                        addArmorSet(lootPoolBuilder, ArmorSets.GLOW_SQUID, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GLOW_SQUID));
                        addArmorSet(lootPoolBuilder, ArmorSets.SPIDER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SPIDER));
                        addArmorSet(lootPoolBuilder, ArmorSets.BLACK_WOLF, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.BLACK_WOLF));
                        addArmorSet(lootPoolBuilder, ArmorSets.FOX, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.FOX));
                        tableBuilder.pool(lootPoolBuilder.build());
                    }
                }
                for (String epicLootTable : EPIC_LOOT_TABLES) {
                    if (epicLootTable.equals(id.toString())) {
                        lootPoolBuilder = LootPool.builder();
                        addArmorSet(lootPoolBuilder, ArmorSets.HERO, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.HERO));
                        addArmorSet(lootPoolBuilder, ArmorSets.TITAN, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.TITAN));
                        addArmorSet(lootPoolBuilder, ArmorSets.ROYAL, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.ROYAL));
                        addArmorSet(lootPoolBuilder, ArmorSets.GILDED, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GILDED));
                        addArmorSet(lootPoolBuilder, ArmorSets.OPULENT, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.OPULENT));
                        addArmorSet(lootPoolBuilder, ArmorSets.GOURDIAN, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GOURDIAN));
                        addArmorSet(lootPoolBuilder, ArmorSets.CURIOUS, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.CURIOUS));
                        addArmorSet(lootPoolBuilder, ArmorSets.HUNGRY_HORROR, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.HUNGRY_HORROR));
                        addArmorSet(lootPoolBuilder, ArmorSets.RED_MYSTERY, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.RED_MYSTERY));
                        addArmorSet(lootPoolBuilder, ArmorSets.MYSTERY, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.MYSTERY));
                        addArmorSet(lootPoolBuilder, ArmorSets.BLUE_MYSTERY, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.BLUE_MYSTERY));
                        addArmorSet(lootPoolBuilder, ArmorSets.GREEN_MYSTERY, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.GREEN_MYSTERY));
                        addArmorSet(lootPoolBuilder, ArmorSets.PURPLE_MYSTERY, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.PURPLE_MYSTERY));
                        addArmorSet(lootPoolBuilder, ArmorSets.SHADOW_WALKER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SHADOW_WALKER));
                        addArmorSet(lootPoolBuilder, ArmorSets.FROST_BITE, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.FROST_BITE));
                        addArmorSet(lootPoolBuilder, ArmorSets.FULL_METAL, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.FULL_METAL));
                        addArmorSet(lootPoolBuilder, ArmorSets.CAULDRON, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.CAULDRON));
                        addArmorSet(lootPoolBuilder, ArmorSets.SHULKER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.SHULKER));
                        addArmorSet(lootPoolBuilder, ArmorSets.STURDY_SHULKER, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.STURDY_SHULKER));
                        addArmorSet(lootPoolBuilder, ArmorSets.UNSTABLE, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.UNSTABLE));
                        addArmorSet(lootPoolBuilder, ArmorSets.NIMBLE_TURTLE, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.NIMBLE_TURTLE));
                        addArmorSet(lootPoolBuilder, ArmorSets.ARCTIC_FOX, CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(ArmorSets.ARCTIC_FOX));
                        tableBuilder.pool(lootPoolBuilder.build());
                    }
                }
            } else {
                if (BASTION_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder = LootPool.builder();
                    addArmorSet(lootPoolBuilder, ArmorSets.GHOSTLY, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.GHOSTLY));
                    addArmorSet(lootPoolBuilder, ArmorSets.GHOST_KINDLER, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.GHOST_KINDLER));
                    addMysteryArmorSets(lootPoolBuilder, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.MYSTERY));
                    tableBuilder.pool(lootPoolBuilder.build());
                }
                else if (PIGLIN_TRADING_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder = LootPool.builder();
                    addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_WHITE), 1, 0.01F);
                    addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_RED), 1, 0.01F);
                    addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_GREEN), 1, 0.01F);
                    addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_BLUE), 1, 0.01F);
                    addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_PURPLE), 1, 0.01F);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
                else if (NETHER_FORTRESS_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder = LootPool.builder();
                    addArmorSet(lootPoolBuilder, ArmorSets.GRIM, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.GRIM));
                    addArmorSet(lootPoolBuilder, ArmorSets.VANGUARD, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.VANGUARD));
                    addMysteryArmorSets(lootPoolBuilder, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.MYSTERY));
                    tableBuilder.pool(lootPoolBuilder.build());
                }
                else if (PILLAGER_TOWER_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder = LootPool.builder();
                    addArmorSet(lootPoolBuilder, ArmorSets.DARK, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.DARK));
                    addArmorSet(lootPoolBuilder, ArmorSets.THIEF, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.THIEF));
                    addArmorSet(lootPoolBuilder, ArmorSets.ROYAL, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.ROYAL));
                    addArmorSet(lootPoolBuilder, ArmorSets.TITAN, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.TITAN));
                    tableBuilder.pool(lootPoolBuilder.build());
                }
                else if (VILLAGE_SMITH_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder = LootPool.builder();
                    addArmorSet(lootPoolBuilder, ArmorSets.PLATE, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.PLATE));
                    addArmorSet(lootPoolBuilder, ArmorSets.FULL_METAL, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.FULL_METAL));
                    addArmorSet(lootPoolBuilder, ArmorSets.SNOW, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.SNOW));
                    addArmorSet(lootPoolBuilder, ArmorSets.WOLF, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.WOLF));
                    addArmorSet(lootPoolBuilder, ArmorSets.FOX, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.FOX));
                    addArmorSet(lootPoolBuilder, ArmorSets.REINFORCED_MAIL, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.REINFORCED));
                    addArmorSet(lootPoolBuilder, ArmorSets.STALWART_MAIL, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.STALWART));
                    tableBuilder.pool(lootPoolBuilder.build());
                }
                else if (SUNKEN_SHIP_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder = LootPool.builder();
                    addArmorSet(lootPoolBuilder, ArmorSets.SCALE_MAIL, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.SCALE));
                    addArmorSet(lootPoolBuilder, ArmorSets.MERCENARY, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.MERCENARY));
                    addMysteryArmorSets(lootPoolBuilder, 0.05F);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
                else if (MINESHAFT_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder = LootPool.builder();
                    addArmorSet(lootPoolBuilder, ArmorSets.SPELUNKER, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.SPELUNKER));
                    addArmorSet(lootPoolBuilder, ArmorSets.CAVE_CRAWLER, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.CAVE_CRAWLER));
                    addMysteryArmorSets(lootPoolBuilder, 0.05F);
                    tableBuilder.pool(lootPoolBuilder.build());
                }
                else if (HERO_OF_THE_VILLAGE_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder = LootPool.builder();
                    addArmorSet(lootPoolBuilder, ArmorSets.HERO, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.HERO));
                    addArmorSet(lootPoolBuilder, ArmorSets.GILDED, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.GILDED));
                    tableBuilder.pool(lootPoolBuilder.build());
                }
                else if (STRONGHOLD_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder = LootPool.builder();
                    addArmorSet(lootPoolBuilder, ArmorSets.TELEPORTATION, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.TELEPORTATION));
                    addArmorSet(lootPoolBuilder, ArmorSets.UNSTABLE, CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(SpawnHelper.UNSTABLE));
                    tableBuilder.pool(lootPoolBuilder.build());
                }
            }
        }));
    }

    public static void addMysteryArmorSets(LootPool.Builder poolBuilder, float p) {
        addArmorSet(poolBuilder, ArmorSets.MYSTERY, p);
        addArmorSet(poolBuilder, ArmorSets.BLUE_MYSTERY, p);
        addArmorSet(poolBuilder, ArmorSets.GREEN_MYSTERY, p);
        addArmorSet(poolBuilder, ArmorSets.PURPLE_MYSTERY, p);
        addArmorSet(poolBuilder, ArmorSets.RED_MYSTERY, p);
    }

    public static void addArmorSet(LootPool.Builder poolBuilder, ArmorSets set, float p) {
        ArmorsRegistry.armorItems.get(set).values()
                .forEach((item -> {
                    poolBuilder.rolls(BinomialLootNumberProvider.create(1, p));
                    poolBuilder.with(ItemEntry.builder(item));
                }));
    }



    public static void addItemDrop(LootPool.Builder poolBuilder, Item item, int n, float p){
        poolBuilder.rolls(BinomialLootNumberProvider.create(n, p));
        poolBuilder.with(ItemEntry.builder(item));
    }

    static {
        WITHOUT_SILK_TOUCH = MatchToolLootCondition.builder(
                ItemPredicate.Builder.create()
                        .enchantment(
                                new EnchantmentPredicate(
                                        Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))))
                .invert();
    }
}
