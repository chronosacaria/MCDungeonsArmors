package chronosacaria.mcda.registry;

import chronosacaria.mcda.factories.BasicTradeFactory;
import chronosacaria.mcda.items.ArmorSets;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import static net.minecraft.village.TradeOffers.PROFESSION_TO_LEVELED_TRADE;

public class TradesRegistry {

    public static void registerVillagerOffers() {

        addTrade(VillagerProfession.ARMORER, 5,
                new TradeOffers.SellItemFactory(new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.HEAD)), 64, 1, 1, 30, 0.2F));
        addTrade(VillagerProfession.ARMORER, 5,
                new TradeOffers.SellItemFactory(new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.CHEST)), 64, 1, 1, 30, 0.2F));
        addTrade(VillagerProfession.ARMORER, 5,
                new TradeOffers.SellItemFactory(new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.LEGS)), 64, 1, 1, 30, 0.2F));
        addTrade(VillagerProfession.ARMORER, 5,
                new TradeOffers.SellItemFactory(new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.FEET)), 64, 1, 1, 30, 0.2F));

    }

    public static void registerWanderingTrades(){
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 64),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.ENTERTAINER).get(EquipmentSlot.HEAD),
                                1), 1, 2, 0.0F))));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 64),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.ENTERTAINER).get(EquipmentSlot.CHEST),
                                1), 1, 2, 0.0F))));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 64),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.ENTERTAINER).get(EquipmentSlot.LEGS),
                                1), 1, 2, 0.0F))));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 64),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.ENTERTAINER).get(EquipmentSlot.FEET),
                                1), 1, 2, 0.0F))));
    }

    public static void addTrade(VillagerProfession profession, int level, TradeOffers.Factory trade) {
        TradeOffers.Factory[] fixedTrades = PROFESSION_TO_LEVELED_TRADE.get(profession).get(level);
        int newSize = fixedTrades.length + 1;

        TradeOffers.Factory[] newTrades = new TradeOffers.Factory[newSize];
        System.arraycopy(fixedTrades, 0, newTrades, 0, fixedTrades.length);
        newTrades[newSize - 1] = trade;

        PROFESSION_TO_LEVELED_TRADE.get(profession).put(level, newTrades);
    }

}
