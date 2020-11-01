package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.DarkArmorItem;
import chronosacaria.mcda.items.armor.GrimArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class GrimArmors {
    public static Item GRIM_HELMET;
    public static Item GRIM_CHESTPLATE;
    public static Item GRIM_LEGGINGS;
    public static Item GRIM_BOOTS;

    public static Item WITHER_HELMET;
    public static Item WITHER_CHESTPLATE;
    public static Item WITHER_LEGGINGS;
    public static Item WITHER_BOOTS;

    public static void init(){
        GRIM_HELMET = new GrimArmorItem(ArmorBases.GRIM, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, "grim_armor_helmet");
        GRIM_CHESTPLATE = new GrimArmorItem(ArmorBases.GRIM, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, "grim_armor_chestplate");
        GRIM_LEGGINGS = new GrimArmorItem(ArmorBases.GRIM, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, "grim_armor_leggings");
        GRIM_BOOTS = new GrimArmorItem(ArmorBases.GRIM, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, "grim_armor_boots");

        WITHER_HELMET = new GrimArmorItem(ArmorBases.WITHER, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, "wither_armor_helmet");
        WITHER_CHESTPLATE = new GrimArmorItem(ArmorBases.WITHER, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, "wither_armor_chestplate");
        WITHER_LEGGINGS = new GrimArmorItem(ArmorBases.WITHER, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, "wither_armor_leggings");
        WITHER_BOOTS = new GrimArmorItem(ArmorBases.WITHER, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, "wither_armor_boots");
    }

}
