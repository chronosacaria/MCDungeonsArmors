package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.ArmorSetItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.*;

import static net.minecraft.entity.EquipmentSlot.*;

public class ArmorsRegistry {
    // (set, slot) -> item
    public static EnumMap<ArmorSets, EnumMap<EquipmentSlot, Item>> armorItems = new EnumMap<>(ArmorSets.class);

    protected static String armorID(ArmorSets set, EquipmentSlot slot) {
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

    protected static void registerArmor(ArmorSets set, EnumSet<EquipmentSlot> slots) {
        EnumMap<EquipmentSlot, Item> slotMap = new EnumMap<>(EquipmentSlot.class);

        for (EquipmentSlot slot : slots) {
            ArmorSetItem item = new ArmorSetItem(set, slot);
            slotMap.put(slot, item);
            Registry.register(Registry.ITEM, Mcda.ID(armorID(set, slot)), item);
        }

        armorItems.put(set, slotMap);
    }

    public static void init() {
        registerArmor(ArmorSets.BATTLE,   EnumSet.of(CHEST, LEGS));
        registerArmor(ArmorSets.SPLENDID, EnumSet.of(CHEST, LEGS));

        registerArmor(ArmorSets.BEENEST, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.BEEHIVE, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.CHAMPION, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.HERO,     EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.DARK,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.TITAN, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        // registerArmor(ArmorSets.ROYAL, EnumSet.of(HEAD, CHEST, LEGS, FEET)); // TODO

        registerArmor(ArmorSets.EVOCATION, EnumSet.of(HEAD, CHEST, LEGS));
        registerArmor(ArmorSets.EMBER,     EnumSet.of(HEAD, CHEST, LEGS));
        registerArmor(ArmorSets.VERDANT,   EnumSet.of(HEAD, CHEST, LEGS));

        registerArmor(ArmorSets.GHOSTLY,       EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.GHOST_KINDLER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.GRIM,   EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.WITHER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.GUARDS,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.CURIOUS, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.HUNTER, EnumSet.of(CHEST));

        registerArmor(ArmorSets.ARCHER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.MERCENARY,     EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.RENEGADE,      EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.HUNGRY_HORROR, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.MYSTERY,        EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.BLUE_MYSTERY,   EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.GREEN_MYSTERY,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.PURPLE_MYSTERY, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.RED_MYSTERY,    EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.OCELOT,        EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.SHADOW_WALKER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.PHANTOM,    EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.FROST_BITE, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.PLATE,      EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.FULL_METAL, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.REINFORCED_MAIL, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.STALWART_MAIL,   EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.SCALE_MAIL, EnumSet.of(CHEST, LEGS, FEET));
        registerArmor(ArmorSets.HIGHLAND,   EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.SNOW,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.FROST, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.SOUL_ROBE,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.SOULDANCER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.SPELUNKER,    EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.CAVE_CRAWLER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.THIEF,  EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.SPIDER, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.WOLF,       EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.BLACK_WOLF, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.FOX,        EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.ARCTIC_FOX, EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.EMERALD, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.OPULENT, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.GILDED,  EnumSet.of(HEAD, CHEST, LEGS, FEET));

        registerArmor(ArmorSets.CLIMBING_GEAR,        EnumSet.of(HEAD, CHEST, LEGS, FEET));
        registerArmor(ArmorSets.RUGGED_CLIMBING_GEAR, EnumSet.of(HEAD, CHEST, LEGS, FEET));
        // registerArmor(ArmorSets.GOAT,                 EnumSet.of(HEAD, CHEST, LEGS, FEET)); // TODO
    }


}
