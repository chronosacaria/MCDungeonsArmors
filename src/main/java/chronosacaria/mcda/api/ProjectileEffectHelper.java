package chronosacaria.mcda.api;

import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ProjectileEffectHelper {

    public static void fireSnowballAtNearbyEnemy(LivingEntity attacker, int distance){
        World world = attacker.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(attacker.getX() - distance, attacker.getY() - distance, attacker.getZ() - distance,
                attacker.getX() + distance, attacker.getY() + distance, attacker.getZ() + distance), (nearbyEntity) -> AbilityHelper.canApplyToEnemy(attacker, nearbyEntity));
        if(nearbyEntities.size() < 2) return;
        nearbyEntities.sort(Comparator.comparingDouble(livingEntity -> livingEntity.squaredDistanceTo(attacker)));
        LivingEntity target =  nearbyEntities.get(0);
        if(target != null){
            SnowballEntity snowballEntity = new SnowballEntity(world, attacker);
            // borrowed from AbstractSkeletonEntity
            double towardsX = target.getX() - attacker.getX();
            double towardsZ = target.getZ() - attacker.getZ();
            double euclideanDist = (double)MathHelper.sqrt(towardsX * towardsX + towardsZ * towardsZ);
            double towardsY =
                    target.getBodyY(0.3333333333333333D) - snowballEntity.getY() + euclideanDist * (double)0.2F;
            snowballEntity.setProperties(attacker, attacker.pitch, attacker.yaw, 0.0F, 1.5F, 1.0F);
            setProjectileTowards(snowballEntity, towardsX, towardsY, towardsZ, 0);
            //
            attacker.world.spawnEntity(snowballEntity);
        }
    }

    public static void setProjectileTowards(ProjectileEntity projectileEntity, double x, double y
            , double z, float inaccuracy){
        Random random = new Random();
        Vec3d vec3d = (new Vec3d(x, y, z))
                .normalize()
                .add(
                        random.nextGaussian() * (double)0.0075f * (double)inaccuracy,
                        random.nextGaussian() * (double)0.0075f * (double)inaccuracy,
                        random.nextGaussian() * (double)0.0075f * (double)inaccuracy);
        projectileEntity.setVelocity(vec3d);
        float f = MathHelper.sqrt(projectileEntity.squaredDistanceTo(vec3d));
        projectileEntity.yaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180f / (float)Math.PI));
        projectileEntity.pitch = (float)(MathHelper.atan2(vec3d.y, f) * (double)(180f / (float)Math.PI));
        projectileEntity.prevYaw = projectileEntity.yaw;
        projectileEntity.prevPitch = projectileEntity.pitch;
    }
}