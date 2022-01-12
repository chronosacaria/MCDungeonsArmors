package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.*;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.effects.EnchantmentEffects;
import chronosacaria.mcda.entities.SummonedBeeEntity;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.EnchantsRegistry;
import chronosacaria.mcda.registry.SummonedEntityRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
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

    // Mixins for apply damage Effects and Enchantments
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
    public void mcdaApplyDamageEffects(DamageSource source, float amount, CallbackInfo info) {

        if (!((Object) this instanceof LivingEntity target))
            return;

        if (config.enableArmorEffect.get(LEADER_OF_THE_PACK))
            ArmorEffects.leaderOfThePackEffect(target, source, amount);

        if(!(source.getAttacker() instanceof PlayerEntity playerEntity))
            return;
        if (!(source.getSource() instanceof PlayerEntity))
            return;

        if (amount != 0.0F) {
            if (config.enableEnchantment.get(FIRE_FOCUS)) {
                EnchantmentEffects.applyFireFocusDamage(playerEntity, target, amount);
            }

            if (source.isMagic()){
                if (config.enableEnchantment.get(POISON_FOCUS)) {
                    EnchantmentEffects.applyPoisonFocusDamage(playerEntity, target, amount);
                }
            }

            ItemStack mainHandStack = playerEntity.getMainHandStack();
            if (!mainHandStack.isEmpty()) {
                if (config.enableArmorEffect.get(TITAN_SHROUD_EFFECTS))
                    ArmorEffects.applyTitanShroudStatuses(playerEntity, target);
                if (config.enableArmorEffect.get(FROST_BITE_EFFECT))
                    ArmorEffects.applyFrostBiteStatus(playerEntity, target);
                if (config.enableArmorEffect.get(GHOST_KINDLING))
                    ArmorEffects.applyGhostKindlingEffect(playerEntity, target);

                if(!source.isProjectile()) {
                    if (config.enableArmorEffect.get(SPLENDID_ATTACK))
                        ArmorEffects.applySplendidAoEAttackEffect(playerEntity, target);
                    if (config.enableArmorEffect.get(GILDED_HERO))
                        ArmorEffects.gildedHeroDamageBuff(playerEntity, target);
                }
            }
        }
    }

    // Mixins for apply movement Effects and Enchantments. Only Fire Trail rn
    @Inject(method = "applyMovementEffects", at = @At("HEAD"))
    protected void applyFireTrailEffects(BlockPos blockPos, CallbackInfo ci){
        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        EnchantmentEffects.applyFireTrail(playerEntity, blockPos);
    }

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

    // Mixins for Armor and Enchantment Effects on Damage at HEAD
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void onMCDADamageEffects(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        // Mixins for Armour and Enchantment Effects on Damage for PlayerEntities
        if ((Object) this instanceof PlayerEntity playerEntity) {

            if (source.getAttacker() instanceof LivingEntity) {
                if (config.enableEnchantment.get(HEAL_ALLIES))
                    AOEHelper.healNearbyAllies(playerEntity, amount);
                if (config.enableArmorEffect.get(WITHERED))
                    ArmorEffects.applyWithered(playerEntity, (LivingEntity) source.getAttacker());
                if (config.enableArmorEffect.get(SHULKER_LIKE))
                    ProjectileEffectHelper.fireShulkerBulletAtNearbyEnemy(playerEntity);
            }

            if (this.lastDamageTaken >= 0) {

                if (source.getAttacker() instanceof LivingEntity || source.isProjectile()) {
                    if (config.enableArmorEffect.get(SOULDANCER_GRACE)) {
                        if (ArmorEffects.souldancerGraceEffect(playerEntity))
                            cir.cancel();
                    }
                }
                if (config.enableArmorEffect.get(NIMBLE_TURTLE_EFFECTS))
                    ArmorEffects.applyNimbleTurtleEffects(playerEntity);
            }
        }
        // Mixins for Armour and Enchantment Effects on Damage for LivingEntities
        if ((Object) this instanceof LivingEntity livingEntity) {

            if((source.getAttacker() instanceof LivingEntity)) {
                if (config.enableArmorEffect.get(CAULDRONS_OVERFLOW))
                    ArmorEffects.applyCauldronsOverflow(livingEntity, amount);
            }
        }
    }

    @Inject(method = "damage", at = @At("HEAD"))
    public void summonBeesOnDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        if (!(source.getAttacker() instanceof LivingEntity)) return;

        LivingEntity targetedEntity = (LivingEntity) (Object) this;

        if (amount != 0.0F) {
            if (!config.enableArmorEffect.get(BUZZY_HIVE))
                return;
            if (hasArmorSet(targetedEntity, ArmorSets.BEEHIVE)) {
                float beeSummonChance = targetedEntity.getRandom().nextFloat();
                if (beeSummonChance <= 0.15F) {
                    SummonedBeeEntity summonedBeeEntity = summonedBee.create(world);
                    if (summonedBeeEntity != null) {
                        summonedBeeEntity.setSummoner(targetedEntity);
                        summonedBeeEntity.refreshPositionAndAngles(this.getX(), this.getY() + 1, this.getZ(), 0, 0);
                        world.spawnEntity(summonedBeeEntity);
                    }
                }
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
            if (config.enableArmorEffect.get(CURIOUS_TELEPORTATION)) {
                ArmorEffects.applyCuriousTeleportationEffect(playerEntity, attacker);
            }
        }
    }

    // Shadow Walker Armor Negate Fall Damage
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

    // Mixin for on death Effects and Enchantments. Only Gourdian's Hatred rn
    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onGourdiansHatredKill(DamageSource source, CallbackInfo ci) {

        if(!(source.getAttacker() instanceof LivingEntity user))return;

        if (user != null) {
            if (config.enableArmorEffect.get(GOURDIANS_HATRED))
                ArmorEffects.applyGourdiansHatredStatus(user);
        }
    }

    // Mixins for Armor and Enchantment Effects on Tick
    @Inject(method = "tick", at = @At("HEAD"))
    private void mcdaTickEffects(CallbackInfo ci){
        // Mixins for Armour and Enchantment Effects on Tick for PlayerEntities
        if((Object) this instanceof PlayerEntity playerEntity) {

            if (config.enableArmorEffect.get(FLUID_FREEZING))
                ArmorEffects.applyFluidFreezing(playerEntity);

            ArmorEffects.applyThiefInvisibilityTick(playerEntity);

            if (config.enableArmorEffect.get(SYLVAN_PRESENCE) && playerEntity.isAlive() && playerEntity.isSneaking() && (hasRobeWithHatSet(playerEntity, ArmorSets.VERDANT)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SYLVAN_PRESENCE)
                    || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == SYLVAN_PRESENCE))
                    && world.getTime() % 20 == 0) {
                ArmorEffects.applySylvanPresence(playerEntity);
            }
        }
        // Mixins for Armour and Enchantment Effects on Tick for LivingEntities
        if((Object)this instanceof LivingEntity livingEntity) {

            if (config.enableEnchantment.get(LUCKY_EXPLORER) && livingEntity.isAlive() && livingEntity.isOnGround() && world.getTime() % 50 == 0) {
                EnchantmentEffects.applyLuckyExplorer(livingEntity);
            }
        }
    }

    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void armorClimbing(CallbackInfoReturnable<Boolean> cir){
        if(!((Object) this instanceof PlayerEntity playerEntity)) return;

        if (playerEntity.isAlive()){
            // Spider Armour Climbing
            if (config.enableArmorEffect.get(SPIDER_CLIMBING)
                    && this.horizontalCollision
                    && (hasArmorSet(playerEntity, ArmorSets.SPIDER)
                    || (ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SPIDER_CLIMBING)
                    || (ArmorEffects.PURPLE_ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == SPIDER_CLIMBING))){
                cir.setReturnValue(true);
            }

            // Rugged Armour Climbing
            if (config.enableArmorEffect.get(RUGGED_CLIMBING)
                    && hasArmorSet(playerEntity, ArmorSets.RUGGED_CLIMBING_GEAR)){
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

                    double f = 0.1D;
                    double x = MathHelper.clamp(this.getVelocity().x, -f, f);
                    double z = MathHelper.clamp(this.getVelocity().z, -f, f);
                    double y = Math.max(this.getVelocity().y, -f);

                    if (y < 0.0D && !this.getBlockStateAtPos().isOf(Blocks.SCAFFOLDING) && this.isSneaking()) {
                        y = 0.0D;
                    } else if (this.horizontalCollision && !this.getBlockStateAtPos().isOf(Blocks.SCAFFOLDING)){
                        x /= 3.5D;
                        y = f/2;
                        z /= 3.5D;
                    }
                    this.setVelocity(x, y, z);
                }
            }
        }
    }

    // Mixin for Teleportation Robe Effect
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

    // Mixin for Unstable Robe Effect
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

    @Inject(method = "jump", at = @At("HEAD"))
    public void onEmberJump(CallbackInfo ci){
        if (!((Object) this instanceof ServerPlayerEntity playerEntity))
            return;
        if (playerEntity != null) {
            if (config.enableArmorEffect.get(EMBER_JUMP))
                ArmorEffects.applyEmberJumpEffect(playerEntity);
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

    // Mixin for Knockback Resistance for Stalwart Bulwark Effect
    @SuppressWarnings("ModifyVariableMayBeArgsOnly")
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

    @SuppressWarnings("ModifyVariableMayBeArgsOnly")
    @ModifyVariable(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;" +
            "Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"))
    private StatusEffectInstance troubadoursCharismaModifyStatusEffect(StatusEffectInstance statusEffectInstance){

        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity instanceof ServerPlayerEntity && config.enableArmorEffect.get(TROUBADOURS_CHARISMA)) {
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
