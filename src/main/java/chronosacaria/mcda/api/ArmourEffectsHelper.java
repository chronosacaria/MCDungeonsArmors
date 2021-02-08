package chronosacaria.mcda.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

// TODO: currently unused
public class ArmourEffectsHelper {

    public static void teleportOnHit(LivingEntity livingEntity){
        World world = livingEntity.getEntityWorld();
        if (!world.isClient){
            double xpos = livingEntity.getX();
            double ypos = livingEntity.getY();
            double zpos = livingEntity.getZ();

            for (int i = 0; i < 16; ++i){
                double teleportX = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;
                double teleportY =
                        MathHelper.clamp(livingEntity.getY() + (double) (livingEntity.getRandom().nextInt(16) - 8),
                                0.0D, world.getHeight() - 1);
                double teleportZ = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (livingEntity.hasVehicle()){
                    livingEntity.stopRiding();
                }

                if (livingEntity.teleport(teleportX, teleportY, teleportZ, true)){
                    SoundEvent soundEvent = livingEntity instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT :
                            SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    livingEntity.world.playSound(
                            (PlayerEntity) null,
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
}
