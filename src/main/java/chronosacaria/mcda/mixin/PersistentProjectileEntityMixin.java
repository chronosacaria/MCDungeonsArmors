
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.enchants.EnchantmentEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    @Inject(method = "onEntityHit", at = @At("HEAD"))
    public void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        EnchantmentEffects.applyDeflect(entityHitResult, (PersistentProjectileEntity) (Object) this);
    }
}