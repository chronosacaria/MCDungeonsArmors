package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.DarkArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class DarkArmors {
    public static Item DARK_HELMET;
    public static Item DARK_CHESTPLATE;
    public static Item DARK_LEGGINGS;
    public static Item DARK_BOOTS;

    public static Item TITANS_HELMET;
    public static Item TITANS_CHESTPLATE;
    public static Item TITANS_LEGGINGS;
    public static Item TITANS_BOOTS;

    public static void init(){
        DARK_HELMET = new DarkArmorItem(ArmorBases.DARK, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, "dark_armor_helmet");
        DARK_CHESTPLATE = new DarkArmorItem(ArmorBases.DARK, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, "dark_armor_chestplate");
        DARK_LEGGINGS = new DarkArmorItem(ArmorBases.DARK, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, "dark_armor_leggings");
        DARK_BOOTS = new DarkArmorItem(ArmorBases.DARK, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, "dark_armor_boots");

        TITANS_HELMET = new DarkArmorItem(ArmorBases.TITAN, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, "titans_shroud_helmet");
        TITANS_CHESTPLATE = new DarkArmorItem(ArmorBases.TITAN, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, "titans_shroud_chestplate");
        TITANS_LEGGINGS = new DarkArmorItem(ArmorBases.TITAN, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, "titans_shroud_leggings");
        TITANS_BOOTS = new DarkArmorItem(ArmorBases.TITAN, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, "titans_shroud_boots");
    }

}
