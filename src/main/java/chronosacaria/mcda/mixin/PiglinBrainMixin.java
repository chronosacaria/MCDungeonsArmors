
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

    @Inject(method = "wearsGoldArmor", at = @At(value = "RETURN"), cancellable = true)
    private static void onPiglinSelectPlayerToAttack(LivingEntity entity, CallbackInfoReturnable<Boolean> cir){
        if (!config.enableArmorEffect.get(PIGLIN_FOOLING))
            return;
        if (entity instanceof PlayerEntity) {
            ItemStack helmetStack = entity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = entity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = entity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = entity.getEquippedStack(EquipmentSlot.FEET);
            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOLDEN_PIGLIN).get(EquipmentSlot.HEAD).asItem() &&
                    chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOLDEN_PIGLIN).get(EquipmentSlot.CHEST).asItem() &&
                    legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOLDEN_PIGLIN).get(EquipmentSlot.LEGS).asItem() &&
                    feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.GOLDEN_PIGLIN).get(EquipmentSlot.FEET).asItem()){
                cir.setReturnValue(true);
            }
        }
    }
}

