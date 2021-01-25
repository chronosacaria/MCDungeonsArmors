package chronosacaria.mcda.mixin;

import chronosacaria.mcda.enchants.EnchantsRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class SwiftfootedEnchantmentMixin extends Entity {

    public SwiftfootedEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "playerTick", at = @At("HEAD"))

    public void onSwiftfootedEnchantmentJump(CallbackInfo ci){
        System.out.println("test");
        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (!(playerEntity.isOnGround())) {
            int swiftfootedLevel = EnchantmentHelper.getLevel(EnchantsRegistry.SWIFTFOOTED,
                    playerEntity.getEquippedStack(EquipmentSlot.FEET));

            if (EnchantmentHelper.getLevel(EnchantsRegistry.SWIFTFOOTED, playerEntity.getEquippedStack(EquipmentSlot.FEET)) > 0) {
                StatusEffectInstance speedBoost = new StatusEffectInstance(StatusEffects.SPEED, 60,
                        swiftfootedLevel - 1);
                playerEntity.addStatusEffect(speedBoost);
            }
        }
    }
}
