package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.items.ArmorSetItem;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;
import java.util.EnumSet;

import static net.minecraft.entity.EquipmentSlot.*;

public class ArmorsRegistry {
    // (set, slot) -> item
    public static final EnumMap<ArmorSets, EnumMap<EquipmentSlot, Item>> armorItems = new EnumMap<>(ArmorSets.class);

    protected static String armorID(ArmorSets set, EquipmentSlot slot) {
        String slotID = switch (slot) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> throw new IllegalArgumentException("armor with non-armor equipment slot");
        };

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
            if (!Mcda.CONFIG.mcdaEnableArmorsConfig.ARMORS_SETS_ENABLED.get(set))
                continue;

            EnumSet<EquipmentSlot> slots = switch (set) {
                case BATTLE, SPLENDID -> EnumSet.of(CHEST, LEGS);
                case EVOCATION, EMBER, VERDANT, VANGUARD -> EnumSet.of(HEAD, CHEST, LEGS);
                case SCALE_MAIL -> EnumSet.of(CHEST, LEGS, FEET);
                case HUNTER -> EnumSet.of(CHEST);
                default -> EnumSet.of(HEAD, CHEST, LEGS, FEET);
            };

            registerArmor(set, slots);
        }
    }
}
