/*
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
public class CowardiceEnchantmentMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void onCowadiceTick(CallbackInfo ci){
        ServerPlayerEntity playerEntity = (ServerPlayerEntity) (Object) this;
        if (playerEntity == null) return;
        if (playerEntity.isAlive()){
            float maxHealth = playerEntity.getMaxHealth();
            float currentHeath = playerEntity.getHealth();
            if (currentHeath == maxHealth){
                if (EnchantHelper.hasEnchantment(playerEntity, EnchantsRegistry.COWARDICE)){
                    int cowardiceLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.COWARDICE, playerEntity);
                    StatusEffectInstance strengthBoost = new StatusEffectInstance(StatusEffects.STRENGTH, 40,
                            cowardiceLevel + 1);
                    playerEntity.addStatusEffect(strengthBoost);
                }
            }
        }
    }
}

 */
