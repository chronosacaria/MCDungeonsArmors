
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.ArmorEffectsHelper;
import chronosacaria.mcda.enchants.EnchantmentEffects;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        ServerPlayerEntity playerEntity = (ServerPlayerEntity) (Object) this;
        if (!playerEntity.isAlive())
            return;

        // Effects from Enchantments
        EnchantmentEffects.applyCowardice(playerEntity);
        EnchantmentEffects.applyFrenzied(playerEntity);

        // Effects from Armour Sets
        ArmorEffectsHelper.applyHaste(playerEntity); // Cave Dweller (below Y level 32)
        ArmorEffectsHelper.applyHeroOfTheVillage(playerEntity); // Hero's Armour
        ArmorEffectsHelper.applyLuck(playerEntity); // Opulent Armour
    }
}
