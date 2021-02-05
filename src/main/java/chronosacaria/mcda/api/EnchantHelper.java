package chronosacaria.mcda.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class EnchantHelper {
    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment){
        return  enchantment != null && EnchantmentHelper.getLevel(enchantment, stack) > 0;
    }

    public static boolean hasEnchantment(LivingEntity entity, Enchantment enchantment){
        return enchantment != null && EnchantmentHelper.getEquipmentLevel(enchantment, entity) > 0;
    }

    public static boolean shooterIsLiving(PersistentProjectileEntity persistentProjectileEntity){
        return persistentProjectileEntity.getOwner() != null && persistentProjectileEntity.getOwner() instanceof LivingEntity;
    }

    public static boolean arrowHitLivingentity(HitResult hitResult){
        if (hitResult instanceof EntityHitResult){
            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
            if (entityHitResult.getEntity() instanceof LivingEntity){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
