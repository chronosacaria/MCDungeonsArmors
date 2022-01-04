package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
}
