package chronosacaria.mcda.mixin;

import chronosacaria.mcda.config.McdaConfig;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.CobwebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.mcda.effects.ArmorEffectID.WEB_WALKING;

@Mixin(CobwebBlock.class)
public class CobwebBlockMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    public void canWalkThroughCobwebs(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci){
        if (!McdaConfig.config.enableArmorEffect.get(WEB_WALKING))
            return;

        if (!(entity instanceof LivingEntity)) return;

        boolean spiderHead = ((LivingEntity)entity).getEquippedStack(EquipmentSlot.HEAD).isOf(ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.HEAD));
        boolean spiderChest = ((LivingEntity)entity).getEquippedStack(EquipmentSlot.CHEST).isOf(ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.CHEST));
        boolean spiderLegs = ((LivingEntity)entity).getEquippedStack(EquipmentSlot.LEGS).isOf(ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.LEGS));
        boolean spiderFeet = ((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET).isOf(ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.FEET));

        boolean spiderArmour = spiderHead && spiderChest && spiderLegs && spiderFeet;
        if (spiderArmour){
            ci.cancel();
        }
    }
}
