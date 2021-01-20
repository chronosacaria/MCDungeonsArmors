package chronosacaria.mcda.enchants.enchantments;

import chronosacaria.mcda.Mcda;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.registry.Registry;

public class BurningEnchantment extends Enchantment {
    public BurningEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT, Mcda.ID("burning"), this);
    }

    public int getMaxLevel(){
        return 3;
    }

    public void onUserDamaged(LivingEntity user, Entity attacker, int level){
        if (attacker != null){
            if (!attacker.isOnFire()){
                attacker.setOnFireFor(3 * level);
            }
        }
    }

}
