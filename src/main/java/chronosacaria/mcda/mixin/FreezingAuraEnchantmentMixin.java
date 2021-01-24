package chronosacaria.mcda.mixin;

import chronosacaria.mcda.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class FreezingAuraEnchantmentMixin extends Entity {

    @Shadow @Nullable private LivingEntity attacker;

    @Shadow @Final private DefaultedList<ItemStack> equippedArmor;

    public FreezingAuraEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "damage")
    private void freezingAuraDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        ItemStack helmetStack = equippedArmor.get(0);
        ItemStack chestStack = equippedArmor.get(1);
        ItemStack legsStack = equippedArmor.get(2);
        ItemStack feetStack = equippedArmor.get(3);

        Entity attackingEntity = source.getAttacker();

        Entity entity = this;

        if (EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING_AURA, helmetStack) >= 1
                || EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING_AURA, chestStack) >= 1
                || EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING_AURA, legsStack) >= 1
                || EnchantmentHelper.getLevel(EnchantsRegistry.FREEZING_AURA, feetStack) >= 1 ) {
            if (attackingEntity instanceof LivingEntity){
                if (attackingEntity != null){
                    ((LivingEntity) attackingEntity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
                            400, 10));
                }
            }
        }
    }
}
