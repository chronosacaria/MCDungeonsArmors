package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.items.ItemID;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.EnumMap;
import java.util.Locale;

public class ItemsRegistry {
    // use ItemID's asItem() as a shorthand for ItemsRegistry.items.get(ItemID)
    public static final EnumMap<ItemID, Item> items = new EnumMap<>(ItemID.class);

    protected static void registerItem(ItemID itemID) {
        Item item = new Item(new Item.Settings());
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(item));
        items.put(itemID, item);
        Registry.register(Registries.ITEM, Mcda.ID(itemID.toString().toLowerCase(Locale.ROOT)), item);
    }

    public static void init() {
        for (ItemID itemID : ItemID.values()) {
            registerItem(itemID);
        }
    }
}