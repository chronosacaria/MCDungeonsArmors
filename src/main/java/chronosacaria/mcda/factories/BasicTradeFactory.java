package chronosacaria.mcda.factories;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

public class BasicTradeFactory implements TradeOffers.Factory {
    private final TradeOffer trade;

    public BasicTradeFactory(TradeOffer trade) {
        this.trade = trade;
    }

    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
        return new TradeOffer(this.trade.toNbt());
    }
}
