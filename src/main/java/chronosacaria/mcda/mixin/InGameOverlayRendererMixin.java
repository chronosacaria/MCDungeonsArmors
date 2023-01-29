package chronosacaria.mcda.mixin;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.mcda.effects.ArmorEffectID.FIRE_RESISTANCE;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void mcda$renderFireOverlayOverride(MinecraftClient minecraftClient, MatrixStack matrixStack,
                                                 CallbackInfo ci) {
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(FIRE_RESISTANCE))
            return;

        PlayerEntity playerEntity = MinecraftClient.getInstance().player;

        if (playerEntity.isAlive()) {
            if (CleanlinessHelper.hasArmorSet(playerEntity, ArmorSets.SPROUT)
                    || CleanlinessHelper.hasArmorSet(playerEntity, ArmorSets.LIVING_VINES)
                    || (ArmorEffects.ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == FIRE_RESISTANCE)
                    || (ArmorEffects.RED_ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == FIRE_RESISTANCE)) {
                ci.cancel();
            }
        }
    }
}