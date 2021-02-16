package chronosacaria.mcda.mixin;

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

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin {

    // Hide Thief Armour on Sneak
    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    public void renderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity livingEntity, EquipmentSlot equipmentSlot, int i, BipedEntityModel<LivingEntity> bipedEntityModel, CallbackInfo info) {
        if (livingEntity instanceof PlayerEntity && livingEntity.isSneaking()){
            ItemStack helmetStack = ((PlayerEntity) livingEntity).inventory.armor.get(3);
            ItemStack chestStack = ((PlayerEntity) livingEntity).inventory.armor.get(2);
            ItemStack legsStack = ((PlayerEntity) livingEntity).inventory.armor.get(1);
            ItemStack feetStack = ((PlayerEntity) livingEntity).inventory.armor.get(0);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.FEET).asItem()){
                info.cancel();
            }
        }
    }
}
