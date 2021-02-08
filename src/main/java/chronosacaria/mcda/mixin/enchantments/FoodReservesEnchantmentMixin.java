package chronosacaria.mcda.mixin.enchantments;

import chronosacaria.mcda.api.EnchantHelper;
import chronosacaria.mcda.enchants.EnchantsRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class FoodReservesEnchantmentMixin {

    @Shadow public abstract ItemStack getMainHandStack();

    @Inject(method = "consumeItem", at = @At("TAIL"))
    public void onPotionUsed(CallbackInfo ci){
        if (!((Object)this instanceof PlayerEntity))
            return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (playerEntity.isAlive()){
            List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(getMainHandStack());
            if (potionEffects.isEmpty()) return;
            if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH){
                if (EnchantHelper.hasEnchantment(playerEntity, EnchantsRegistry.FOOD_RESERVES)){
                    int foodReserveLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.FOOD_RESERVES,
                            playerEntity);
                    List<Item> foodList = Arrays.asList(Items.APPLE, Items.BREAD, Items.COOKED_SALMON,
                            Items.COOKED_PORKCHOP, Items.COOKED_MUTTON, Items.COOKED_COD, Items.COOKED_COD,
                            Items.COOKED_RABBIT, Items.COOKED_CHICKEN, Items.COOKED_BEEF, Items.MELON_SLICE,
                            Items.CARROT, Items.GOLDEN_CARROT, Items.GOLDEN_APPLE, Items.BAKED_POTATO);

                    while (foodReserveLevel > 0){
                            Item foodToDrop = foodList.get(playerEntity.getRandom().nextInt(foodList.size()));
                            ItemEntity foodDrop = new ItemEntity(playerEntity.world, playerEntity.getX(),
                                    playerEntity.getY(), playerEntity.getZ(), new ItemStack(foodToDrop));
                            playerEntity.world.spawnEntity(foodDrop);
                            foodReserveLevel--;
                    }
                }
            }
        }
    }
}

