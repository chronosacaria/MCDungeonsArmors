package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.ChampionsArmorItem;
import chronosacaria.mcda.items.armor.OcelotArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class OcelotArmors {
    public static Item SHADOW_WALKER_HELMET;
    public static Item SHADOW_WALKER_CHESTPLATE;
    public static Item SHADOW_WALKER_LEGGINGS;
    public static Item SHADOW_WALKER_BOOTS;

    public static Item OCELOT_HELMET;
    public static Item OCELOT_CHESTPLATE;
    public static Item OCELOT_LEGGINGS;
    public static Item OCELOT_BOOTS;

    public static void init(){
        OCELOT_HELMET = new OcelotArmorItem(ArmorBases.HERO, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, "ocelot_armor_helmet");
        OCELOT_CHESTPLATE = new OcelotArmorItem(ArmorBases.HERO, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, "ocelot_armor_chestplate");
        OCELOT_LEGGINGS = new OcelotArmorItem(ArmorBases.HERO, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, "ocelot_armor_leggings");
        OCELOT_BOOTS = new OcelotArmorItem(ArmorBases.HERO, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, "ocelot_armor_boots");

        SHADOW_WALKER_HELMET = new OcelotArmorItem(ArmorBases.CHAMPION, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, "shadow_walker_armor_helmet");
        SHADOW_WALKER_CHESTPLATE = new OcelotArmorItem(ArmorBases.CHAMPION, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, "shadow_walker_armor_chestplate");
        SHADOW_WALKER_LEGGINGS = new OcelotArmorItem(ArmorBases.CHAMPION, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, "shadow_walker_armor_leggings");
        SHADOW_WALKER_BOOTS = new OcelotArmorItem(ArmorBases.CHAMPION, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, "shadow_walker_armor_boots");
    }

}
