package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.api.McdaEnchantmentHelper;
import chronosacaria.mcda.enchants.EnchantID;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.SOULDANCER_EXPERIENCE;
import static chronosacaria.mcda.enchants.EnchantID.*;

@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbEntityMixin extends Entity {

    @Shadow private int amount;

    public ExperienceOrbEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

   /* @Inject(method = "onPlayerCollision", at = @At("HEAD"))
    public void applyBagOfSouls(PlayerEntity playerEntity, CallbackInfo ci){
        if (!this.world.isClient) {
            boolean removalBool = false;
            if (config.enableArmorEffect.get(SOULDANCER_EXPERIENCE))
                if (CleanlinessHelper.hasArmorSet(playerEntity, ArmorSets.SOULDANCER)) {
                    this.amount = (int) Math.round(1.5 * this.amount);
                    removalBool = true;
                }

            if (config.enableEnchantment.get(BAG_OF_SOULS)) {
                int bagOfSoulsLevel =
                        McdaEnchantmentHelper.getBagOfSoulsLevel(EnchantsRegistry.enchants.get(EnchantID.BAG_OF_SOULS),
                                playerEntity);

                if (bagOfSoulsLevel > 0) {
                    int bagOfSoulsCount = 0;
                    for (ItemStack itemStack : playerEntity.getArmorItems())
                        if (EnchantmentHelper.getLevel(EnchantsRegistry.enchants.get(BAG_OF_SOULS), itemStack) > 0)
                            bagOfSoulsCount++;

                    // Thank you, Amph
                    this.amount = (this.amount * (1 + (bagOfSoulsLevel / 12)) + Math.round(((bagOfSoulsLevel % 12) / 12.0f) * this.amount)) * bagOfSoulsCount;
                    removalBool = true;
                }
            }
            if (removalBool)
                playerEntity.world.playSound(
                        null,
                        playerEntity.getX(),
                        playerEntity.getY(),
                        playerEntity.getZ(),
                        SoundEvents.PARTICLE_SOUL_ESCAPE,
                        SoundCategory.PLAYERS,
                        1.0F,
                        1.0F);
                this.remove(RemovalReason.KILLED);
        }
    } */

    @ModifyArgs(method = "onPlayerCollision", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/ExperienceOrbEntity;repairPlayerGears(Lnet/minecraft/entity/player/PlayerEntity;I)I"))
    public void realExperienceChange(Args args){
        int amount = args.get(1);
        PlayerEntity playerEntity = args.get(0);

        if (config.enableArmorEffect.get(SOULDANCER_EXPERIENCE))
            if (CleanlinessHelper.hasArmorSet(playerEntity, ArmorSets.SOULDANCER))
                amount = (int) Math.round(1.5 * amount);

        if (config.enableEnchantment.get(BAG_OF_SOULS)) {
            int bagOfSoulsLevel = McdaEnchantmentHelper.getBagOfSoulsLevel(EnchantsRegistry.enchants.get(EnchantID.BAG_OF_SOULS),
                            playerEntity);

            if (bagOfSoulsLevel > 0) {
                int bagOfSoulsCount = 0;
                for (ItemStack itemStack : playerEntity.getArmorItems())
                    if (EnchantmentHelper.getLevel(EnchantsRegistry.enchants.get(BAG_OF_SOULS), itemStack) > 0)
                        bagOfSoulsCount++;

                // Thank you, Amph
                amount = (amount * (1 + (bagOfSoulsLevel / 12)) + Math.round(((bagOfSoulsLevel % 12) / 12.0f) * amount)) * bagOfSoulsCount;
            }
        }
        args.set(1, amount);
    }
}