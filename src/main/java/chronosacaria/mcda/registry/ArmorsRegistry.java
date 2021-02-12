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
    public static final EnumMap<ArmorSets, EnumMap<EquipmentSlot, Item>> armorItems = new EnumMap<>(ArmorSets.class);

    protected static String armorID(ArmorSets set, EquipmentSlot slot) {
        String slotID;
        switch (slot) {
            case HEAD:
                slotID = "helmet";
                break;
            case CHEST:
                slotID = "chestplate";
                break;
            case LEGS:
                slotID = "leggings";
                break;
            case FEET:
                slotID = "boots";
                break;
            default:
                throw new IllegalArgumentException("armor with non-armor equipment slot");
        }

        return set.getSetName() + "_" + slotID;
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
        for (ArmorSets set : ArmorSets.values()) {
            EnumSet<EquipmentSlot> slots;

            switch (set) {
                case BATTLE:
                case SPLENDID:
                    slots = EnumSet.of(CHEST, LEGS);
                    break;
                case EVOCATION:
                case EMBER:
                case VERDANT:
                    slots = EnumSet.of(HEAD, CHEST, LEGS);
                    break;
                case SCALE_MAIL:
                    slots = EnumSet.of(CHEST, LEGS, FEET);
                    break;
                case HUNTER:
                    slots = EnumSet.of(CHEST);
                    break;
                case ROYAL: continue; // TODO: remove when implementing royal armor
                default:
                    slots = EnumSet.of(HEAD, CHEST, LEGS, FEET);
                    break;
            }

            registerArmor(set, slots);
        }
    }
}
