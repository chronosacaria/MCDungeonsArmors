package chronosacaria.mcda.mixin;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.effects.ArmorEffectID;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSetItem;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "onCraft", at = @At("HEAD"))
    public void mcda$onCraftMysteryArmor(World world, PlayerEntity player, int amount, CallbackInfo ci){
        ItemStack stack = (ItemStack) (Object) this;

        if (stack.getItem() instanceof ArmorSetItem armorItem) {
            ArrayList<ArmorSets> sets = new ArrayList<>(List.of(ArmorSets.MYSTERY, ArmorSets.RED_MYSTERY,
                    ArmorSets.GREEN_MYSTERY, ArmorSets.BLUE_MYSTERY, ArmorSets.PURPLE_MYSTERY));
            ArrayList<List<ArmorEffectID>> effects = new ArrayList<>(List.of(ArmorEffects.ARMOR_EFFECT_ID_LIST,
                    ArmorEffects.RED_ARMOR_EFFECT_ID_LIST, ArmorEffects.GREEN_ARMOR_EFFECT_ID_LIST,
                    ArmorEffects.BLUE_ARMOR_EFFECT_ID_LIST, ArmorEffects.PURPLE_ARMOR_EFFECT_ID_LIST));

            if (sets.contains(armorItem.getSet())) {
                stack.getOrCreateNbt().putInt("dominance", new Random().nextInt(99));
                stack.getOrCreateNbt().putInt("mystery_effect",
                        new Random().nextInt(effects.get(sets.indexOf(armorItem.getSet())).size() - 1) + 1);
            }
        }
    }

    @Inject(method = "use", at = @At("HEAD"))
    private void mcda$useEmeraldToChargeArmor(World world, PlayerEntity user, Hand hand,
                                        CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        ItemStack getMainHandStack = user.getMainHandStack();

        if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(ArmorEffectID.GILDED_HERO) && CleanlinessHelper.checkFullArmor(user, ArmorSets.GILDED)) {
            if (getMainHandStack.getItem() == Items.EMERALD && !user.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE)) {
                int decrementAmount = 10;
                if (getMainHandStack.getCount() >= decrementAmount) {
                    getMainHandStack.decrement(decrementAmount);
                    StatusEffectInstance hov = new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 42, 0, false,
                            false);
                    user.addStatusEffect(hov);
                    CleanlinessHelper.playCenteredSound(user, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,0.8f, 0.8f);
                }
            }
        }
    }
}
