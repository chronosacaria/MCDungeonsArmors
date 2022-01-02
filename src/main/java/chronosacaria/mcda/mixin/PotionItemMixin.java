package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.PotionItem;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.TROUBADOURS_CHARISMA;

@Mixin(PotionItem.class)
public class PotionItemMixin {
    @Redirect(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;" +
            "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"))
    private boolean troubadoursCharisma(LivingEntity livingEntity, StatusEffectInstance instance) {
        int interceptedDuration = instance.getDuration();

        if (livingEntity instanceof ServerPlayerEntity && config.enableArmorEffect.get(TROUBADOURS_CHARISMA)) {
            if (CleanlinessHelper.hasArmorSet(livingEntity, ArmorSets.TROUBADOUR)) {

                // Positive Effect
                if (instance.getEffectType().getCategory().equals(StatusEffectCategory.BENEFICIAL)) {
                    // Positive Effect
                    interceptedDuration = instance.getDuration() * 2;
                } else if (instance.getEffectType().getCategory().equals(StatusEffectCategory.HARMFUL)){
                    // Negative Effect
                    interceptedDuration = instance.getDuration() / 2;
                }
            }

            return livingEntity.addStatusEffect(new StatusEffectInstance(
                    instance.getEffectType(),
                    interceptedDuration,
                    instance.getAmplifier(),
                    instance.isAmbient(),
                    instance.shouldShowParticles(),
                    instance.shouldShowIcon()
            ));
        } else {
            return livingEntity.addStatusEffect(instance);
        }
    }
}
