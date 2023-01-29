package chronosacaria.mcda.mixin;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.mcda.effects.ArmorEffectID.SWEET_BERRY_BUSH_WALKING;

@Mixin(SweetBerryBushBlock.class)
public class SweetBerryBushBlockMixin {

    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    public void mcda$canWalkThroughSweetBerryBushes(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci){
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SWEET_BERRY_BUSH_WALKING))
            return;

        if (!(entity instanceof LivingEntity livingEntity))
            return;

        if (CleanlinessHelper.hasArmorSet(livingEntity, ArmorSets.FOX) || CleanlinessHelper.hasArmorSet(livingEntity, ArmorSets.ARCTIC_FOX)){
            ci.cancel();
        }
    }
}
