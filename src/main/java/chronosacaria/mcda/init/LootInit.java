package chronosacaria.mcda.init;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class LootInit {

    public static final Identifier[] BASTION_LOOT_TABLES = new Identifier[] { LootTables.PIGLIN_BARTERING_GAMEPLAY,
            LootTables.BASTION_BRIDGE_CHEST, LootTables.BASTION_HOGLIN_STABLE_CHEST, LootTables.BASTION_OTHER_CHEST,
            LootTables.BASTION_TREASURE_CHEST };

    private static boolean bastionLootTable(Identifier lootTable) {
        for (Identifier id : BASTION_LOOT_TABLES) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    public static final Identifier[] NETHER_FORTRESS_LOOT_TABLES =
            new Identifier[] { LootTables.NETHER_BRIDGE_CHEST};

    private static boolean netherFortressLootTables(Identifier lootTable) {
        for (Identifier id : NETHER_FORTRESS_LOOT_TABLES) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    public static final Identifier[] PILLAGER_TOWER_LOOT_TABLES =
            new Identifier[] { LootTables.PILLAGER_OUTPOST_CHEST};
    private static boolean pillagerTowerLootTables(Identifier lootTable) {
        for (Identifier id : PILLAGER_TOWER_LOOT_TABLES) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    public static final Identifier[] VILLAGE_SMITH_LOOT_TABLES = new Identifier[] { LootTables.VILLAGE_TOOLSMITH_CHEST,
            LootTables.VILLAGE_WEAPONSMITH_CHEST};

    private static boolean villageSmithLootTables(Identifier lootTable) {
        for (Identifier id : VILLAGE_SMITH_LOOT_TABLES) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    public static final Identifier[] SUNKEN_SHIP_LOOT_TABLES = new Identifier[] { LootTables.SHIPWRECK_TREASURE_CHEST,
            LootTables.SHIPWRECK_SUPPLY_CHEST};

    private static boolean sunkenShipLootTables(Identifier lootTable) {
        for (Identifier id : SUNKEN_SHIP_LOOT_TABLES) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    public static final Identifier[] MINESHAFT_LOOT_TABLES = new Identifier[] { LootTables.ABANDONED_MINESHAFT_CHEST};

    private static boolean mineshaftLootTables(Identifier lootTable) {
        for (Identifier id : MINESHAFT_LOOT_TABLES) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }


    public static void init(){
        LootTableLoadingCallback.EVENT.register((((resourceManager, lootManager, id,
                                                   supplier, setter) -> {
            /* * * * * * * * * * |
            | PHANTOM LOOT TABLE |
            | * * * * * * * * * */
            if ("minecraft:entities/phantom".equals(id.toString())){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(1,0.25F))
                        .with(ItemEntry.builder(ItemsInit.PHANTOM_BONES))
                        .rolls(BinomialLootTableRange.create(1,0.10F))
                        .with(ItemEntry.builder(ItemsInit.PHANTOM_SKIN));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * * *|
            | OCELOT LOOT TABLE |
            |* * * * * * * * * */
            if ("minecraft:entities/ocelot".equals(id.toString())){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(1,0.25F))
                        .with(ItemEntry.builder(ItemsInit.OCELOT_PELT))
                        .rolls(BinomialLootTableRange.create(1,0.05F))
                        .with(ItemEntry.builder(ItemsInit.BLACK_OCELOT_PELT));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * * * *|
            | SKELETON LOOT TABLE |
            |* * * * * * * * * * */
            if ("minecraft:entities/skeleton".equals(id.toString())){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(1, 0.05F))
                        .with(ItemEntry.builder(Items.SKELETON_SKULL));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * *|
            | WOLF LOOT TABLE |
            |* * * * * * * * */
            if ("minecraft:entities/wolf".equals(id.toString())){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(1,0.25F))
                        .with(ItemEntry.builder(ItemsInit.WOLF_PELT))
                        .rolls(BinomialLootTableRange.create(1,0.05F))
                        .with(ItemEntry.builder(ItemsInit.BLACK_WOLF_PELT));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * |
            | FOX LOOT TABLE |
            | * * * * * * * */
            if ("minecraft:entities/fox".equals(id.toString())){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(1,0.25F))
                        .with(ItemEntry.builder(ItemsInit.FOX_PELT));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * * * *|
            | BLUE ICE LOOT TABLE |
            |* * * * * * * * * * */
            if ("minecraft:blocks/blue_ice".equals(id.toString())){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(1, 0.05F))
                        .with(ItemEntry.builder(ItemsInit.FROST_CRYSTAL));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * * * *|
            | BASTION LOOT TABLES |
            |* * * * * * * * * * */
            if (bastionLootTable(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.GHOST_KINDLER_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.GHOST_KINDLER_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.GHOST_KINDLER_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.GHOST_KINDLER_HELMET))
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ArmorsInit.GHOSTLY_ARMOR_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ArmorsInit.GHOSTLY_ARMOR_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ArmorsInit.GHOSTLY_ARMOR_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ArmorsInit.GHOSTLY_ARMOR_HELMET));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * * * * * * * *|
            | NETHER FORTRESS LOOT TABLES |
            |* * * * * * * * * * * * * * */
            if (netherFortressLootTables(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.GRIM_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.GRIM_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.GRIM_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.GRIM_HELMET));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * * * * * * * |
            | PILLAGER TOWER LOOT TABLES |
            | * * * * * * * * * * * * * */
            if (pillagerTowerLootTables(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.DARK_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.DARK_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.DARK_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.DARK_HELMET))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.THIEF_ARMOR_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.THIEF_ARMOR_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.THIEF_ARMOR_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.THIEF_ARMOR_HELMET));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * * * * * * * |
            | VILLAGER SMITH LOOT TABLES |
            | * * * * * * * * * * * * * */
            if (villageSmithLootTables(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.PLATE_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.PLATE_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.PLATE_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.PLATE_HELMET))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.WOLF_ARMOR_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.WOLF_ARMOR_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.WOLF_ARMOR_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.WOLF_ARMOR_HELMET))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.REINFORCED_MAIL_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.REINFORCED_MAIL_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.REINFORCED_MAIL_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.REINFORCED_MAIL_HELMET))
                        .rolls(new BinomialLootTableRange(1, 0.05F));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * * * * * * * * * * * * * * * * *|
            | SUNKEN SHIP SUPPLIES AND TREASURE LOOT TABLES |
            |* * * * * * * * * * * * * * * * * * * * * * * */
            if (sunkenShipLootTables(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.SCALE_MAIL_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.SCALE_MAIL_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.SCALE_MAIL_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.MERCENARY_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.MERCENARY_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.MERCENARY_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.MERCENARY_HELMET));
                supplier.pool(poolBuilder);
            }

            /* * * * * * * * * * * * * * * * *|
            | ABANDONED MINESHAFT LOOT TABLES |
            |* * * * * * * * * * * * * * * * */
            if (mineshaftLootTables(id)){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.SPELUNKER_ARMOR_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.SPELUNKER_ARMOR_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.SPELUNKER_ARMOR_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.05F))
                        .with(ItemEntry.builder(ArmorsInit.SPELUNKER_ARMOR_HELMET))
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ArmorsInit.CAVE_CRAWLER_BOOTS))
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ArmorsInit.CAVE_CRAWLER_LEGGINGS))
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ArmorsInit.CAVE_CRAWLER_CHESTPLATE))
                        .rolls(new BinomialLootTableRange(1, 0.01F))
                        .with(ItemEntry.builder(ArmorsInit.CAVE_CRAWLER_HELMET));
                supplier.pool(poolBuilder);
            }
        })));

    }
}
