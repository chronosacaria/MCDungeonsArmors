package chronosacaria.mcda.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class AOEHelper {
    public static void healNearbyAllies(LivingEntity healer, StatusEffectInstance effectInstance, float distance){
        World world = healer.getEntityWorld();
        PlayerEntity playerEntity = healer instanceof PlayerEntity ? (PlayerEntity)healer : null;
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(healer.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canHealEntity(healer, nearbyEntity));
        for (LivingEntity nearbyEntity : nearbyEntities){
            if (nearbyEntity.getHealth() < nearbyEntity.getMaxHealth()){
                if (effectInstance.getEffectType().isInstant()){
                    effectInstance.getEffectType().applyInstantEffect(playerEntity, playerEntity, nearbyEntity,
                            effectInstance.getAmplifier(), 1.0D);
                } else {
                    nearbyEntity.addStatusEffect(new StatusEffectInstance(effectInstance));
                }
                ParticleEffect particle = ParticleTypes.HEART;

                double velX = 0;
                double velY = 1;
                double velZ = 0;

                double startX = nearbyEntity.getX() - .275f;
                double startY = nearbyEntity.getY();
                double startZ = nearbyEntity.getZ() - .275f;

                Random random = new Random();
                for (int i = 0; i < 10; i++) {
                    double frontX = .5f * random.nextDouble();
                    world.addParticle(particle, startX + frontX, startY + random.nextDouble() * .5, startZ + .5f,
                            velX, velY, velZ);

                    double backX = .5f * random.nextDouble();
                    world.addParticle(particle, startX + backX, startY + random.nextDouble() * .5, startZ, velX, velY, velZ);

                    double leftZ = .5f * random.nextDouble();
                    world.addParticle(particle, startX, startY + random.nextDouble() * .5, startZ + leftZ, velX, velY, velZ);

                    double rightZ = .5f * random.nextDouble();
                    world.addParticle(particle, startX + .5f, startY + random.nextDouble() * .5, startZ + rightZ, velX, velY, velZ);
                }

            }
        }

    }

    public static void healNearbyAllies(LivingEntity healer, float amount, float distance){
        World world = healer.getEntityWorld();
        PlayerEntity playerEntity = healer instanceof PlayerEntity ? (PlayerEntity)healer : null;
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(healer.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canHealEntity(healer, nearbyEntity));
        for (LivingEntity nearbyEntity : nearbyEntities){
            if (nearbyEntity.getHealth() < nearbyEntity.getMaxHealth()){
                nearbyEntity.heal(amount);

                ParticleEffect particle = ParticleTypes.HEART;

                double velX = 0;
                double velY = 1;
                double velZ = 0;

                double startX = nearbyEntity.getX() - .275f;
                double startY = nearbyEntity.getY();
                double startZ = nearbyEntity.getZ() - .275f;

                Random random = new Random();
                for (int i = 0; i < 10; i++) {
                    double frontX = .5f * random.nextDouble();
                    world.addParticle(particle, startX + frontX, startY + random.nextDouble() * .5, startZ + .5f,
                            velX, velY, velZ);

                    double backX = .5f * random.nextDouble();
                    world.addParticle(particle, startX + backX, startY + random.nextDouble() * .5, startZ, velX, velY, velZ);

                    double leftZ = .5f * random.nextDouble();
                    world.addParticle(particle, startX, startY + random.nextDouble() * .5, startZ + leftZ, velX, velY, velZ);

                    double rightZ = .5f * random.nextDouble();
                    world.addParticle(particle, startX + .5f, startY + random.nextDouble() * .5, startZ + rightZ, velX, velY, velZ);
                }

            }
        }
    }

    public static void burnNearbyEnemies(LivingEntity attacker, float damage, float distance){
        World world = attacker.getEntityWorld();

        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(attacker.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.canApplyToEnemy(attacker, nearbyEntity));
        if (nearbyEntities.isEmpty()) return;
        for (LivingEntity nearbyEntity : nearbyEntities){
            nearbyEntity.damage(DamageSource.ON_FIRE, damage);
        }
    }
}
