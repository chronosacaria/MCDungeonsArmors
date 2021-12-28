package chronosacaria.mcda.registry;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.itemhelpers.DropHelper;
import chronosacaria.mcda.items.ItemID;
import chronosacaria.mcda.items.itemhelpers.SpawnHelper;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.*;

import static chronosacaria.mcda.config.McdaConfig.config;

public class LootRegistry {

    public static final Collection<Identifier> BASTION_LOOT_TABLES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            LootTables.PIGLIN_BARTERING_GAMEPLAY, LootTables.BASTION_BRIDGE_CHEST,
            LootTables.BASTION_HOGLIN_STABLE_CHEST, LootTables.BASTION_OTHER_CHEST,
            LootTables.BASTION_TREASURE_CHEST)));

    public static final Collection<Identifier> NETHER_FORTRESS_LOOT_TABLES = Collections.singletonList(
            LootTables.NETHER_BRIDGE_CHEST);

    public static final Collection<Identifier> PILLAGER_TOWER_LOOT_TABLES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            LootTables.PILLAGER_OUTPOST_CHEST, LootTables.WOODLAND_MANSION_CHEST)));

    public static final Collection<Identifier> VILLAGE_SMITH_LOOT_TABLES = Collections.unmodifiableList(Arrays.asList(
            LootTables.VILLAGE_TOOLSMITH_CHEST,
            LootTables.VILLAGE_WEAPONSMITH_CHEST));

    public static final Collection<Identifier> SUNKEN_SHIP_LOOT_TABLES = Collections.unmodifiableList(Arrays.asList(
            LootTables.SHIPWRECK_TREASURE_CHEST,
            LootTables.SHIPWRECK_SUPPLY_CHEST));

    public static final Collection<Identifier> MINESHAFT_LOOT_TABLES = Collections.singletonList(
            LootTables.ABANDONED_MINESHAFT_CHEST);

    public static final Collection<Identifier> HERO_OF_THE_VILLAGE_LOOT_TABLES = Collections.singletonList(
            LootTables.HERO_OF_THE_VILLAGE_ARMORER_GIFT_GAMEPLAY);

    public static final Collection<Identifier> STRONGHOLD_LOOT_TABLES = Collections.unmodifiableList(Arrays.asList(
            LootTables.STRONGHOLD_CORRIDOR_CHEST, LootTables.STRONGHOLD_CROSSING_CHEST, LootTables.STRONGHOLD_LIBRARY_CHEST));

    public static void init() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (!id.getNamespace().equals("minecraft"))
                return;

            FabricLootPoolBuilder poolBuilder;

            switch (id.getPath()) {
                case "entities/phantom":
                    LootPool phantomBonesLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.PHANTOM_BONES).weight(100))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.PHANTOM_SKIN_AND_BONES))).build())

                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.PHANTOM_SKIN).weight(50))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.PHANTOM_SKIN_AND_BONES))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)).build())
                            .build();
                    supplier.withPool(phantomBonesLootPool);
                    break;
                case "entities/ocelot":
                    LootPool ocelotPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.OCELOT_PELT).weight(100))
                            .with(ItemEntry.builder(ItemID.OCELOT_PELT_BLACK).weight(10))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.OCELOT_PELTS))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(ocelotPeltLootPool);
                    break;
                case "entities/skeleton":
                    LootPool skullLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(Items.SKELETON_SKULL))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                            config.maxDropAmounts.get(DropHelper.SKELETON_SKULL))).build())
                            .build();
                    supplier.withPool(skullLootPool);
                    break;
                case "entities/wolf":
                    LootPool wolfPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.WOLF_PELT).weight(100))
                            .with(ItemEntry.builder(ItemID.WOLF_PELT_BLACK).weight(10))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.WOLF_PELTS))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(wolfPeltLootPool);
                    break;
                case "entities/fox":
                    LootPool foxPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.FOX_PELT).weight(100))
                            .with(ItemEntry.builder(ItemID.FOX_PELT_ARCTIC).weight(10))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.FOX_PELTS))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(foxPeltLootPool);
                    break;
                case "entities/evoker":
                    LootPool evocationHatLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).get(EquipmentSlot.HEAD)))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.EVOCATION_ROBE))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(evocationHatLootPool);
                    LootPool evocationRobeLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).get(EquipmentSlot.CHEST)))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.EVOCATION_ROBE))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(evocationRobeLootPool);
                    LootPool evocationPantsLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ArmorsRegistry.armorItems.get(ArmorSets.EVOCATION).get(EquipmentSlot.LEGS)))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.EVOCATION_ROBE))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(evocationPantsLootPool);
                    break;
                case "entities/goat":
                    LootPool goatPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.GOAT_PELT))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.GOAT_PELT))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(goatPeltLootPool);
                    break;
                case "blocks/blue_ice":
                    LootPool frostCrystalLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.FROST_CRYSTAL))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.FROST_CRYSTAL))).build())
                            .build();
                    supplier.withPool(frostCrystalLootPool);
                    break;
            }

            if (BASTION_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.GHOSTLY,       config.armorSpawnRates.get(SpawnHelper.GHOSTLY));
                addArmorSet(poolBuilder, ArmorSets.GHOST_KINDLER, config.armorSpawnRates.get(SpawnHelper.GHOST_KINDLER));
                addMysteryArmorSets(poolBuilder, config.armorSpawnRates.get(SpawnHelper.MYSTERY));
                supplier.pool(poolBuilder);
            } else if (NETHER_FORTRESS_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.GRIM, config.armorSpawnRates.get(SpawnHelper.GRIM));
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
}
