package chronosacaria.mcda.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class McdaEnchantmentHelper {
    public static int getBagOfSoulsLevel(Enchantment enchantment, PlayerEntity playerEntity){
        int totalLevel = 0;
        for (ItemStack itemStack : enchantment.getEquipment(playerEntity).values())
            totalLevel += EnchantmentHelper.getLevel(enchantment, itemStack);

        return totalLevel;
    }
}
