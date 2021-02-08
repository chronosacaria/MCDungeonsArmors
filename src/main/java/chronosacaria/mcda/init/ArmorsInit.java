package chronosacaria.mcda.init;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.items.ArmorSetItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.*;

import static net.minecraft.entity.EquipmentSlot.*;

public class ArmorsInit {
    // (set, slot) -> item
    public static EnumMap<ArmorBases, EnumMap<EquipmentSlot, Item>> armorItems = new EnumMap<>(ArmorBases.class);

    protected static String armorID(ArmorBases set, EquipmentSlot slot) {
        String slotId;
        switch (slot) {
            case HEAD:
                slotId = "helmet";
                break;
            case CHEST:
                slotId = "chestplate";
                break;
            case LEGS:
                slotId = "leggings";
                break;
            case FEET:
                slotId = "boots";
                break;
            default:
                throw new IllegalArgumentException("armor with non-armor equipment slot");
        }

        return set.getSetName() + "_" + slotId;
    }

    protected static void registerArmor(ArmorBases set, EnumSet<EquipmentSlot> slots) {
        EnumMap<EquipmentSlot, Item> slotMap = new EnumMap<>(EquipmentSlot.class);

        for (EquipmentSlot slot : slots) {
            ArmorSetItem item = new ArmorSetItem(set, slot);
            slotMap.put(slot, item);
            Registry.register(Registry.ITEM, Mcda.ID(armorID(set, slot)), item);
        }

        armorItems.put(set, slotMap);
    }

    public static void init() {
        registerArmor(ArmorBases.BATTLE,   EnumSet.of(CHEST, LEGS));
        registerArmor(ArmorBases.SPLENDID, EnumSet.of(CHEST, LEGS));

        registerArmor(ArmorBases.BEENEST, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.BEEHIVE, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.CHAMPION, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.HERO,     EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.DARK,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.TITAN, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        // registerArmor(ArmorSets.ROYAL, EnumSet.of(HEAD, CHEST, LEGS, FEET)); // TODO

        registerArmor(ArmorBases.EVOCATION, EnumSet.of(HEAD, CHEST, LEGS));
        registerArmor(ArmorBases.EMBER,     EnumSet.of(HEAD, CHEST, LEGS));
        registerArmor(ArmorBases.VERDANT,   EnumSet.of(HEAD, CHEST, LEGS));

        registerArmor(ArmorBases.GHOSTLY,       EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.GHOST_KINDLER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.GRIM,   EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.WITHER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.GUARDS,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.CURIOUS, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.HUNTER, EnumSet.of(CHEST));

        registerArmor(ArmorBases.ARCHER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.MERCENARY,     EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.RENEGADE,      EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.HUNGRY_HORROR, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.MYSTERY,        EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.BLUE_MYSTERY,   EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.GREEN_MYSTERY,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.PURPLE_MYSTERY, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.RED_MYSTERY,    EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.OCELOT,        EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.SHADOW_WALKER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.PHANTOM,    EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.FROST_BITE, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.PLATE,      EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.FULL_METAL, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.REINFORCED_MAIL, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.STALWART_MAIL,   EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.SCALE_MAIL, EnumSet.of(CHEST, LEGS, FEET));
        registerArmor(ArmorBases.HIGHLAND,   EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.SNOW,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.FROST, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.SOUL_ROBE,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.SOULDANCER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.SPELUNKER,    EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.CAVE_CRAWLER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.THIEF,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.SPIDER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.WOLF,       EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.BLACK_WOLF, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.FOX,        EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.ARCTIC_FOX, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.EMERALD, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.OPULENT, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.GILDED,  EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorBases.CLIMBING_GEAR,        EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorBases.RUGGED_CLIMBING_GEAR, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        // registerArmor(ArmorSets.GOAT,                 EnumSet.of(HEAD, CHEST, LEGS, FEET)); // TODO
    }


}
