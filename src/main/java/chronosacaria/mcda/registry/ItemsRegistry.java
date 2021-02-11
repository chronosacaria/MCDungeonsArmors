package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.items.ItemID;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;

public class ItemsRegistry {

    public static EnumMap<ItemID, Item> items = new EnumMap<>(ItemID.class);

    protected static Item registerItem(ItemID itemID) {
        Item item = new Item(new Item.Settings().group(ItemGroup.MISC));
        items.put(itemID, item);
        return Registry.register(Registry.ITEM, Mcda.ID(itemID.toString().toLowerCase()), item);
    }

    public static void init() {
        for (ItemID itemID : ItemID.values()) {
            registerItem(itemID);
        }
    }
}
