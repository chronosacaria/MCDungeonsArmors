
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.ProjectileEffectHelper;
import chronosacaria.mcda.registries.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.mcda.enchants.EnchantID.DEFLECT;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    public void mcda$onDeflectHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(DEFLECT))
            return;

        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        if (!(persistentProjectileEntity.getOwner() instanceof LivingEntity)) return;
        if (!((entityHitResult.getEntity()) instanceof LivingEntity)) return;

        Entity victim = entityHitResult.getEntity();
        int deflectLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(DEFLECT), (LivingEntity) victim);
        if (deflectLevel == 0) return;

        double originalDamage = persistentProjectileEntity.getDamage();
        double deflectChance = deflectLevel * 0.2F;
        float deflectRand = ((LivingEntity)victim).getRandom().nextFloat();
        if (EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(DEFLECT), (LivingEntity) victim) > 0){
            if (deflectRand <= deflectChance) {
                persistentProjectileEntity.setDamage(originalDamage * 0.5D);
                if (ci.isCancellable()) {
                    ci.cancel();
                }
                ProjectileEffectHelper.ricochetArrowLikeShield(persistentProjectileEntity);
            }
        }
    }
}