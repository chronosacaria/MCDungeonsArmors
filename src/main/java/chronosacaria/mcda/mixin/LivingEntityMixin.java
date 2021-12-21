package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.AOECloudHelper;
import chronosacaria.mcda.api.AOEHelper;
import chronosacaria.mcda.api.McdaEnchantmentHelper;
import chronosacaria.mcda.api.ProjectileEffectHelper;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.effects.EnchantmentEffects;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import chronosacaria.mcda.registry.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.*;
import static chronosacaria.mcda.enchants.EnchantID.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow public abstract boolean isAlive();

    @Shadow protected abstract int computeFallDamage(float fallDistance, float damageMultiplier);

    @Shadow protected float lastDamageTaken;

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    public LivingEntityMixin(EntityType<?> type, World world) {super(type, world);}

    // Mixins for enchants related to consuming a potion
    @Inject(method = "consumeItem", at = @At("HEAD"))
    public void consumeItem(CallbackInfo ci) {
        if (!((Object)this instanceof PlayerEntity))
            return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (!playerEntity.isAlive())
            return;

        EnchantmentEffects.applyFoodReserves(playerEntity);
        EnchantmentEffects.applyPotionBarrier(playerEntity);
        EnchantmentEffects.applySurpriseGift(playerEntity);
    }

    // Mixin for Heal Allies Enchantment
    @Inject(method = "damage", at = @At("HEAD"))
    public void healAlliesDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableEnchantment.get(HEAL_ALLIES))
            return;

        if (!((Object)this instanceof PlayerEntity))
            return;
        if (!(source.getAttacker() instanceof LivingEntity))
            return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;

        int healAlliesLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(HEAL_ALLIES), playerEntity);
        if (EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(HEAL_ALLIES), playerEntity) > 0) {
            EnchantmentEffects.applyHealAllies(playerEntity, (0.25f * amount) * healAlliesLevel);
        }

    }

    // Mixin for Wither Effect
    @Inject(method = "damage", at = @At("HEAD"))
    public void applyEffectsFromPlayerDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!((Object)this instanceof PlayerEntity))
            return;
        if (!(source.getAttacker() instanceof LivingEntity)){
            return;
        }

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        ArmorEffects.applyWithered(playerEntity, (LivingEntity) source.getAttacker());
    }

    // Mixin for Nimble Turtle Armour Effects
    @Inject(method = "damage", at = @At("HEAD"))
    public void applyNimbleTurtleDamageEffects(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(NIMBLE_TURTLE_EFFECTS))
            return;
        if(!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (playerEntity.isAlive()){
            if (this.lastDamageTaken >= 0){
                    ArmorEffects.applyNimbleTurtleEffects(playerEntity);
            }
        }
    }

    // Mixins for Armour and Enchantment Effects on Tick
    @Inject(method = "tick", at = @At("HEAD"))
    private void tickEffects(CallbackInfo ci){
        if(!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;

        ArmorEffects.applyFluidFreezing(playerEntity);
        ArmorEffects.applyThiefInvisibilityTick(playerEntity);
        //EnchantmentEffects.applyFireTrail(playerEntity);
    }

    // Mixin for Fire Trail
    @Inject(method = "applyMovementEffects", at = @At("HEAD"))
    protected void applyFireTrailEffects(BlockPos blockPos, CallbackInfo ci){
        if(!((Object)this instanceof PlayerEntity)) return;
        PlayerEntity playerEntity = (PlayerEntity) (Object) this;

        EnchantmentEffects.applyFireTrail(playerEntity, blockPos);
    }

    // Spider Armour Climbing
    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void spiderArmourClimbing(CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(SPIDER_CLIMBING))
            return;
        if(!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (playerEntity.isAlive()){

            if (playerEntity == null) return;

            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.FEET).asItem())
                if (this.horizontalCollision){
                    cir.setReturnValue(true);
                }
        }
    }

    // Shadow Walker Armour Negate Fall Damage
    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    public void shadowWalkerArmorNoFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(NO_FALL_DAMAGE))
            return;
        if(!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (playerEntity.isAlive()) {

            if (playerEntity == null) return;

            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SHADOW_WALKER).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SHADOW_WALKER).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SHADOW_WALKER).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SHADOW_WALKER).get(EquipmentSlot.FEET).asItem()) {
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

        if(!(source.getAttacker() instanceof PlayerEntity))return;

        PlayerEntity playerEntity = (PlayerEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {
            if (amount != 0.0F) {
                if (playerEntity != null) {
                    ItemStack mainHandStack = playerEntity.getMainHandStack();
                    ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
                    ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
                    ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
                    ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);


                    if (mainHandStack != null && helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TITAN).get(EquipmentSlot.HEAD).asItem()
                            && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TITAN).get(EquipmentSlot.CHEST).asItem()
                            && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TITAN).get(EquipmentSlot.LEGS).asItem()
                            && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TITAN).get(EquipmentSlot.FEET).asItem()) {

                        ArmorEffects.applyTitanShroudStatuses(playerEntity, target);

                    }
                }
            }
        }
    }

    // Mixin for Fire Focus
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void onFireFocusAttack(DamageSource source, float amount, CallbackInfo info) {
       if (!config.enableEnchantment.get(FIRE_FOCUS))
           return;

        if(!(source.getAttacker() instanceof PlayerEntity))return;

        PlayerEntity playerEntity = (PlayerEntity) source.getAttacker();
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

        if (petSource.world instanceof ServerWorld && petSource instanceof TameableEntity){
            ServerWorld serverWorld = (ServerWorld) petSource.world;
            PlayerEntity owner = (PlayerEntity) ((TameableEntity) petSource).getOwner();
            if (owner != null){
                UUID petOwnerUUID = owner.getUuid();
                ItemStack helmetStack = owner.getEquippedStack(EquipmentSlot.HEAD);
                ItemStack chestStack = owner.getEquippedStack(EquipmentSlot.CHEST);
                ItemStack legsStack = owner.getEquippedStack(EquipmentSlot.LEGS);
                ItemStack feetStack = owner.getEquippedStack(EquipmentSlot.FEET);

                if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLACK_WOLF).get(EquipmentSlot.HEAD).asItem()
                        && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLACK_WOLF).get(EquipmentSlot.CHEST).asItem()
                        && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLACK_WOLF).get(EquipmentSlot.LEGS).asItem()
                        && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLACK_WOLF).get(EquipmentSlot.FEET).asItem()) {

                    if (petOwnerUUID != null) {
                        Entity petOwner = serverWorld.getEntity(petOwnerUUID);
                        if (petOwner instanceof LivingEntity) {
                            float blackWolfArmourFactor = 1.5f;
                            float newDamage = amount * blackWolfArmourFactor;
                            float h = target.getHealth();
                            target.setHealth(h - newDamage);
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
        if (!((Object)this instanceof PlayerEntity))
            return;
        if (!(source.getAttacker() instanceof LivingEntity)){
            return;
        }

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (playerEntity != null) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);


            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.STURDY_SHULKER).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.STURDY_SHULKER).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.STURDY_SHULKER).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.STURDY_SHULKER).get(EquipmentSlot.FEET).asItem()) {

                ProjectileEffectHelper.fireShulkerBulletAtNearbyEnemy(playerEntity, 10);
            }
        }
    }

    // Mixin for Teleportation Robes Effect
    @Inject(method = "jump", at = @At("HEAD"))
    public void onTeleportationRobesTeleport(CallbackInfo ci){
        if (!config.enableArmorEffect.get(TELEPORTATION_ROBES_EFFECT))
            return;
        if (!((Object)this instanceof ServerPlayerEntity))
            return;
        ServerPlayerEntity playerEntity = (ServerPlayerEntity) (Object) this;
        if (playerEntity != null) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);


            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TELEPORTATION).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TELEPORTATION).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TELEPORTATION).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TELEPORTATION).get(EquipmentSlot.FEET).asItem()) {
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
        if (!((Object)this instanceof ServerPlayerEntity))
            return;
        ServerPlayerEntity playerEntity = (ServerPlayerEntity) (Object) this;

        if (playerEntity != null) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);


            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.UNSTABLE).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.UNSTABLE).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.UNSTABLE).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.UNSTABLE).get(EquipmentSlot.FEET).asItem()) {
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

        if(!(source.getAttacker() instanceof PlayerEntity))return;

        PlayerEntity playerEntity = (PlayerEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (source.getSource() instanceof PlayerEntity) {
            if (amount != 0.0F) {
                if (playerEntity != null) {
                    ItemStack mainHandStack = playerEntity.getMainHandStack();
                    ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
                    ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
                    ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
                    ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);


                    if (mainHandStack != null && helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.FROST_BITE).get(EquipmentSlot.HEAD).asItem()
                            && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.FROST_BITE).get(EquipmentSlot.CHEST).asItem()
                            && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.FROST_BITE).get(EquipmentSlot.LEGS).asItem()
                            && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.FROST_BITE).get(EquipmentSlot.FEET).asItem()) {

                        ArmorEffects.applyFrostBiteStatus(playerEntity, target);

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

        if(!(source.getAttacker() instanceof LivingEntity))return;

        LivingEntity user = (LivingEntity) source.getAttacker();

        if (user != null) {
            ItemStack helmetStack = user.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = user.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = user.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = user.getEquippedStack(EquipmentSlot.FEET);


            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOURDIAN).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOURDIAN).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOURDIAN).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOURDIAN).get(EquipmentSlot.FEET).asItem()) {

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
            if (amount != 0.0F) {
                ItemStack helmetStack = targetedEntity.getEquippedStack(EquipmentSlot.HEAD);
                ItemStack chestStack = targetedEntity.getEquippedStack(EquipmentSlot.CHEST);
                ItemStack legsStack = targetedEntity.getEquippedStack(EquipmentSlot.LEGS);
                ItemStack feetStack = targetedEntity.getEquippedStack(EquipmentSlot.FEET);

                if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAULDRON).get(EquipmentSlot.HEAD).asItem()
                        && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAULDRON).get(EquipmentSlot.CHEST).asItem()
                        && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAULDRON).get(EquipmentSlot.LEGS).asItem()
                        && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAULDRON).get(EquipmentSlot.FEET).asItem()) {

                    float overflowRand = targetedEntity.getRandom().nextFloat();
                    if (overflowRand <= 0.15F) {
                        ArmorEffects.applyCauldronsOverflow(targetedEntity);
                    }
                }
            }
        }
    }
}
