package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.items.armor.HuntersArmorItem;
import chronosacaria.mcda.bases.ArmorBases;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class HuntersArmors {
    public static Item HUNTERS_CHESTPLATE;
    public static Item ARCHERS_HELMET;
    public static Item ARCHERS_CHESTPLATE;
    public static Item ARCHERS_LEGGINGS;
    public static Item ARCHERS_BOOTS;

    public static void init(){

        HUNTERS_CHESTPLATE = new HuntersArmorItem(ArmorBases.HUNTER, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, "hunters_chestplate");

        ARCHERS_HELMET = new HuntersArmorItem(ArmorBases.ARCHER, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, "archers_helmet");
        ARCHERS_CHESTPLATE = new HuntersArmorItem(ArmorBases.ARCHER, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, "archers_chestplate");
        ARCHERS_LEGGINGS = new HuntersArmorItem(ArmorBases.ARCHER, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, "archers_leggings");
        ARCHERS_BOOTS = new HuntersArmorItem(ArmorBases.ARCHER, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, "archers_boots");
    }
}
