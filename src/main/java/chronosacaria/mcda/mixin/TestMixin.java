package chronosacaria.mcda.mixin;

import chronosacaria.mcda.init.ArmorsInit;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public class TestMixin {
    @Inject(method = "isPlayerStaring", at = @At("HEAD"), cancellable = true)
    private void isPlayerStaring(PlayerEntity player, CallbackInfoReturnable<Boolean> cir){
        ItemStack helmetStack = (ItemStack)player.inventory.armor.get(3);
        ItemStack chestplateStack = (ItemStack)player.inventory.armor.get(2);
        ItemStack leggingsStack = (ItemStack)player.inventory.armor.get(1);
        ItemStack bootsStack = (ItemStack)player.inventory.armor.get(0);

        if (helmetStack.getItem() == ArmorsInit.BEEHIVE_ARMOR_HELMET.asItem()
                && chestplateStack.getItem() == ArmorsInit.BEEHIVE_ARMOR_CHESTPLATE.asItem()
                && leggingsStack.getItem() == ArmorsInit.BEEHIVE_ARMOR_LEGGINGS.asItem()
                && bootsStack.getItem() == ArmorsInit.BEEHIVE_ARMOR_BOOTS.asItem()){
            cir.setReturnValue(false);
        }
    }

}