package chronosacaria.mcda.registry;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.itemhelpers.DropHelper;
import chronosacaria.mcda.items.ItemID;
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

            if (BASTION_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.GHOSTLY,       config.armorSpawnRates.get(SpawnHelper.GHOSTLY));
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
                addArmorSet(poolBuilder, ArmorSets.DARK,  config.armorSpawnRates.get(SpawnHelper.DARK));
                addArmorSet(poolBuilder, ArmorSets.THIEF, config.armorSpawnRates.get(SpawnHelper.THIEF));
                addArmorSet(poolBuilder, ArmorSets.ROYAL, config.armorSpawnRates.get(SpawnHelper.ROYAL));
                addArmorSet(poolBuilder, ArmorSets.TITAN, config.armorSpawnRates.get(SpawnHelper.TITAN));
                supplier.pool(poolBuilder);
            } else if (VILLAGE_SMITH_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.PLATE,           config.armorSpawnRates.get(SpawnHelper.PLATE));
                addArmorSet(poolBuilder, ArmorSets.FULL_METAL,      config.armorSpawnRates.get(SpawnHelper.FULL_METAL));
                addArmorSet(poolBuilder, ArmorSets.SNOW,            config.armorSpawnRates.get(SpawnHelper.SNOW));
                addArmorSet(poolBuilder, ArmorSets.WOLF,            config.armorSpawnRates.get(SpawnHelper.WOLF));
                addArmorSet(poolBuilder, ArmorSets.FOX,             config.armorSpawnRates.get(SpawnHelper.FOX));
                addArmorSet(poolBuilder, ArmorSets.REINFORCED_MAIL, config.armorSpawnRates.get(SpawnHelper.REINFORCED));
                addArmorSet(poolBuilder, ArmorSets.STALWART_MAIL,   config.armorSpawnRates.get(SpawnHelper.STALWART));
                supplier.pool(poolBuilder);
            } else if (SUNKEN_SHIP_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.SCALE_MAIL, config.armorSpawnRates.get(SpawnHelper.SCALE));
                addArmorSet(poolBuilder, ArmorSets.MERCENARY,  config.armorSpawnRates.get(SpawnHelper.MERCENARY));
                addMysteryArmorSets(poolBuilder, 0.05F);
                supplier.pool(poolBuilder);
            } else if (MINESHAFT_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.SPELUNKER,    config.armorSpawnRates.get(SpawnHelper.SPELUNKER));
                addArmorSet(poolBuilder, ArmorSets.CAVE_CRAWLER, config.armorSpawnRates.get(SpawnHelper.CAVE_CRAWLER));
                addMysteryArmorSets(poolBuilder, 0.05F);
                supplier.pool(poolBuilder);
            } else if (HERO_OF_THE_VILLAGE_LOOT_TABLES.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.HERO, config.armorSpawnRates.get(SpawnHelper.HERO));
                addArmorSet(poolBuilder, ArmorSets.GILDED, config.armorSpawnRates.get(SpawnHelper.GILDED));
                supplier.pool(poolBuilder);
            } else if (STRONGHOLD_LOOT_TABLES.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.TELEPORTATION, config.armorSpawnRates.get(SpawnHelper.TELEPORTATION));
                addArmorSet(poolBuilder, ArmorSets.UNSTABLE, config.armorSpawnRates.get(SpawnHelper.UNSTABLE));
                supplier.pool(poolBuilder);
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
