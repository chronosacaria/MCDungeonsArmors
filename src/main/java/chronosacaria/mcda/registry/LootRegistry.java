package chronosacaria.mcda.registry;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.ItemID;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
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
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootNumberProvider.create(1, config.phantomBones))
                            .with(ItemEntry.builder(ItemID.PHANTOM_BONES))
                            .rolls(BinomialLootNumberProvider.create(1,config.phantomSkin))
                            .with(ItemEntry.builder(ItemID.PHANTOM_SKIN));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/ocelot":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootNumberProvider.create(1,config.ocelotPelt))
                            .with(ItemEntry.builder(ItemID.OCELOT_PELT))
                            .rolls(BinomialLootNumberProvider.create(1,config.blackOcelotPelt))
                            .with(ItemEntry.builder(ItemID.OCELOT_PELT_BLACK));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/skeleton":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootNumberProvider.create(1, config.skeletonSkull))
                            .with(ItemEntry.builder(Items.SKELETON_SKULL));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/wolf":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootNumberProvider.create(1,config.wolfPelt))
                            .with(ItemEntry.builder(ItemID.WOLF_PELT))
                            .rolls(BinomialLootNumberProvider.create(1,config.blackWolfPelt))
                            .with(ItemEntry.builder(ItemID.WOLF_PELT_BLACK));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/fox":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootNumberProvider.create(1,config.foxPelt))
                            .with(ItemEntry.builder(ItemID.FOX_PELT))
                            .rolls(BinomialLootNumberProvider.create(1,config.arcticFoxPelt))
                            .with(ItemEntry.builder(ItemID.FOX_PELT_ARCTIC));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/evoker":
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorSets.EVOCATION, config.evocationRobe);
                    supplier.pool(poolBuilder);
                    break;
                case "entities/goat":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootNumberProvider.create(1,config.goatPelt))
                            .with(ItemEntry.builder(ItemID.GOAT_PELT));
                    supplier.pool(poolBuilder);
                    break;
                case "blocks/blue_ice":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootNumberProvider.create(1, config.frostCrystal))
                            .with(ItemEntry.builder(ItemID.FROST_CRYSTAL));
                    supplier.pool(poolBuilder);
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
