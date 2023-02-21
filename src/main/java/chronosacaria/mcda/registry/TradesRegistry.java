package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.factories.BasicTradeFactory;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.village.VillagerProfession;

import java.util.List;

public class TradesRegistry {

    public static void registerVillagerOffers() {
        for (EquipmentSlot equipmentSlot : List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)) {
            if (Mcda.CONFIG.mcdaEnableArmorsConfig.ARMORS_SETS_ENABLED.get(ArmorSets.CHAMPION)) {
                BasicTradeFactory.registerVillagerTrade(VillagerProfession.ARMORER, 5,
                        Items.EMERALD, 64,
                        ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(equipmentSlot), 1,
                        1, 30, 0.2F);
            }
        }
    }

    public static void registerWanderingTrades(){
        for (EquipmentSlot equipmentSlot : List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)) {
            if (Mcda.CONFIG.mcdaEnableArmorsConfig.ARMORS_SETS_ENABLED.get(ArmorSets.ENTERTAINER)) {
                BasicTradeFactory.registerWanderingTrade(
                        Items.EMERALD, 64,
                        ArmorsRegistry.armorItems.get(ArmorSets.ENTERTAINER).get(equipmentSlot), 1,
                        1, 2, 0.0F);
            }
        }
        BasicTradeFactory.registerWanderingTrade(
                Items.EMERALD, 16,
                ItemsRegistry.FOX_PELT, 1,
                1, 2, 0.0F);
        for (Item item : List.of(ItemsRegistry.OCELOT_PELT, ItemsRegistry.WOLF_PELT, ItemsRegistry.GOAT_PELT)) {
            BasicTradeFactory.registerWanderingTrade(
                    Items.EMERALD, 16,
                    item, 1,
                    4, 2, 0.0F);
        }
        for (Item item : List.of(ItemsRegistry.FOX_PELT_ARCTIC, ItemsRegistry.OCELOT_PELT_BLACK, ItemsRegistry.WOLF_PELT_BLACK)) {
            BasicTradeFactory.registerWanderingTrade(
                    Items.EMERALD, 24,
                    item, 1,
                    1, 2, 0.0F);
        }
    }
}
