package chronosacaria.mcda.init;

import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.bases.ItemID;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

import java.util.*;

public class LootInit {

    public static final Collection<Identifier> BASTION_LOOT_TABLES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            LootTables.PIGLIN_BARTERING_GAMEPLAY, LootTables.BASTION_BRIDGE_CHEST,
            LootTables.BASTION_HOGLIN_STABLE_CHEST, LootTables.BASTION_OTHER_CHEST,
            LootTables.BASTION_TREASURE_CHEST)));

    public static final Collection<Identifier> NETHER_FORTRESS_LOOT_TABLES = Collections.singletonList(
            LootTables.NETHER_BRIDGE_CHEST);

    public static final Collection<Identifier> PILLAGER_TOWER_LOOT_TABLES = Collections.singletonList(
            LootTables.PILLAGER_OUTPOST_CHEST);

    public static final Collection<Identifier> VILLAGE_SMITH_LOOT_TABLES = Collections.unmodifiableList(Arrays.asList(
            LootTables.VILLAGE_TOOLSMITH_CHEST,
            LootTables.VILLAGE_WEAPONSMITH_CHEST));

    public static final Collection<Identifier> SUNKEN_SHIP_LOOT_TABLES = Collections.unmodifiableList(Arrays.asList(
            LootTables.SHIPWRECK_TREASURE_CHEST,
            LootTables.SHIPWRECK_SUPPLY_CHEST));

    public static final Collection<Identifier> MINESHAFT_LOOT_TABLES = Collections.singletonList(
            LootTables.ABANDONED_MINESHAFT_CHEST);

    public static void init() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (!id.getNamespace().equals("minecraft"))
                return;

            FabricLootPoolBuilder poolBuilder;

            switch (id.getPath()) {
                case "entities/phantom":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootTableRange.create(1,0.25F))
                            .with(ItemEntry.builder(ItemID.PHANTOM_BONES))
                            .rolls(BinomialLootTableRange.create(1,0.10F))
                            .with(ItemEntry.builder(ItemID.PHANTOM_SKIN));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/ocelot":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootTableRange.create(1,0.25F))
                            .with(ItemEntry.builder(ItemID.OCELOT_PELT))
                            .rolls(BinomialLootTableRange.create(1,0.05F))
                            .with(ItemEntry.builder(ItemID.OCELOT_PELT_BLACK));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/skeleton":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootTableRange.create(1, 0.05F))
                            .with(ItemEntry.builder(Items.SKELETON_SKULL));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/wolf":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootTableRange.create(1,0.25F))
                            .with(ItemEntry.builder(ItemID.WOLF_PELT))
                            .rolls(BinomialLootTableRange.create(1,0.05F))
                            .with(ItemEntry.builder(ItemID.WOLF_PELT_BLACK));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/fox":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootTableRange.create(1,0.25F))
                            .with(ItemEntry.builder(ItemID.FOX_PELT))
                            .rolls(BinomialLootTableRange.create(1,0.05F))
                            .with(ItemEntry.builder(ItemID.FOX_PELT_ARCTIC));
                    supplier.pool(poolBuilder);
                    break;
                case "entities/evoker":
                    poolBuilder = FabricLootPoolBuilder.builder();
                    addArmorSet(poolBuilder, ArmorBases.EVOCATION,0.10F);
                    supplier.pool(poolBuilder);
                    break;
                case "blocks/blue_ice":
                    poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(BinomialLootTableRange.create(1, 0.05F))
                            .with(ItemEntry.builder(ItemID.FROST_CRYSTAL));
                    supplier.pool(poolBuilder);
                    break;
            }

            if (BASTION_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorBases.GHOSTLY,       0.05F);
                addArmorSet(poolBuilder, ArmorBases.GHOST_KINDLER, 0.01F);
                supplier.pool(poolBuilder);
            } else if (NETHER_FORTRESS_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorBases.GRIM, 0.05F);
                supplier.pool(poolBuilder);
            } else if (PILLAGER_TOWER_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorBases.DARK,  0.05F);
                addArmorSet(poolBuilder, ArmorBases.THIEF, 0.05F);
                supplier.pool(poolBuilder);
            } else if (VILLAGE_SMITH_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorBases.PLATE,           0.05F);
                addArmorSet(poolBuilder, ArmorBases.FULL_METAL,      0.01F);
                addArmorSet(poolBuilder, ArmorBases.SNOW,            0.03F);
                addArmorSet(poolBuilder, ArmorBases.WOLF,            0.05F);
                addArmorSet(poolBuilder, ArmorBases.FOX,             0.01F);
                addArmorSet(poolBuilder, ArmorBases.REINFORCED_MAIL, 0.05F);
                addArmorSet(poolBuilder, ArmorBases.STALWART_MAIL,   0.01F);
                supplier.pool(poolBuilder);
            } else if (SUNKEN_SHIP_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorBases.SCALE_MAIL, 0.05F);
                addArmorSet(poolBuilder, ArmorBases.MERCENARY,  0.05F);
                supplier.pool(poolBuilder);
            } else if (MINESHAFT_LOOT_TABLES.contains(id)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addArmorSet(poolBuilder, ArmorBases.SPELUNKER,    0.05F);
                addArmorSet(poolBuilder, ArmorBases.CAVE_CRAWLER, 0.01F);
                supplier.pool(poolBuilder);
            }
        });

    }

    public static void addArmorSet(FabricLootPoolBuilder poolBuilder, ArmorBases set, float p) {
        ArmorsInit.armorItems.get(set).values()
                .forEach((item -> {
                    poolBuilder.rolls(new BinomialLootTableRange(1, p));
                    poolBuilder.with(ItemEntry.builder(item));
                }));
    }
}
