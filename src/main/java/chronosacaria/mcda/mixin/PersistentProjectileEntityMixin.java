
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.api.ProjectileEffectHelper;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.ARCHERS_PROWESS;
import static chronosacaria.mcda.enchants.EnchantID.*;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    public void onDeflectHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        if (!config.enableEnchantment.get(DEFLECT))
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

    @Inject(method = "onEntityHit", at = @At("HEAD"))
    public void onArchersProwessHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        if (!config.enableArmorEffect.get(ARCHERS_PROWESS))
            return;

        Random random = new Random();

        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        if (!(persistentProjectileEntity.getOwner() instanceof LivingEntity shooter)) return;
        if (!((entityHitResult.getEntity()) instanceof LivingEntity target)) return;

        if (CleanlinessHelper.hasArmorSet((LivingEntity) persistentProjectileEntity.getOwner(), ArmorSets.ARCHER)) {

            double randArrowMult = (random.nextInt(4) + 4);
            double modifiedArrowDamage = (double)(2.0F) * (2 + randArrowMult);
            modifiedArrowDamage -= randArrowMult;

            target.damage(DamageSource.arrow(persistentProjectileEntity, shooter), (float) modifiedArrowDamage);
            persistentProjectileEntity.kill();
        }
    }
}