package chronosacaria.mcda.mixin;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.mcda.effects.ArmorEffectID.WEB_WALKING;

@Mixin(CobwebBlock.class)
public class CobwebBlockMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    public void mcda$canWalkThroughCobwebs(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci){
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(WEB_WALKING))
            return;

        if (!(entity instanceof LivingEntity livingEntity))
            return;

        if (CleanlinessHelper.hasArmorSet(livingEntity, ArmorSets.SPIDER)
                || (ArmorEffects.ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(livingEntity, ArmorSets.MYSTERY)) == WEB_WALKING)
                || (ArmorEffects.PURPLE_ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(livingEntity, ArmorSets.PURPLE_MYSTERY)) == WEB_WALKING)){
            ci.cancel();
        }
    }
}
