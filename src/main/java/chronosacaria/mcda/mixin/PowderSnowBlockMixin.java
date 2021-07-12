package chronosacaria.mcda.mixin;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Inject(method = "canWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void canWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!(entity instanceof LivingEntity)) return;
        if (((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET).isOf(ArmorsRegistry.armorItems.get(ArmorSets.RUGGED_CLIMBING_GEAR).get(EquipmentSlot.FEET)) ||
                ((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET).isOf(ArmorsRegistry.armorItems.get(ArmorSets.GOAT).get(EquipmentSlot.FEET))){
            cir.setReturnValue(true);
        }
    }
}
