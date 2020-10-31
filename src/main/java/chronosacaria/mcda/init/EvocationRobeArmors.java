package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.BattleRobeArmorItem;
import chronosacaria.mcda.items.armor.EvocationRobeArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class EvocationRobeArmors {
    public static Item EVOCATION_ROBE_HELMET;
    public static Item EVOCATION_ROBE_CHESTPLATE;
    public static Item EVOCATION_ROBE_LEGGINGS;

    public static Item EMBER_ROBE_HELMET;
    public static Item EMBER_ROBE_CHESTPLATE;
    public static Item EMBER_ROBE_LEGGINGS;

    public static Item VERDANT_ROBE_HELMET;
    public static Item VERDANT_ROBE_CHESTPLATE;
    public static Item VERDANT_ROBE_LEGGINGS;

    public static void init(){
        EVOCATION_ROBE_HELMET = new EvocationRobeArmorItem(ArmorBases.EVOCATION, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, false, "evocation_robe_helmet");
        EVOCATION_ROBE_CHESTPLATE = new EvocationRobeArmorItem(ArmorBases.EVOCATION, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false,false,"evocation_robe_chestplate");
        EVOCATION_ROBE_LEGGINGS = new EvocationRobeArmorItem(ArmorBases.EVOCATION, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false,false, "evocation_robe_leggings");

        EMBER_ROBE_HELMET = new EvocationRobeArmorItem(ArmorBases.EMBER, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true,false, "ember_robe_helmet");
        EMBER_ROBE_CHESTPLATE = new EvocationRobeArmorItem(ArmorBases.EMBER, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true,false, "ember_robe_chestplate");
        EMBER_ROBE_LEGGINGS = new EvocationRobeArmorItem(ArmorBases.EMBER, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true,false, "ember_robe_leggings");

        VERDANT_ROBE_HELMET = new EvocationRobeArmorItem(ArmorBases.VERDANT, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, true, "verdant_robe_helmet");
        VERDANT_ROBE_CHESTPLATE = new EvocationRobeArmorItem(ArmorBases.VERDANT, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, true, "verdant_robe_chestplate");
        VERDANT_ROBE_LEGGINGS = new EvocationRobeArmorItem(ArmorBases.VERDANT, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, true, "verdant_robe_leggings");
    }

}
