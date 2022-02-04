package chronosacaria.mcda.registry;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.itemhelpers.DropHelper;
import chronosacaria.mcda.items.ItemID;
import chronosacaria.mcda.items.itemhelpers.ItemSettingsHelper;
import chronosacaria.mcda.items.itemhelpers.SpawnHelper;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.*;

import static chronosacaria.mcda.config.McdaConfig.config;

public class LootRegistry {

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
            new ArrayList<>(config.commonLootTables.get(ItemSettingsHelper.COMMON_LOOT_TABLES));
    public static final ArrayList<String> UNCOMMON_LOOT_TABLES =
            new ArrayList<>(config.uncommonLootTables.get(ItemSettingsHelper.UNCOMMON_LOOT_TABLES));
    public static final ArrayList<String> RARE_LOOT_TABLES =
            new ArrayList<>(config.rareLootTables.get(ItemSettingsHelper.RARE_LOOT_TABLES));
    public static final ArrayList<String> EPIC_LOOT_TABLES =
            new ArrayList<>(config.epicLootTables.get(ItemSettingsHelper.EPIC_LOOT_TABLES));

    public static void init() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (!id.getNamespace().equals("minecraft"))
                return;

            FabricLootPoolBuilder poolBuilder;

            switch (id.getPath()) {
                case "entities/phantom" -> {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.PHANTOM_BONES),
                            config.maxDropAmounts.get(DropHelper.PHANTOM_BONES).intValue(), 0.35f);
                    supplier.pool(poolBuilder);
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.PHANTOM_SKIN),
                            config.maxDropAmounts.get(DropHelper.PHANTOM_SKIN).intValue(), 0.20f);
                    supplier.pool(poolBuilder);

                }
                case "entities/ocelot" -> {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.OCELOT_PELT),
                            config.maxDropAmounts.get(DropHelper.OCELOT_PELT).intValue(), 0.35f);
                    supplier.pool(poolBuilder);
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.OCELOT_PELT_BLACK),
                            config.maxDropAmounts.get(DropHelper.BLACK_OCELOT_PELT).intValue(), 0.20f);
                    supplier.pool(poolBuilder);

                }
                case "entities/skeleton" -> {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, Items.SKELETON_SKULL,
                            config.maxDropAmounts.get(DropHelper.SKELETON_SKULL).intValue(), 0.20f);
                    supplier.pool(poolBuilder);
                }
                case "entities/wolf" -> {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.WOLF_PELT),
                            config.maxDropAmounts.get(DropHelper.WOLF_PELT).intValue(), 0.25f);
                    supplier.pool(poolBuilder);
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.WOLF_PELT_BLACK),
                            config.maxDropAmounts.get(DropHelper.BLACK_WOLF_PELT).intValue(), 0.08f);
                    supplier.pool(poolBuilder);
                }
                case "entities/fox" -> {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.FOX_PELT),
                            config.maxDropAmounts.get(DropHelper.FOX_PELT).intValue(), 0.25f);
                    supplier.pool(poolBuilder);
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.FOX_PELT_ARCTIC),
                            config.maxDropAmounts.get(DropHelper.ARCTIC_FOX_PELT).intValue(), 0.10f);
                    supplier.pool(poolBuilder);
                }
                case "entities/evoker" -> {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).get(EquipmentSlot.HEAD),
                            config.maxDropAmounts.get(DropHelper.EVOCATION_ROBE).intValue(), 0.20f);
                    supplier.pool(poolBuilder);
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).get(EquipmentSlot.CHEST),
                            config.maxDropAmounts.get(DropHelper.EVOCATION_ROBE).intValue(), 0.20f);
                    supplier.pool(poolBuilder);
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).get(EquipmentSlot.LEGS),
                            config.maxDropAmounts.get(DropHelper.EVOCATION_ROBE).intValue(), 0.20f);
                    supplier.pool(poolBuilder);
                }
                case "entities/goat" -> {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.GOAT_PELT),
                            config.maxDropAmounts.get(DropHelper.GOAT_PELT).intValue(), 0.50f);
                    supplier.pool(poolBuilder);
                }
                case "blocks/blue_ice" -> {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.FROST_CRYSTAL),
                            config.maxDropAmounts.get(DropHelper.FROST_CRYSTAL).intValue(), 0.20f);
                    supplier.pool(poolBuilder);
                }
            }

            if (config.armorsEnabledInLootTables.get(ItemSettingsHelper.ENABLE_ARMOR_IN_LOOT_TABLES)){
                for (String commonLootTable : COMMON_LOOT_TABLES) {
                    if (commonLootTable.equals(id.toString())) {
                        poolBuilder = FabricLootPoolBuilder.builder();
                        addArmorSet(poolBuilder, ArmorSets.BATTLE, config.armorLootTableSpawnRates.get(ArmorSets.BATTLE));
                        addArmorSet(poolBuilder, ArmorSets.BEENEST, config.armorLootTableSpawnRates.get(ArmorSets.BEENEST));
                        addArmorSet(poolBuilder, ArmorSets.CLIMBING_GEAR, config.armorLootTableSpawnRates.get(ArmorSets.CLIMBING_GEAR));
                        addArmorSet(poolBuilder, ArmorSets.EVOCATION, config.armorLootTableSpawnRates.get(ArmorSets.EVOCATION));
                        addArmorSet(poolBuilder, ArmorSets.GHOSTLY, config.armorLootTableSpawnRates.get(ArmorSets.GHOSTLY));
                        addArmorSet(poolBuilder, ArmorSets.HUNTER, config.armorLootTableSpawnRates.get(ArmorSets.HUNTER));
                        addArmorSet(poolBuilder, ArmorSets.SCALE_MAIL, config.armorLootTableSpawnRates.get(ArmorSets.SCALE_MAIL));
                        addArmorSet(poolBuilder, ArmorSets.SNOW, config.armorLootTableSpawnRates.get(ArmorSets.SNOW));
                        addArmorSet(poolBuilder, ArmorSets.SOUL_ROBE, config.armorLootTableSpawnRates.get(ArmorSets.SOUL_ROBE));
                        addArmorSet(poolBuilder, ArmorSets.SPELUNKER, config.armorLootTableSpawnRates.get(ArmorSets.SPELUNKER));
                        addArmorSet(poolBuilder, ArmorSets.SQUID, config.armorLootTableSpawnRates.get(ArmorSets.SQUID));
                        addArmorSet(poolBuilder, ArmorSets.VANGUARD, config.armorLootTableSpawnRates.get(ArmorSets.VANGUARD));
                        addArmorSet(poolBuilder, ArmorSets.WOLF, config.armorLootTableSpawnRates.get(ArmorSets.WOLF));
                        supplier.pool(poolBuilder);
                    }
                }
                for (String uncommonLootTable : UNCOMMON_LOOT_TABLES) {
                    if (uncommonLootTable.equals(id.toString())) {
                        poolBuilder = FabricLootPoolBuilder.builder();
                        addArmorSet(poolBuilder, ArmorSets.CHAMPION, config.armorLootTableSpawnRates.get(ArmorSets.CHAMPION));
                        addArmorSet(poolBuilder, ArmorSets.EMERALD, config.armorLootTableSpawnRates.get(ArmorSets.EMERALD));
                        addArmorSet(poolBuilder, ArmorSets.ENTERTAINER, config.armorLootTableSpawnRates.get(ArmorSets.ENTERTAINER));
                        addArmorSet(poolBuilder, ArmorSets.GOAT, config.armorLootTableSpawnRates.get(ArmorSets.GOAT));
                        addArmorSet(poolBuilder, ArmorSets.GRIM, config.armorLootTableSpawnRates.get(ArmorSets.GRIM));
                        addArmorSet(poolBuilder, ArmorSets.GUARDS, config.armorLootTableSpawnRates.get(ArmorSets.GUARDS));
                        addArmorSet(poolBuilder, ArmorSets.MERCENARY, config.armorLootTableSpawnRates.get(ArmorSets.MERCENARY));
                        addArmorSet(poolBuilder, ArmorSets.OCELOT, config.armorLootTableSpawnRates.get(ArmorSets.OCELOT));
                        addArmorSet(poolBuilder, ArmorSets.PHANTOM, config.armorLootTableSpawnRates.get(ArmorSets.PHANTOM));
                        addArmorSet(poolBuilder, ArmorSets.PIGLIN, config.armorLootTableSpawnRates.get(ArmorSets.PIGLIN));
                        addArmorSet(poolBuilder, ArmorSets.PLATE, config.armorLootTableSpawnRates.get(ArmorSets.PLATE));
                        addArmorSet(poolBuilder, ArmorSets.REINFORCED_MAIL, config.armorLootTableSpawnRates.get(ArmorSets.REINFORCED_MAIL));
                        addArmorSet(poolBuilder, ArmorSets.SWEET_TOOTH, config.armorLootTableSpawnRates.get(ArmorSets.SWEET_TOOTH));
                        addArmorSet(poolBuilder, ArmorSets.TELEPORTATION, config.armorLootTableSpawnRates.get(ArmorSets.TELEPORTATION));
                        addArmorSet(poolBuilder, ArmorSets.THIEF, config.armorLootTableSpawnRates.get(ArmorSets.THIEF));
                        addArmorSet(poolBuilder, ArmorSets.TURTLE, config.armorLootTableSpawnRates.get(ArmorSets.TURTLE));
                        supplier.pool(poolBuilder);
                    }
                }
                for (String rareLootTable : RARE_LOOT_TABLES) {
                    if (rareLootTable.equals(id.toString())) {
                        poolBuilder = FabricLootPoolBuilder.builder();
                        addArmorSet(poolBuilder, ArmorSets.SPLENDID, config.armorLootTableSpawnRates.get(ArmorSets.SPLENDID));
                        addArmorSet(poolBuilder, ArmorSets.BEEHIVE, config.armorLootTableSpawnRates.get(ArmorSets.BEEHIVE));
                        addArmorSet(poolBuilder, ArmorSets.RUGGED_CLIMBING_GEAR, config.armorLootTableSpawnRates.get(ArmorSets.RUGGED_CLIMBING_GEAR));
                        addArmorSet(poolBuilder, ArmorSets.DARK, config.armorLootTableSpawnRates.get(ArmorSets.DARK));
                        addArmorSet(poolBuilder, ArmorSets.TROUBADOUR, config.armorLootTableSpawnRates.get(ArmorSets.TROUBADOUR));
                        addArmorSet(poolBuilder, ArmorSets.EMBER, config.armorLootTableSpawnRates.get(ArmorSets.EMBER));
                        addArmorSet(poolBuilder, ArmorSets.VERDANT, config.armorLootTableSpawnRates.get(ArmorSets.VERDANT));
                        addArmorSet(poolBuilder, ArmorSets.GHOST_KINDLER, config.armorLootTableSpawnRates.get(ArmorSets.GHOST_KINDLER));
                        addArmorSet(poolBuilder, ArmorSets.WITHER, config.armorLootTableSpawnRates.get(ArmorSets.WITHER));
                        addArmorSet(poolBuilder, ArmorSets.ARCHER, config.armorLootTableSpawnRates.get(ArmorSets.ARCHER));
                        addArmorSet(poolBuilder, ArmorSets.LIVING_VINES, config.armorLootTableSpawnRates.get(ArmorSets.LIVING_VINES));
                        addArmorSet(poolBuilder, ArmorSets.SPROUT, config.armorLootTableSpawnRates.get(ArmorSets.SPROUT));
                        addArmorSet(poolBuilder, ArmorSets.RENEGADE, config.armorLootTableSpawnRates.get(ArmorSets.RENEGADE));
                        addArmorSet(poolBuilder, ArmorSets.GOLDEN_PIGLIN, config.armorLootTableSpawnRates.get(ArmorSets.GOLDEN_PIGLIN));
                        addArmorSet(poolBuilder, ArmorSets.STALWART_MAIL, config.armorLootTableSpawnRates.get(ArmorSets.STALWART_MAIL));
                        addArmorSet(poolBuilder, ArmorSets.HIGHLAND, config.armorLootTableSpawnRates.get(ArmorSets.HIGHLAND));
                        addArmorSet(poolBuilder, ArmorSets.FROST, config.armorLootTableSpawnRates.get(ArmorSets.FROST));
                        addArmorSet(poolBuilder, ArmorSets.SOULDANCER, config.armorLootTableSpawnRates.get(ArmorSets.SOULDANCER));
                        addArmorSet(poolBuilder, ArmorSets.CAVE_CRAWLER, config.armorLootTableSpawnRates.get(ArmorSets.CAVE_CRAWLER));
                        addArmorSet(poolBuilder, ArmorSets.GLOW_SQUID, config.armorLootTableSpawnRates.get(ArmorSets.GLOW_SQUID));
                        addArmorSet(poolBuilder, ArmorSets.SPIDER, config.armorLootTableSpawnRates.get(ArmorSets.SPIDER));
                        addArmorSet(poolBuilder, ArmorSets.BLACK_WOLF, config.armorLootTableSpawnRates.get(ArmorSets.BLACK_WOLF));
                        addArmorSet(poolBuilder, ArmorSets.FOX, config.armorLootTableSpawnRates.get(ArmorSets.FOX));
                        supplier.pool(poolBuilder);
                    }
                }
                for (String epicLootTable : EPIC_LOOT_TABLES) {
                    if (epicLootTable.equals(id.toString())) {
                        poolBuilder = FabricLootPoolBuilder.builder();
                        addArmorSet(poolBuilder, ArmorSets.HERO, config.armorLootTableSpawnRates.get(ArmorSets.HERO));
                        addArmorSet(poolBuilder, ArmorSets.TITAN, config.armorLootTableSpawnRates.get(ArmorSets.TITAN));
                        addArmorSet(poolBuilder, ArmorSets.ROYAL, config.armorLootTableSpawnRates.get(ArmorSets.ROYAL));
                        addArmorSet(poolBuilder, ArmorSets.GILDED, config.armorLootTableSpawnRates.get(ArmorSets.GILDED));
                        addArmorSet(poolBuilder, ArmorSets.OPULENT, config.armorLootTableSpawnRates.get(ArmorSets.OPULENT));
                        addArmorSet(poolBuilder, ArmorSets.GOURDIAN, config.armorLootTableSpawnRates.get(ArmorSets.GOURDIAN));
                        addArmorSet(poolBuilder, ArmorSets.CURIOUS, config.armorLootTableSpawnRates.get(ArmorSets.CURIOUS));
                        addArmorSet(poolBuilder, ArmorSets.HUNGRY_HORROR, config.armorLootTableSpawnRates.get(ArmorSets.HUNGRY_HORROR));
                        addArmorSet(poolBuilder, ArmorSets.RED_MYSTERY, config.armorLootTableSpawnRates.get(ArmorSets.RED_MYSTERY));
                        addArmorSet(poolBuilder, ArmorSets.MYSTERY, config.armorLootTableSpawnRates.get(ArmorSets.MYSTERY));
                        addArmorSet(poolBuilder, ArmorSets.BLUE_MYSTERY, config.armorLootTableSpawnRates.get(ArmorSets.BLUE_MYSTERY));
                        addArmorSet(poolBuilder, ArmorSets.GREEN_MYSTERY, config.armorLootTableSpawnRates.get(ArmorSets.GREEN_MYSTERY));
                        addArmorSet(poolBuilder, ArmorSets.PURPLE_MYSTERY, config.armorLootTableSpawnRates.get(ArmorSets.PURPLE_MYSTERY));
                        addArmorSet(poolBuilder, ArmorSets.SHADOW_WALKER, config.armorLootTableSpawnRates.get(ArmorSets.SHADOW_WALKER));
                        addArmorSet(poolBuilder, ArmorSets.FROST_BITE, config.armorLootTableSpawnRates.get(ArmorSets.FROST_BITE));
                        addArmorSet(poolBuilder, ArmorSets.FULL_METAL, config.armorLootTableSpawnRates.get(ArmorSets.FULL_METAL));
                        addArmorSet(poolBuilder, ArmorSets.CAULDRON, config.armorLootTableSpawnRates.get(ArmorSets.CAULDRON));
                        addArmorSet(poolBuilder, ArmorSets.SHULKER, config.armorLootTableSpawnRates.get(ArmorSets.SHULKER));
                        addArmorSet(poolBuilder, ArmorSets.STURDY_SHULKER, config.armorLootTableSpawnRates.get(ArmorSets.STURDY_SHULKER));
                        addArmorSet(poolBuilder, ArmorSets.UNSTABLE, config.armorLootTableSpawnRates.get(ArmorSets.UNSTABLE));
                        addArmorSet(poolBuilder, ArmorSets.NIMBLE_TURTLE, config.armorLootTableSpawnRates.get(ArmorSets.NIMBLE_TURTLE));
                        addArmorSet(poolBuilder, ArmorSets.ARCTIC_FOX, config.armorLootTableSpawnRates.get(ArmorSets.ARCTIC_FOX));
                        supplier.pool(poolBuilder);
                    }
                }
            } else {
                if (BASTION_LOOT_TABLES.contains(id)) {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorSets.GHOSTLY, config.armorSpawnRates.get(SpawnHelper.GHOSTLY));
                    addArmorSet(poolBuilder, ArmorSets.GHOST_KINDLER, config.armorSpawnRates.get(SpawnHelper.GHOST_KINDLER));
                    addMysteryArmorSets(poolBuilder, config.armorSpawnRates.get(SpawnHelper.MYSTERY));
                    supplier.pool(poolBuilder);
                } else if (PIGLIN_TRADING_LOOT_TABLES.contains(id)) {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_WHITE), 1, 0.01F);
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_RED), 1, 0.01F);
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_GREEN), 1, 0.01F);
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_BLUE), 1, 0.01F);
                    addItemDrop(poolBuilder, ItemsRegistry.items.get(ItemID.GEMSTONE_PURPLE), 1, 0.01F);
                    supplier.pool(poolBuilder);
                } else if (NETHER_FORTRESS_LOOT_TABLES.contains(id)) {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorSets.GRIM, config.armorSpawnRates.get(SpawnHelper.GRIM));
                    addArmorSet(poolBuilder, ArmorSets.VANGUARD, config.armorSpawnRates.get(SpawnHelper.VANGUARD));
                    addMysteryArmorSets(poolBuilder, config.armorSpawnRates.get(SpawnHelper.MYSTERY));
                    supplier.pool(poolBuilder);
                } else if (PILLAGER_TOWER_LOOT_TABLES.contains(id)) {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorSets.DARK, config.armorSpawnRates.get(SpawnHelper.DARK));
                    addArmorSet(poolBuilder, ArmorSets.THIEF, config.armorSpawnRates.get(SpawnHelper.THIEF));
                    addArmorSet(poolBuilder, ArmorSets.ROYAL, config.armorSpawnRates.get(SpawnHelper.ROYAL));
                    addArmorSet(poolBuilder, ArmorSets.TITAN, config.armorSpawnRates.get(SpawnHelper.TITAN));
                    supplier.pool(poolBuilder);
                } else if (VILLAGE_SMITH_LOOT_TABLES.contains(id)) {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorSets.PLATE, config.armorSpawnRates.get(SpawnHelper.PLATE));
                    addArmorSet(poolBuilder, ArmorSets.FULL_METAL, config.armorSpawnRates.get(SpawnHelper.FULL_METAL));
                    addArmorSet(poolBuilder, ArmorSets.SNOW, config.armorSpawnRates.get(SpawnHelper.SNOW));
                    addArmorSet(poolBuilder, ArmorSets.WOLF, config.armorSpawnRates.get(SpawnHelper.WOLF));
                    addArmorSet(poolBuilder, ArmorSets.FOX, config.armorSpawnRates.get(SpawnHelper.FOX));
                    addArmorSet(poolBuilder, ArmorSets.REINFORCED_MAIL, config.armorSpawnRates.get(SpawnHelper.REINFORCED));
                    addArmorSet(poolBuilder, ArmorSets.STALWART_MAIL, config.armorSpawnRates.get(SpawnHelper.STALWART));
                    supplier.pool(poolBuilder);
                } else if (SUNKEN_SHIP_LOOT_TABLES.contains(id)) {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorSets.SCALE_MAIL, config.armorSpawnRates.get(SpawnHelper.SCALE));
                    addArmorSet(poolBuilder, ArmorSets.MERCENARY, config.armorSpawnRates.get(SpawnHelper.MERCENARY));
                    addMysteryArmorSets(poolBuilder, 0.05F);
                    supplier.pool(poolBuilder);
                } else if (MINESHAFT_LOOT_TABLES.contains(id)) {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorSets.SPELUNKER, config.armorSpawnRates.get(SpawnHelper.SPELUNKER));
                    addArmorSet(poolBuilder, ArmorSets.CAVE_CRAWLER, config.armorSpawnRates.get(SpawnHelper.CAVE_CRAWLER));
                    addMysteryArmorSets(poolBuilder, 0.05F);
                    supplier.pool(poolBuilder);
                } else if (HERO_OF_THE_VILLAGE_LOOT_TABLES.contains(id)) {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorSets.HERO, config.armorSpawnRates.get(SpawnHelper.HERO));
                    addArmorSet(poolBuilder, ArmorSets.GILDED, config.armorSpawnRates.get(SpawnHelper.GILDED));
                    supplier.pool(poolBuilder);
                } else if (STRONGHOLD_LOOT_TABLES.contains(id)) {
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorSets.TELEPORTATION, config.armorSpawnRates.get(SpawnHelper.TELEPORTATION));
                    addArmorSet(poolBuilder, ArmorSets.UNSTABLE, config.armorSpawnRates.get(SpawnHelper.UNSTABLE));
                    supplier.pool(poolBuilder);
                }
            }
        });
    }

    public static void addMysteryArmorSets(FabricLootPoolBuilder poolBuilder, float p) {
        addArmorSet(poolBuilder, ArmorSets.MYSTERY, p);
        addArmorSet(poolBuilder, ArmorSets.BLUE_MYSTERY, p);
        addArmorSet(poolBuilder, ArmorSets.GREEN_MYSTERY, p);
        addArmorSet(poolBuilder, ArmorSets.PURPLE_MYSTERY, p);
        addArmorSet(poolBuilder, ArmorSets.RED_MYSTERY, p);
    }

    public static void addArmorSet(FabricLootPoolBuilder poolBuilder, ArmorSets set, float p) {
        ArmorsRegistry.armorItems.get(set).values()
                .forEach((item -> {
                    poolBuilder.rolls(BinomialLootNumberProvider.create(1, p));
                    poolBuilder.with(ItemEntry.builder(item));
                }));
    }

    public static void addItemDrop(FabricLootPoolBuilder poolBuilder, Item item, int n, float p){
        poolBuilder.rolls(BinomialLootNumberProvider.create(n, p));
        poolBuilder.withEntry(ItemEntry.builder(item).build());
    }
}
