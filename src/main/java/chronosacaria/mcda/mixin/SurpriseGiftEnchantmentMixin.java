/*
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.EnchantHelper;
import chronosacaria.mcda.enchants.EnchantsRegistry;
import chronosacaria.mcda.items.itemlists.McdaPotionsList;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
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
public abstract class SurpriseGiftEnchantmentMixin {
    @Shadow protected ItemStack activeItemStack;

    @Shadow public abstract ItemStack getMainHandStack();

    @Inject(method = "stopUsingItem", at = @At("TAIL"))
    public void onPotionUsed(CallbackInfo ci){
        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        if (playerEntity.isAlive()){
            List<StatusEffectInstance> potionEffects = PotionUtil.getPotionEffects(getMainHandStack());
            if (potionEffects.isEmpty()) return;
            if (potionEffects.get(0).getEffectType() == StatusEffects.INSTANT_HEALTH){
                if (EnchantHelper.hasEnchantment(playerEntity, EnchantsRegistry.SURPRISE_GIFT)){
                    int surpriseGiftLevel = EnchantmentHelper.getEquipmentLevel(EnchantsRegistry.SURPRISE_GIFT,
                            playerEntity);
                    float surpriseGiftChance = 0.5F * surpriseGiftLevel;

                    ItemStack strengthPotion = PotionUtil.setPotion(new ItemStack(Items.POTION),
                            McdaPotionsList.SHORT_STRENGTH);
                    ItemStack speedPotion = PotionUtil.setPotion(new ItemStack(Items.POTION),
                            McdaPotionsList.SHORT_SWIFTNESS);
                    ItemStack shadowBrew = PotionUtil.setPotion(new ItemStack(Items.POTION),
                            McdaPotionsList.SHADOW_BREW);
                    ItemStack oakwoodBrew = PotionUtil.setPotion(new ItemStack(Items.POTION),
                            McdaPotionsList.OAKWOOD_BREW);
                    List<ItemStack> giftList = Arrays.asList(strengthPotion, speedPotion, shadowBrew, oakwoodBrew);

                    while (surpriseGiftChance > 0){
                        float surpriseGiftRand = playerEntity.getRandom().nextFloat();
                        if (surpriseGiftRand <= surpriseGiftChance){
                            ItemStack potionToDrop = giftList.get(playerEntity.getRandom().nextInt(giftList.size()));
                            ItemEntity surpriseGift = new ItemEntity(playerEntity.world, playerEntity.getX(),
                                    playerEntity.getY(), playerEntity.getZ(), potionToDrop);
                            playerEntity.world.spawnEntity(surpriseGift);
                        }
                        surpriseGiftChance -= 1.0F;
                    }
                }
            }
        }
    }
}
 */
