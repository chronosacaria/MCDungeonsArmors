
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.PIGLIN_FOOLING;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {

    @Inject(method = "wearsGoldArmor", at = @At(value = "RETURN"), cancellable = true)
    private static void onPiglinSelectPlayerToAttack(LivingEntity entity, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(PIGLIN_FOOLING))
            return;
        if (entity instanceof PlayerEntity) {
            if (CleanlinessHelper.hasArmorSet((LivingEntity) entity, ArmorSets.GOLDEN_PIGLIN)){
                cir.setReturnValue(true);
            }
        }
    }
}

