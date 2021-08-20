
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.effects.EnchantmentEffects;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.registry.ArmorsRegistry;
import net.minecraft.entity.EquipmentSlot;
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
        if (playerEntity == null) return;


        // Effects from Enchantments
        EnchantmentEffects.applyCowardice(playerEntity);
        EnchantmentEffects.applyFrenzied(playerEntity);
        EnchantmentEffects.applyReckless(playerEntity);
        EnchantmentEffects.applySwiftfooted(playerEntity);

        // Effects from Armour Sets
        ArmorEffects.applyFireResistance(playerEntity); // Sprout & Living Vines Armour
        ArmorEffects.applyInvisibility(playerEntity); // Thief Armour Sneaking
        ArmorEffects.applyHaste(playerEntity); // Cave Crawler (below Y level 32) & Highland (above Y level 100)
        ArmorEffects.applyHeroOfTheVillage(playerEntity); // Hero's Armour & Gilded Glory
        ArmorEffects.applyHunger(playerEntity); // Hungry Horror Armour
        ArmorEffects.applyLuck(playerEntity); // Opulent Armour
        ArmorEffects.applySlowFalling(playerEntity); // Phantom and Frost Bite Armour
        ArmorEffects.applySprintingSpeed(playerEntity); // Shadow Walker Armour
        ArmorEffects.applyWaterBreathing(playerEntity); // Glow Squid Armour Water Breathing under water


        ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legsStack = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feetStack = playerEntity.getEquippedStack(EquipmentSlot.FEET);
        if (helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TELEPORTATION).get(EquipmentSlot.HEAD).asItem()
                && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TELEPORTATION).get(EquipmentSlot.CHEST).asItem()
                && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TELEPORTATION).get(EquipmentSlot.LEGS).asItem()
                && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.TELEPORTATION).get(EquipmentSlot.FEET).asItem()
                || helmetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.UNSTABLE).get(EquipmentSlot.HEAD).asItem()
                && chestStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.UNSTABLE).get(EquipmentSlot.CHEST).asItem()
                && legsStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.UNSTABLE).get(EquipmentSlot.LEGS).asItem()
                && feetStack.getItem() == ArmorsRegistry.armorItems.get(ArmorSets.UNSTABLE).get(EquipmentSlot.FEET).asItem()) {
            ((PlayerTeleportationStateAccessor) playerEntity).setInTeleportationState(true);
        }
    }
}
