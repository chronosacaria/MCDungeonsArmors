package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.effects.ArmorEffectID;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.SoundsRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.MYSTERY_EFFECTS_ON_CRAFTING;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract NbtCompound getOrCreateNbt();

    @Shadow @Final @Deprecated private Item item;
    Random random = new Random();

    private boolean isMysteryArmor(){
        return CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.MYSTERY)
                || CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.RED_MYSTERY)
                || CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.GREEN_MYSTERY)
                || CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.BLUE_MYSTERY)
                || CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.PURPLE_MYSTERY);
    }

    @Inject(method = "onCraft", at = @At("HEAD"))
    public void applyMysteryArmorNBTDominanceApplication(World world, PlayerEntity player, int amount, CallbackInfo ci){
        if (isMysteryArmor() && config.enableArmorEffect.get(MYSTERY_EFFECTS_ON_CRAFTING)) {
            this.getOrCreateNbt().putInt("dominance", random.nextInt(99));

            if(CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.MYSTERY)){
                this.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }
            if(CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.RED_MYSTERY)){
                this.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.RED_ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }
            if(CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.GREEN_MYSTERY)){
                this.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.GREEN_ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }
            if(CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.BLUE_MYSTERY)){
                this.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.BLUE_ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }
            if(CleanlinessHelper.isMysteryArmor(this.item, ArmorSets.PURPLE_MYSTERY)){
                this.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.PURPLE_ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }
        }
    }

    @Inject(method = "use", at = @At("HEAD"))
    public void useEmeraldToChargeArmor(World world, PlayerEntity user, Hand hand,
                                        CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        ItemStack getMainHandStack = user.getMainHandStack();
        int decrementAmount = 10;

        if (config.enableArmorEffect.get(ArmorEffectID.GILDED_HERO) && CleanlinessHelper.hasArmorSet(user, ArmorSets.GILDED)) {
            if (getMainHandStack.getItem() == Items.EMERALD) {
                int getMainHandInventorySize = getMainHandStack.getCount();
                if (getMainHandInventorySize >= decrementAmount) {
                    getMainHandStack.decrement(decrementAmount);
                    StatusEffectInstance hov = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 42, 0, false,
                            false);
                    user.addStatusEffect(hov);
                    user.world.playSound(
                            null,
                            user.getX(),
                            user.getY(),
                            user.getZ(),
                            SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                            SoundCategory.PLAYERS,
                            0.8F,
                            0.8F);
                }
            }
        }
    }
}
