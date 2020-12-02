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
    public static final Item IRON_PLATE = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item SKELETON_SKULL = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item WOLF_PELT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BLACK_WOLF_PELT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item FOX_PELT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item FROST_CRYSTAL = new Item(new Item.Settings().group(ItemGroup.MISC));
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




    public static void doRegister() {
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, "bone_rib_cage"), BONE_RIB_CAGE);
    }
}
