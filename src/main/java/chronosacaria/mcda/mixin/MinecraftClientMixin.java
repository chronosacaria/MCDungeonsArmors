package chronosacaria.mcda.mixin;

import chronosacaria.mcda.client.McdaClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void mcdaHandleInputEvents(CallbackInfo ci){
        McdaClient.handleInputEvent();
    }
}
