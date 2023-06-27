package chronosacaria.mcda.registries;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.api.McdaEnchantmentHelper;
import chronosacaria.mcda.enchants.EnchantID;
import chronosacaria.mcda.items.ArmorSets;
import com.blamejared.clumps.api.events.ClumpsEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import static chronosacaria.mcda.effects.ArmorEffectID.SOULDANCER_EXPERIENCE;
import static chronosacaria.mcda.enchants.EnchantID.BAG_OF_SOULS;

public class CompatRegistry {

    public static void register() {
        if (FabricLoader.getInstance().isModLoaded("clumps")) {
            ClumpsEvents.VALUE_EVENT.register(event -> {
                int amount = event.getValue();
                PlayerEntity playerEntity = event.getPlayer();

                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableArmorEffect.get(SOULDANCER_EXPERIENCE))
                    if (CleanlinessHelper.checkFullArmor(playerEntity, ArmorSets.SOULDANCER))
                        amount = (int) Math.round(1.5 * amount);

                if (Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(BAG_OF_SOULS)) {
                    int bagOfSoulsLevel = McdaEnchantmentHelper.getBagOfSoulsLevel(EnchantsRegistry.enchants.get(EnchantID.BAG_OF_SOULS),
                            playerEntity);

                    if (bagOfSoulsLevel > 0) {
                        int bagOfSoulsCount = 0;
                        for (ItemStack itemStack : playerEntity.getArmorItems())
                            if (EnchantmentHelper.getLevel(EnchantsRegistry.enchants.get(BAG_OF_SOULS), itemStack) > 0)
                                bagOfSoulsCount++;

                        // Thank you, Amph
                        amount = (amount * (1 + (bagOfSoulsLevel / 12)) + Math.round(((bagOfSoulsLevel % 12) / 12.0f) * amount)) * bagOfSoulsCount;
                    }
                }
                event.setValue(amount);
                return null;
            });
        }
    }
}
