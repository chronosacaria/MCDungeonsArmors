package chronosacaria.mcda.registry;

import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
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

    public static void addTrade(VillagerProfession profession, int level, TradeOffers.Factory trade) {
        TradeOffers.Factory[] fixedTrades = PROFESSION_TO_LEVELED_TRADE.get(profession).get(level);
        int newSize = fixedTrades.length + 1;

        TradeOffers.Factory[] newTrades = new TradeOffers.Factory[newSize];
        System.arraycopy(fixedTrades, 0, newTrades, 0, fixedTrades.length);
        newTrades[newSize - 1] = trade;

        PROFESSION_TO_LEVELED_TRADE.get(profession).put(level, newTrades);
    }

}
