package chronosacaria.mcda.mixin;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.*;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.effects.EnchantmentEffects;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static chronosacaria.mcda.api.CleanlinessHelper.*;
import static chronosacaria.mcda.effects.ArmorEffectID.*;
import static chronosacaria.mcda.effects.ArmorEffects.*;
import static chronosacaria.mcda.enchants.EnchantID.*;

@SuppressWarnings("ConstantConditions")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow public abstract boolean isAlive();

    @Shadow protected abstract int computeFallDamage(float fallDistance, float damageMultiplier);

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public abstract int getArmor();

    @Shadow public abstract void setOnGround(boolean onGround);

    public LivingEntityMixin(EntityType<?> type, World world) {super(type, world);}

    // Mixins for apply damage Effects and Enchantments
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void mcdaApplyDamageEffects(DamageSource source, float amount, CallbackInfo info) {

        if (!((Object) this instanceof LivingEntity target))
            return;

        if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(LEADER_OF_THE_PACK))
            ArmorEffects.leaderOfThePackEffect(target, source, amount);

        if(!(source.getAttacker() instanceof PlayerEntity playerEntity))
            return;
        if (!(source.getSource() instanceof PlayerEntity))
            return;

        if (amount != 0.0F) {
            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(FIRE_FOCUS))
                EnchantmentEffects.applyFireFocusDamage(playerEntity, target, amount);

            if (source.isMagic()){
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(POISON_FOCUS))
                    EnchantmentEffects.applyPoisonFocusDamage(playerEntity, target, amount);
            }

            ItemStack mainHandStack = playerEntity.getMainHandStack();
            if (!mainHandStack.isEmpty()) {
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(TITAN_SHROUD_EFFECTS))
                    ArmorEffects.applyTitanShroudStatuses(playerEntity, target);
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(FROST_BITE_EFFECT))
                    ArmorEffects.applyFrostBiteStatus(playerEntity, target);
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(GHOST_KINDLING))
                    ArmorEffects.applyGhostKindlingEffect(playerEntity, target);

                if(!source.isProjectile()) {
                    if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SPLENDID_ATTACK))
                        ArmorEffects.applySplendidAoEAttackEffect(playerEntity, target);
                    if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(GILDED_HERO))
                        ArmorEffects.gildedHeroDamageBuff(playerEntity, target);
                    if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(ARCTIC_FOX_HIGH_GROUND))
                        ArmorEffects.arcticFoxesHighGround(playerEntity, target, amount);
                }
            }
        }
    }

    // Mixins for apply movement Effects and Enchantments. Only Fire Trail rn
    @Inject(method = "applyMovementEffects", at = @At("HEAD"))
    protected void applyFireTrailEffects(BlockPos blockPos, CallbackInfo ci) {
        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        World world = playerEntity.getEntityWorld();

        if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(FIRE_TRAIL))
            EnchantmentEffects.applyFireTrail(playerEntity, blockPos);
        if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(GHOST_KINDLER_TRAIL) && world.getTime() % 3 == 0)
            ArmorEffects.ghostKindlerTrail(playerEntity, blockPos);

    }

    // Mixins for enchants related to consuming an item. Only potions rn
    @Inject(method = "consumeItem", at = @At("HEAD"))
    public void consumeItem(CallbackInfo ci) {
        if (!((Object) this instanceof PlayerEntity playerEntity))
            return;

        if (!playerEntity.isAlive())
            return;
        if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(FOOD_RESERVES))
            EnchantmentEffects.applyFoodReserves(playerEntity);
        if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(POTION_BARRIER))
            EnchantmentEffects.applyPotionBarrier(playerEntity);
        if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(SURPRISE_GIFT))
            EnchantmentEffects.applySurpriseGift(playerEntity);
    }

    // Mixins for Armor and Enchantment Effects on Damage at HEAD
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void onMCDADamageEffects(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        // Mixins for Armour and Enchantment Effects on Damage for PlayerEntities
        if ((Object) this instanceof PlayerEntity playerEntity) {

            if (source.getAttacker() instanceof LivingEntity) {
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(HEAL_ALLIES))
                    AOEHelper.healNearbyAllies(playerEntity, amount);
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(WITHERED))
                    ArmorEffects.applyWithered(playerEntity, (LivingEntity) source.getAttacker());
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SHULKER_LIKE))
                    ProjectileEffectHelper.fireShulkerBulletAtNearbyEnemy(playerEntity);
            }

            if (amount > 0) {
                if (source.getAttacker() instanceof LivingEntity || source.isProjectile()) {
                    if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SOULDANCER_GRACE)) {
                        if (ArmorEffects.souldancerGraceEffect(playerEntity))
                            cir.cancel();
                    }
                }
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(NIMBLE_TURTLE_EFFECTS))
                    ArmorEffects.applyNimbleTurtleEffects(playerEntity);
            }
        }
        // Mixins for Armour and Enchantment Effects on Damage for LivingEntities
        if ((Object) this instanceof LivingEntity livingEntity) {

            if (amount > 0 && source.getAttacker() instanceof LivingEntity attackingEntity) {
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(BUZZY_HIVE))
                    ArmorEffects.buzzyHiveEffect(livingEntity);
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(CAULDRONS_OVERFLOW))
                    ArmorEffects.applyCauldronsOverflow(livingEntity);
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(CHILLING))
                    EnchantmentEffects.applyChilling(livingEntity, attackingEntity);
            }
        }
    }

    // Mixins for Armor and Enchantment Effects on Damage at TAIL only Curious rn
    @Inject(method = "damage", at = @At("TAIL"))
    public void applyCuriousTeleportation(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){

        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        if (!(source.getAttacker() instanceof LivingEntity attacker))
            return;

        if (amount != 0.0F) {
            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(CURIOUS_TELEPORTATION)) {
                ArmorEffects.applyCuriousTeleportationEffect(playerEntity, attacker);
            }
        }
    }

    // Shadow Walker Armor Negate Fall Damage
    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    public void shadowWalkerArmorNoFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir){
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(NO_FALL_DAMAGE))
            return;
        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        if (playerEntity.isAlive()) {

            if (playerEntity == null) return;

            if (hasArmorSet(playerEntity, ArmorSets.SHADOW_WALKER)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == NO_FALL_DAMAGE)
                    || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == NO_FALL_DAMAGE)) {
                int i = this.computeFallDamage(fallDistance, damageMultiplier);
                if (i > 0) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    // Mixins for climbing effects
    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void mcdaArmorClimbing(CallbackInfoReturnable<Boolean> cir){
        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        if (playerEntity.isAlive()){
            // Spider Armour Climbing
            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SPIDER_CLIMBING))
                if (ArmorEffects.spiderClimbing(playerEntity))
                    cir.setReturnValue(true);

            // Rugged Armour Climbing
            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(RUGGED_CLIMBING))
                if (ArmorEffects.ruggedClimbing(playerEntity))
                    cir.setReturnValue(true);
        }
    }

    // Mixin for Jump Effects
    @Inject(method = "jump", at = @At("HEAD"))
    public void onMCDAJumpEffects(CallbackInfo ci){
        if (!((Object) this instanceof ServerPlayerEntity playerEntity))
            return;
        if (playerEntity != null) {
            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(EMBER_JUMP))
                ArmorEffects.applyEmberJumpEffect(playerEntity);
            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(TELEPORTATION_ROBES_EFFECT))
                ArmorEffects.teleportationRobeTeleport(playerEntity);
            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(UNSTABLE_ROBES_EFFECT))
                ArmorEffects.unstableRobeTeleport(playerEntity);
        }
    }

    // Mixin for on death Effects and Enchantments. Only Gourdian's Hatred rn
    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onGourdiansHatredKill(DamageSource source, CallbackInfo ci) {

        if(!(source.getAttacker() instanceof LivingEntity user))return;

        if (user != null) {
            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(GOURDIANS_HATRED))
                ArmorEffects.applyGourdiansHatredStatus(user);
        }
    }

    // Mixin for Swing Hand. Only Fox Pounce rn
    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;)V", at = @At("HEAD"))
    public void onMCDAFoxPounce(Hand hand, CallbackInfo ci){
        if ((!((Object) this instanceof PlayerEntity playerEntity)))
            return;

        if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(FOX_POUNCING))
            ArmorEffects.foxPouncing(playerEntity);
    }

    // Mixins for Armor and Enchantment Effects on Tick
    @Inject(method = "tick", at = @At("HEAD"))
    private void onMCDATickEffects(CallbackInfo ci){
        // Mixins for Armour and Enchantment Effects on Tick for PlayerEntities
        if((Object) this instanceof PlayerEntity playerEntity) {

            if(playerEntity.isAlive()) {
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(FLUID_FREEZING))
                    ArmorEffects.applyFluidFreezing(playerEntity);
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(INVISIBILITY))
                    ArmorEffects.applyThiefInvisibilityTick(playerEntity);
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SYLVAN_PRESENCE))
                    ArmorEffects.applySylvanPresence(playerEntity);
            }
        }
        // Mixins for Armour and Enchantment Effects on Tick for LivingEntities
        if((Object)this instanceof LivingEntity livingEntity) {

            if (livingEntity.isAlive()) {
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(LUCKY_EXPLORER))
                    EnchantmentEffects.applyLuckyExplorer(livingEntity);
            }
        }
    }

    // Mixin for MCDA Totem Deaths
    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    public void onMCDATotemDeaths(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir){
        // Death Barter
        if(((Object) this instanceof PlayerEntity playerEntity)) {

            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(DEATH_BARTER)) {
                if (!damageSource.isOutOfWorld()) {
                    if (EnchantmentEffects.deathBarterEffect(playerEntity)) {
                        cir.setReturnValue(true);
                    }
                }
            }
        }

        // Gilded Glory Gilded Hero Effect
        if(((Object) this instanceof LivingEntity livingEntity)) {

            if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(GILDED_HERO)) {
                if (!damageSource.isOutOfWorld()) {
                    if (ArmorEffects.gildedGloryTotemEffect(livingEntity)) {
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }

    // Mixin for Knockback Resistance for Stalwart Bulwark Effect
    @ModifyVariable(method = "takeKnockback", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private double applyStalwartBulwarkKnockbackResistanceEffect(double strength){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (this.isAlive() && this.isSneaking() && (hasArmorSet(livingEntity, ArmorSets.STALWART_MAIL)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.MYSTERY)) == STALWART_BULWARK)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.RED_MYSTERY)) == STALWART_BULWARK))) {
            return strength * 0;
        }
        return strength;
    }

    @ModifyVariable(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;" +
            "Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"), argsOnly = true)
    private StatusEffectInstance troubadoursCharismaModifyStatusEffect(StatusEffectInstance statusEffectInstance){

        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity instanceof ServerPlayerEntity && Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(TROUBADOURS_CHARISMA)) {
            if (CleanlinessHelper.hasArmorSet(livingEntity, ArmorSets.TROUBADOUR)) {
                StatusEffect statusEffectType = statusEffectInstance.getEffectType();

                int interceptedDuration = statusEffectInstance.getDuration();

                int newDuration = statusEffectInstance.getDuration();
                if (statusEffectType.isBeneficial()) {
                    newDuration *= 2;
                }
                if (statusEffectType.getCategory() == StatusEffectCategory.HARMFUL) {
                    newDuration /= 2;
                }

                if (newDuration != interceptedDuration) {
                    return new StatusEffectInstance(
                            statusEffectType,
                            newDuration,
                            statusEffectInstance.getAmplifier(),
                            statusEffectInstance.isAmbient(),
                            statusEffectInstance.shouldShowParticles(),
                            statusEffectInstance.shouldShowIcon()
                    );
                }
            }
        }
        return statusEffectInstance;
    }
}