package chronosacaria.mcda.effects;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.AOECloudHelper;
import chronosacaria.mcda.api.AOEHelper;
import chronosacaria.mcda.api.AbilityHelper;
import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.entities.SummonedBeeEntity;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.mixin.PlayerTeleportationStateAccessor;
import chronosacaria.mcda.registry.SoundsRegistry;
import chronosacaria.mcda.registry.StatusEffectsRegistry;
import chronosacaria.mcda.registry.SummonedEntityRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
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
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

import static chronosacaria.mcda.api.CleanlinessHelper.*;
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
                    SPIDER_CLIMBING, SPRINTING, STALWART_BULWARK, SYLVAN_PRESENCE, WATER_BREATHING, WEB_WALKING,
                    WITHERED, GHOST_KINDLER_TRAIL);

    public static final List<ArmorEffectID> RED_ARMOR_EFFECT_ID_LIST =
            List.of(MYSTERY_EFFECTS, FIRE_RESISTANCE, GHOST_KINDLING, GOURDIANS_HATRED, LEADER_OF_THE_PACK,
                    RENEGADES_RUSH,STALWART_BULWARK, WITHERED, GHOST_KINDLER_TRAIL);

    public static final List<ArmorEffectID> GREEN_ARMOR_EFFECT_ID_LIST =
            List.of(MYSTERY_EFFECTS, HASTE, HERO_OF_THE_VILLAGE, LUCK, NO_FALL_DAMAGE, SYLVAN_PRESENCE);

    public static final List<ArmorEffectID> BLUE_ARMOR_EFFECT_ID_LIST =
            List.of(MYSTERY_EFFECTS, FLUID_FREEZING, FROST_BITE_EFFECT, NIMBLE_TURTLE_EFFECTS, SLOW_FALLING, WATER_BREATHING);

    public static final List<ArmorEffectID> PURPLE_ARMOR_EFFECT_ID_LIST =
            List.of(MYSTERY_EFFECTS, CURIOUS_TELEPORTATION, INVISIBILITY, SHULKER_LIKE, SPIDER_CLIMBING,
                    SOULDANCER_GRACE, SPRINTING, WEB_WALKING);

    public static int applyMysteryArmorEffect(LivingEntity livingEntity, ArmorSets armorSets) {
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(MYSTERY_EFFECTS))
            return 0;

        //Checks Mystery Armor Color
        if (hasArmorSet(livingEntity, armorSets)){

            ItemStack helmetStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestplateStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack leggingsStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack bootsStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);

            int[] domArr = {0,0,0,0};

            domArr[0] = helmetStack.getOrCreateNbt().getInt("dominance");
            domArr[1] = chestplateStack.getOrCreateNbt().getInt("dominance");
            domArr[2] = leggingsStack.getOrCreateNbt().getInt("dominance");
            domArr[3] = bootsStack.getOrCreateNbt().getInt("dominance");

            switch (mcdaIndexOfLargestElementInArray(domArr)) {
                case 0: return helmetStack.getOrCreateNbt().getInt("mystery_effect");
                case 1: return chestplateStack.getOrCreateNbt().getInt("mystery_effect");
                case 2: return leggingsStack.getOrCreateNbt().getInt("mystery_effect");
                case 3: return bootsStack.getOrCreateNbt().getInt("mystery_effect");
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

            double xPos = livingEntity.getX();
            double yPos = livingEntity.getY();
            double zPos = livingEntity.getZ();

            double teleportX;
            double teleportY;
            double teleportZ;
            int i = 0;

            do {
                // TODO TEST TELEPORTATION FOR GRANULARITY AND ACCURACY
                double xDiff = ((livingEntity.getRandom().nextDouble() / 2) + 0.5D) * 32.0D;
                double zDiff = ((livingEntity.getRandom().nextDouble() / 2) + 0.5D) * 32.0D;
                teleportX = livingEntity.getRandom().nextInt() % 2 == 0 ?
                        xPos + xDiff :
                        xPos - xDiff;
                teleportY =
                        MathHelper.clamp(yPos + (double) (livingEntity.getRandom().nextInt(16) - 8),
                                0.0D, world.getHeight() - 1);
                teleportZ = livingEntity.getRandom().nextInt() % 2 == 0 ?
                        zPos + zDiff :
                        zPos - zDiff;
                i++;

            } while (!livingEntity.teleport(teleportX, teleportY, teleportZ, true) && i != 16);

            if (i == 16 && livingEntity.getX() == xPos && livingEntity.getY() == yPos && livingEntity.getZ() == zPos)
                return;
            SoundEvent soundEvent = livingEntity instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT :
                    SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
            CleanlinessHelper.playCenteredSound(livingEntity, soundEvent, 1f, 1f);
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
                CleanlinessHelper.playCenteredSound(livingEntity, soundEvent, 1f, 1f);
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
                if (mcdaCooldownCheck(playerEntity, 40))
                    mcdaRandomArmorDamage(playerEntity, 0.10f, 4, false);

            } else ((PlayerTeleportationStateAccessor)playerEntity).setInTeleportationState(false);

        }
    }

    public static void unstableRobeTeleport(ServerPlayerEntity playerEntity){
        if (hasArmorSet(playerEntity, ArmorSets.UNSTABLE)) {
            if (playerEntity.isSneaking()) {
                ((PlayerTeleportationStateAccessor)playerEntity).setInTeleportationState(true);
                AOECloudHelper.spawnParticleCloud(playerEntity, playerEntity, 3.0F, 0, ParticleTypes.EXPLOSION);
                AOEHelper.causeExplosion(playerEntity, playerEntity, 5, 3.0f);
                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.controlledTeleportation){
                    ArmorEffects.controlledTeleportEffect(playerEntity);
                } else {
                    ArmorEffects.endermanLikeTeleportEffect(playerEntity);
                }
                if (mcdaCooldownCheck(playerEntity, 40))
                    mcdaRandomArmorDamage(playerEntity, 0.10f, 4, false);
            } else {
                ((PlayerTeleportationStateAccessor)playerEntity).setInTeleportationState(false);
            }
        }
    }

    public static void applyFluidFreezing(PlayerEntity playerEntity) {
        if (hasArmorSet(playerEntity, ArmorSets.FROST)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == FLUID_FREEZING)
                || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == FLUID_FREEZING)){
            // From FrostWalkerEnchantment
            if (!playerEntity.isOnGround())
                return;

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

    public static void applyTitanShroudStatuses(LivingEntity livingEntity, LivingEntity target) {
        if (hasArmorSet(livingEntity, ArmorSets.TITAN)) {
            StatusEffect titanStatusEffect =
                    TITAN_SHROUD_STATUS_EFFECTS_LIST.get(livingEntity.getRandom().nextInt(TITAN_SHROUD_STATUS_EFFECTS_LIST.size()));
            target.addStatusEffect(new StatusEffectInstance(titanStatusEffect, 60, 0));
        }
    }

    public static void applyFrostBiteStatus(LivingEntity livingEntity, LivingEntity target) {
        if (hasArmorSet(livingEntity, ArmorSets.FROST_BITE)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.MYSTERY)) == FROST_BITE_EFFECT)
                || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.BLUE_MYSTERY)) == FROST_BITE_EFFECT)) {
            if (percentToOccur(30)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.FREEZING, 60, 0, true, true,
                        false));
            }
        }
    }

    public static void applyGourdiansHatredStatus(LivingEntity livingEntity) {
        if (!livingEntity.isAlive())
            return;

        if (hasArmorSet(livingEntity, ArmorSets.GOURDIAN)
                || (ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect(livingEntity, ArmorSets.MYSTERY)) == GOURDIANS_HATRED)
                || (RED_ARMOR_EFFECT_ID_LIST.get(ArmorEffects.applyMysteryArmorEffect( livingEntity, ArmorSets.RED_MYSTERY)) == GOURDIANS_HATRED)) {
            if (percentToOccur(15)) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1));
            }
        }
    }

    public static void applyCauldronsOverflow(LivingEntity targetedEntity) {
        if (targetedEntity == null)
            return;

        if (hasArmorSet(targetedEntity, ArmorSets.CAULDRON)) {
            if (percentToOccur(15)) {
                ItemStack potionToDrop =
                        CAULDRONS_OVERFLOW_LIST.get(targetedEntity.getRandom().nextInt(CAULDRONS_OVERFLOW_LIST.size()));
                CleanlinessHelper.mcda$dropItem(targetedEntity, potionToDrop);
            }
        }

    }

    public static void applyCuriousTeleportationEffect(PlayerEntity playerEntity, LivingEntity target) {
        if (!playerEntity.isAlive())
            return;

        if (hasArmorSet(playerEntity, ArmorSets.CURIOUS)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == CURIOUS_TELEPORTATION)
                || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == CURIOUS_TELEPORTATION)) {
            if (percentToOccur(10)) {
                if (percentToOccur(50))
                    endermanLikeTeleportEffect(playerEntity);
                else
                    endermanLikeTeleportEffect(target);
            }
        }
    }

    public static void applyGhostKindlingEffect(LivingEntity livingEntity, LivingEntity target) {
        if (hasArmorSet(livingEntity, ArmorSets.GHOST_KINDLER)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.MYSTERY)) == GHOST_KINDLING)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(livingEntity, ArmorSets.RED_MYSTERY)) == GHOST_KINDLING)) {
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
            if (mcdaCooldownCheck(livingEntity, 40))
                mcdaRandomArmorDamage(livingEntity, 0.10f, 3, true);
            CleanlinessHelper.playCenteredSound(livingEntity, SoundEvents.ENTITY_BLAZE_SHOOT, 1f, 1f);
        }
    }

    public static void applySplendidAoEAttackEffect(LivingEntity livingEntity, LivingEntity target) {
        if (!hasRobeSet(livingEntity, ArmorSets.SPLENDID))
            return;

        if (percentToOccur(30)) {

            for (LivingEntity nearbyEntity : AOEHelper.getAoeTargets(target, livingEntity, 6.0f)){
                float damageToBeDone = (float) livingEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                if (nearbyEntity instanceof IllagerEntity){
                    damageToBeDone = damageToBeDone * 1.5f;
                }
                if (nearbyEntity instanceof Monster && nearbyEntity != target){
                    nearbyEntity.damage(DamageSource.GENERIC, damageToBeDone);

                    CleanlinessHelper.playCenteredSound(nearbyEntity, SoundEvents.ENTITY_VEX_CHARGE, 1f, 1f);
                    AOEHelper.addParticlesToBlock((ServerWorld) nearbyEntity.world, nearbyEntity.getBlockPos(), ParticleTypes.ENCHANTED_HIT);
                }
            }
        }
    }

    public static float gildedHeroDamageBuff(LivingEntity livingEntity, LivingEntity target) {
        if ((hasArmorSet(livingEntity, ArmorSets.GILDED))) {
            float gildedDamage =
                    (float) livingEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            if (target instanceof IllagerEntity && livingEntity.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE))
                return gildedDamage * 0.5f;
        }
        return 0;
    }

    public static float archersProwessDamageBuff(LivingEntity livingEntity) {
        if ((hasArmorSet(livingEntity, ArmorSets.ARCHER)))
            return 1.5f;
        return 1f;
    }

    public static float leaderOfThePackEffect(DamageSource source) {
        if (!(source.getSource() instanceof TameableEntity petSrc) || !(petSrc.world instanceof ServerWorld serverWorld))
            return 1f;
        if (!(petSrc.getOwner() instanceof PlayerEntity owner))
            return 1f;

        if (hasArmorSet(owner, ArmorSets.BLACK_WOLF)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(owner, ArmorSets.MYSTERY)) == LEADER_OF_THE_PACK)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(owner, ArmorSets.RED_MYSTERY)) == LEADER_OF_THE_PACK)) {
            UUID petOwnerUUID = owner.getUuid();

            if (petOwnerUUID != null)
                if (serverWorld.getEntity(petOwnerUUID) instanceof LivingEntity)
                    return 1.5f;
        }
        return 1f;
    }

    public static boolean souldancerGraceEffect(PlayerEntity playerEntity) {
        if (!playerEntity.isAlive())
            return false;
        if (!hasArmorSet(playerEntity, ArmorSets.SOULDANCER))
            return false;

        if (percentToOccur(30)) {
            // Dodge the damage
            CleanlinessHelper.playCenteredSound(playerEntity, SoundsRegistry.DODGE_SOUND_EVENT, 1f, 1f);
            AOECloudHelper.spawnParticleCloud(playerEntity, playerEntity, 0.5F, 0, ParticleTypes.CLOUD);
            // Apply Speed after dodge
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 42, 0, false, false));
            return true;
        }
        return  false;
    }

    public static boolean gildedGloryTotemEffect(LivingEntity livingEntity) {
        if (hasArmorSet(livingEntity, ArmorSets.GILDED)
                && livingEntity.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE)) {

            int index = mcdaFindHighestDurabilityEquipment(livingEntity);
            EquipmentSlot equipmentSlot = switch (index) {
                case 0 -> EquipmentSlot.FEET;
                case 1 -> EquipmentSlot.LEGS;
                case 2 -> EquipmentSlot.CHEST;
                case 3 -> EquipmentSlot.HEAD;
                // Never reached but make Java happy
                default -> throw new IllegalStateException("Unexpected value: " + index);
            };
            mcdaDamageEquipment(livingEntity,equipmentSlot, 0.5f);
            return true;
        }
        return false;
    }

    public static void buzzyHiveEffect(LivingEntity targetedEntity) {
        int beeSummonChance = 0;
        if (hasArmorSet(targetedEntity, ArmorSets.BEEHIVE))
            beeSummonChance = 30;
        if (hasArmorSet(targetedEntity, ArmorSets.BEENEST))
            beeSummonChance = 10;

        if (beeSummonChance == 0)
            return;
        if (percentToOccur(beeSummonChance)) {
            World world = targetedEntity.getEntityWorld();
            SummonedBeeEntity summonedBeeEntity = summonedBee.create(world);
            if (summonedBeeEntity != null) {
                summonedBeeEntity.setSummoner(targetedEntity);
                summonedBeeEntity.refreshPositionAndAngles(targetedEntity.getX(), targetedEntity.getY() + 1, targetedEntity.getZ(), 0, 0);
                world.spawnEntity(summonedBeeEntity);
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
            if (mcdaBoundingBox(playerEntity, 0.01f)
                    || mcdaBoundingBox(playerEntity, -0.01f)) {
                playerEntity.setOnGround(true);
                playerEntity.onLanding();

                double f = 0.1D;
                double x = MathHelper.clamp(playerEntity.getVelocity().x, -f, f);
                double z = MathHelper.clamp(playerEntity.getVelocity().z, -f, f);
                double y = Math.max(playerEntity.getVelocity().y, -f);

                if (y < 0.0D && !playerEntity.getBlockStateAtPos().isOf(Blocks.SCAFFOLDING) && playerEntity.isSneaking()) {
                    y = 0.0D;
                } else if (playerEntity.horizontalCollision
                        && !playerEntity.getBlockStateAtPos().isOf(Blocks.SCAFFOLDING)
                        && !playerEntity.getBlockStateAtPos().isOf(Blocks.VINE)) {
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

    public static float arcticFoxesHighGround(LivingEntity livingEntity){
        if (hasArmorSet(livingEntity, ArmorSets.ARCTIC_FOX)) {
            if (livingEntity.getVelocity().y < 0
                    && !livingEntity.isOnGround()
                    && !livingEntity.isHoldingOntoLadder())
                return 1.2f;
        }
        return 1f;
    }

    public static void ghostKindlerTrail(PlayerEntity playerEntity, BlockPos blockPos){
        if (hasArmorSet(playerEntity, ArmorSets.GHOST_KINDLER)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == GHOST_KINDLER_TRAIL)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == GHOST_KINDLER_TRAIL)) {

            for (LivingEntity nearbyEntity : AOEHelper.getAoeTargets(playerEntity, playerEntity, 3.0f)){
                if (nearbyEntity instanceof Monster){
                    if (blockPos.offset(playerEntity.getMovementDirection().getOpposite()).isWithinDistance(nearbyEntity.getPos(), 3)) {
                        nearbyEntity.setOnFireFor(5);
                        AOEHelper.addParticlesToBlock((ServerWorld) playerEntity.world, playerEntity.getBlockPos(),
                                ParticleTypes.FLAME);
                    }
                }
            }
        }
    }

    public static void foxPouncing(PlayerEntity playerEntity){
        if (hasArmorSet(playerEntity, ArmorSets.FOX)
                || hasArmorSet(playerEntity, ArmorSets.ARCTIC_FOX)) {
            if (!mcdaCheckHorizontalVelocity(playerEntity.getVelocity(), 0, true))
                return;
            if (!playerEntity.isSneaking() || !playerEntity.isOnGround())
                return;

            LivingEntity target = playerEntity.getEntityWorld().getClosestEntity(
                    AbilityHelper.getPotentialPounceTargets(playerEntity, 6.0f),
                    TargetPredicate.DEFAULT,
                    playerEntity,
                    playerEntity.getX(),
                    playerEntity.getY(),
                    playerEntity.getZ());

            if (target == null) return;

            // TODO Look into changing out brute force box to EntityDimensions#getBoxAt?
            if (mcdaCanTargetEntity(playerEntity, target)){

                Vec3d vecHorTargetDist = new Vec3d((target.getX() - playerEntity.getX()),
                        (target.getY() - playerEntity.getY()),(target.getZ() - playerEntity.getZ()));
                Vec3d vecVelHorTargetDist = vecHorTargetDist.normalize().multiply(vecHorTargetDist.horizontalLength()/6);

                playerEntity.setVelocity(vecVelHorTargetDist.x + target.getVelocity().x, 0.8,
                        vecVelHorTargetDist.z + target.getVelocity().z);
                // Thanks Apace!
                playerEntity.velocityModified = true;
                // Somehow make the player model move like the fox does?
            }
        }
    }

    // Effects for ServerPlayerEntityMixin
    public static void applyFireResistance(ServerPlayerEntity playerEntity) {
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(FIRE_RESISTANCE))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.SPROUT) || hasArmorSet(playerEntity, ArmorSets.LIVING_VINES)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == FIRE_RESISTANCE)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == FIRE_RESISTANCE)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 42, 1,
                    false,false));
        }
    }

    public static void applyHaste(ServerPlayerEntity playerEntity){
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(HASTE))
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
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(HERO_OF_THE_VILLAGE))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.HERO)
                || (hasArmorSet(playerEntity, ArmorSets.GILDED)
                    && Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(GILDED_HERO)
                    && playerEntity.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE))
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == HERO_OF_THE_VILLAGE)
                || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == HERO_OF_THE_VILLAGE)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 42, 0, false,
                    false));
        }
    }

    public static void applyHungerPain(ServerPlayerEntity playerEntity){
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(HUNGER))
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
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(INVISIBILITY))
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
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(LUCK))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.OPULENT)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == LUCK)
                || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == LUCK)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 42, 0, false,
                    false));
        }
    }

    public static void applySprintingSpeed(ServerPlayerEntity playerEntity){
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SPRINTING))
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
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SLOW_FALLING))
            return;

        if (hasArmorSet(playerEntity, ArmorSets.PHANTOM) || hasArmorSet(playerEntity, ArmorSets.FROST_BITE)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SLOW_FALLING)
                || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == SLOW_FALLING)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 42, 0, false,
                    false));
        }
    }

    public static void applyStalwartBulwarkResistanceEffect(ServerPlayerEntity playerEntity){
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(STALWART_BULWARK))
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
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(WATER_BREATHING))
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
        if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(RENEGADES_RUSH))
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

    public static void sweetBerrySpeed(ServerPlayerEntity playerEntity){
        if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SWEET_BERRY_SPEED)) {
            if (hasArmorSet(playerEntity, ArmorSets.ARCTIC_FOX)) {
                if (playerEntity.getMainHandStack().getItem() == Items.SWEET_BERRIES) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 0, false, false));
                }
            }
        }
    }
}