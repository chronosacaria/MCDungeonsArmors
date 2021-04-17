package chronosacaria.mcda.mixin;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.FIRE_RESISTANCE;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderFireOverlayOverride(MinecraftClient minecraftClient, MatrixStack matrixStack,
                                                 CallbackInfo ci) {
        if (!config.enableArmorEffect.get(FIRE_RESISTANCE))
            return;
        if (MinecraftClient.getInstance().player.isAlive()) {
            ItemStack helmetStack = MinecraftClient.getInstance().player.inventory.armor.get(3);
            ItemStack chestStack = MinecraftClient.getInstance().player.inventory.armor.get(2);
            ItemStack legsStack = MinecraftClient.getInstance().player.inventory.armor.get(1);
            ItemStack feetStack = MinecraftClient.getInstance().player.inventory.armor.get(0);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPROUT).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPROUT).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPROUT).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPROUT).get(EquipmentSlot.FEET).asItem()) {
                ci.cancel();
            }

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.LIVING_VINES).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.LIVING_VINES).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.LIVING_VINES).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.LIVING_VINES).get(EquipmentSlot.FEET).asItem()) {
                ci.cancel();
            }
        }
    }
}
