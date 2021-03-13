package chronosacaria.mcda.registry;

import chronosacaria.mcda.factories.BasicTradeFactory;
import chronosacaria.mcda.items.ArmorSets;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class TradesRegistry {

    public static void registerVillagerOffers() {

        // Master Armourer Trades for Champion Armour Set
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5, factories -> factories.add(new BasicTradeFactory(
                new TradeOffer(new ItemStack(Items.EMERALD_BLOCK, 10),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.HEAD)), 1, 30, 0.2F))));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5, factories -> factories.add(new BasicTradeFactory(
                new TradeOffer(new ItemStack(Items.EMERALD_BLOCK, 10),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.CHEST)), 1,30, 0.2F))));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5, factories -> factories.add(new BasicTradeFactory(
                new TradeOffer(new ItemStack(Items.EMERALD_BLOCK, 10),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.LEGS)), 1, 30, 0.2F))));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5, factories -> factories.add(new BasicTradeFactory(
                new TradeOffer(new ItemStack(Items.EMERALD_BLOCK, 10),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.FEET)), 1, 30, 0.2F))));
    }
}
