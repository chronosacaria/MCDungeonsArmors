package chronosacaria.mcda.effects;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.*;

public class ArmorEffects {

    public static final List<StatusEffect> TITAN_SHROUD_STATUS_EFFECTS_LIST =
            List.of(StatusEffects.HUNGER, StatusEffects.NAUSEA, StatusEffects.BLINDNESS,
                    StatusEffects.MINING_FATIGUE, StatusEffects.SLOWNESS,
                    StatusEffects.UNLUCK, StatusEffects.WEAKNESS);

    // Effects for LivingEntityMixin
    public static void teleportOnHit(LivingEntity livingEntity) {
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

    public static void applyFluidFreezing(PlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(FLUID_FREEZING))
            return;
        World world = playerEntity.getEntityWorld();
        BlockPos blockPos = playerEntity.getBlockPos();

        if (playerEntity.isAlive()) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.FROST).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.FROST).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.FROST).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.FROST).get(EquipmentSlot.FEET).asItem()) {
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
                                    world.getBlockTickScheduler().schedule(blockPos2, Blocks.FROSTED_ICE, MathHelper.nextInt(playerEntity.getRandom(), 60, 120));
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
                                    world.getBlockTickScheduler().schedule(blockPos2, Blocks.CRYING_OBSIDIAN,
                                            MathHelper.nextInt(playerEntity.getRandom(), 60, 120));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void applyThiefInvisibilityTick(PlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(INVISIBILITY))
            return;
        if (playerEntity.isAlive()){
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.FEET).asItem())
                playerEntity.setInvisible(playerEntity.isSneaking());
        }
    }

    public static void applyWithered(PlayerEntity playerEntity, LivingEntity attacker){
        if (!config.enableArmorEffect.get(WITHERED))
            return;
        if (attacker != null) {
            if (playerEntity.isAlive()) {
                ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
                ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
                ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
                ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

                if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.WITHER).get(EquipmentSlot.HEAD).asItem()
                        && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.WITHER).get(EquipmentSlot.CHEST).asItem()
                        && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.WITHER).get(EquipmentSlot.LEGS).asItem()
                        && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.WITHER).get(EquipmentSlot.FEET).asItem()) {
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 0));
                }
            }
        }
    }

    public static void applyNimbleTurtleEffects(PlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(NIMBLE_TURTLE_EFFECTS))
            return;
        if (playerEntity.isAlive()) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.NIMBLE_TURTLE).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.NIMBLE_TURTLE).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.NIMBLE_TURTLE).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.NIMBLE_TURTLE).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 1, false,
                        false);
                StatusEffectInstance healing = new StatusEffectInstance(StatusEffects.REGENERATION, 60, 1, false,
                        false);
                playerEntity.addStatusEffect(resistance);
                playerEntity.addStatusEffect(healing);
            }
        }
    }

    public static void applyTitanShroudStatuses(PlayerEntity playerEntity, LivingEntity target){
        if (!config.enableArmorEffect.get(TITAN_SHROUD_EFFECTS))
            return;
        StatusEffect titanStatusEffect =
                TITAN_SHROUD_STATUS_EFFECTS_LIST.get(playerEntity.getRandom().nextInt(TITAN_SHROUD_STATUS_EFFECTS_LIST.size()));
        target.addStatusEffect(new StatusEffectInstance(titanStatusEffect, 60, 0));
    }

    // Effects for ServerPlayerEntityMixin
    public static void applyHaste(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(HASTE))
            return;
        if (playerEntity.getY() < 32.0F) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAVE_CRAWLER).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAVE_CRAWLER).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAVE_CRAWLER).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAVE_CRAWLER).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance haste = new StatusEffectInstance(StatusEffects.HASTE, 42, 0, false, false);
                playerEntity.addStatusEffect(haste);
            }
        }
        if (playerEntity.getY() > 100.0F) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HIGHLAND).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HIGHLAND).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HIGHLAND).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HIGHLAND).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance haste = new StatusEffectInstance(StatusEffects.HASTE, 42, 0, false, false);
                playerEntity.addStatusEffect(haste);
            }
        }
    }

    public static void applyHeroOfTheVillage(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(HERO_OF_THE_VILLAGE))
            return;
        if (playerEntity.isAlive()) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HERO).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HERO).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HERO).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HERO).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance heroOfTheVillage = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 42, 0, false,
                        false);
                playerEntity.addStatusEffect(heroOfTheVillage);
            }

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GILDED).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GILDED).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GILDED).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GILDED).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance heroOfTheVillage = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 42, 0, false,
                        false);
                playerEntity.addStatusEffect(heroOfTheVillage);
            }
        }
    }

    public static void applyHunger(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(HUNGER))
            return;
        if (playerEntity.isAlive()) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HUNGRY_HORROR).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HUNGRY_HORROR).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HUNGRY_HORROR).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.HUNGRY_HORROR).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance hunger = new StatusEffectInstance(StatusEffects.HUNGER, 42, 1, false,
                        false);
                playerEntity.addStatusEffect(hunger);
            }
        }
    }

    public static void applyFireResistance(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(FIRE_RESISTANCE))
            return;
        if (playerEntity.isAlive()) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPROUT).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPROUT).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPROUT).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPROUT).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance fireResistance = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 42, 1,
                        false,false);
                playerEntity.addStatusEffect(fireResistance);
            }

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.LIVING_VINES).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.LIVING_VINES).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.LIVING_VINES).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.LIVING_VINES).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance fireResistance = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 42, 1,
                        false,false);
                playerEntity.addStatusEffect(fireResistance);
            }
        }
    }

    public static void applyLuck(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(LUCK))
            return;
        if (playerEntity.isAlive()) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.OPULENT).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.OPULENT).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.OPULENT).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.OPULENT).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance luck = new StatusEffectInstance(StatusEffects.LUCK, 42, 0, false,
                        false);
                playerEntity.addStatusEffect(luck);
            }
        }
    }

    public static void applySprintingSpeed(ServerPlayerEntity playerEntity){
        if (!config.enableArmorEffect.get(SPRINTING))
            return;
        if (playerEntity.isAlive()) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SHADOW_WALKER).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SHADOW_WALKER).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SHADOW_WALKER).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SHADOW_WALKER).get(EquipmentSlot.FEET).asItem()) {
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
        if (playerEntity.isAlive()) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GLOW_SQUID).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GLOW_SQUID).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GLOW_SQUID).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GLOW_SQUID).get(EquipmentSlot.FEET).asItem()) {
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
        if (playerEntity.isAlive()) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.FEET).asItem()) {
                if (playerEntity.isSneaking()) {
                    StatusEffectInstance invisibility = new StatusEffectInstance(StatusEffects.INVISIBILITY, 42, 0,
                            false, false);
                    playerEntity.addStatusEffect(invisibility);
                }
            }
        }
    }



}
