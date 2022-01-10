package chronosacaria.mcda.effects;

import chronosacaria.mcda.api.AOEHelper;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.StatusEffectsRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.FoxEntity;
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

import static chronosacaria.mcda.api.CleanlinessHelper.hasArmorSet;
import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.*;

public class ArmorEffects {

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
        if (!config.enableArmorEffect.get(MYSTERY_EFFECTS)) {
            return 0;
        }

        //Checks Mystery Armor Color
        if (hasArmorSet(livingEntity, armorSets)){

            ItemStack helmetStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestplateStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack leggingsStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack bootsStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);


            int[] domArr = {0,0,0,0};

            if (helmetStack.getNbt() != null) {
                domArr[0] = helmetStack.getNbt().getInt("dominance");
            }
            if (chestplateStack.getNbt() != null) {
                domArr[1] = chestplateStack.getNbt().getInt("dominance");
            }
            if (leggingsStack.getNbt() != null) {
                domArr[2] = leggingsStack.getNbt().getInt("dominance");
            }
            if (bootsStack.getNbt() != null) {
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
            double xpos = livingEntity.getX();
            double ypos = livingEntity.getY();
            double zpos = livingEntity.getZ();

            for (int i = 0; i < 16; i++) {
                double teleportX = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;
                double teleportY =
                        MathHelper.clamp(livingEntity.getY() + (double) (livingEntity.getRandom().nextInt(16) - 8),
                                0.0D, world.getHeight() - 1);
                double teleportZ = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (livingEntity.hasVehicle()) {
                    livingEntity.stopRiding();
                }

                if (livingEntity.teleport(teleportX, teleportY, teleportZ, true)) {
                    SoundEvent soundEvent = livingEntity instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT :
                            SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    livingEntity.world.playSound(
                            null,
                            xpos,
                            ypos,
                            zpos,
                            soundEvent,
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.0F);
                }
            }
        }
    }

    public static void controlledTeleportEffect(LivingEntity livingEntity) {
        World world = livingEntity.getEntityWorld();
        Vec3d target = raytraceForTeleportation(livingEntity);

        if (!world.isClient && target != null) {
            double xpos = livingEntity.getX();
            double ypos = livingEntity.getY();
            double zpos = livingEntity.getZ();

            for (int i = 0; i < 16; i++) {
                Random random = world.random;
                double teleportX =
                        (target.x - livingEntity.getX()) * random.nextDouble() + livingEntity.getX() - 0.5;
                double teleportY =
                        (target.y - livingEntity.getY()) * random.nextDouble() + livingEntity.getY() + 1;
                double teleportZ =
                        (target.z - livingEntity.getZ()) * random.nextDouble() + livingEntity.getZ() - 0.5;
                if (livingEntity.hasVehicle()) {
                    livingEntity.stopRiding();
                }

                if (livingEntity.teleport(teleportX, teleportY, teleportZ, true)) {
                    SoundEvent soundEvent = livingEntity instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT :
                            SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    livingEntity.world.playSound(
                            null,
                            xpos,
                            ypos,
                            zpos,
                            soundEvent,
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.0F);
                }
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

        BlockPos teleportPos = result.getBlockPos().down(2);

        boolean positionIsFree = positionIsClear(world, teleportPos);

        if (!world.isClient && !result.isInsideBlock()) {

            while (!positionIsFree) {
                teleportPos = livingEntity.getBlockPos();
                positionIsFree = positionIsClear(world, teleportPos) && world.raycast(new RaycastContext(eyeVec,
                        Vec3d.ofCenter(teleportPos.up()),
                        RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, livingEntity)).getType() == HitResult.Type.MISS;
                //if (teleportPos.getY() <= 0){
                //    break;
                //}
            }
        } else if (positionIsFree) {
            Vec3d.ofCenter(teleportPos);
        }
        return Vec3d.ofCenter(teleportPos);
    }

    private static boolean positionIsClear(World world, BlockPos pos) {
        return (world.isAir(pos) || world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()
                && (world.isAir(pos.up()) || world.getBlockState(pos.up()).getCollisionShape(world, pos.up()).isEmpty()));
    }

    public static void applyFluidFreezing(PlayerEntity playerEntity) {
        if (!config.enableArmorEffect.get(FLUID_FREEZING))
            return;
        World world = playerEntity.getEntityWorld();
        BlockPos blockPos = playerEntity.getBlockPos();

        if (playerEntity.isAlive() && world.getTime() % 10 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.FROST)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == FLUID_FREEZING)
                    || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == FLUID_FREEZING)){
                // From FrostWalkerEnchantment
                if (playerEntity.isOnGround()) {
                    BlockState blockState = Blocks.FROSTED_ICE.getDefaultState();
                    float f = (float) Math.min(16, 2 + 1);
                    BlockPos.Mutable mutable = new BlockPos.Mutable();
                    Iterator var7 = BlockPos.iterate(blockPos.add(-f, -1.0D, -f), blockPos.add(f, -1.0D, f)).iterator();

                    while (var7.hasNext()) {
                        BlockPos blockPos2 = (BlockPos) var7.next();
                        if (blockPos2.isWithinDistance(playerEntity.getPos(), f)) {
                            mutable.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                            BlockState blockState2 = world.getBlockState(mutable);
                            if (blockState2.isAir()) {
                                BlockState blockState3 = world.getBlockState(blockPos2);
                                if (blockState3.getMaterial() == Material.WATER && blockState3.get(FluidBlock.LEVEL) == 0 && blockState.canPlaceAt(world, blockPos2) && world.canPlace(blockState, blockPos2, ShapeContext.absent())) {
                                    world.setBlockState(blockPos2, blockState);
                                    world.createAndScheduleBlockTick(blockPos2, Blocks.FROSTED_ICE, MathHelper.nextInt(playerEntity.getRandom(), 60, 120));
                                }
                            }
                        }
                    }
                }
                if (playerEntity.isOnGround()) {
                    BlockState blockState = Blocks.CRYING_OBSIDIAN.getDefaultState();
                    float f = (float) Math.min(16, 2 + 1);
                    BlockPos.Mutable mutable = new BlockPos.Mutable();
                    Iterator var7 = BlockPos.iterate(blockPos.add(-f, -1.0D, -f), blockPos.add(f, -1.0D, f)).iterator();

                    while (var7.hasNext()) {
                        BlockPos blockPos2 = (BlockPos) var7.next();
                        if (blockPos2.isWithinDistance(playerEntity.getPos(), f)) {
                            mutable.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                            BlockState blockState2 = world.getBlockState(mutable);
                            if (blockState2.isAir()) {
                                BlockState blockState3 = world.getBlockState(blockPos2);
                                if (blockState3.getMaterial() == Material.LAVA && blockState3.get(FluidBlock.LEVEL) == 0 && blockState.canPlaceAt(world, blockPos2) && world.canPlace(blockState, blockPos2, ShapeContext.absent())) {
                                    world.setBlockState(blockPos2, blockState);
                                    world.createAndScheduleBlockTick(blockPos2, Blocks.CRYING_OBSIDIAN,
                                            MathHelper.nextInt(playerEntity.getRandom(), 60, 120));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void applyThiefInvisibilityTick(PlayerEntity playerEntity) {
        if (!config.enableArmorEffect.get(INVISIBILITY))
            return;
        if (playerEntity.isAlive()) {

            if (hasArmorSet(playerEntity, ArmorSets.THIEF)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == INVISIBILITY)
                    || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == INVISIBILITY))
                playerEntity.setInvisible(playerEntity.isSneaking());
        }
    }

    public static void applyWithered(LivingEntity attacker) {
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 0));
    }

    public static void applyNimbleTurtleEffects(PlayerEntity playerEntity) {
        StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 1, false,
                false);
        StatusEffectInstance healing = new StatusEffectInstance(StatusEffects.REGENERATION, 60, 1, false,
                false);
        playerEntity.addStatusEffect(resistance);
        playerEntity.addStatusEffect(healing);
    }

    public static void applyTitanShroudStatuses(PlayerEntity playerEntity, LivingEntity target) {
        StatusEffect titanStatusEffect =
                TITAN_SHROUD_STATUS_EFFECTS_LIST.get(playerEntity.getRandom().nextInt(TITAN_SHROUD_STATUS_EFFECTS_LIST.size()));
        target.addStatusEffect(new StatusEffectInstance(titanStatusEffect, 60, 0));
    }

    public static void applyFrostBiteStatus(LivingEntity target) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.FREEZING, 60, 0, true, true,
                    false));
    }

    public static void applyGourdiansHatredStatus(LivingEntity livingEntity) {
        if (!config.enableArmorEffect.get(GOURDIANS_HATRED))
            return;
        if (livingEntity.isAlive()) {

            if (hasArmorSet(livingEntity, ArmorSets.GOURDIAN)) {

                StatusEffectInstance strength = new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1);
                livingEntity.addStatusEffect(strength);
            }
        }
    }

    public static void applyCauldronsOverflow(LivingEntity targetedEntity) {
        if (!config.enableArmorEffect.get(CAULDRONS_OVERFLOW))
            return;
        ItemStack potionToDrop =
                CAULDRONS_OVERFLOW_LIST.get(targetedEntity.getRandom().nextInt(CAULDRONS_OVERFLOW_LIST.size()));

        ItemEntity potion = new ItemEntity(targetedEntity.world, targetedEntity.getX(), targetedEntity.getY(),
                targetedEntity.getZ(), potionToDrop);
        targetedEntity.world.spawnEntity(potion);
    }

    // Effects for ServerPlayerEntityMixin
    public static void applyHaste(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(HASTE))
            return;
        World world = playerEntity.getEntityWorld();

        if (playerEntity.getY() < 32.0F && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.CAVE_CRAWLER)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == HASTE)
                    || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == HASTE)) {

                StatusEffectInstance haste = new StatusEffectInstance(StatusEffects.HASTE, 42, 0, false, false);
                playerEntity.addStatusEffect(haste);
            }
        }
        if (playerEntity.getY() > 100.0F && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.HIGHLAND)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == HASTE)
                    || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == HASTE)) {

                StatusEffectInstance haste = new StatusEffectInstance(StatusEffects.HASTE, 42, 0, false, false);
                playerEntity.addStatusEffect(haste);
            }
        }
    }

    public static void applyHeroOfTheVillage(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(HERO_OF_THE_VILLAGE))
            return;

        World world = playerEntity.getEntityWorld();

        if (playerEntity.isAlive() && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.HERO)
                    || (hasArmorSet(playerEntity, ArmorSets.GILDED)
                        && config.enableArmorEffect.get(GILDED_HERO)
                        && playerEntity.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE))
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == HERO_OF_THE_VILLAGE)
                    || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == HERO_OF_THE_VILLAGE)) {

                StatusEffectInstance heroOfTheVillage = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 42, 0, false,
                        false);
                playerEntity.addStatusEffect(heroOfTheVillage);
            }
        }
    }

    public static void applyHungerPain(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(HUNGER))
            return;

        World world = playerEntity.getEntityWorld();

        if (playerEntity.isAlive() && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.HUNGRY_HORROR)) {

                StatusEffectInstance hunger = new StatusEffectInstance(StatusEffects.HUNGER, 42, 1, false,
                        false);
                playerEntity.addStatusEffect(hunger);

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
    }

    public static void applyFireResistance(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(FIRE_RESISTANCE))
            return;

        World world = playerEntity.getEntityWorld();

        if (playerEntity.isAlive() && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.SPROUT) || hasArmorSet(playerEntity, ArmorSets.LIVING_VINES)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == FIRE_RESISTANCE)
                    || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == FIRE_RESISTANCE)) {

                StatusEffectInstance fireResistance = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 42, 1,
                        false,false);
                playerEntity.addStatusEffect(fireResistance);
            }
        }
    }

    public static void applyLuck(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(LUCK))
            return;

        World world = playerEntity.getEntityWorld();

        if (playerEntity.isAlive() && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.OPULENT)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == LUCK)
                    || (GREEN_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.GREEN_MYSTERY)) == LUCK)) {

                StatusEffectInstance luck = new StatusEffectInstance(StatusEffects.LUCK, 42, 0, false,
                        false);
                playerEntity.addStatusEffect(luck);
            }
        }
    }

    public static void applySprintingSpeed(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(SPRINTING))
            return;

        World world = playerEntity.getEntityWorld();

        if (playerEntity.isAlive() && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.SHADOW_WALKER)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SPRINTING)
                    || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == SPRINTING)) {

                if (playerEntity.isSprinting()) {
                    StatusEffectInstance speed = new StatusEffectInstance(StatusEffects.SPEED, 42, 0, false,
                            false);
                    playerEntity.addStatusEffect(speed);
                }
            }
        }
    }

    public static void applyWaterBreathing(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(WATER_BREATHING))
            return;

        World world = playerEntity.getEntityWorld();

        if (playerEntity.isAlive() && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.GLOW_SQUID)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == WATER_BREATHING)
                    || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == WATER_BREATHING)) {

                if (playerEntity.isSubmergedInWater()) {
                    StatusEffectInstance waterBreathing = new StatusEffectInstance(StatusEffects.WATER_BREATHING, 42, 0,
                            false, false);
                    playerEntity.addStatusEffect(waterBreathing);
                }
            }
        }
    }

    public static void applyInvisibility(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(INVISIBILITY))
            return;

        World world = playerEntity.getEntityWorld();

        if (playerEntity.isAlive() && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.THIEF)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == INVISIBILITY)
                    || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == INVISIBILITY)) {

                if (playerEntity.isSneaking()) {
                    StatusEffectInstance invisibility = new StatusEffectInstance(StatusEffects.INVISIBILITY, 42, 0,
                            false, false);
                    playerEntity.addStatusEffect(invisibility);
                }
            }
        }
    }

    public static void applySlowFalling(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(SLOW_FALLING))
            return;

        World world = playerEntity.getEntityWorld();

        if (playerEntity.isAlive() && world.getTime() % 30 == 0) {

            if (hasArmorSet(playerEntity, ArmorSets.PHANTOM) || hasArmorSet(playerEntity, ArmorSets.FROST_BITE)
                    || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SLOW_FALLING)
                    || (BLUE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.BLUE_MYSTERY)) == SLOW_FALLING)) {

                StatusEffectInstance slowFalling = new StatusEffectInstance(StatusEffects.SLOW_FALLING, 42, 0, false,
                        false);
                playerEntity.addStatusEffect(slowFalling);
            }
        }
    }

    public static void applyLevitationRemoval(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(SHULKER_LIKE))
            return;

        if (playerEntity.isAlive() && (hasArmorSet(playerEntity, ArmorSets.STURDY_SHULKER)
            || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == SHULKER_LIKE)
            || (PURPLE_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.PURPLE_MYSTERY)) == SHULKER_LIKE))){

            if (playerEntity.hasStatusEffect(StatusEffects.LEVITATION)) {
                playerEntity.removeStatusEffect(StatusEffects.LEVITATION);
            }
        }
    }

    public static void applyStalwartBulwarkResistanceEffect(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(STALWART_BULWARK))
            return;

        if (playerEntity.isAlive() && playerEntity.isSneaking() && (hasArmorSet(playerEntity, ArmorSets.STALWART_MAIL)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == STALWART_BULWARK)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == STALWART_BULWARK))){

            StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, 42, 0, false,
                    false);
            playerEntity.addStatusEffect(resistance);
        }
    }

    public static void applyRenegadesRushEffect(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(RENEGADES_RUSH))
            return;

        if (playerEntity.isAlive() && playerEntity.isSprinting() && (hasArmorSet(playerEntity, ArmorSets.RENEGADE)
                || (ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.MYSTERY)) == RENEGADES_RUSH)
                || (RED_ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(playerEntity, ArmorSets.RED_MYSTERY)) == RENEGADES_RUSH))){

            StatusEffectInstance strength = new StatusEffectInstance(StatusEffects.STRENGTH, 42, 2, false,
                    false);
            playerEntity.addStatusEffect(strength);
        }
    }

    public static void applyCuriousTeleportationEffect(LivingEntity player, LivingEntity target){
        float teleportationRand = player.getRandom().nextFloat();
        int toBeTeleportedRand = player.getRandom().nextInt(99);
        if (teleportationRand <= 0.1F){
            if (toBeTeleportedRand % 2 == 0){
                endermanLikeTeleportEffect(player);
            } else {
                endermanLikeTeleportEffect(target);
            }
        }
    }

    public static void applyGhostKindlingEffect(LivingEntity target){
        target.setOnFireFor(4);
    }

    public static void applySylvanPresence(LivingEntity livingEntity){
        World world = livingEntity.getWorld();
        BlockPos blockPos = livingEntity.getBlockPos();

        float size = (float) Math.min(16, 2 + 1);
        BlockPos.Mutable mutablePosition = new BlockPos.Mutable();

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

    public static void applyEmberJumpEffect(LivingEntity livingEntity){
        boolean playFireSound = false;

        for (LivingEntity nearbyEntity : AOEHelper.getAoeTargets(livingEntity, livingEntity, 6.0f)) {
            if (nearbyEntity instanceof Monster && livingEntity.isSneaking()){
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

    public static void applySplendidAoEAttackEffect(LivingEntity livingEntity, LivingEntity target){

        for (LivingEntity nearbyEntity : AOEHelper.getAoeTargets(target, livingEntity, 6.0f)){
            float damageToBeDone = (float) livingEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
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
