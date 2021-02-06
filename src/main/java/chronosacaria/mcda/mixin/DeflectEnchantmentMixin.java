
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.EnchantHelper;
import chronosacaria.mcda.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class DeflectEnchantmentMixin {

    @Inject(method = "onEntityHit", at = @At("HEAD"))
    public void onDeflectCollision(EntityHitResult entityHitResult, CallbackInfo ci){
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        if (!EnchantHelper.shooterIsLiving(persistentProjectileEntity)) return;
        LivingEntity victim = (LivingEntity) entityHitResult.getEntity();
        if (EnchantHelper.hasEnchantment(victim, EnchantsRegistry.DEFLECT)){
            int deflectLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.DEFLECT, victim);
            double originalDamage = persistentProjectileEntity.getDamage();
            double deflectChance;
            deflectChance = deflectLevel * 0.2F;
            float deflectRand = victim.getRandom().nextFloat();
            if (deflectRand <= deflectChance){
                persistentProjectileEntity.setDamage(originalDamage * 0.5D);
                persistentProjectileEntity.yaw += 180.0F;
                persistentProjectileEntity.prevYaw += 180.0F;
            }
        }
    }

}