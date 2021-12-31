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

    @Inject(method = "onCraft", at = @At("HEAD"))
    public void applyMysteryArmorNBTDominanceApplication(World world, PlayerEntity player, int amount, CallbackInfo ci){
        if (isMysteryArmor()) {
            this.getOrCreateNbt().putInt("dominance", random.nextInt(99));
            this.getOrCreateNbt().putInt("mystery_effect", random.nextInt(ArmorEffects.ARMOR_EFFECT_ID_LIST.size()));
        }
    }
}
