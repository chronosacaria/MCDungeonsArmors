package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.PlateArmorItem;
import chronosacaria.mcda.items.armor.ReinforcedMailItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class ReinforcedMailArmors {
    public static Item REINFORCED_MAIL_HELMET;
    public static Item REINFORCED_MAIL_CHESTPLATE;
    public static Item REINFORCED_MAIL_LEGGINGS;
    public static Item REINFORCED_MAIL_BOOTS;

    public static Item STALWART_MAIL_HELMET;
    public static Item STALWART_MAIL_CHESTPLATE;
    public static Item STALWART_MAIL_LEGGINGS;
    public static Item STALWART_MAIL_BOOTS;

    public static void init(){
        REINFORCED_MAIL_HELMET = new ReinforcedMailItem(ArmorBases.REINFORCED_MAIL, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, false, "reinforced_mail_helmet");
        REINFORCED_MAIL_CHESTPLATE = new ReinforcedMailItem(ArmorBases.REINFORCED_MAIL, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, false,"reinforced_mail_chestplate");
        REINFORCED_MAIL_LEGGINGS = new ReinforcedMailItem(ArmorBases.REINFORCED_MAIL, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, false, "reinforced_mail_leggings");
        REINFORCED_MAIL_BOOTS = new ReinforcedMailItem(ArmorBases.REINFORCED_MAIL, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, false, "reinforced_mail_boots");

        STALWART_MAIL_HELMET = new ReinforcedMailItem(ArmorBases.STALWART_MAIL, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, true, "stalwart_armor_helmet");
        STALWART_MAIL_CHESTPLATE = new ReinforcedMailItem(ArmorBases.STALWART_MAIL, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, true,"stalwart_armor_chestplate");
        STALWART_MAIL_LEGGINGS = new ReinforcedMailItem(ArmorBases.STALWART_MAIL, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, true, "stalwart_armor_leggings");
        STALWART_MAIL_BOOTS = new ReinforcedMailItem(ArmorBases.STALWART_MAIL, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, true, "stalwart_armor_boots");

    }

}
