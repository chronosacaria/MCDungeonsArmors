
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static chronosacaria.mcda.effects.ArmorEffectID.PIGLIN_FOOLING;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {

    @Inject(method = "wearsGoldArmor", at = @At(value = "RETURN"), cancellable = true)
    private static void mcda$onPiglinSelectPlayerToAttack(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir){
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(PIGLIN_FOOLING))
            return;
        if (livingEntity instanceof PlayerEntity)
            if (CleanlinessHelper.checkFullArmor(livingEntity, ArmorSets.GOLDEN_PIGLIN))
                cir.setReturnValue(true);
    }
}

