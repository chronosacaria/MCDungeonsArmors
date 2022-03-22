package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.items.ItemID;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;
import java.util.Locale;

public class ItemsRegistry {
    // use ItemID's asItem() as a shorthand for ItemsRegistry.items.get(ItemID)
    public static final EnumMap<ItemID, Item> items = new EnumMap<>(ItemID.class);

    protected static void registerItem(ItemID itemID) {
        Item item = new Item(new Item.Settings().group(ItemGroup.MISC));
        items.put(itemID, item);
        Registry.register(Registry.ITEM, Mcda.ID(itemID.toString().toLowerCase(Locale.ROOT)), item);
    }

    public static void init() {
        for (ItemID itemID : ItemID.values()) {
            registerItem(itemID);
        }
    }
}
