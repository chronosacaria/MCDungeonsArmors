package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ItemsRegistry {
    // Mob Drops
    public static final Item BONE_RIB_CAGE              = registerItem("bone_rib_cage");
    public static final Item FOX_PELT                   = registerItem("fox_pelt");
    public static final Item FOX_PELT_ARCTIC            = registerItem("fox_pelt_arctic");
    public static final Item GOAT_PELT                  = registerItem("goat_pelt");
    public static final Item OCELOT_PELT                = registerItem("ocelot_pelt");
    public static final Item OCELOT_PELT_BLACK          = registerItem("ocelot_pelt_black");
    public static final Item PHANTOM_BONES              = registerItem("phantom_bones");
    public static final Item PHANTOM_SKIN               = registerItem("phantom_skin");
    public static final Item WOLF_PELT                  = registerItem("wolf_pelt");
    public static final Item WOLF_PELT_BLACK            = registerItem("wolf_pelt_black");

    // Block Drops
    public static final Item FROST_CRYSTAL              = registerItem("frost_crystal");

    // Crafting
    public static final Item FABRIC_BOLT_WHITE          = registerItem("fabric_bolt_white");
    public static final Item FABRIC_BOLT_BLACK          = registerItem("fabric_bolt_black");
    public static final Item FABRIC_BOLT_RED            = registerItem("fabric_bolt_red");
    public static final Item FABRIC_BOLT_GREEN          = registerItem("fabric_bolt_green");
    public static final Item FABRIC_BOLT_BROWN          = registerItem("fabric_bolt_brown");
    public static final Item FABRIC_BOLT_BLUE           = registerItem("fabric_bolt_blue");
    public static final Item FABRIC_BOLT_PURPLE         = registerItem("fabric_bolt_purple");
    public static final Item FABRIC_BOLT_CYAN           = registerItem("fabric_bolt_cyan");
    public static final Item FABRIC_BOLT_LIGHT_GRAY     = registerItem("fabric_bolt_light_gray");
    public static final Item FABRIC_BOLT_GRAY           = registerItem("fabric_bolt_gray");
    public static final Item FABRIC_BOLT_PINK           = registerItem("fabric_bolt_pink");
    public static final Item FABRIC_BOLT_LIME           = registerItem("fabric_bolt_lime");
    public static final Item FABRIC_BOLT_YELLOW         = registerItem("fabric_bolt_yellow");
    public static final Item FABRIC_BOLT_LIGHT_BLUE     = registerItem("fabric_bolt_light_blue");
    public static final Item FABRIC_BOLT_MAGENTA        = registerItem("fabric_bolt_magenta");
    public static final Item FABRIC_BOLT_ORANGE         = registerItem("fabric_bolt_orange");
    public static final Item GLUT_CHARM                 = registerItem("glut_charm");
    public static final Item IRON_PLATE                 = registerItem("iron_plate");
    public static final Item IRON_SCALE                 = registerItem("iron_scale");

    // Upgrades
    public static final Item UPGRADE_CORE               = registerItem("upgrade_core");
    public static final Item UPGRADE_CORE_ARCHER        = registerItem("upgrade_core_archer");
    public static final Item UPGRADE_CORE_DEPTH         = registerItem("upgrade_core_depth");
    public static final Item UPGRADE_CORE_EMBER         = registerItem("upgrade_core_ember");
    public static final Item UPGRADE_CORE_FOX           = registerItem("upgrade_core_fox");
    public static final Item UPGRADE_CORE_FROST         = registerItem("upgrade_core_frost");
    public static final Item UPGRADE_CORE_GLUT          = registerItem("upgrade_core_glut");
    public static final Item UPGRADE_CORE_GOLDEN        = registerItem("upgrade_core_golden");
    public static final Item UPGRADE_CORE_HIGHLAND      = registerItem("upgrade_core_highland");
    public static final Item UPGRADE_CORE_HIVE          = registerItem("upgrade_core_hive");
    public static final Item UPGRADE_CORE_MAGICKED      = registerItem("upgrade_core_magicked");
    public static final Item UPGRADE_CORE_METAL         = registerItem("upgrade_core_metal");
    public static final Item UPGRADE_CORE_OCEANIC       = registerItem("upgrade_core_oceanic");
    public static final Item UPGRADE_CORE_SHADOWS       = registerItem("upgrade_core_shadows");
    public static final Item UPGRADE_CORE_SPIDER        = registerItem("upgrade_core_spider");
    public static final Item UPGRADE_CORE_SOUL          = registerItem("upgrade_core_soul");
    public static final Item UPGRADE_CORE_VERDANT       = registerItem("upgrade_core_verdant");
    public static final Item UPGRADE_CORE_VOID          = registerItem("upgrade_core_void");
    public static final Item UPGRADE_CORE_WITHER        = registerItem("upgrade_core_wither");
    public static final Item UPGRADE_CORE_WOLF          = registerItem("upgrade_core_wolf");

    // Gemstones
    public static final Item GEMSTONE_BLUE              = registerItem("gemstone_blue");
    public static final Item GEMSTONE_GREEN             = registerItem("gemstone_green");
    public static final Item GEMSTONE_PURPLE            = registerItem("gemstone_purple");
    public static final Item GEMSTONE_RED               = registerItem("gemstone_red");
    public static final Item GEMSTONE_WHITE             = registerItem("gemstone_white");

    protected static Item registerItem(String id) {
        return registerItem(id, new Item(new Item.Settings().group(ItemGroup.MISC)));
    }

    protected static Item registerItem(String id, Item newItem) {
        return Registry.register(Registry.ITEM, Mcda.ID(id), newItem);
    }

    public static void init() {
    }
}