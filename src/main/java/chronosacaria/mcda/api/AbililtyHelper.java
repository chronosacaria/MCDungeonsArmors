package chronosacaria.mcda.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class AbililtyHelper {

    public static void stealSpeedFromTarget(LivingEntity attacker, LivingEntity target, int amplifer){
        StatusEffectInstance speed = new StatusEffectInstance(StatusEffects.SPEED, 80, amplifer);
        StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 80, amplifer);
        attacker.addStatusEffect(speed);
        target.addStatusEffect(slowness);
    }

    public static void makeNearbyPetsAttackTarget(LivingEntity target, LivingEntity owner){

        List<LivingEntity> nearbyEntities = owner.getEntityWorld().getEntitiesByClass(LivingEntity.class,
                new Box(owner.getX() - 12, owner.getY() - 12, owner.getZ() - 12,
                owner.getX() + 12, owner.getY() + 12, owner.getZ() + 12), (nearbyEntity) -> {
            return canPetAttackEntity(owner, nearbyEntity);
        });

        for(LivingEntity nearbyEntity : nearbyEntities){
            if(nearbyEntity instanceof TameableEntity){
                TameableEntity tameableEntity = (TameableEntity) nearbyEntity;
                tameableEntity.setTarget(target);
            }
            if(nearbyEntity instanceof LlamaEntity){
                LlamaEntity llamaEntity = (LlamaEntity) nearbyEntity;
                llamaEntity.setTarget(target);
            }
            if(nearbyEntity instanceof IronGolemEntity){
                IronGolemEntity ironGolemEntity = (IronGolemEntity) nearbyEntity;
                ironGolemEntity.setTarget(target);
            }
        }
    }

    private static boolean canPetAttackEntity(LivingEntity owner, LivingEntity nearbyEntity) {
        return nearbyEntity != owner && isPetOfAttacker(owner, nearbyEntity) && nearbyEntity.isAlive();
    }

    public static boolean isPetOfAttacker(LivingEntity possibleOwner, LivingEntity possiblePet){
        if(possiblePet instanceof TameableEntity){
            TameableEntity pet = (TameableEntity) possiblePet;
            return pet.getOwner() == possibleOwner;
        }
        /*if(possiblePet instanceof HorseBaseEntity){
            HorseBaseEntity abstractHorse = (HorseBaseEntity) possiblePet;
            //return GoalUtils.getOwner(abstractHorse) == possibleOwner;
        }
        if(possiblePet instanceof IronGolemEntity){
            IronGolemEntity ironGolem = (IronGolemEntity) possiblePet;
            //return GoalUtils.getOwner(ironGolem) == possibleOwner;
        }
        if(possiblePet instanceof BatEntity){
            BatEntity batEntity = (BatEntity) possiblePet;
            //return GoalUtils.getOwner(batEntity) == possibleOwner;
        }
        if(possiblePet instanceof BeeEntity){
            BeeEntity beeEntity = (BeeEntity) possiblePet;
            //return GoalUtils.getOwner(beeEntity) == possibleOwner;
        }
        if(possiblePet instanceof SheepEntity){
            SheepEntity sheepEntity = (SheepEntity) possiblePet;
            //return GoalUtils.getOwner(sheepEntity) == possibleOwner;
        }*/
        return false;
    }


    private static boolean isAVillagerOrIronGolem(LivingEntity nearbyEntity) {
        return (nearbyEntity instanceof VillagerEntity) || (nearbyEntity instanceof IronGolemEntity);
    }

    private static boolean isNotTheTargetOrAttacker(LivingEntity attacker, LivingEntity target, LivingEntity nearbyEntity) {
        return nearbyEntity != target
                && nearbyEntity != attacker;
    }

    private static boolean isNotAPlayerOrCanApplyToPlayers(LivingEntity nearbyEntity) {
        if(!(nearbyEntity instanceof PlayerEntity)){
            return true;}
        else {
            return true;
        }
    }

    public static boolean canHealEntity(LivingEntity healer, LivingEntity nearbyEntity){
        return nearbyEntity != healer
                && isAlly(healer, nearbyEntity)
                && isAliveAndCanBeSeen(nearbyEntity, healer);
    }

    private static boolean isAlly(LivingEntity healer, LivingEntity nearbyEntity){
        return isPetOfAttacker(healer, nearbyEntity)
                || isAVillagerOrIronGolem(nearbyEntity)
                || healer.isTeammate(nearbyEntity);
    }

    private static boolean isAliveAndCanBeSeen(LivingEntity nearbyEntity, LivingEntity attacker){
        return nearbyEntity.isAlive() && attacker.canSee(nearbyEntity);
    }

    public static boolean canApplyToEnemy(LivingEntity attacker, LivingEntity target, LivingEntity nearbyEntity) {
        return isNotTheTargetOrAttacker(attacker, target, nearbyEntity)
                && isAliveAndCanBeSeen(nearbyEntity, attacker)
                && !isAlly(attacker, nearbyEntity)
                && isNotAPlayerOrCanApplyToPlayers(nearbyEntity);
    }

    public static boolean canApplyToEnemy(LivingEntity attacker, LivingEntity nearbyEntity) {
        return nearbyEntity != attacker
                && isAliveAndCanBeSeen(nearbyEntity, attacker)
                && !isAlly(attacker, nearbyEntity)
                && isNotAPlayerOrCanApplyToPlayers(nearbyEntity);
    }
}
