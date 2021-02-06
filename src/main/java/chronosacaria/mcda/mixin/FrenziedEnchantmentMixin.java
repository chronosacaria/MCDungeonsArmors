
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.EnchantHelper;
import chronosacaria.mcda.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class FrenziedEnchantmentMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void onFrenziedTick(CallbackInfo ci){
        ServerPlayerEntity playerEntity = (ServerPlayerEntity) (Object) this;
        if (playerEntity == null) return;
        if (playerEntity.isAlive()){
            float maxHealth = playerEntity.getMaxHealth();
            float currentHeath = playerEntity.getHealth();
            if (currentHeath <= (0.5F * maxHealth)){
                if (EnchantHelper.hasEnchantment(playerEntity, EnchantsRegistry.FRENZIED)){
                    int cowardiceLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.FRENZIED, playerEntity);
                    StatusEffectInstance frenzied = new StatusEffectInstance(StatusEffects.HASTE, 40,
                            cowardiceLevel);
                    playerEntity.addStatusEffect(frenzied);
                }
            }
        }

    }
}
