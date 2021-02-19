package chronosacaria.mcda.api;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

// TODO: unused
public class ArmorEffectsHelper {

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

    public static void applyWithered(PlayerEntity playerEntity, LivingEntity attacker){

        if (attacker != null) {
            if (playerEntity.isAlive()) {
                ItemStack helmetStack = playerEntity.inventory.armor.get(3);
                ItemStack chestStack = playerEntity.inventory.armor.get(2);
                ItemStack legsStack = playerEntity.inventory.armor.get(1);
                ItemStack feetStack = playerEntity.inventory.armor.get(0);

                if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.WITHER).get(EquipmentSlot.HEAD).asItem()
                        && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.WITHER).get(EquipmentSlot.CHEST).asItem()
                        && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.WITHER).get(EquipmentSlot.LEGS).asItem()
                        && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.WITHER).get(EquipmentSlot.FEET).asItem()) {
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 0));
                }
            }
        }
    }

    public static void applyThiefInvisibilityTick(PlayerEntity playerEntity){
        if (playerEntity.isAlive()){
            ItemStack helmetStack = playerEntity.inventory.armor.get(3);
            ItemStack chestStack = playerEntity.inventory.armor.get(2);
            ItemStack legsStack = playerEntity.inventory.armor.get(1);
            ItemStack feetStack = playerEntity.inventory.armor.get(0);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.FEET).asItem())
                playerEntity.setInvisible(playerEntity.isSneaking());
        }
    }

    public static void applyHaste(ServerPlayerEntity playerEntity){
        if (playerEntity.getY() < 32.0F) {
            ItemStack helmetStack = playerEntity.inventory.armor.get(3);
            ItemStack chestStack = playerEntity.inventory.armor.get(2);
            ItemStack legsStack = playerEntity.inventory.armor.get(1);
            ItemStack feetStack = playerEntity.inventory.armor.get(0);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAVE_CRAWLER).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAVE_CRAWLER).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAVE_CRAWLER).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.CAVE_CRAWLER).get(EquipmentSlot.FEET).asItem()) {
                StatusEffectInstance haste = new StatusEffectInstance(StatusEffects.HASTE, 42, 0, false, false);
                playerEntity.addStatusEffect(haste);
            }
        }
    }
}
