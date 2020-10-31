package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.armor.BattleRobeArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BattleRobeArmors {
    public static Item BATTLE_ROBE_CHESTPLATE;
    public static Item BATTLE_ROBE_LEGGINGS;

    public static Item SPLENDID_ROBE_CHESTPLATE;
    public static Item SPLENDID_ROBE_LEGGINGS;

    public static void init(){
        BATTLE_ROBE_CHESTPLATE = new BattleRobeArmorItem(ArmorBases.BATTLE, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), false, "battle_robe_chestplate");
        BATTLE_ROBE_LEGGINGS = new BattleRobeArmorItem(ArmorBases.BATTLE, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), false, "battle_robe_leggings");

        SPLENDID_ROBE_CHESTPLATE = new BattleRobeArmorItem(ArmorBases.SPLENDID, EquipmentSlot.CHEST,
                new Item.Settings().group(Mcda.ARMORS), true, "splendid_robe_chestplate");
        SPLENDID_ROBE_LEGGINGS = new BattleRobeArmorItem(ArmorBases.SPLENDID, EquipmentSlot.LEGS,
                new Item.Settings().group(Mcda.ARMORS), true, "splendid_robe_leggings");
    }

}
