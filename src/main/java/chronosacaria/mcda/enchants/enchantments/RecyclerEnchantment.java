package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class RecyclerEnchantment extends Enchantment {
    public RecyclerEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
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

    @Override
    public int getMinPower(int level) {
        return 1 + level * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 5;
    }
}
