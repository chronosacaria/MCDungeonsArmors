package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class RecyclerEnchantment extends ArmorEnchantment {
    public RecyclerEnchantment(EnchantID enchantID) {
        super(enchantID);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        DamageSource damageSource = user.getRecentDamageSource();

        if (damageSource != null && damageSource.isProjectile()) {
            float recyclerRand = user.getRandom().nextFloat();
            float recyclerChance = level * 0.1F;
            if (recyclerRand <= recyclerChance) {
                ItemEntity arrowDrop = new ItemEntity(user.world, user.getX(), user.getY(), user.getZ(),
                        new ItemStack(Items.ARROW));
                user.world.spawnEntity(arrowDrop);
            }
        }
    }
}
