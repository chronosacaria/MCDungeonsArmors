package chronosacaria.mcda.enchants;

import chronosacaria.mcda.enchants.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class EnchantsRegistry {
    public static Enchantment BURNING;
    public static Enchantment CHILLING;
    public static Enchantment COWARDICE;
    public static Enchantment DEFLECT;
    public static Enchantment FOOD_RESERVES;
    public static Enchantment FRENZIED;
    public static Enchantment POTION_BARRIER;
    public static Enchantment RECYCLER;
    public static Enchantment SNOWBALL;
    public static Enchantment SURPRISE_GIFT;

    public static void init(){
        BURNING = new BurningEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        CHILLING = new ChillingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        COWARDICE = new CowardiceEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        DEFLECT = new DeflectEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        FOOD_RESERVES = new FoodReservesEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        FRENZIED = new FrenziedEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        POTION_BARRIER = new PotionBarrierEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        RECYCLER = new RecyclerEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,
                new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        SNOWBALL = new SnowballEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
        SURPRISE_GIFT = new SurpriseGiftEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR,new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }
}
