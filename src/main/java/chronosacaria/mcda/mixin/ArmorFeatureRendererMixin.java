package chronosacaria.mcda.mixin;

import chronosacaria.mcda.effects.ArmorEffectID;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.*;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin {

    // Hide Thief Armour on Sneak
    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    public void renderArmorOverride(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                 LivingEntity livingEntity, EquipmentSlot equipmentSlot, int i, BipedEntityModel<LivingEntity> bipedEntityModel, CallbackInfo info) {
        if (!config.enableArmorEffect.get(INVISIBILITY))
            return;

        if (livingEntity instanceof PlayerEntity && livingEntity.isSneaking()){

            ItemStack helmetStack = ((PlayerEntity) livingEntity).getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = ((PlayerEntity) livingEntity).getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = ((PlayerEntity) livingEntity).getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = ((PlayerEntity) livingEntity).getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.FEET).asItem()){
                info.cancel();
            }
        }
    }
}
