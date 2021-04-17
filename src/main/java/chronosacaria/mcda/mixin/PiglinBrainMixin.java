
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.PIGLIN_FOOLING;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {

    @Inject(method = "shouldAttack", at = @At(value = "RETURN"), cancellable = true)
    private static void onPiglinSelectPlayerToAttack(LivingEntity target, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(PIGLIN_FOOLING))
            return;
        boolean shouldAttack = cir.getReturnValue();
        if (shouldAttack && target instanceof PlayerEntity) {
            ItemStack helmetStack = ((PlayerEntity) target).inventory.armor.get(3);
            ItemStack chestStack = ((PlayerEntity) target).inventory.armor.get(2);
            ItemStack legsStack = ((PlayerEntity) target).inventory.armor.get(1);
            ItemStack feetStack = ((PlayerEntity) target).inventory.armor.get(0);
            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOLDEN_PIGLIN).get(EquipmentSlot.HEAD).asItem() &&
                    chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOLDEN_PIGLIN).get(EquipmentSlot.CHEST).asItem() &&
                    legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOLDEN_PIGLIN).get(EquipmentSlot.LEGS).asItem() &&
                    feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOLDEN_PIGLIN).get(EquipmentSlot.FEET).asItem())
            cir.setReturnValue(false);
        }
    }
}

