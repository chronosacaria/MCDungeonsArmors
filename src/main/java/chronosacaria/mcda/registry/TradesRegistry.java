package chronosacaria.mcda.registry;

import chronosacaria.mcda.factories.BasicTradeFactory;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.ItemID;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.village.VillagerProfession;

import java.util.List;

public class TradesRegistry {

    public static void registerVillagerOffers() {
        for (EquipmentSlot equipmentSlot : List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)) {
            BasicTradeFactory.registerVillagerTrade(VillagerProfession.ARMORER, 5,
                    Items.EMERALD, 64,
                    ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(equipmentSlot), 1,
                    1, 30, 0.2F);
        }
    }

    public static void registerWanderingTrades(){
        for (EquipmentSlot equipmentSlot : List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)) {
            BasicTradeFactory.registerWanderingTrade(
                    Items.EMERALD, 64,
                    ArmorsRegistry.armorItems.get(ArmorSets.ENTERTAINER).get(equipmentSlot), 1,
                    1, 2, 0.0F);
        }
        BasicTradeFactory.registerWanderingTrade(
                Items.EMERALD, 16,
                ItemsRegistry.items.get(ItemID.FOX_PELT), 1,
                1, 2, 0.0F);
        for (ItemID itemID : List.of(ItemID.OCELOT_PELT, ItemID.WOLF_PELT, ItemID.GOAT_PELT)) {
            BasicTradeFactory.registerWanderingTrade(
                    Items.EMERALD, 16,
                    ItemsRegistry.items.get(itemID), 1,
                    4, 2, 0.0F);
        }
        for (ItemID itemID : List.of(ItemID.FOX_PELT_ARCTIC, ItemID.OCELOT_PELT_BLACK, ItemID.WOLF_PELT_BLACK)) {
            BasicTradeFactory.registerWanderingTrade(
                    Items.EMERALD, 24,
                    ItemsRegistry.items.get(itemID), 1,
                    1, 2, 0.0F);
        }
    }
}
