package chronosacaria.mcda.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        double d = target.getX() - snowballEntity.getX();
        double e = target.getBodyY(0.3333333333333333D) - snowballEntity.getY();
        double f = target.getZ() - snowballEntity.getZ();
        double g = Math.sqrt(d * d + f * f);
        snowballEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
        setProjectileTowards(snowballEntity, d, e, g, 0);
        //
        user.world.spawnEntity(snowballEntity);
    }

    public static void ricochetArrowLikeShield(PersistentProjectileEntity persistentProjectileEntity, LivingEntity livingEntity){
        persistentProjectileEntity.setVelocity(persistentProjectileEntity.getVelocity().multiply(-0.1D));
        persistentProjectileEntity.getYaw(180.0F);
        persistentProjectileEntity.prevYaw += 180.0F;
        if (!persistentProjectileEntity.world.isClient && persistentProjectileEntity.getVelocity().lengthSquared() < 1.0E-7D){
            if (persistentProjectileEntity.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED){
                persistentProjectileEntity.dropStack(new ItemStack(Items.ARROW), 0.1F);
            }
            persistentProjectileEntity.remove(Entity.RemovalReason.KILLED);
        }
    }

    public static void setProjectileTowards(ProjectileEntity projectileEntity, double x, double y, double z, float inaccuracy) {
        Vec3d vec3d = (new Vec3d(x, y, z))
                .normalize()
                .add(
                        random.nextGaussian() * 0.0075 * inaccuracy,
                        random.nextGaussian() * 0.0075 * inaccuracy,
                        random.nextGaussian() * 0.0075 * inaccuracy);
        projectileEntity.setVelocity(vec3d);
        float f = MathHelper.sqrt((float)projectileEntity.squaredDistanceTo(vec3d));
        projectileEntity.getYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * (180.0 / Math.PI)));
        projectileEntity.getPitch((float)(MathHelper.atan2(vec3d.y, f) * (180.0 / Math.PI)));
        projectileEntity.prevYaw = projectileEntity.getYaw();
        projectileEntity.prevPitch = projectileEntity.getPitch();
    }
}