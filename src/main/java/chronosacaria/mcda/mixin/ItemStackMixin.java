package chronosacaria.mcda.mixin;

import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.entity.EquipmentSlot;
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

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract NbtCompound getOrCreateNbt();

    @Shadow @Final @Deprecated private Item item;
    Random random = new Random();

    private boolean isMysteryArmor(){
        return this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.FEET)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.FEET)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.GREEN_MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.GREEN_MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.GREEN_MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.GREEN_MYSTERY).get(EquipmentSlot.FEET)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLUE_MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLUE_MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLUE_MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLUE_MYSTERY).get(EquipmentSlot.FEET)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.PURPLE_MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.PURPLE_MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.PURPLE_MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.PURPLE_MYSTERY).get(EquipmentSlot.FEET);
    }
    private boolean isWhiteMysteryArmor() {
        return this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.FEET);
    }
    private boolean isRedMysteryArmor() {
        return this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.FEET);
    }
    private boolean isGreenMysteryArmor() {
        return this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.GREEN_MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.GREEN_MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.GREEN_MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.GREEN_MYSTERY).get(EquipmentSlot.FEET);
    }
    private boolean isBlueMysteryArmor() {
        return this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLUE_MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLUE_MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLUE_MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.BLUE_MYSTERY).get(EquipmentSlot.FEET);
    }
    private boolean isPurpleMysteryArmor() {
        return this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.PURPLE_MYSTERY).get(EquipmentSlot.HEAD)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.PURPLE_MYSTERY).get(EquipmentSlot.CHEST)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.PURPLE_MYSTERY).get(EquipmentSlot.LEGS)
                || this.item.asItem() == ArmorsRegistry.armorItems.get(ArmorSets.PURPLE_MYSTERY).get(EquipmentSlot.FEET);
    }

    @Inject(method = "onCraft", at = @At("HEAD"))
    public void applyMysteryArmorNBTDominanceApplication(World world, PlayerEntity player, int amount, CallbackInfo ci){
        if (isMysteryArmor()) {
            this.getOrCreateNbt().putInt("dominance", random.nextInt(99));

            if(isWhiteMysteryArmor()/* && !config.enableArmorEffect.get(WHITE_MYSTERY_TAGS*/){
                this.getOrCreateNbt().putInt("mystery_effect", random.nextInt(ArmorEffects.ARMOR_EFFECT_ID_LIST.size()));
            }
            if(isRedMysteryArmor()/* && !config.enableArmorEffect.get(RED_MYSTERY_TAGS*/){
                this.getOrCreateNbt().putInt("mystery_effect", random.nextInt(ArmorEffects.RED_ARMOR_EFFECT_ID_LIST.size()));
            }
            if(isGreenMysteryArmor()/* && !config.enableArmorEffect.get(GREEN_MYSTERY_TAGS*/){
                this.getOrCreateNbt().putInt("mystery_effect", random.nextInt(ArmorEffects.GREEN_ARMOR_EFFECT_ID_LIST.size()));
            }
            if(isBlueMysteryArmor()/* && !config.enableArmorEffect.get(BLUE_MYSTERY_TAGS*/){
                this.getOrCreateNbt().putInt("mystery_effect", random.nextInt(ArmorEffects.BLUE_ARMOR_EFFECT_ID_LIST.size()));
            }
            if(isPurpleMysteryArmor()/* && !config.enableArmorEffect.get(PURPLE_MYSTERY_TAGS*/){
                this.getOrCreateNbt().putInt("mystery_effect", random.nextInt(ArmorEffects.PURPLE_ARMOR_EFFECT_ID_LIST.size()));
            }

        }
    }
}
