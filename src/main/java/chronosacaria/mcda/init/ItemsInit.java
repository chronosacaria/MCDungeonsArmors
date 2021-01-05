package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemsInit {
    public static final Item BONE_RIB_CAGE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item PHANTOM_BONES = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item PHANTOM_SKIN = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item OCELOT_PELT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BLACK_OCELOT_PELT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item WOLF_PELT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BLACK_WOLF_PELT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item FOX_PELT = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static final Item FROST_CRYSTAL = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static final Item GLUT_CHARM = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item IRON_PLATE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item IRON_SCALE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_WHITE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_BLACK = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_RED = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_GREEN = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_BROWN = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_BLUE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_PURPLE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_CYAN = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_LIGHT_GRAY = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_GRAY = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_PINK = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_LIME = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_YELLOW = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_LIGHT_BLUE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_MAGENTA = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BOLT_OF_FABRIC_ORANGE = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static final Item UPGRADE_CORE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_ARCHER = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_DEPTH = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_EMBER = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_FOX = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_FROST = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_GLUT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_HIGHLAND = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_HIVE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_MAGICKED = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_METAL = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_SHADOWS = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_SPIDER = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_SOUL = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_VERDANT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_WITHER = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item UPGRADE_CORE_WOLF = new Item(new Item.Settings().group(ItemGroup.MISC));






    public static void doRegister() {
        //MOB DROPS
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "bone_rib_cage"), BONE_RIB_CAGE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "phantom_bones"), PHANTOM_BONES);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "phantom_skin"), PHANTOM_SKIN);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "ocelot_pelt"), OCELOT_PELT);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "ocelot_pelt_black"), BLACK_OCELOT_PELT);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "wolf_pelt"), WOLF_PELT);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "wolf_pelt_black"), BLACK_WOLF_PELT);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fox_pelt"), FOX_PELT);

        //BLOCK DROPS
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "frost_crystal"), FROST_CRYSTAL);

        //CRAFTING
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "glut_charm"), GLUT_CHARM);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "iron_plate"), IRON_PLATE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "iron_scale"), IRON_SCALE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_white"), BOLT_OF_FABRIC_WHITE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_orange"), BOLT_OF_FABRIC_ORANGE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_magenta"), BOLT_OF_FABRIC_MAGENTA);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_light_blue"), BOLT_OF_FABRIC_LIGHT_BLUE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_yellow"), BOLT_OF_FABRIC_YELLOW);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_lime"), BOLT_OF_FABRIC_LIME);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_pink"), BOLT_OF_FABRIC_PINK);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_gray"), BOLT_OF_FABRIC_GRAY);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_light_gray"), BOLT_OF_FABRIC_LIGHT_GRAY);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_cyan"), BOLT_OF_FABRIC_CYAN);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_purple"), BOLT_OF_FABRIC_PURPLE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_blue"), BOLT_OF_FABRIC_BLUE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_brown"), BOLT_OF_FABRIC_BROWN);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_green"), BOLT_OF_FABRIC_GREEN);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_red"), BOLT_OF_FABRIC_RED);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "fabric_bolt_black"), BOLT_OF_FABRIC_BLACK);

        //UPGRADES
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core"), UPGRADE_CORE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_archer"), UPGRADE_CORE_ARCHER);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_depth"), UPGRADE_CORE_DEPTH);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_ember"), UPGRADE_CORE_EMBER);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_fox"), UPGRADE_CORE_FOX);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_frost"), UPGRADE_CORE_FROST);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_glut"), UPGRADE_CORE_GLUT);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_highland"), UPGRADE_CORE_HIGHLAND);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_hive"), UPGRADE_CORE_HIVE);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_magicked"), UPGRADE_CORE_MAGICKED);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_metal"), UPGRADE_CORE_METAL);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_shadows"), UPGRADE_CORE_SHADOWS);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_spider"), UPGRADE_CORE_SPIDER);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_soul"), UPGRADE_CORE_SOUL);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_verdant"), UPGRADE_CORE_VERDANT);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_wither"), UPGRADE_CORE_WITHER);
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "upgrade_core_wolf"), UPGRADE_CORE_WOLF);


    }
}
