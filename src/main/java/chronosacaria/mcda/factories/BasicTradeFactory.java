package chronosacaria.mcda.factories;

import net.minecraft.entity.Entity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.Random;

public class BasicTradeFactory implements TradeOffers.Factory {
    private final TradeOffer offer;

    public BasicTradeFactory(TradeOffer offer){
        this.offer = offer;
    }

    @Override
    public TradeOffer create(Entity entity, Random random){
        return new TradeOffer(this.offer.toTag());
    }
}
