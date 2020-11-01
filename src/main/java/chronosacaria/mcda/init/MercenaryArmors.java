package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.EvocationRobeArmorItem;
import chronosacaria.mcda.items.armor.MercenaryArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class MercenaryArmors {
    public static Item MERCENARY_HELMET;
    public static Item MERCENARY_CHESTPLATE;
    public static Item MERCENARY_LEGGINGS;
    public static Item MERCENARY_BOOTS;

    public static Item RENEGADE_HELMET;
    public static Item RENEGADE_CHESTPLATE;
    public static Item RENEGADE_LEGGINGS;
    public static Item RENEGADE_BOOTS;

    public static Item HUNGRY_HORROR_HELMET;
    public static Item HUNGRY_HORROR_CHESTPLATE;
    public static Item HUNGRY_HORROR_LEGGINGS;
    public static Item HUNGRY_HORROR_BOOTS;

    public static void init(){
        MERCENARY_HELMET = new MercenaryArmorItem(ArmorBases.MERCENARY, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, false, false, "mercenary_armor_helmet");
        MERCENARY_CHESTPLATE = new MercenaryArmorItem(ArmorBases.MERCENARY, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, false, false,"mercenary_armor_chestplate");
        MERCENARY_LEGGINGS = new MercenaryArmorItem(ArmorBases.MERCENARY, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, false, false, "mercenary_armor_leggings");
        MERCENARY_BOOTS = new MercenaryArmorItem(ArmorBases.MERCENARY, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, false, false, "mercenary_armor_boots");

        RENEGADE_HELMET = new MercenaryArmorItem(ArmorBases.RENEGADE, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, true, false, "renegade_armor_helmet");
        RENEGADE_CHESTPLATE = new MercenaryArmorItem(ArmorBases.RENEGADE, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, true, false,"renegade_armor_chestplate");
        RENEGADE_LEGGINGS = new MercenaryArmorItem(ArmorBases.RENEGADE, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, true, false, "renegade_armor_leggings");
        RENEGADE_BOOTS = new MercenaryArmorItem(ArmorBases.RENEGADE, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, true, false, "renegade_armor_boots");

        HUNGRY_HORROR_HELMET = new MercenaryArmorItem(ArmorBases.HUNGRY_HORROR, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, false, true, "hungry_horror_helmet");
        HUNGRY_HORROR_CHESTPLATE = new MercenaryArmorItem(ArmorBases.HUNGRY_HORROR, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, false, true,"hungry_horror_chestplate");
        HUNGRY_HORROR_LEGGINGS = new MercenaryArmorItem(ArmorBases.HUNGRY_HORROR, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, false, true, "hungry_horror_leggings");
        HUNGRY_HORROR_BOOTS = new MercenaryArmorItem(ArmorBases.HUNGRY_HORROR, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, false, true,"hungry_horror_boots");
    }

}
