package chronosacaria.mcda.enchants.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

import java.util.Map.Entry;

public class BurningEnchantment extends Enchantment {

    public BurningEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        Random random = user.getRandom();
        Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.chooseEquipmentWith(BurningEnchantment.this, user);
        if (shouldDamageAttacker(level, random)) {
            if (attacker != null) {
                if (!attacker.isOnFire()) {
                    attacker.setOnFireFor(3 * level);
                }
            }
        }
    }

    public static boolean shouldDamageAttacker(int level, Random random){
        if (level <= 0){
            return false;
        } else {
            return random.nextFloat() < 0.15F * (float) level;
        }
    }

    @Override
    protected boolean canAccept(Enchantment other){
        return !(other instanceof ChillingEnchantment);
    }

    @Override
    public int getMinPower(int level) {
        return 1 + level * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 5;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
