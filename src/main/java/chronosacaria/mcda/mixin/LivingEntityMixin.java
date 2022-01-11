package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.*;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.effects.EnchantmentEffects;
import chronosacaria.mcda.entities.SummonedBeeEntity;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.EnchantsRegistry;
import chronosacaria.mcda.registry.SoundsRegistry;
import chronosacaria.mcda.registry.SummonedEntityRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

import static chronosacaria.mcda.api.CleanlinessHelper.*;
import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.*;
import static chronosacaria.mcda.effects.ArmorEffects.*;
import static chronosacaria.mcda.enchants.EnchantID.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow public abstract boolean isAlive();

    @Shadow protected abstract int computeFallDamage(float fallDistance, float damageMultiplier);

    @Shadow protected float lastDamageTaken;

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public abstract int getArmor();

    @Shadow public abstract void setOnGround(boolean onGround);

    public EntityType<SummonedBeeEntity> summonedBee = SummonedEntityRegistry.SUMMONED_BEE_ENTITY;

    public LivingEntityMixin(EntityType<?> type, World world) {super(type, world);}

    // Mixins for enchants related to consuming a potion
    @Inject(method = "consumeItem", at = @At("HEAD"))
    public void consumeItem(CallbackInfo ci) {
        if (!((Object) this instanceof PlayerEntity playerEntity))
            return;

        if (!playerEntity.isAlive())
            return;

        EnchantmentEffects.applyFoodReserves(playerEntity);
        EnchantmentEffects.applyPotionBarrier(playerEntity);
        EnchantmentEffects.applySurpriseGift(playerEntity);
    }

    // Mixins for Armour and Enchantment Effects on Damage for PlayerEntities
    @Inject(method = "damage", at = @At("HEAD"))
    public void onDamageEffects(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){

        if (!((Object) this instanceof PlayerEntity playerEntity))
            return;

        if (source.getAttacker() instanceof LivingEntity) {
            if (config.enableEnchantment.get(HEAL_ALLIES))
                AOEHelper.healNearbyAllies(playerEntity, amount);
            if (config.enableArmorEffect.get(WITHERED))
                ArmorEffects.applyWithered(playerEntity, (LivingEntity) source.getAttacker());
        }

        if (this.lastDamageTaken >= 0) {
            if (config.enableArmorEffect.get(NIMBLE_TURTLE_EFFECTS))
                ArmorEffects.applyNimbleTurtleEffects(playerEntity);
        }
    }

    // Mixins for Armour and Enchantment Effects on Tick for PlayerEntities
    @Inject(method = "tick", at = @At("HEAD"))
    private void tickEffects(CallbackInfo ci){
        if(!((Object) this instanceof PlayerEntity playerEntity))
            return;

        ArmorEffects.applyFluidFreezing(playerEntity);
        ArmorEffects.applyThiefInvisibilityTick(playerEntity);

        if (config.enableArmorEffect.get(SYLVAN_PRESENCE) && playerEntity.isAlive() && playerEntity.isSneaking() && (hasRobeWithHatSet(playerEntity, ArmorSets.VERDANT)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SYLVAN_PRESENCE)
                || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == SYLVAN_PRESENCE))
                && world.getTime() % 20 == 0){
            ArmorEffects.applySylvanPresence(playerEntity);
        }
    }

    // Mixins for Armour and Enchantment Effects on Tick for LivingEntities
    @Inject(method = "tick", at = @At("HEAD"))
    private void livingEntityTickEffects(CallbackInfo ci){

        if(!((Object)this instanceof LivingEntity livingEntity))
            return;

        if (config.enableEnchantment.get(LUCKY_EXPLORER) && livingEntity.isAlive() && livingEntity.isOnGround() && world.getTime() % 50 == 0){
            EnchantmentEffects.applyLuckyExplorer(livingEntity);
        }
    }

    // Mixin for Fire Trail
    @Inject(method = "applyMovementEffects", at = @At("HEAD"))
    protected void applyFireTrailEffects(BlockPos blockPos, CallbackInfo ci){
        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        EnchantmentEffects.applyFireTrail(playerEntity, blockPos);
    }

    // Spider Armour Climbing
    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void spiderArmourClimbing(CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(SPIDER_CLIMBING))
            return;
        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        if (playerEntity.isAlive()){

            if (playerEntity == null) return;

            if (hasArmorSet(playerEntity, ArmorSets.SPIDER)
                    || (ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SPIDER_CLIMBING)
                    || (ArmorEffects.PURPLE_ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == SPIDER_CLIMBING)){

                // If Statement provided by Apace100; Thanks, Apace!
                if (playerEntity.world.getBlockCollisions(playerEntity,
                        playerEntity.getBoundingBox().offset(0.01 * playerEntity.getBoundingBox().getXLength(), 0,
                                0.01 * playerEntity.getBoundingBox().getZLength())).iterator().hasNext()
                        || playerEntity.world.getBlockCollisions(playerEntity,
                                playerEntity.getBoundingBox().offset(-0.01 * playerEntity.getBoundingBox().getXLength(), 0,
                                        -0.01 * playerEntity.getBoundingBox().getZLength())).iterator().hasNext() ) {
                    this.setOnGround(true);
                    cir.setReturnValue(true);
                    this.onLanding();

                    double f = 0.15D;
                    double d = MathHelper.clamp(this.getVelocity().x, -f, f);
                    double e = MathHelper.clamp(this.getVelocity().z, -f, f);
                    double g = Math.max(this.getVelocity().y, -f);

                    if (g < 0.0D && !this.getBlockStateAtPos().isOf(Blocks.SCAFFOLDING) && this.isSneaking()) {
                        g = 0.0D;
                    }
                    this.setVelocity(d, g, e);
                }
            }
        }
    }

    // Shadow Walker Armour Negate Fall Damage
    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    public void shadowWalkerArmorNoFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(NO_FALL_DAMAGE))
            return;
        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        if (playerEntity.isAlive()) {

            if (playerEntity == null) return;

            if (hasArmorSet(playerEntity, ArmorSets.SHADOW_WALKER)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(MinecraftClient.getInstance().player, ArmorSets.MYSTERY)) == NO_FALL_DAMAGE)
                    || (ArmorEffects.GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(MinecraftClient.getInstance().player, ArmorSets.GREEN_MYSTERY)) == NO_FALL_DAMAGE)) {
                int i = this.computeFallDamage(fallDistance, damageMultiplier);
                if (i > 0) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    // Mixin for Death Barter
    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    public void onDeathBarterDeath(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableEnchantment.get(DEATH_BARTER))
            return;

        LivingEntity livingEntity = (LivingEntity) (Object) this;

        PlayerEntity playerEntity = null;

        if (livingEntity instanceof PlayerEntity){
            playerEntity = (PlayerEntity) livingEntity;
        } else {
            return;
        }

        PlayerInventory playerInventory = playerEntity.getInventory();
        int emeraldTotal = 0;
        List<Integer> emeraldSlotIndices = new ArrayList<>();
        for(int slotIndex = 0; slotIndex < playerInventory.size(); slotIndex++){
            ItemStack currentStack = playerInventory.getStack(slotIndex);
            if(currentStack.getItem() == Items.EMERALD){
                emeraldTotal += currentStack.getCount();
                emeraldSlotIndices.add(slotIndex);
            }
        }

        int deathBarterLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(DEATH_BARTER), playerEntity);
        if (deathBarterLevel > 0) {
            int minEmeralds = 150 / deathBarterLevel;
            if (emeraldTotal >= minEmeralds && emeraldTotal > 0) {

                for (Integer slotIndex : emeraldSlotIndices) {
                    if (minEmeralds > 0) {
                        ItemStack currentEmeraldsStack = playerInventory.getStack(slotIndex);
                        int currentEmeraldsCount = currentEmeraldsStack.getCount();
                        if (currentEmeraldsCount >= minEmeralds) {
                            currentEmeraldsStack.setCount(currentEmeraldsCount - minEmeralds);
                            minEmeralds = 0;
                        } else {
                            currentEmeraldsStack.setCount(0);
                            minEmeralds -= currentEmeraldsCount;
                        }
                    } else {
                        break;
                    }

                    playerEntity.setHealth(1.0F);
                    playerEntity.clearStatusEffects();
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 900, 1));
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));

                    cir.setReturnValue(true);
                }
            }
        }
    }

    // Mixin for Titan Shroud Effect
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyTitanShroudStatusEffects(DamageSource source, float amount, CallbackInfo info) {
        if (!config.enableArmorEffect.get(TITAN_SHROUD_EFFECTS))
            return;

        if(!(source.getAttacker() instanceof PlayerEntity playerEntity))return;

        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {

            ItemStack mainHandStack = playerEntity.getMainHandStack();
            if (mainHandStack != null && hasArmorSet(playerEntity, ArmorSets.TITAN)) {
                if (amount != 0.0F) {
                    ArmorEffects.applyTitanShroudStatuses(playerEntity, target);
                }
            }
        }
    }

    //TODO Fix Fire Focus Damage Calculation
    // Mixin for Fire Focus
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void onFireFocusAttack(DamageSource source, float amount, CallbackInfo info) {
       if (!config.enableEnchantment.get(FIRE_FOCUS))
           return;

        if(!(source.getAttacker() instanceof PlayerEntity playerEntity))return;

        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {
            if (amount != 0.0F) {
                if (playerEntity != null && McdaEnchantmentHelper.hasFireAspect(playerEntity) || target.isOnFire()) {
                    int fireFocusLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(FIRE_FOCUS), playerEntity);
                    if (fireFocusLevel > 0) {
                        float h = target.getHealth();
                        float multiplier = 1 + (0.25F * fireFocusLevel);
                        target.setHealth(h - (amount * multiplier));
                    }
                }
            }
        }
    }

    // Mixin for Poison Focus
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void onPoisonFocusAttack(DamageSource source, float amount, CallbackInfo info) {
        if (!config.enableEnchantment.get(POISON_FOCUS))
            return;

        if(!(source.getAttacker() instanceof PlayerEntity))return;

        if (source != DamageSource.MAGIC) return;

        LivingEntity target = (LivingEntity) (Object) this;

        if (target.getActiveStatusEffects().get(StatusEffects.POISON) != null) {
            PlayerEntity playerEntity = (PlayerEntity) target.getAttacker();
            if (playerEntity != null) {
                int poisonFocusLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(POISON_FOCUS),
                        playerEntity);
                if (poisonFocusLevel > 0) {
                    float h = target.getHealth();
                    float multiplier = 1 + (0.25F * poisonFocusLevel);
                    target.setHealth(h - (amount * multiplier));
                }
            }
        }
    }

    // Mixin for Black Wolf Armour Effect (Leader of the Pack)
    @Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
    public void onBlackWolfArmourEffect(DamageSource source, float amount, CallbackInfo info){
        if (!config.enableArmorEffect.get(LEADER_OF_THE_PACK))
            return;

        LivingEntity target = (LivingEntity) (Object) this;
        Entity petSource = source.getSource();

        if (petSource == null) return;

        if (petSource.world instanceof ServerWorld serverWorld && petSource instanceof TameableEntity){
            PlayerEntity owner = (PlayerEntity) ((TameableEntity) petSource).getOwner();
            if (owner != null){
                UUID petOwnerUUID = owner.getUuid();
                if (hasArmorSet(owner, ArmorSets.BLACK_WOLF)
                        || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(MinecraftClient.getInstance().player, ArmorSets.MYSTERY)) == LEADER_OF_THE_PACK)
                        || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(MinecraftClient.getInstance().player, ArmorSets.RED_MYSTERY)) == LEADER_OF_THE_PACK)) {

                    if (petOwnerUUID != null) {
                        Entity petOwner = serverWorld.getEntity(petOwnerUUID);
                        if (petOwner instanceof LivingEntity) {
                            float blackWolfArmorFactor = 1.5f;
                            float newDamage = amount * blackWolfArmorFactor;
                            target.damage(DamageSource.GENERIC, newDamage);
                        }
                    }
                }
            }
        }
    }

    // Mixin for Shulker Bullet Effect
    @Inject(method = "damage", at = @At("HEAD"))
    public void fireShulkerBulletOnDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(SHULKER_LIKE))
            return;
        if (!((Object) this instanceof PlayerEntity playerEntity))
            return;
        if (!(source.getAttacker() instanceof LivingEntity)){
            return;
        }

        if (playerEntity != null) {
            if (hasArmorSet(playerEntity, ArmorSets.STURDY_SHULKER)) {
                ProjectileEffectHelper.fireShulkerBulletAtNearbyEnemy(playerEntity, 10);
            }
        }
    }

    // Mixin for Teleportation Robes Effect
    @Inject(method = "jump", at = @At("HEAD"))
    public void onTeleportationRobesTeleport(CallbackInfo ci){
        if (!config.enableArmorEffect.get(TELEPORTATION_ROBES_EFFECT))
            return;
        if (!((Object) this instanceof ServerPlayerEntity playerEntity))
            return;
        if (playerEntity != null) {
            if (hasArmorSet(playerEntity, ArmorSets.TELEPORTATION)) {
                if (playerEntity.isSneaking()) {
                    ((PlayerTeleportationStateAccessor)playerEntity).setInTeleportationState(true);
                        ArmorEffects.endermanLikeTeleportEffect(playerEntity);
                } else {
                    ((PlayerTeleportationStateAccessor)playerEntity).setInTeleportationState(false);
                }
            }
        }
    }

    // Mixin for Unstable Robes Effect
    @Inject(method = "jump", at = @At("HEAD"))
    public void onUnstableRobesTeleport(CallbackInfo ci){
        if (!config.enableArmorEffect.get(UNSTABLE_ROBES_EFFECT))
            return;
        if (!((Object) this instanceof ServerPlayerEntity playerEntity))
            return;

        if (playerEntity != null) {
            if (hasArmorSet(playerEntity, ArmorSets.UNSTABLE)) {
                if (playerEntity.isSneaking()) {
                    ((PlayerTeleportationStateAccessor)playerEntity).setInTeleportationState(true);
                    AOECloudHelper.spawnExplosionCloud(playerEntity, playerEntity, 3.0F);
                    AOEHelper.causeExplosion(playerEntity, playerEntity, 5, 3.0f);
                    if (config.controlledTeleportation){
                        ArmorEffects.controlledTeleportEffect(playerEntity);
                    } else {
                        ArmorEffects.endermanLikeTeleportEffect(playerEntity);
                    }
                } else {
                    ((PlayerTeleportationStateAccessor)playerEntity).setInTeleportationState(false);
                }
            }
        }
    }

    // Mixin for Frost Bite Effect
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyFrostBiteStatusEffect(DamageSource source, float amount, CallbackInfo info) {
        if (!config.enableArmorEffect.get(FROST_BITE_EFFECT))
            return;

        if(!(source.getAttacker() instanceof PlayerEntity playerEntity))return;

        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {
            if (hasArmorSet(playerEntity, ArmorSets.FROST_BITE)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == FROST_BITE_EFFECT)
                    || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == FROST_BITE_EFFECT)) {
                if (amount != 0.0F) {
                    if (playerEntity != null) {
                        ItemStack mainHandStack = playerEntity.getMainHandStack();
                        if (mainHandStack != null) {
                            ArmorEffects.applyFrostBiteStatus(target);
                        }
                    }
                }
            }
        }
    }

    // Mixin for Gourdian's Hatred Effect
    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onGourdiansHatredKill(DamageSource source, CallbackInfo ci){
        if (!config.enableArmorEffect.get(GOURDIANS_HATRED))
            return;

        if(!(source.getAttacker() instanceof LivingEntity user))return;

        if (user != null) {
            if (hasArmorSet(user, ArmorSets.GOURDIAN)
                    || (ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(user, ArmorSets.MYSTERY)) == GOURDIANS_HATRED)
                    || (RED_ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect( user, ArmorSets.RED_MYSTERY)) == GOURDIANS_HATRED)) {
                float hatredRand = user.getRandom().nextFloat();
                if (hatredRand <= 0.15F) {
                    ArmorEffects.applyGourdiansHatredStatus(user);
                }
            }
        }
    }

    // Mixin for Cauldron's Overflow Effect
    @Inject(method = "damage", at = @At("HEAD"))
    public void applyCauldronsOverflow(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!config.enableArmorEffect.get(CAULDRONS_OVERFLOW))
            return;

        if(!(source.getAttacker() instanceof LivingEntity))return;

        LivingEntity targetedEntity = (LivingEntity) (Object) this;

        if (targetedEntity != null) {
            if (hasArmorSet(targetedEntity, ArmorSets.CAULDRON)) {
                if (amount != 0.0F) {
                    float overflowRand = targetedEntity.getRandom().nextFloat();
                    if (overflowRand <= 0.15F) {
                        ArmorEffects.applyCauldronsOverflow(targetedEntity);
                    }
                }
            }
        }
    }

    // Mixin for Knockback Resistance for Stalwart Bulwark Effect
    @ModifyVariable(method = "takeKnockback", at = @At("HEAD"), ordinal = 0)
    private double applyStalwartBulwarkKnockbackResistanceEffect(double strength){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (this.isAlive() && this.isSneaking() && (hasArmorSet(livingEntity, ArmorSets.STALWART_MAIL)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.MYSTERY)) == STALWART_BULWARK)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.RED_MYSTERY)) == STALWART_BULWARK))) {
            return strength * 0;
        }
        return strength;
    }

    @Inject(method = "damage", at = @At("HEAD"))
    public void summonBeesOnDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!config.enableArmorEffect.get(BUZZY_HIVE))
            return;

        if (!(source.getAttacker() instanceof LivingEntity)) return;

        LivingEntity targetedEntity = (LivingEntity) (Object) this;

        SummonedBeeEntity summonedBeeEntity = summonedBee.create(world);
        if (hasArmorSet(targetedEntity, ArmorSets.BEEHIVE)) {
            if (amount != 0.0F) {
                float beeSummonChance = targetedEntity.getRandom().nextFloat();
                if (beeSummonChance <= 0.15F) {
                    if (summonedBeeEntity != null) {
                        summonedBeeEntity.setSummoner(targetedEntity);
                        summonedBeeEntity.refreshPositionAndAngles(this.getX(), this.getY() + 1, this.getZ(), 0, 0);
                        world.spawnEntity(summonedBeeEntity);
                    }
                }
            }
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void applySouldancerGrace(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(SOULDANCER_GRACE))
            return;

        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        if (playerEntity.isAlive()) {
            if (hasArmorSet(playerEntity, ArmorSets.SOULDANCER)) {

                if (!(source.getAttacker() instanceof LivingEntity || source.isProjectile()))
                    return;

                if (amount != 0.0F) {
                    float dodgeRand = playerEntity.getRandom().nextFloat();
                    if (dodgeRand <= 1.0F) {
                        // Dodge the damage
                        cir.cancel();
                        playerEntity.world.playSound(
                                null,
                                playerEntity.getX(),
                                playerEntity.getY(),
                                playerEntity.getZ(),
                                SoundsRegistry.DODGE_SOUND_EVENT,
                                SoundCategory.PLAYERS,
                                1.0F,
                                1.0F);
                        AOECloudHelper.spawnCloudCloud(playerEntity, playerEntity, 0.5F);
                        // Apply Speed after dodge
                        StatusEffectInstance speed = new StatusEffectInstance(StatusEffects.SPEED, 42, 0, false,
                                false);
                        playerEntity.addStatusEffect(speed);
                    }
                }
            }
        }
    }

    @Inject(method = "damage", at = @At("TAIL"))
    public void applyCuriousTeleportation(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(CURIOUS_TELEPORTATION))
            return;

        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        if (playerEntity.isAlive() && (hasArmorSet(playerEntity, ArmorSets.CURIOUS)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == CURIOUS_TELEPORTATION)
                || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == CURIOUS_TELEPORTATION))){

            if (!(source.getAttacker() instanceof LivingEntity))
                return;

            if (amount != 0.0F) {
                ArmorEffects.applyCuriousTeleportationEffect(playerEntity, (LivingEntity) source.getAttacker());
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void applyGhostKindling(DamageSource source, float amount, CallbackInfo info) {
        if (!config.enableArmorEffect.get(GHOST_KINDLING))
            return;

        if(!(source.getAttacker() instanceof PlayerEntity playerEntity))return;

        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {

            ItemStack mainHandStack = playerEntity.getMainHandStack();
            if (mainHandStack != null && (hasArmorSet(playerEntity, ArmorSets.GHOST_KINDLER)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == GHOST_KINDLING)
                    || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == GHOST_KINDLING))) {
                if (amount != 0.0F) {
                    ArmorEffects.applyGhostKindlingEffect(target);
                }
            }
        }
    }

    @Inject(method = "jump", at = @At("HEAD"))
    public void onEmberJump(CallbackInfo ci){
        if (!config.enableArmorEffect.get(EMBER_JUMP))
            return;
        if (!((Object) this instanceof ServerPlayerEntity playerEntity))
            return;
        if (playerEntity != null) {
            if (hasRobeWithHatSet(playerEntity, ArmorSets.EMBER)) {
                ArmorEffects.applyEmberJumpEffect(playerEntity);
            }
        }
    }

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("TAIL"))
    public void applySplendidAoEAttack(DamageSource source, float amount, CallbackInfo info) {
        if (!config.enableArmorEffect.get(SPLENDID_ATTACK))
            return;

        if(!(source.getAttacker() instanceof PlayerEntity playerEntity))return;
        if(source.isProjectile()) return;

        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {

            ItemStack mainHandStack = playerEntity.getMainHandStack();
            if (mainHandStack != null && (hasRobeSet(playerEntity, ArmorSets.SPLENDID))) {
                if (amount != 0.0F) {
                    float splendidRand = playerEntity.getRandom().nextFloat();

                    if (splendidRand <= 0.3f) {
                        ArmorEffects.applySplendidAoEAttackEffect(playerEntity, target);
                    }
                }
            }
        }
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void tryUseGildedTotemEffect(DamageSource source, CallbackInfoReturnable<Boolean> cir){

        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (config.enableArmorEffect.get(GILDED_HERO)
                && hasArmorSet(livingEntity, ArmorSets.GILDED)
                && livingEntity.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE)){
            if (!source.isOutOfWorld()) {
                Iterable<ItemStack> iterable = livingEntity.getArmorItems();
                Iterator var4 = iterable.iterator();

                // Trackers
                boolean itemToBreak = false;
                int i = 0;
                int r = 0;

                float[] armorPieceDurability = {0, 0, 0, 0};

                while(var4.hasNext()) {
                    ItemStack itemStack = (ItemStack)var4.next();
                    float k = itemStack.getMaxDamage();
                    float j = k - itemStack.getDamage();
                    armorPieceDurability[i] = j/k;
                    i++;
                }
                for (int k = 0; k < 3 ; k++){
                    if (armorPieceDurability[k + 1] > armorPieceDurability[r]) {
                        r = k + 1;
                    }
                }
                if (!(armorPieceDurability[r] > 0.50f)) {
                    itemToBreak = true;
                }

                if (itemToBreak) {
                    switch (r) {
                        case 0 -> {
                            ItemStack feetStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);
                            int k = feetStack.getMaxDamage();
                            int j = k - feetStack.getDamage();
                            feetStack.damage(j, livingEntity,
                                    (entity) -> entity.sendEquipmentBreakStatus(EquipmentSlot.FEET));
                        }
                        case 1 -> {
                            ItemStack legStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);
                            int k = legStack.getMaxDamage();
                            int j = k - legStack.getDamage();
                            legStack.damage(j, livingEntity,
                                    (entity) -> entity.sendEquipmentBreakStatus(EquipmentSlot.LEGS));
                        }
                        case 2 -> {
                            ItemStack chestStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
                            int k = chestStack.getMaxDamage();
                            int j = k - chestStack.getDamage();
                            chestStack.damage(j, livingEntity,
                                    (entity) -> entity.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
                        }
                        case 3 -> {
                            ItemStack headStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
                            int k = headStack.getMaxDamage();
                            int j = k - headStack.getDamage();
                            headStack.damage(j, livingEntity,
                                    (entity) -> entity.sendEquipmentBreakStatus(EquipmentSlot.HEAD));
                        }
                    }
                } else {
                    switch (r) {
                        case 0 -> {
                            ItemStack feetStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);
                            int k = feetStack.getMaxDamage();
                            feetStack.damage((k / 2), livingEntity,
                                    (entity) -> entity.sendEquipmentBreakStatus(EquipmentSlot.FEET));
                        }
                        case 1 -> {
                            ItemStack legStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);
                            int k = legStack.getMaxDamage();
                            legStack.damage((k / 2), livingEntity,
                                    (entity) -> entity.sendEquipmentBreakStatus(EquipmentSlot.LEGS));
                        }
                        case 2 -> {
                            ItemStack chestStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
                            int k = chestStack.getMaxDamage();
                            chestStack.damage((k / 2), livingEntity,
                                    (entity) -> entity.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
                        }
                        case 3 -> {
                            ItemStack headStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
                            int k = headStack.getMaxDamage();
                            headStack.damage((k / 2), livingEntity,
                                    (entity) -> entity.sendEquipmentBreakStatus(EquipmentSlot.HEAD));
                        }
                    }
                }
                livingEntity.setHealth(1.0F);
                livingEntity.clearStatusEffects();
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 900, 1));
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                livingEntity.world.sendEntityStatus(livingEntity, (byte)35);
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "damage", at = @At("HEAD"))
    public void applyGildedGloryDamageBuff(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!config.enableArmorEffect.get(GILDED_HERO))
            return;

        if(!(source.getAttacker() instanceof PlayerEntity playerEntity))return;
        if(source.isProjectile()) return;

        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {

            ItemStack mainHandStack = playerEntity.getMainHandStack();
            if (mainHandStack != null && (hasArmorSet(playerEntity, ArmorSets.GILDED))) {
                if (amount != 0.0F) {
                    ArmorEffects.gildedHeroDamageBuff(playerEntity, target);
                }
            }
        }
    }
}
