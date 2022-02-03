package chronosacaria.mcda.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class AbilityHelper {

    public static List<LivingEntity> getPotentialPounceTargets(LivingEntity pouncer, float distance){
        return pouncer.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(pouncer.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.isPounceTarget(nearbyEntity, pouncer, pouncer));
    }

    public static boolean isPetOf(LivingEntity self, LivingEntity owner) {
        if (self instanceof TameableEntity pet)
            return pet.getOwner() == owner;
        return false;
    }

    private static boolean isAVillagerOrIronGolem(LivingEntity entity) {
        return (entity instanceof VillagerEntity) || (entity instanceof IronGolemEntity);
    }

    private static boolean isNotAPlayer(LivingEntity entity) {
        return !(entity instanceof PlayerEntity);
    }

    private static boolean isAllyOf(LivingEntity self, LivingEntity other) {
        return isPetOf(other, self)
                || isAVillagerOrIronGolem(other)
                || self.isTeammate(other);
    }

    public static boolean isAoeTarget(LivingEntity self, LivingEntity attacker, LivingEntity center) {
        return self != attacker
                && self.isAlive()
                && !isAllyOf(attacker, self)
                && !isUnaffectedByAoe(self)
                && center.canSee(self);
    }

    public static boolean isPounceTarget(LivingEntity self, LivingEntity attacker, LivingEntity center){
        return self != attacker
                && self.isAlive()
                && !isAllyOf(attacker, self)
                && center.canSee(self);
    }

    private static boolean isUnaffectedByAoe(LivingEntity entity) {
        if (entity instanceof PlayerEntity)
            return ((PlayerEntity) entity).isCreative();
        return false;
    }

    public static boolean canHealEntity(LivingEntity self, LivingEntity other) {
        if (!self.isAlive() || !other.isAlive())
            return false;
        return isAllyOf(self, other)
                && self.canSee(other);
    }

    public static boolean canFireAtEnemy(LivingEntity self, LivingEntity enemy) {
        if (!self.isAlive() || !enemy.isAlive())
            return false;
        return self.canSee(enemy)
                && !isAllyOf(self, enemy)
                && isNotAPlayer(enemy);
    }
}
