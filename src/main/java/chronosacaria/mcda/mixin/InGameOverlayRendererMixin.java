package chronosacaria.mcda.mixin;

import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.FIRE_RESISTANCE;
import static chronosacaria.mcda.effects.ArmorEffects.applyMysteryArmorEffect;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderFireOverlayOverride(MinecraftClient minecraftClient, MatrixStack matrixStack,
                                                 CallbackInfo ci) {
        if (!config.enableArmorEffect.get(FIRE_RESISTANCE))
            return;
        if (MinecraftClient.getInstance().player.isAlive()) {
            ItemStack helmetStack = MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.FEET);

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

            if (((helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.MYSTERY).get(EquipmentSlot.FEET).asItem())
                    || (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.RED_MYSTERY).get(EquipmentSlot.FEET).asItem()))
                    && ArmorEffects.ARMOR_EFFECT_ID_LIST.get(applyMysteryArmorEffect(MinecraftClient.getInstance().player)) == FIRE_RESISTANCE){
                ci.cancel();
            }
        }
    }
}
