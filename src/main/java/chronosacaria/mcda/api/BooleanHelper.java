package chronosacaria.mcda.api;

import chronosacaria.mcda.api.interfaces.IMcdaBooleans;
import net.minecraft.entity.player.PlayerEntity;

public class BooleanHelper {

    public static boolean isFireTrailEnabled(PlayerEntity playerEntity) {
        return ((IMcdaBooleans) playerEntity).isFireTrailEnabled();
    }

    public static void setFireTrailEnabled(PlayerEntity pe, boolean fireTrailEnabled) {
        ((IMcdaBooleans) pe).setFireTrailEnabled(fireTrailEnabled);
    }
}
