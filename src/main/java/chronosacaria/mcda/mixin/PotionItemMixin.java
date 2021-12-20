package chronosacaria.mcda.mixin;

import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static chronosacaria.mcda.config.McdaConfig.config;
import static chronosacaria.mcda.effects.ArmorEffectID.TROUBADOURS_CHARISMA;

@Mixin(PotionItem.class)
public class PotionItemMixin {
    @Redirect(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;" +
            "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"))
    private boolean troubadoursCharisma(LivingEntity livingEntity, StatusEffectInstance instance) {
        int newDuration = 0;

        if (livingEntity instanceof ServerPlayerEntity && config.enableArmorEffect.get(TROUBADOURS_CHARISMA)) {
            ItemStack helmetStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chestStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legsStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feetStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TROUBADOUR).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TROUBADOUR).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TROUBADOUR).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TROUBADOUR).get(EquipmentSlot.FEET).asItem()) {

                // Positive Effect
                if (instance.getEffectType().getCategory().equals(StatusEffectCategory.BENEFICIAL)) {
                    // Positive Effect
                    newDuration = instance.getDuration() * 2;
                } else if (instance.getEffectType().getCategory().equals(StatusEffectCategory.HARMFUL)){
                    // Negative Effect
                    newDuration = instance.getDuration() / 2;
                }
            }

            return livingEntity.addStatusEffect(new StatusEffectInstance(
                    instance.getEffectType(),
                    newDuration,
                    instance.getAmplifier(),
                    instance.isAmbient(),
                    instance.shouldShowParticles(),
                    instance.shouldShowIcon()
            ));
        } else {
            return livingEntity.addStatusEffect(instance);
        }
    }
}
