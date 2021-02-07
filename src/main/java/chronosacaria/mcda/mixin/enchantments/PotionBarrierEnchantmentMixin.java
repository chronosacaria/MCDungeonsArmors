package chronosacaria.mcda.mixin.enchantments;

import chronosacaria.mcda.api.EnchantHelper;
import chronosacaria.mcda.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class PotionBarrierEnchantmentMixin {

    @Shadow
    public abstract ItemStack getMainHandStack();

    @Inject(method = "consumeItem", at = @At("TAIL"))
    public void onPotionBarrierPotionUsed(CallbackInfo ci) {
        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (playerEntity.isAlive()) {
            List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(getMainHandStack());
            if (potionEffects.isEmpty()) return;
            if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH) {
                if (EnchantHelper.hasEnchantment(playerEntity, EnchantsRegistry.POTION_BARRIER)) {
                    int potionBarrierLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.POTION_BARRIER,
                            playerEntity);
                    int duration = 60 + (20 * potionBarrierLevel);
                    StatusEffectInstance resistance = new StatusEffectInstance(StatusEffects.RESISTANCE, duration, 3);
                    playerEntity.addStatusEffect(resistance);
                }
            }
        }
    }
}
