package chronosacaria.mcda.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.Iterator;

public class McdaEnchantmentHelper {

    public static boolean hasFireAspect(LivingEntity entity) {
        return EnchantmentHelper.getEquipmentLevel(Enchantments.FIRE_ASPECT, entity) > 0;
    }

    public static int getBagOfSoulsLevel(Enchantment enchantment, PlayerEntity entity){
        Iterable<ItemStack> iterable = enchantment.getEquipment(entity).values();
        if (iterable == null) {
            return 0;
        } else {
            int i = 0;
            Iterator var4 = iterable.iterator();

            while(var4.hasNext()) {
                ItemStack itemStack = (ItemStack)var4.next();
                int j = EnchantmentHelper.getLevel(enchantment, itemStack);
                i += j;
            }

            return i;
        }
    }
}
