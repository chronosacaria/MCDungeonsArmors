package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.DarkArmorItem;
import chronosacaria.mcda.items.armor.GrimArmorItem;
import chronosacaria.mcda.items.armor.GuardsArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class GuardsArmors {
    public static Item GUARDS_HELMET;
    public static Item GUARDS_CHESTPLATE;
    public static Item GUARDS_LEGGINGS;
    public static Item GUARDS_BOOTS;

    public static Item CURIOUS_HELMET;
    public static Item CURIOUS_CHESTPLATE;
    public static Item CURIOUS_LEGGINGS;
    public static Item CURIOUS_BOOTS;

    public static void init(){
        GUARDS_HELMET = new GuardsArmorItem(ArmorBases.GUARDS, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, "guards_armor_helmet");
        GUARDS_CHESTPLATE = new GuardsArmorItem(ArmorBases.GUARDS, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, "guards_armor_chestplate");
        GUARDS_LEGGINGS = new GuardsArmorItem(ArmorBases.GUARDS, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, "guards_armor_leggings");
        GUARDS_BOOTS = new GuardsArmorItem(ArmorBases.GUARDS, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, "guards_armor_boots");

        CURIOUS_HELMET = new GuardsArmorItem(ArmorBases.CURIOUS, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, "curious_armor_helmet");
        CURIOUS_CHESTPLATE = new GuardsArmorItem(ArmorBases.CURIOUS, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, "curious_armor_chestplate");
        CURIOUS_LEGGINGS = new GuardsArmorItem(ArmorBases.CURIOUS, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, "curious_armor_leggings");
        CURIOUS_BOOTS = new GuardsArmorItem(ArmorBases.CURIOUS, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, "curious_armor_boots");
    }

}
