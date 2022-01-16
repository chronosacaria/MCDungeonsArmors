package chronosacaria.mcda.effects;

import chronosacaria.mcda.api.AOECloudHelper;
import chronosacaria.mcda.api.AOEHelper;
import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.entities.SummonedBeeEntity;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.mixin.PlayerTeleportationStateAccessor;
import chronosacaria.mcda.registry.SoundsRegistry;
import chronosacaria.mcda.registry.StatusEffectsRegistry;
import chronosacaria.mcda.registry.SummonedEntityRegistry;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.*;

import static chronosacaria.mcda.api.CleanlinessHelper.*;
import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.*;

public class ArmorEffects {

    public static EntityType<SummonedBeeEntity> summonedBee = SummonedEntityRegistry.SUMMONED_BEE_ENTITY;

    public static final List<StatusEffect> TITAN_SHROUD_STATUS_EFFECTS_LIST =
            List.of(StatusEffects.HUNGER, StatusEffects.NAUSEA, StatusEffects.BLINDNESS,
                    StatusEffects.MINING_FATIGUE, StatusEffects.SLOWNESS,
                    StatusEffects.UNLUCK, StatusEffects.WEAKNESS);

    public static final List<ItemStack> CAULDRONS_OVERFLOW_LIST =
            List.of(PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH),
                    PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS),
                    PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY));

    // DO NOT CHANGE THE ORDER OF THESE ARMOUR EFFECTS
    public static final List<ArmorEffectID> ARMOR_EFFECT_ID_LIST =
            List.of(MYSTERY_EFFECTS, CURIOUS_TELEPORTATION, FIRE_RESISTANCE, FLUID_FREEZING, FROST_BITE_EFFECT,
                    GHOST_KINDLING, GOURDIANS_HATRED, HASTE, HERO_OF_THE_VILLAGE, INVISIBILITY, LEADER_OF_THE_PACK,
                    LUCK, NIMBLE_TURTLE_EFFECTS, NO_FALL_DAMAGE, RENEGADES_RUSH, SHULKER_LIKE, SLOW_FALLING,
                    SPIDER_CLIMBING, SPRINTING, STALWART_BULWARK, SYLVAN_PRESENCE, WATER_BREATHING, WEB_WALKING, WITHERED);

    public static final List<ArmorEffectID> RED_ARMOR_EFFECT_ID_LIST =
            List.of(MYSTERY_EFFECTS, FIRE_RESISTANCE, GHOST_KINDLING, GOURDIANS_HATRED, LEADER_OF_THE_PACK,
                    RENEGADES_RUSH,STALWART_BULWARK, WITHERED);

    public static final List<ArmorEffectID> GREEN_ARMOR_EFFECT_ID_LIST =
            List.of(MYSTERY_EFFECTS, HASTE, HERO_OF_THE_VILLAGE, LUCK, NO_FALL_DAMAGE, SYLVAN_PRESENCE);

    public static final List<ArmorEffectID> BLUE_ARMOR_EFFECT_ID_LIST =
            List.of(MYSTERY_EFFECTS, FLUID_FREEZING, FROST_BITE_EFFECT, NIMBLE_TURTLE_EFFECTS, SLOW_FALLING, WATER_BREATHING);

    public static final List<ArmorEffectID> PURPLE_ARMOR_EFFECT_ID_LIST =
            List.of(MYSTERY_EFFECTS, CURIOUS_TELEPORTATION, INVISIBILITY, SHULKER_LIKE, SPIDER_CLIMBING,
                    SOULDANCER_GRACE, SPRINTING, WEB_WALKING);

    public static int applyMysteryArmorEffect(LivingEntity livingEntity, ArmorSets armorSets) {
        if (!config.enableArmorEffect.get(MYSTERY_EFFECTS))
            return 0;

        //Checks Mystery Armor Color
        if (hasArmorSet(livingEntity, armorSets)){

            ItemStack helmetStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestplateStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack leggingsStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack bootsStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);

            int[] domArr = {0,0,0,0};

            if (helmetStack.getNbt().get("dominance") != null) {
                domArr[0] = helmetStack.getNbt().getInt("dominance");
            }
            if (chestplateStack.getNbt().get("dominance") != null) {
                domArr[1] = chestplateStack.getNbt().getInt("dominance");
            }
            if (leggingsStack.getNbt().get("dominance") != null) {
                domArr[2] = leggingsStack.getNbt().getInt("dominance");
            }
            if (bootsStack.getNbt().get("dominance") != null) {
                domArr[3] = bootsStack.getNbt().getInt("dominance");
            }

            int maxDOM = domArr[0];
            int stackTracker = 0;

            for (int i = 1; i < domArr.length; i++) {
                if (domArr[i] > maxDOM) {
                    maxDOM = domArr[i];
                    stackTracker = i;
                }
            }

            switch (stackTracker) {
                case 0 -> {
                    return helmetStack.getNbt().getInt("mystery_effect");
                }
                case 1 -> {
                    return chestplateStack.getNbt().getInt("mystery_effect");
                }
                case 2 -> {
                    return leggingsStack.getNbt().getInt("mystery_effect");
                }
                case 3 -> {
                    return bootsStack.getNbt().getInt("mystery_effect");
                }
            }
        }
        return 0;
    }

    // Effects for LivingEntityMixin
    public static void endermanLikeTeleportEffect(LivingEntity livingEntity) {
        World world = livingEntity.getEntityWorld();

        if (!world.isClient) {

            if (livingEntity.hasVehicle())
                livingEntity.stopRiding();

            double xpos = livingEntity.getX();
            double ypos = livingEntity.getY();
            double zpos = livingEntity.getZ();

            double teleportX;
            double teleportY;
            double teleportZ;
            int i = 0;

            do {
                double xDiff = (livingEntity.getRandom().nextDouble(0.5D) + 0.5D) * 32.0D;
                double zDiff = (livingEntity.getRandom().nextDouble(0.5D) + 0.5D) * 32.0D;
                teleportX = livingEntity.getRandom().nextInt() % 2 == 0 ?
                        xpos + xDiff :
                        xpos - xDiff;
                teleportY =
                        MathHelper.clamp(ypos + (double) (livingEntity.getRandom().nextInt(16) - 8),
                                0.0D, world.getHeight() - 1);
                teleportZ = livingEntity.getRandom().nextInt() % 2 == 0 ?
                        zpos + zDiff :
                        zpos - zDiff;
                i++;

            } while (!livingEntity.teleport(teleportX, teleportY, teleportZ, true) && i != 16);

            if (i == 16 && livingEntity.getX() == xpos && livingEntity.getY() == ypos && livingEntity.getZ() == zpos)
                return;
            SoundEvent soundEvent = livingEntity instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT :
                    SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
            livingEntity.world.playSound(
                    null,
                    livingEntity.getX(),
                    livingEntity.getY(),
                    livingEntity.getZ(),
                    soundEvent,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F);
        }
    }

    public static void controlledTeleportEffect(LivingEntity livingEntity) {
        Vec3d target = raytraceForTeleportation(livingEntity);

        if (!livingEntity.getEntityWorld().isClient /*&& target != null*/) {

            if (livingEntity.hasVehicle())
                livingEntity.stopRiding();

            if (livingEntity.teleport(target.x, target.y, target.z, true)) {
                SoundEvent soundEvent = livingEntity instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT :
                        SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                livingEntity.world.playSound(
                        null,
                        livingEntity.getX(),
                        livingEntity.getY(),
                        livingEntity.getZ(),
                        soundEvent,
                        SoundCategory.PLAYERS,
                        1.0F,
                        1.0F);
            }
        }
    }

    public static Vec3d raytraceForTeleportation(LivingEntity livingEntity) {
        World world = livingEntity.getEntityWorld();
        Vec3d eyeVec = livingEntity.getCameraPosVec(0f);
        Vec3d direction = livingEntity.getRotationVec(0f);
        Vec3d rayEnd = eyeVec.add(direction.x * 16, direction.y * 16, direction.z * 16);
        BlockHitResult result = world.raycast(new RaycastContext(eyeVec, rayEnd, RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE, livingEntity));

        BlockPos rayResult = result.getBlockPos().offset(result.getSide());

        boolean positionIsFree = positionIsClear(world, rayResult);

        if (!result.isInsideBlock()) {

            while (!positionIsFree) {
                rayResult = livingEntity.getBlockPos();
                positionIsFree = positionIsClear(world, rayResult) && world.raycast(new RaycastContext(eyeVec,
                        Vec3d.ofCenter(rayResult.up()),
                        RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, livingEntity)).getType() == HitResult.Type.MISS;
                if (rayResult.getY() <= -60)
                    break;
            }
        } else if (positionIsFree) {
            Vec3d.ofCenter(rayResult);
        }
        return Vec3d.ofCenter(rayResult);
    }

    private static boolean positionIsClear(World world, BlockPos pos) {
        return ((world.isAir(pos)
                || world.getBlockState(pos).getBlock() == Blocks.DEAD_BUSH
                || world.getBlockState(pos).getBlock() == Blocks.GRASS
                || (world.getBlockState(pos).getBlock() == Blocks.TALL_GRASS && world.getBlockState(pos.up()).getBlock() == Blocks.TALL_GRASS)
                || world.getBlockState(pos).getCollisionShape(world, pos).isEmpty())
                && (world.isAir(pos.up()) || world.getBlockState(pos.up()).getBlock() == Blocks.TALL_GRASS || world.getBlockState(pos.up()).getCollisionShape(world, pos.up()).isEmpty()));
    }

    public static void teleportationRobeTeleport(ServerPlayerEntity playerEntity) {
        if (hasArmorSet(playerEntity, ArmorSets.TELEPORTATION)) {
            if (playerEntity.isSneaking()) {
                ((PlayerTeleportationStateAccessor)playerEntity).setInTeleportationState(true);
                ArmorEffects.endermanLikeTeleportEffect(playerEntity);
            } else {
                ((PlayerTeleportationStateAccessor)playerEntity).setInTeleportationState(false);
            }
        }
    }

    public static void unstableRobeTeleport(ServerPlayerEntity playerEntity){
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

    public static void applyFluidFreezing(PlayerEntity playerEntity) {

        if (!playerEntity.isAlive())
            return;

        if (hasArmorSet(playerEntity, ArmorSets.FROST)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == FLUID_FREEZING)
                || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == FLUID_FREEZING)){
            // From FrostWalkerEnchantment
            if (!playerEntity.isOnGround()) {
                return;
            }

            float f = (float) Math.min(16, 2 + 1);
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            World world = playerEntity.getEntityWorld();
            BlockPos blockPos = playerEntity.getBlockPos();

            for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-f, -1.0D, -f), blockPos.add(f, -1.0D, f))) {
                if (blockPos2.isWithinDistance(playerEntity.getPos(), f)) {
                    mutable.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                    BlockState blockState2 = world.getBlockState(mutable);
                    if (blockState2.isAir()) {
                        BlockState blockState3 = world.getBlockState(blockPos2);
                        // Transform Water
                        BlockState blockState = Blocks.FROSTED_ICE.getDefaultState();
                        if (blockState3.getMaterial() == Material.WATER && blockState3.get(FluidBlock.LEVEL) == 0 && blockState.canPlaceAt(world, blockPos2) && world.canPlace(blockState, blockPos2, ShapeContext.absent())) {
                            world.setBlockState(blockPos2, blockState);
                            world.createAndScheduleBlockTick(blockPos2, Blocks.FROSTED_ICE, MathHelper.nextInt(playerEntity.getRandom(), 60, 120));
                        }
                        // Transform Lava
                        blockState = Blocks.CRYING_OBSIDIAN.getDefaultState();
                        if (blockState3.getMaterial() == Material.LAVA && blockState3.get(FluidBlock.LEVEL) == 0 && blockState.canPlaceAt(world, blockPos2) && world.canPlace(blockState, blockPos2, ShapeContext.absent())) {
                            world.setBlockState(blockPos2, blockState);
                            world.createAndScheduleBlockTick(blockPos2, Blocks.CRYING_OBSIDIAN, MathHelper.nextInt(playerEntity.getRandom(), 60, 120));
                        }
                    }
                }
            }
        }
    }

    public static void applyThiefInvisibilityTick(PlayerEntity playerEntity) {
        if (hasArmorSet(playerEntity, ArmorSets.THIEF)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == INVISIBILITY)
                || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == INVISIBILITY))
            playerEntity.setInvisible(playerEntity.isSneaking());
    }

    public static void applyWithered(PlayerEntity playerEntity, LivingEntity attacker) {
        if (attacker == null)
            return;
        if (!playerEntity.isAlive())
            return;

        if (hasArmorSet(playerEntity, ArmorSets.WITHER)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == WITHERED)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == WITHERED)) {
            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 0));
        }
    }

    public static void applyNimbleTurtleEffects(PlayerEntity playerEntity) {
        if (!playerEntity.isAlive())
            return;

        if (hasArmorSet(playerEntity, ArmorSets.NIMBLE_TURTLE)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == NIMBLE_TURTLE_EFFECTS)
                || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == NIMBLE_TURTLE_EFFECTS)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 1, false,
                    false));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 1, false,
                    false));
        }

    }

    public static void applyTitanShroudStatuses(PlayerEntity playerEntity, LivingEntity target) {
        if (hasArmorSet(playerEntity, ArmorSets.TITAN)) {
            StatusEffect titanStatusEffect =
                    TITAN_SHROUD_STATUS_EFFECTS_LIST.get(playerEntity.getRandom().nextInt(TITAN_SHROUD_STATUS_EFFECTS_LIST.size()));
            target.addStatusEffect(new StatusEffectInstance(titanStatusEffect, 60, 0));
        }
    }

    public static void applyFrostBiteStatus(PlayerEntity playerEntity, LivingEntity target) {
        if (hasArmorSet(playerEntity, ArmorSets.FROST_BITE)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == FROST_BITE_EFFECT)
                || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == FROST_BITE_EFFECT)) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.FREEZING, 60, 0, true, true,
                        false));
        }
    }

    public static void applyGourdiansHatredStatus(LivingEntity livingEntity) {
        if (!livingEntity.isAlive())
            return;

        if (hasArmorSet(livingEntity, ArmorSets.GOURDIAN)
                || (ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(livingEntity, ArmorSets.MYSTERY)) == GOURDIANS_HATRED)
                || (RED_ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect( livingEntity, ArmorSets.RED_MYSTERY)) == GOURDIANS_HATRED)) {
            float hatredRand = livingEntity.getRandom().nextFloat();
            if (hatredRand <= 0.15F) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1));
            }
        }
    }

    public static void applyCauldronsOverflow(LivingEntity targetedEntity) {
        if (targetedEntity == null)
            return;

        if (hasArmorSet(targetedEntity, ArmorSets.CAULDRON)) {
            float overflowRand = targetedEntity.getRandom().nextFloat();
            if (overflowRand <= 0.15F) {
                ItemStack potionToDrop =
                        CAULDRONS_OVERFLOW_LIST.get(targetedEntity.getRandom().nextInt(CAULDRONS_OVERFLOW_LIST.size()));
                ItemEntity potion = new ItemEntity(targetedEntity.world, targetedEntity.getX(), targetedEntity.getY(),
                        targetedEntity.getZ(), potionToDrop);
                targetedEntity.world.spawnEntity(potion);
            }
        }

    }

    public static void applyCuriousTeleportationEffect(PlayerEntity playerEntity, LivingEntity target) {
        if (!playerEntity.isAlive())
            return;

        if (hasArmorSet(playerEntity, ArmorSets.CURIOUS)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == CURIOUS_TELEPORTATION)
                || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == CURIOUS_TELEPORTATION)) {
            float teleportationRand = playerEntity.getRandom().nextFloat();
            if (teleportationRand <= 0.1F) {
                if (playerEntity.getRandom().nextInt() % 2 == 0)
                    endermanLikeTeleportEffect(playerEntity);
                else
                    endermanLikeTeleportEffect(target);
            }
        }
    }

    public static void applyGhostKindlingEffect(PlayerEntity playerEntity, LivingEntity target) {
        if (hasArmorSet(playerEntity, ArmorSets.GHOST_KINDLER)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == GHOST_KINDLING)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == GHOST_KINDLING)) {
            target.setOnFireFor(4);
        }
    }

    public static void applySylvanPresence(LivingEntity livingEntity) {
        World world = livingEntity.getWorld();

        if (world.getTime() % 20 != 0)
            return;
        if (!livingEntity.isSneaking())
            return;

        if (hasRobeWithHatSet(livingEntity, ArmorSets.VERDANT)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.MYSTERY)) == SYLVAN_PRESENCE)
                || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.GREEN_MYSTERY)) == SYLVAN_PRESENCE)) {
            float size = (float) Math.min(16, 2 + 1);
            BlockPos.Mutable mutablePosition = new BlockPos.Mutable();
            BlockPos blockPos = livingEntity.getBlockPos();

            for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-size, 0.0D, -size), blockPos.add(size, 0.0D, size))) {
                if (blockPos2.isWithinDistance(livingEntity.getPos(), size)) {
                    mutablePosition.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                    BlockState checkstate = world.getBlockState(blockPos2);
                    if (checkstate.getBlock() instanceof Fertilizable fertilizable) {
                        if (fertilizable.isFertilizable(world, blockPos2, checkstate, world.isClient)) {
                            if (world instanceof ServerWorld) {
                                if (fertilizable.canGrow(world, world.random, blockPos2, checkstate)) {
                                    fertilizable.grow((ServerWorld) world, world.random, blockPos2, checkstate);
                                    AOEHelper.addParticlesToBlock((ServerWorld) world, blockPos2,
                                            ParticleTypes.HAPPY_VILLAGER);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void applyEmberJumpEffect(LivingEntity livingEntity) {
        if (!hasRobeWithHatSet(livingEntity, ArmorSets.EMBER))
            return;
        if (!livingEntity.isSneaking())
            return;

        boolean playFireSound = false;

        for (LivingEntity nearbyEntity : AOEHelper.getAoeTargets(livingEntity, livingEntity, 6.0f)) {
            if (nearbyEntity instanceof Monster){
                nearbyEntity.setOnFireFor(5);
                playFireSound = true;
            }
        }
        if (playFireSound) {
            livingEntity.world.playSound(
                    null,
                    livingEntity.getX(),
                    livingEntity.getY(),
                    livingEntity.getZ(),
                    SoundEvents.ENTITY_BLAZE_SHOOT,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
            );
        }
    }

    public static void applySplendidAoEAttackEffect(PlayerEntity playerEntity, LivingEntity target) {
        if (!hasRobeSet(playerEntity, ArmorSets.SPLENDID))
            return;

        float splendidRand = playerEntity.getRandom().nextFloat();
        if (splendidRand <= 0.3f) {

            for (LivingEntity nearbyEntity : AOEHelper.getAoeTargets(target, playerEntity, 6.0f)){
                float damageToBeDone = (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                if (nearbyEntity instanceof IllagerEntity){
                    damageToBeDone = damageToBeDone * 1.5f;
                }
                if (nearbyEntity instanceof Monster){
                    nearbyEntity.damage(DamageSource.GENERIC, damageToBeDone);
                    nearbyEntity.world.playSound(
                            null,
                            nearbyEntity.getX(),
                            nearbyEntity.getY(),
                            nearbyEntity.getZ(),
                            SoundEvents.ENTITY_VEX_CHARGE,
                            SoundCategory.PLAYERS,
                            1.0f,
                            1.0f
                    );
                    AOEHelper.addParticlesToBlock((ServerWorld) nearbyEntity.world, nearbyEntity.getBlockPos(), ParticleTypes.ENCHANTED_HIT);
                }
            }
        }
    }

    public static void gildedHeroDamageBuff(PlayerEntity playerEntity, LivingEntity target) {
        if ((hasArmorSet(playerEntity, ArmorSets.GILDED))) {
            float gildedDamage =
                    (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            if (target instanceof IllagerEntity && playerEntity.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE))
                gildedDamage *= 1.5f;
            target.damage(DamageSource.GENERIC, gildedDamage);
        }
    }

    public static void leaderOfThePackEffect(LivingEntity target, DamageSource source, float amount) {
        if (!(source.getSource() instanceof TameableEntity petSrc) || !(petSrc.world instanceof ServerWorld serverWorld))
            return;
        if (!(petSrc.getOwner() instanceof PlayerEntity owner))
            return;

        if (hasArmorSet(owner, ArmorSets.BLACK_WOLF)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(MinecraftClient.getInstance().player, ArmorSets.MYSTERY)) == LEADER_OF_THE_PACK)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(MinecraftClient.getInstance().player, ArmorSets.RED_MYSTERY)) == LEADER_OF_THE_PACK)) {
            UUID petOwnerUUID = owner.getUuid();

            if (petOwnerUUID != null)
                if (serverWorld.getEntity(petOwnerUUID) instanceof LivingEntity)
                    target.damage(DamageSource.GENERIC, 1.5f * amount);
        }
    }

    public static boolean souldancerGraceEffect(PlayerEntity playerEntity) {
        if (!playerEntity.isAlive())
            return false;
        if (!hasArmorSet(playerEntity, ArmorSets.SOULDANCER))
            return false;

        float dodgeRand = playerEntity.getRandom().nextFloat();
        if (dodgeRand <= 0.3F) {
            // Dodge the damage
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
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 42, 0, false,
                    false));
            return true;
        }
        return  false;
    }

    public static boolean gildedGloryTotemEffect(LivingEntity livingEntity) {
        if (hasArmorSet(livingEntity, ArmorSets.GILDED)
                && livingEntity.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE)) {

            float[] armorPieceDurability = CleanlinessHelper.mcdaFindMostDamagedEquipment(livingEntity);
            int index = (int) armorPieceDurability[0];
            boolean toBreak = armorPieceDurability[index] <= 0.50f;
            switch (index) {
                case 1 -> CleanlinessHelper.mcdaDamageEquipment(livingEntity, EquipmentSlot.FEET, toBreak);
                case 2 -> CleanlinessHelper.mcdaDamageEquipment(livingEntity, EquipmentSlot.LEGS, toBreak);
                case 3 -> CleanlinessHelper.mcdaDamageEquipment(livingEntity, EquipmentSlot.CHEST, toBreak);
                case 4 -> CleanlinessHelper.mcdaDamageEquipment(livingEntity, EquipmentSlot.HEAD, toBreak);
            }
            CleanlinessHelper.onTotemDeathEffects(livingEntity);
            return true;
        }
        return false;
    }

    public static void buzzyHiveEffect(LivingEntity targetedEntity) {
        if (hasArmorSet(targetedEntity, ArmorSets.BEEHIVE)) {
            float beeSummonChance = targetedEntity.getRandom().nextFloat();
            if (beeSummonChance <= 0.15F) {
                World world = targetedEntity.getEntityWorld();
                SummonedBeeEntity summonedBeeEntity = summonedBee.create(world);
                if (summonedBeeEntity != null) {
                    summonedBeeEntity.setSummoner(targetedEntity);
                    summonedBeeEntity.refreshPositionAndAngles(targetedEntity.getX(), targetedEntity.getY() + 1, targetedEntity.getZ(), 0, 0);
                    world.spawnEntity(summonedBeeEntity);
                }
            }
        }
    }

    public static boolean spiderClimbing(PlayerEntity playerEntity) {
        return playerEntity.horizontalCollision
                && (hasArmorSet(playerEntity, ArmorSets.SPIDER)
                || (ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SPIDER_CLIMBING)
                || (ArmorEffects.PURPLE_ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == SPIDER_CLIMBING));
    }

    public static boolean ruggedClimbing(PlayerEntity playerEntity){
        if (hasArmorSet(playerEntity, ArmorSets.RUGGED_CLIMBING_GEAR)){
            // If Statement provided by Apace100; Thanks, Apace!
            if (CleanlinessHelper.mcdaBoundingBox(playerEntity, 0.01f)
                    || CleanlinessHelper.mcdaBoundingBox(playerEntity, -0.01f)) {
                playerEntity.setOnGround(true);
                playerEntity.onLanding();

                double f = 0.1D;
                double x = MathHelper.clamp(playerEntity.getVelocity().x, -f, f);
                double z = MathHelper.clamp(playerEntity.getVelocity().z, -f, f);
                double y = Math.max(playerEntity.getVelocity().y, -f);

                if (y < 0.0D && !playerEntity.getBlockStateAtPos().isOf(Blocks.SCAFFOLDING) && playerEntity.isSneaking()) {
                    y = 0.0D;
                } else if (playerEntity.horizontalCollision && !playerEntity.getBlockStateAtPos().isOf(Blocks.SCAFFOLDING)){
                    x /= 3.5D;
                    y = f/2;
                    z /= 3.5D;
                }
                playerEntity.setVelocity(x, y, z);
                return true;
            }
        }
        return false;
    }

    public static void arcticFoxesHighGround(PlayerEntity playerEntity, LivingEntity target, float amount){
        if (CleanlinessHelper.hasArmorSet(playerEntity, ArmorSets.ARCTIC_FOX)) {
            if (playerEntity.getVelocity().y < 0)
                target.damage(DamageSource.GENERIC, 1.5f * amount);
        }
    }

    // Effects for ServerPlayerEntityMixin
    public static void applyFireResistance(ServerPlayerEntity playerEntity) {
        if (!config.enableArmorEffect.get(FIRE_RESISTANCE))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.SPROUT) || hasArmorSet(playerEntity, ArmorSets.LIVING_VINES)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == FIRE_RESISTANCE)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == FIRE_RESISTANCE)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 42, 1,
                    false,false));
        }
    }

    public static void applyHaste(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(HASTE))
            return;

        if (playerEntity.getY() < 32.0F) {

            if (hasArmorSet(playerEntity, ArmorSets.CAVE_CRAWLER)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == HASTE)
                    || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == HASTE)) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 42, 0, false, false));
            }
        }
        if (playerEntity.getY() > 100.0F) {

            if (hasArmorSet(playerEntity, ArmorSets.HIGHLAND)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == HASTE)
                    || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == HASTE)) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 42, 0, false, false));
            }
        }
    }

    public static void applyHeroOfTheVillage(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(HERO_OF_THE_VILLAGE))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.HERO)
                || (hasArmorSet(playerEntity, ArmorSets.GILDED)
                    && config.enableArmorEffect.get(GILDED_HERO)
                    && playerEntity.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE))
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == HERO_OF_THE_VILLAGE)
                || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == HERO_OF_THE_VILLAGE)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 42, 0, false,
                    false));
        }
    }

    public static void applyHungerPain(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(HUNGER))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.HUNGRY_HORROR)) {

            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 42, 1, false,
                    false));

            int foodLevel = playerEntity.getHungerManager().getFoodLevel();

            if(foodLevel <= 18){

                if (foodLevel > 12){
                    //apply Strength 1
                    StatusEffectInstance snacky = new StatusEffectInstance(StatusEffects.STRENGTH, 42, 0,
                            false, true);
                    playerEntity.addStatusEffect(snacky);
                } else if (foodLevel > 6){
                    //apply Strength 2
                    StatusEffectInstance tummyGrumbles = new StatusEffectInstance(StatusEffects.STRENGTH, 42, 1,
                            false, true);
                    playerEntity.removeStatusEffect(StatusEffects.STRENGTH);
                    playerEntity.addStatusEffect(tummyGrumbles);
                } else {
                    //Sooner Starvation
                    playerEntity.damage(DamageSource.STARVE, 0.5f);
                    //apply Strength 3
                    StatusEffectInstance hungerPain = new StatusEffectInstance(StatusEffects.STRENGTH, 42, 2,
                            false, true);
                    playerEntity.removeStatusEffect(StatusEffects.STRENGTH);
                    playerEntity.addStatusEffect(hungerPain);
                }
            }
        }
    }

    public static void applyInvisibility(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(INVISIBILITY))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.THIEF)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == INVISIBILITY)
                || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == INVISIBILITY)) {
            if (playerEntity.isSneaking()) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 42, 0,
                        false, false));
            }
        }
    }

    public static void applyLuck(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(LUCK))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.OPULENT)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == LUCK)
                || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == LUCK)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 42, 0, false,
                    false));
        }
    }

    public static void applySprintingSpeed(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(SPRINTING))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.SHADOW_WALKER)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SPRINTING)
                || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == SPRINTING)) {
            if (playerEntity.isSprinting()) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 42, 0, false,
                        false));
            }
        }
    }

    public static void applySlowFalling(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(SLOW_FALLING))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.PHANTOM) || hasArmorSet(playerEntity, ArmorSets.FROST_BITE)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SLOW_FALLING)
                || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == SLOW_FALLING)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 42, 0, false,
                    false));
        }
    }

    public static void applyStalwartBulwarkResistanceEffect(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(STALWART_BULWARK))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.STALWART_MAIL)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == STALWART_BULWARK)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == STALWART_BULWARK)) {
            if (playerEntity.isSneaking()) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 42, 0, false,
                        false));
            }
        }
    }

    public static void applyWaterBreathing(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(WATER_BREATHING))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.GLOW_SQUID)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == WATER_BREATHING)
                || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == WATER_BREATHING)) {
            if (playerEntity.isSubmergedInWater()) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 42, 0,
                        false, false));
            }
        }
    }

    public static void applyRenegadesRushEffect(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(RENEGADES_RUSH))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.RENEGADE)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == RENEGADES_RUSH)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == RENEGADES_RUSH)) {
            if (playerEntity.isSprinting()) {
                StatusEffectInstance strength = new StatusEffectInstance(StatusEffects.STRENGTH, 42, 2, false,
                        false);
                playerEntity.addStatusEffect(strength);
            }
        }
    }

    public static void applyLevitationRemoval(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(SHULKER_LIKE))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.STURDY_SHULKER)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SHULKER_LIKE)
                || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == SHULKER_LIKE)){
            if (playerEntity.hasStatusEffect(StatusEffects.LEVITATION))
                playerEntity.removeStatusEffect(StatusEffects.LEVITATION);
        }
    }

    public static void sweetBerrySpeed(ServerPlayerEntity playerEntity){
        if (config.enableArmorEffect.get(SWEET_BERRY_SPEED)) {
            if (hasArmorSet(playerEntity, ArmorSets.ARCTIC_FOX)) {
                if (playerEntity.getMainHandStack().getItem() == Items.SWEET_BERRIES) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 0, false, false));
                }
            }
        }
    }

    //To be removed for less sad pouncing
    public static void foxSneakJumpBoost(ServerPlayerEntity playerEntity){
        if (config.enableArmorEffect.get(ArmorEffectID.FOX_SNEAK_JUMP_BOOST)) {
            if (playerEntity.world.getTime() % 100 == 0) {
                if (CleanlinessHelper.hasArmorSet(playerEntity, ArmorSets.FOX)
                        || CleanlinessHelper.hasArmorSet(playerEntity, ArmorSets.ARCTIC_FOX)) {
                    if (playerEntity.isSneaking()) {
                        StatusEffectInstance jumpBoost = new StatusEffectInstance(StatusEffects.JUMP_BOOST, 60, 0,
                                false, true);
                        playerEntity.addStatusEffect(jumpBoost);
                    }
                }
            }
        }
    }
}