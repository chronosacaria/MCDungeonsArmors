package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.config.McdaConfig;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.mcda.effects.ArmorEffectID.INVISIBILITY;
import static chronosacaria.mcda.effects.ArmorEffectID.WEB_WALKING;

@Mixin(CobwebBlock.class)
public class CobwebBlockMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    public void canWalkThroughCobwebs(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci){
        if (!McdaConfig.config.enableArmorEffect.get(WEB_WALKING))
            return;

        if (!(entity instanceof LivingEntity)) return;

        if (CleanlinessHelper.hasArmorSet((LivingEntity) entity, ArmorSets.SPIDER)
                || (ArmorEffects.ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect((PlayerEntity) entity)) == WEB_WALKING)){
            ci.cancel();
        }
    }
}
