package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.AOEHelper;
import chronosacaria.mcda.enchants.EnchantmentEffects;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import chronosacaria.mcda.registry.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static chronosacaria.mcda.enchants.EnchantID.HEAL_ALLIES;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow public abstract boolean isAlive();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    // Mixins for enchants related to consuming a potion
    @Inject(method = "consumeItem", at = @At("HEAD"))
    public void consumeItem(CallbackInfo ci) {
        if (!((Object)this instanceof PlayerEntity))
            return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (!playerEntity.isAlive())
            return;

        EnchantmentEffects.applyFoodReserves(playerEntity);
        EnchantmentEffects.applyPotionBarrier(playerEntity);
        EnchantmentEffects.applySurpriseGift(playerEntity);
    }

    // Mixins for enchants related to damage reception
    @Inject(method = "damage", at = @At("HEAD"))
    public void healAlliesDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (!((Object)this instanceof PlayerEntity))
            return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        int healAlliesLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.enchants.get(HEAL_ALLIES), playerEntity);

        EnchantmentEffects.applyHealAllies(playerEntity, (0.25f * amount) * healAlliesLevel);

    }

    // Thief Armour Sneaking Player Invisibility
    @Inject(method = "tick", at = @At("HEAD"))
    private void thiefInvisibilityTick(CallbackInfo ci){
        if(!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (playerEntity.isAlive()){
            ItemStack helmetStack = playerEntity.inventory.armor.get(3);
            ItemStack chestStack = playerEntity.inventory.armor.get(2);
            ItemStack legsStack = playerEntity.inventory.armor.get(1);
            ItemStack feetStack = playerEntity.inventory.armor.get(0);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.HEAD).asItem()
                && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.CHEST).asItem()
                && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.LEGS).asItem()
                && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.THIEF).get(EquipmentSlot.FEET).asItem())
                    setInvisible(isSneaking());
        }
    }

    // Spider Armour Climbing
    @Inject(method = "isClimbing", at = @At("HEAD"), cancellable = true)
    private void spiderArmourClimbing(CallbackInfoReturnable<Boolean> cir){
        if(!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (playerEntity.isAlive()){
            ItemStack helmetStack = playerEntity.inventory.armor.get(3);
            ItemStack chestStack = playerEntity.inventory.armor.get(2);
            ItemStack legsStack = playerEntity.inventory.armor.get(1);
            ItemStack feetStack = playerEntity.inventory.armor.get(0);

            if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.HEAD).asItem()
                    && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.CHEST).asItem()
                    && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.LEGS).asItem()
                    && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.SPIDER).get(EquipmentSlot.FEET).asItem())
                if (this.horizontalCollision){
                    cir.setReturnValue(true);
                }
        }
    }
}
