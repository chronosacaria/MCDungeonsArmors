package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import java.util.Map.Entry;
import java.util.Random;

public class BurningEnchantment extends ArmorEnchantment {

    public BurningEnchantment(EnchantID enchantID) {
        super(enchantID);
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
}
