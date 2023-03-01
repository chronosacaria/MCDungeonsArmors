package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.itemhelpers.DropHelper;
import chronosacaria.mcda.items.itemhelpers.ItemSettingsHelper;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static chronosacaria.mcda.Mcda.CONFIG;

public class LootRegistry {

    private static final LootCondition.Builder WITHOUT_SILK_TOUCH;

    public static final Collection<Identifier> BASTION_LOOT_TABLES = List.of(LootTables.BASTION_BRIDGE_CHEST,
            LootTables.BASTION_HOGLIN_STABLE_CHEST, LootTables.BASTION_OTHER_CHEST, LootTables.BASTION_TREASURE_CHEST);

    public static final Collection<Identifier> PIGLIN_TRADING_LOOT_TABLES = Collections.singletonList(
            LootTables.PIGLIN_BARTERING_GAMEPLAY);

    public static final Collection<Identifier> NETHER_FORTRESS_LOOT_TABLES = Collections.singletonList(
            LootTables.NETHER_BRIDGE_CHEST);

    public static final Collection<Identifier> PILLAGER_TOWER_LOOT_TABLES = List.of(LootTables.PILLAGER_OUTPOST_CHEST,
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
            new ArrayList<>(List.of(CONFIG.mcdaLootTablesConfig.commonLootTables.get(ItemSettingsHelper.COMMON_LOOT_TABLES)));
    public static final ArrayList<String> UNCOMMON_LOOT_TABLES =
            new ArrayList<>(List.of(CONFIG.mcdaLootTablesConfig.uncommonLootTables.get(ItemSettingsHelper.UNCOMMON_LOOT_TABLES)));
    public static final ArrayList<String> RARE_LOOT_TABLES =
            new ArrayList<>(List.of(CONFIG.mcdaLootTablesConfig.rareLootTables.get(ItemSettingsHelper.RARE_LOOT_TABLES)));
    public static final ArrayList<String> EPIC_LOOT_TABLES =
            new ArrayList<>(List.of(CONFIG.mcdaLootTablesConfig.epicLootTables.get(ItemSettingsHelper.EPIC_LOOT_TABLES)));

    public static void init() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (EntityType.PHANTOM.getLootTableId().equals(id) && source.isBuiltin()) {
                addItemLootPool(tableBuilder, ItemsRegistry.PHANTOM_BONES, DropHelper.PHANTOM_BONES, 0.5f);
                addItemLootPool(tableBuilder, ItemsRegistry.PHANTOM_SKIN, DropHelper.PHANTOM_SKIN);
            }
            if (EntityType.OCELOT.getLootTableId().equals(id) && source.isBuiltin()) {
                addItemLootPool(tableBuilder, ItemsRegistry.OCELOT_PELT, DropHelper.OCELOT_PELT, 0.5f);
                addItemLootPool(tableBuilder, ItemsRegistry.OCELOT_PELT_BLACK, DropHelper.OCELOT_PELT_BLACK);
            }
            if (EntityType.SKELETON.getLootTableId().equals(id) && source.isBuiltin()) {
                addItemLootPool(tableBuilder, Items.SKELETON_SKULL, DropHelper.SKELETON_SKULL);
            }
            if (EntityType.WOLF.getLootTableId().equals(id) && source.isBuiltin()) {
                addItemLootPool(tableBuilder, ItemsRegistry.WOLF_PELT, DropHelper.WOLF_PELT, 0.5f);
                addItemLootPool(tableBuilder, ItemsRegistry.WOLF_PELT_BLACK, DropHelper.WOLF_PELT_BLACK);
            }
            if (EntityType.FOX.getLootTableId().equals(id) && source.isBuiltin()) {
                addItemLootPool(tableBuilder, ItemsRegistry.FOX_PELT, DropHelper.FOX_PELT, 0.5f);
                addItemLootPool(tableBuilder, ItemsRegistry.FOX_PELT_ARCTIC, DropHelper.FOX_PELT_ARCTIC);
            }
            if (EntityType.EVOKER.getLootTableId().equals(id) && source.isBuiltin()) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(CONFIG.mcdaItemDropsConfig.numberOfRollsOnLootTable.get(DropHelper.EVOCATION_ROBE)));
                lootPoolBuilder.conditionally(RandomChanceWithLootingLootCondition.builder(
                        CONFIG.mcdaItemDropsConfig.dropBaseChance.get(DropHelper.EVOCATION_ROBE),
                        CONFIG.mcdaItemDropsConfig.dropChancePerLootingLevel.get(DropHelper.EVOCATION_ROBE)));
                if (Mcda.CONFIG.mcdaEnableArmorsConfig.ARMORS_SETS_ENABLED.get(ArmorSets.EVOCATION)) {
                    ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).values()
                            .forEach((item -> lootPoolBuilder.with(ItemEntry.builder(item))));
                }
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.GOAT.getLootTableId().equals(id) && source.isBuiltin()) {
                addItemLootPool(tableBuilder, ItemsRegistry.GOAT_PELT, DropHelper.GOAT_PELT, 0.5f);
            }
            if (Blocks.BLUE_ICE.getLootTableId().equals(id) && source.isBuiltin()) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(CONFIG.mcdaItemDropsConfig.numberOfRollsOnLootTable.get(DropHelper.FROST_CRYSTAL)));
                lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(CONFIG.mcdaItemDropsConfig.dropBaseChance.get(DropHelper.FROST_CRYSTAL))).conditionally(WITHOUT_SILK_TOUCH);
                lootPoolBuilder.with(ItemEntry.builder(ItemsRegistry.FROST_CRYSTAL));
                tableBuilder.pool(lootPoolBuilder.build());
            }

            LootPool.Builder lootPoolBuilder = LootPool.builder();
            lootPoolBuilder.rolls(ConstantLootNumberProvider.create(1));
            lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(CONFIG.mcdaLootTablesConfig.findArmorChance));
            lootPoolBuilder.bonusRolls(ConstantLootNumberProvider.create(CONFIG.mcdaLootTablesConfig.bonusRollsWithLuck));
            if (CONFIG.mcdaLootTablesConfig.enableTieredLootTables.get(ItemSettingsHelper.ENABLE_TIERED_LOOT_TABLES)) {
                if (COMMON_LOOT_TABLES.contains(id.toString())) {
                    for (ArmorSets armorSets : List.of(ArmorSets.BATTLE, ArmorSets.BEENEST, ArmorSets.CLIMBING_GEAR, ArmorSets.EVOCATION,
                            ArmorSets.GHOSTLY, ArmorSets.HUNTER, ArmorSets.SCALE_MAIL, ArmorSets.SNOW, ArmorSets.SOUL_ROBE, ArmorSets.SPELUNKER,
                            ArmorSets.SQUID, ArmorSets.VANGUARD, ArmorSets.WOLF)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
                if (UNCOMMON_LOOT_TABLES.contains(id.toString())) {
                    for (ArmorSets armorSets : List.of(ArmorSets.CHAMPION, ArmorSets.EMERALD, ArmorSets.ENTERTAINER, ArmorSets.GOAT, ArmorSets.GRIM,
                            ArmorSets.GUARDS, ArmorSets.MERCENARY, ArmorSets.OCELOT, ArmorSets.PHANTOM, ArmorSets.PIGLIN, ArmorSets.PLATE, ArmorSets.REINFORCED_MAIL,
                            ArmorSets.SWEET_TOOTH, ArmorSets.TELEPORTATION, ArmorSets.THIEF, ArmorSets.TURTLE)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
                if (RARE_LOOT_TABLES.contains(id.toString())) {
                    for (ArmorSets armorSets : List.of(ArmorSets.SPLENDID, ArmorSets.BEEHIVE, ArmorSets.RUGGED_CLIMBING_GEAR, ArmorSets.DARK, ArmorSets.TROUBADOUR,
                            ArmorSets.EMBER, ArmorSets.VERDANT, ArmorSets.GHOST_KINDLER, ArmorSets.WITHER, ArmorSets.ARCHER, ArmorSets.LIVING_VINES, ArmorSets.SPROUT, ArmorSets.RENEGADE,
                            ArmorSets.GOLDEN_PIGLIN, ArmorSets.STALWART_MAIL, ArmorSets.HIGHLAND, ArmorSets.FROST, ArmorSets.SOULDANCER, ArmorSets.CAVE_CRAWLER, ArmorSets.GLOW_SQUID,
                            ArmorSets.SPIDER, ArmorSets.BLACK_WOLF, ArmorSets.FOX)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
                if (EPIC_LOOT_TABLES.contains(id.toString())) {
                    for (ArmorSets armorSets : List.of(ArmorSets.HERO, ArmorSets.TITAN, ArmorSets.ROYAL, ArmorSets.GILDED, ArmorSets.OPULENT, ArmorSets.GOURDIAN,
                            ArmorSets.CURIOUS, ArmorSets.HUNGRY_HORROR, ArmorSets.RED_MYSTERY, ArmorSets.MYSTERY, ArmorSets.BLUE_MYSTERY, ArmorSets.GREEN_MYSTERY,
                            ArmorSets.PURPLE_MYSTERY, ArmorSets.SHADOW_WALKER, ArmorSets.FROST_BITE, ArmorSets.FULL_METAL, ArmorSets.CAULDRON, ArmorSets.SHULKER,
                            ArmorSets.STURDY_SHULKER, ArmorSets.UNSTABLE, ArmorSets.NIMBLE_TURTLE, ArmorSets.ARCTIC_FOX)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
            } else {
                if (BASTION_LOOT_TABLES.contains(id)) {
                    for (ArmorSets armorSets : List.of(ArmorSets.MYSTERY, ArmorSets.BLUE_MYSTERY, ArmorSets.GREEN_MYSTERY, ArmorSets.PURPLE_MYSTERY, ArmorSets.RED_MYSTERY,
                            ArmorSets.GHOSTLY, ArmorSets.GHOST_KINDLER)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
                else if (PIGLIN_TRADING_LOOT_TABLES.contains(id)) {
                    lootPoolBuilder.rolls(ConstantLootNumberProvider.create(CONFIG.mcdaItemDropsConfig.numberOfRollsOnLootTable.get(DropHelper.MYSTERY_GEMSTONE)));
                    lootPoolBuilder.conditionally(RandomChanceLootCondition.builder(CONFIG.mcdaItemDropsConfig.dropBaseChance.get(DropHelper.MYSTERY_GEMSTONE)));
                    for (Item item : List.of(ItemsRegistry.GEMSTONE_WHITE, ItemsRegistry.GEMSTONE_RED, ItemsRegistry.GEMSTONE_GREEN, ItemsRegistry.GEMSTONE_BLUE, ItemsRegistry.GEMSTONE_PURPLE)) {
                        lootPoolBuilder.with(ItemEntry.builder(item));
                    }
                }
                else if (NETHER_FORTRESS_LOOT_TABLES.contains(id)) {
                    for (ArmorSets armorSets : List.of(ArmorSets.MYSTERY, ArmorSets.BLUE_MYSTERY, ArmorSets.GREEN_MYSTERY, ArmorSets.PURPLE_MYSTERY, ArmorSets.RED_MYSTERY,
                            ArmorSets.GRIM, ArmorSets.VANGUARD)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
                else if (PILLAGER_TOWER_LOOT_TABLES.contains(id)) {
                    for (ArmorSets armorSets : List.of(ArmorSets.DARK, ArmorSets.THIEF, ArmorSets.ROYAL, ArmorSets.TITAN)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
                else if (VILLAGE_SMITH_LOOT_TABLES.contains(id)) {
                    for (ArmorSets armorSets : List.of(ArmorSets.PLATE, ArmorSets.FULL_METAL, ArmorSets.SNOW, ArmorSets.WOLF, ArmorSets.FOX, ArmorSets.REINFORCED_MAIL,
                            ArmorSets.STALWART_MAIL)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
                else if (SUNKEN_SHIP_LOOT_TABLES.contains(id)) {
                    for (ArmorSets armorSets : List.of(ArmorSets.MYSTERY, ArmorSets.BLUE_MYSTERY, ArmorSets.GREEN_MYSTERY, ArmorSets.PURPLE_MYSTERY, ArmorSets.RED_MYSTERY,
                            ArmorSets.SCALE_MAIL, ArmorSets.MERCENARY)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
                else if (MINESHAFT_LOOT_TABLES.contains(id)) {
                    for (ArmorSets armorSets : List.of(ArmorSets.MYSTERY, ArmorSets.BLUE_MYSTERY, ArmorSets.GREEN_MYSTERY, ArmorSets.PURPLE_MYSTERY, ArmorSets.RED_MYSTERY,
                            ArmorSets.SPELUNKER, ArmorSets.CAVE_CRAWLER)) {
                        addArmorSet(lootPoolBuilder, armorSets);
                    }
                }
                else if (HERO_OF_THE_VILLAGE_LOOT_TABLES.contains(id)) {
                    addArmorSet(lootPoolBuilder, ArmorSets.HERO);
                    addArmorSet(lootPoolBuilder, ArmorSets.GILDED);
                }
                else if (STRONGHOLD_LOOT_TABLES.contains(id)) {
                    addArmorSet(lootPoolBuilder, ArmorSets.TELEPORTATION);
                    addArmorSet(lootPoolBuilder, ArmorSets.UNSTABLE);
                }
            }
            tableBuilder.pool(lootPoolBuilder.build());
        }));
    }

    private static void addArmorSet(LootPool.Builder poolBuilder, ArmorSets set) {
        int weight = CONFIG.mcdaLootTablesConfig.enableTieredLootTables.get(ItemSettingsHelper.ENABLE_TIERED_LOOT_TABLES) ?
                CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(set) :
                CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(set);
        if (CONFIG.mcdaEnableArmorsConfig.ARMORS_SETS_ENABLED.get(set)) {
            ArmorsRegistry.armorItems.get(set).values()
                    .forEach((item -> poolBuilder.with(ItemEntry.builder(item).weight(weight))));
        }
    }

    private static void addItemLootPool(LootTable.Builder tableBuilder, Item item, DropHelper dropHelper, float lootingProviderMax) {
        LootPool.Builder lootPoolBuilder = LootPool.builder();
        lootPoolBuilder.rolls(ConstantLootNumberProvider.create(CONFIG.mcdaItemDropsConfig.numberOfRollsOnLootTable.get(dropHelper)));
        lootPoolBuilder.conditionally(RandomChanceWithLootingLootCondition.builder(
                CONFIG.mcdaItemDropsConfig.dropBaseChance.get(dropHelper),
                CONFIG.mcdaItemDropsConfig.dropChancePerLootingLevel.get(dropHelper)));
        lootPoolBuilder.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, lootingProviderMax)));
        lootPoolBuilder.with(ItemEntry.builder(item));
        tableBuilder.pool(lootPoolBuilder.build());
    }

    private static void addItemLootPool(LootTable.Builder tableBuilder, Item item, DropHelper dropHelper) {
        LootPool.Builder lootPoolBuilder = LootPool.builder();
        lootPoolBuilder.rolls(ConstantLootNumberProvider.create(CONFIG.mcdaItemDropsConfig.numberOfRollsOnLootTable.get(dropHelper)));
        lootPoolBuilder.conditionally(RandomChanceWithLootingLootCondition.builder(
                CONFIG.mcdaItemDropsConfig.dropBaseChance.get(dropHelper),
                CONFIG.mcdaItemDropsConfig.dropChancePerLootingLevel.get(dropHelper)));
        lootPoolBuilder.with(ItemEntry.builder(item));
        tableBuilder.pool(lootPoolBuilder.build());
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
