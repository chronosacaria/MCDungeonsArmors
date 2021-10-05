package chronosacaria.mcda.registry;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.DropHelper;
import chronosacaria.mcda.items.ItemID;
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
                            .with(ItemEntry.builder(ItemID.PHANTOM_BONES))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.PHANTOM_BONES))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(phantomBonesLootPool);
                    LootPool phantomSkinLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.PHANTOM_SKIN))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.PHANTOM_SKIN))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(phantomSkinLootPool);
                    break;
                case "entities/ocelot":
                    LootPool ocelotPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.OCELOT_PELT))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.OCELOT_PELT))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(ocelotPeltLootPool);
                    LootPool blackOcelotPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.OCELOT_PELT_BLACK))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.OCELOT_PELT_BLACK))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(blackOcelotPeltLootPool);
                    break;
                case "entities/skeleton":
                    LootPool skullLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(Items.SKELETON_SKULL))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                            config.maxDropAmounts.get(DropHelper.SKELETON_SKULL))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(skullLootPool);
                    break;
                case "entities/wolf":
                    LootPool wolfPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.WOLF_PELT))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.WOLF_PELT))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(wolfPeltLootPool);
                    LootPool blackWolfPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.WOLF_PELT_BLACK))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.WOLF_PELT_BLACK))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(blackWolfPeltLootPool);
                    break;
                case "entities/fox":
                    LootPool foxPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.FOX_PELT))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.FOX_PELT))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(foxPeltLootPool);
                    LootPool arcticFoxPeltLootPool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(ItemID.FOX_PELT_ARCTIC))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F,
                                    config.maxDropAmounts.get(DropHelper.FOX_PELT_ARCTIC))).build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                            .build();
                    supplier.withPool(arcticFoxPeltLootPool);
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
                addArmorSet(poolBuilder, ArmorSets.GHOSTLY,       config.ghostlyArmor);
                addArmorSet(poolBuilder, ArmorSets.GHOST_KINDLER, config.ghostlyKindler);
                addMysteryArmorSets(poolBuilder, config.mysteryArmors);
                supplier.pool(poolBuilder);
            } else if (NETHER_FORTRESS_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.GRIM, config.grimArmor);
                addMysteryArmorSets(poolBuilder, config.mysteryArmors);
                supplier.pool(poolBuilder);
            } else if (PILLAGER_TOWER_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.DARK,  config.darkArmor);
                addArmorSet(poolBuilder, ArmorSets.THIEF, config.thiefArmor);
                addArmorSet(poolBuilder, ArmorSets.ROYAL, config.royalArmor);
                addArmorSet(poolBuilder, ArmorSets.TITAN, config.titansShroud);
                supplier.pool(poolBuilder);
            } else if (VILLAGE_SMITH_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.PLATE,           config.plateArmor);
                addArmorSet(poolBuilder, ArmorSets.FULL_METAL,      config.fullMetalArmor);
                addArmorSet(poolBuilder, ArmorSets.SNOW,            config.snowArmor);
                addArmorSet(poolBuilder, ArmorSets.WOLF,            config.wolfArmor);
                addArmorSet(poolBuilder, ArmorSets.FOX,             config.foxArmor);
                addArmorSet(poolBuilder, ArmorSets.REINFORCED_MAIL, config.reinforcedMail);
                addArmorSet(poolBuilder, ArmorSets.STALWART_MAIL,   config.stalwartMail);
                supplier.pool(poolBuilder);
            } else if (SUNKEN_SHIP_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.SCALE_MAIL, config.scaleMail);
                addArmorSet(poolBuilder, ArmorSets.MERCENARY,  config.mercenaryArmor);
                addMysteryArmorSets(poolBuilder, 0.05F);
                supplier.pool(poolBuilder);
            } else if (MINESHAFT_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.SPELUNKER,    config.spelunkerArmor);
                addArmorSet(poolBuilder, ArmorSets.CAVE_CRAWLER, config.caveCrawler);
                addMysteryArmorSets(poolBuilder, 0.05F);
                supplier.pool(poolBuilder);
            } else if (HERO_OF_THE_VILLAGE_LOOT_TABLES.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.HERO, config.heroArmor);
                addArmorSet(poolBuilder, ArmorSets.GILDED, config.gildedArmor);
                supplier.pool(poolBuilder);
            } else if (STRONGHOLD_LOOT_TABLES.contains(id)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorSets.TELEPORTATION, config.teleportationRobes);
                addArmorSet(poolBuilder, ArmorSets.UNSTABLE, config.unstableRobes);
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
