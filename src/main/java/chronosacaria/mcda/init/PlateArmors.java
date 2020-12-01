package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.MercenaryArmorItem;
import chronosacaria.mcda.items.armor.PlateArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class PlateArmors {
    public static Item PLATE_HELMET;
    public static Item PLATE_CHESTPLATE;
    public static Item PLATE_LEGGINGS;
    public static Item PLATE_BOOTS;

    public static Item FULL_METAL_HELMET;
    public static Item FULL_METAL_CHESTPLATE;
    public static Item FULL_METAL_LEGGINGS;
    public static Item FULL_METAL_BOOTS;

    public static void init(){
        PLATE_HELMET = new PlateArmorItem(ArmorBases.PLATE, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, false, "plate_armor_helmet");
        PLATE_CHESTPLATE = new PlateArmorItem(ArmorBases.PLATE, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, false,"plate_armor_chestplate");
        PLATE_LEGGINGS = new PlateArmorItem(ArmorBases.PLATE, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, false, "plate_armor_leggings");
        PLATE_BOOTS = new PlateArmorItem(ArmorBases.PLATE, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, false, "plate_armor_boots");

        FULL_METAL_HELMET = new PlateArmorItem(ArmorBases.FULL_METAL, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, true, "full_metal_armor_helmet");
        FULL_METAL_CHESTPLATE = new PlateArmorItem(ArmorBases.FULL_METAL, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, true,"full_metal_armor_chestplate");
        FULL_METAL_LEGGINGS = new PlateArmorItem(ArmorBases.FULL_METAL, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, true, "full_metal_armor_leggings");
        FULL_METAL_BOOTS = new PlateArmorItem(ArmorBases.FULL_METAL, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, true, "full_metal_armor_boots");

    }

}
