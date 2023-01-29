package chronosacaria.mcda.mixin;

import chronosacaria.mcda.items.ArmorSetItem;
import chronosacaria.mcda.items.ArmorSets;
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
    private static void mcda$canWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity le)
            if (le.getEquippedStack(EquipmentSlot.FEET).getItem() instanceof ArmorSetItem armorSetItem)
                if (armorSetItem.getSet().isOf(ArmorSets.RUGGED_CLIMBING_GEAR, ArmorSets.GOAT, ArmorSets.SNOW))
                    cir.setReturnValue(true);
    }
}
