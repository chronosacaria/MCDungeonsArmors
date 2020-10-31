package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.BattleRobeArmorItem;
import chronosacaria.mcda.items.armor.ChampionsArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class ChampionsArmors {
    public static Item CHAMPIONS_HELMET;
    public static Item CHAMPIONS_CHESTPLATE;
    public static Item CHAMPIONS_LEGGINGS;
    public static Item CHAMPIONS_BOOTS;

    public static Item HEROS_HELMET;
    public static Item HEROS_CHESTPLATE;
    public static Item HEROS_LEGGINGS;
    public static Item HEROS_BOOTS;

    public static void init(){
        HEROS_HELMET = new ChampionsArmorItem(ArmorBases.HERO, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, "heros_armor_helmet");
        HEROS_CHESTPLATE = new ChampionsArmorItem(ArmorBases.HERO, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, "heros_armor_chestplate");
        HEROS_LEGGINGS = new ChampionsArmorItem(ArmorBases.HERO, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, "heros_armor_leggings");
        HEROS_BOOTS = new ChampionsArmorItem(ArmorBases.HERO, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, "heros_armor_boots");

        CHAMPIONS_HELMET = new ChampionsArmorItem(ArmorBases.CHAMPION, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, "champions_armor_helmet");
        CHAMPIONS_CHESTPLATE = new ChampionsArmorItem(ArmorBases.CHAMPION, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, "champions_armor_chestplate");
        CHAMPIONS_LEGGINGS = new ChampionsArmorItem(ArmorBases.CHAMPION, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, "champions_armor_leggings");
        CHAMPIONS_BOOTS = new ChampionsArmorItem(ArmorBases.CHAMPION, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, "champions_armor_boots");
    }

}
