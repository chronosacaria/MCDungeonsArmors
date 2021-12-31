package chronosacaria.mcda.registry;

import chronosacaria.mcda.factories.BasicTradeFactory;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.ItemID;
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
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 64),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.HEAD)), 1,
                        30, 0.2F))));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 64),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.CHEST)), 1,
                        30, 0.2F))));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 64),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.LEGS)), 1,
                        30, 0.2F))));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 64),
                        new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.CHAMPION).get(EquipmentSlot.FEET)), 1,
                        30, 0.2F))));
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
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 16),
                        new ItemStack(ItemsRegistry.items.get(ItemID.FOX_PELT),
                                1), 1, 2, 0.0F))));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 24),
                        new ItemStack(ItemsRegistry.items.get(ItemID.FOX_PELT_ARCTIC),
                                1), 1, 2, 0.0F))));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 16),
                        new ItemStack(ItemsRegistry.items.get(ItemID.OCELOT_PELT),
                                1), 1, 2, 0.0F))));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 24),
                        new ItemStack(ItemsRegistry.items.get(ItemID.OCELOT_PELT_BLACK),
                                1), 1, 2, 0.0F))));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 16),
                        new ItemStack(ItemsRegistry.items.get(ItemID.WOLF_PELT),
                                1), 1, 2, 0.0F))));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 24),
                        new ItemStack(ItemsRegistry.items.get(ItemID.WOLF_PELT_BLACK),
                                1), 1, 2, 0.0F))));
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(new BasicTradeFactory(new TradeOffer(new ItemStack(Items.EMERALD, 24),
                        new ItemStack(ItemsRegistry.items.get(ItemID.GOAT_PELT),
                                1), 1, 2, 0.0F))));
    }
}
