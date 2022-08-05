package chronosacaria.mcda.registry;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.ItemID;
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
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

import java.util.*;

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

            /*if (EntityType.PHANTOM.getLootTableId().equals(id) && source.isBuiltin()) {
                addIndividualItemDropPool(tableBuilder, ItemsRegistry.items.get(ItemID.PHANTOM_BONES),
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.PHANTOM_BONES),
                        0.35f, UniformLootNumberProvider.create(0, 0.5f));
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.PHANTOM_SKIN),
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.PHANTOM_SKIN),
                        0.20f);
                tableBuilder.pool(lootPoolBuilder.build());
            }*/
            if (EntityType.OCELOT.getLootTableId().equals(id) && source.isBuiltin()) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.OCELOT_PELT)));
                lootPoolBuilder.conditionally(RandomChanceWithLootingLootCondition.builder(0.35f, 0.20f));
                lootPoolBuilder.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 0.5f)));
                lootPoolBuilder.with(ItemEntry.builder(ItemsRegistry.items.get(ItemID.OCELOT_PELT)));
                tableBuilder.pool(lootPoolBuilder.build());

                lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(ConstantLootNumberProvider.create(CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.BLACK_OCELOT_PELT)));
                lootPoolBuilder.conditionally(RandomChanceWithLootingLootCondition.builder(0.10f, 0.10f));
                lootPoolBuilder.with(ItemEntry.builder(ItemsRegistry.items.get(ItemID.OCELOT_PELT_BLACK)));
                tableBuilder.pool(lootPoolBuilder.build());
            }
            /*if (EntityType.SKELETON.getLootTableId().equals(id) && source.isBuiltin()) {
                addIndividualItemDropPool(tableBuilder, Items.SKELETON_SKULL,
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.SKELETON_SKULL),
                        0.20f);
            }
            if (EntityType.WOLF.getLootTableId().equals(id) && source.isBuiltin()) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.WOLF_PELT),
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.WOLF_PELT),
                        0.25f);
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.WOLF_PELT_BLACK),
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.BLACK_WOLF_PELT),
                        0.08f);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.FOX.getLootTableId().equals(id) && source.isBuiltin()) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.FOX_PELT),
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.FOX_PELT),
                        0.25f);
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.FOX_PELT_ARCTIC),
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.ARCTIC_FOX_PELT),
                        0.10f);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.EVOKER.getLootTableId().equals(id) && source.isBuiltin()) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                lootPoolBuilder.rolls(BinomialLootNumberProvider.create(
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.EVOCATION_ROBE), .20f));
                addArmorSet(lootPoolBuilder, ArmorSets.EVOCATION, 1); // weight value doesn't matter here but things are dumb
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (EntityType.GOAT.getLootTableId().equals(id) && source.isBuiltin()) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.GOAT_PELT),
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.GOAT_PELT),
                        0.50F);
                tableBuilder.pool(lootPoolBuilder.build());
            }
            if (Blocks.BLUE_ICE.getLootTableId().equals(id) && source.isBuiltin()) {
                LootPool.Builder lootPoolBuilder = LootPool.builder();
                addItemDrop(lootPoolBuilder, ItemsRegistry.items.get(ItemID.FROST_CRYSTAL),
                        CONFIG.mcdaItemDropsConfig.dropAmounts.get(DropHelper.FROST_CRYSTAL),
                        0.20F);
                lootPoolBuilder.conditionally(WITHOUT_SILK_TOUCH);
                //lootPoolBuilder.apply(ApplyBonusLootFunction.uniformBonusCount())
                tableBuilder.pool(lootPoolBuilder.build());
            }*/

            LootPool.Builder lootPoolBuilder = LootPool.builder();
            lootPoolBuilder.rolls(BinomialLootNumberProvider.create(1, CONFIG.mcdaLootTablesConfig.findArmorChance));
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
                    for (ItemID itemID : List.of(ItemID.GEMSTONE_WHITE, ItemID.GEMSTONE_RED, ItemID.GEMSTONE_GREEN, ItemID.GEMSTONE_BLUE, ItemID.GEMSTONE_PURPLE)) {
                        lootPoolBuilder.rolls(BinomialLootNumberProvider.create(1, 0.01f));
                        lootPoolBuilder.with(ItemEntry.builder(ItemsRegistry.items.get(itemID)));
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

    public static void addArmorSet(LootPool.Builder poolBuilder, ArmorSets set) {
        int weight = CONFIG.mcdaLootTablesConfig.enableTieredLootTables.get(ItemSettingsHelper.ENABLE_TIERED_LOOT_TABLES) ?
                CONFIG.mcdaLootTablesConfig.armorLootTableSpawnRates.get(set) :
                CONFIG.mcdaLootTablesConfig.armorSpawnRates.get(set);
        addArmorSet(poolBuilder, set, weight);
    }

    public static void addArmorSet(LootPool.Builder poolBuilder, ArmorSets set, int weight) {
        ArmorsRegistry.armorItems.get(set).values()
                .forEach((item -> poolBuilder.with(ItemEntry.builder(item).weight(weight))));
    }

    public static void addIndividualItemDropPool(LootTable.Builder tableBuilder, Item item, int n, float p) {
        LootPool.Builder lootPoolBuilder = LootPool.builder();
        lootPoolBuilder.rolls(BinomialLootNumberProvider.create(n, p));
        lootPoolBuilder.with(ItemEntry.builder(item));
        tableBuilder.pool(lootPoolBuilder.build());
    }

    public static void addIndividualItemDropPool(LootTable.Builder tableBuilder, Item item, int n, float p, LootNumberProvider lootingNumberProvider) {
        LootPool.Builder lootPoolBuilder = LootPool.builder();
        lootPoolBuilder.rolls(BinomialLootNumberProvider.create(n, p));
        lootPoolBuilder.with(ItemEntry.builder(item));
        lootPoolBuilder.apply(LootingEnchantLootFunction.builder(lootingNumberProvider));
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
