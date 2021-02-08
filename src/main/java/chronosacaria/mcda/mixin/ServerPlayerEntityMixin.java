
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.enchants.EnchantmentEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        ServerPlayerEntity playerEntity = (ServerPlayerEntity) (Object) this;
        if (!playerEntity.isAlive())
            return;

        EnchantmentEffects.applyCowardice(playerEntity);
        EnchantmentEffects.applyFrenzied(playerEntity);
    }
}
