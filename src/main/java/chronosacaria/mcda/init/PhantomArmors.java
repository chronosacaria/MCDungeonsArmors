package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.OcelotArmorItem;
import chronosacaria.mcda.items.armor.PhantomArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public class PhantomArmors {
    public static Item FROST_BITE_HELMET;
    public static Item FROST_BITE_CHESTPLATE;
    public static Item FROST_BITE_LEGGINGS;
    public static Item FROST_BITE_BOOTS;

    public static Item PHANTOM_HELMET;
    public static Item PHANTOM_CHESTPLATE;
    public static Item PHANTOM_LEGGINGS;
    public static Item PHANTOM_BOOTS;

    public static void init(){
        PHANTOM_HELMET = new PhantomArmorItem(ArmorBases.PHANTOM, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), false, "phantom_armor_helmet");
        PHANTOM_CHESTPLATE = new PhantomArmorItem(ArmorBases.PHANTOM, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, "phantom_armor_chestplate");
        PHANTOM_LEGGINGS = new PhantomArmorItem(ArmorBases.PHANTOM, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, "phantom_armor_leggings");
        PHANTOM_BOOTS = new PhantomArmorItem(ArmorBases.PHANTOM, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), false, "phantom_armor_boots");

        FROST_BITE_HELMET = new PhantomArmorItem(ArmorBases.FROST_BITE, EquipmentSlot.HEAD,
                new Item.Settings().group(Mcda.ARMORS), true, "frost_bite_armor_helmet");
        FROST_BITE_CHESTPLATE = new PhantomArmorItem(ArmorBases.FROST_BITE, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, "frost_bite_armor_chestplate");
        FROST_BITE_LEGGINGS = new PhantomArmorItem(ArmorBases.FROST_BITE, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, "frost_bite_armor_leggings");
        FROST_BITE_BOOTS = new PhantomArmorItem(ArmorBases.FROST_BITE, EquipmentSlot.FEET,
                new Item.Settings().group(Mcda.ARMORS), true, "frost_bite_armor_boots");
    }

}
