package chronosacaria.mcda.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static chronosacaria.mcda.Mcda.random;

public class ProjectileEffectHelper {

    public static void fireSnowballAtNearbyEnemy(LivingEntity user, int distance) {
        World world = user.getEntityWorld();
        List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class,
                new Box(user.getX() - distance, user.getY() - distance, user.getZ() - distance,
                        user.getX() + distance, user.getY() + distance, user.getZ() + distance),
                (nearbyEntity) -> nearbyEntity != user && AbilityHelper.canFireAtEnemy(user, nearbyEntity));
        if (nearbyEntities.size() < 2) return;
        Optional<LivingEntity> nearest = nearbyEntities.stream().min(Comparator.comparingDouble(e -> e.squaredDistanceTo(user)));
        LivingEntity target = nearest.get();
        SnowballEntity snowballEntity = new SnowballEntity(world, user);
        // borrowed from AbstractSkeletonEntity
        double towardsX = target.getX() - user.getX();
        double towardsZ = target.getZ() - user.getZ();
        double euclideanDist = MathHelper.sqrt(towardsX * towardsX + towardsZ * towardsZ);
        double towardsY =
                target.getBodyY(0.3333333333333333D) - snowballEntity.getY() + euclideanDist * (double)0.2F;
        snowballEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
        setProjectileTowards(snowballEntity, towardsX, towardsY, towardsZ, 0);
        //
        user.world.spawnEntity(snowballEntity);
    }

    public static void setProjectileTowards(ProjectileEntity projectileEntity, double x, double y
            , double z, float inaccuracy) {
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