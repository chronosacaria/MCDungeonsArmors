package chronosacaria.mcda.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class AbilityHelper {

    // TODO: unused
    public static void makeNearbyPetsAttackTarget(LivingEntity target, LivingEntity owner) {
        List<MobEntity> nearbyPets = owner.getEntityWorld().getEntitiesByClass(MobEntity.class,
                new Box(owner.getX() - 12, owner.getY() - 12, owner.getZ() - 12,
                        owner.getX() + 12, owner.getY() + 12, owner.getZ() + 12),
                (nearbyEntity) -> nearbyEntity != owner && isPetOf(nearbyEntity, owner) && nearbyEntity.isAlive());

        for (MobEntity nearbyPet : nearbyPets) {
            nearbyPet.setTarget(target);
        }
    }

    public static LivingEntity getClosestPounceTarget(LivingEntity pouncer, LivingEntity targets, LivingEntity closest, float distance){
        return targets.getEntityWorld().getClosestEntity(getPotentialPounceTargets(pouncer, distance),
                TargetPredicate.DEFAULT,
                closest, closest.getX(), closest.getY(), closest.getZ());
    }

    public static List<LivingEntity> getPotentialPounceTargets(LivingEntity pouncer, float distance){
        return pouncer.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(pouncer.getBlockPos()).expand(distance),
                (nearbyEntity) -> AbilityHelper.isPounceTarget(nearbyEntity, pouncer, pouncer));
    }

    public static boolean isPetOf(LivingEntity self, LivingEntity owner) {
        if (self instanceof TameableEntity) {
            TameableEntity pet = (TameableEntity) self;
            return pet.getOwner() == owner;
        }
        /*if (self instanceof HorseBaseEntity) {
            HorseBaseEntity abstractHorse = (HorseBaseEntity) self;
            //return GoalUtils.getOwner(abstractHorse) == owner;
        }
        if (self instanceof IronGolemEntity) {
            IronGolemEntity ironGolem = (IronGolemEntity) self;
            //return GoalUtils.getOwner(ironGolem) == owner;
        }
        if (self instanceof BatEntity) {
            BatEntity batEntity = (BatEntity) self;
            //return GoalUtils.getOwner(batEntity) == owner;
        }
        if (self instanceof BeeEntity) {
            BeeEntity beeEntity = (BeeEntity) self;
            //return GoalUtils.getOwner(beeEntity) == owner;
        }
        if (self instanceof SheepEntity) {
            SheepEntity sheepEntity = (SheepEntity) self;
            //return GoalUtils.getOwner(sheepEntity) == owner;
        }*/
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
                //&& !isAllyOf(attacker, self)
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

        return isAllyOf(self, other) && self.canSee(other);
    }

    public static boolean canFireAtEnemy(LivingEntity self, LivingEntity enemy) {
        if (!self.isAlive() || !enemy.isAlive())
            return false;

        return self.canSee(enemy)
                && !isAllyOf(self, enemy)
                && isNotAPlayer(enemy);
    }


}
