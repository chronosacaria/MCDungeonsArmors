package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.enchants.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

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

    public static void init() {
        BURNING        = registerEnchant("burning",        new BurningEnchantment());
        CHILLING       = registerEnchant("chilling",       new ChillingEnchantment());
        COWARDICE      = registerEnchant("cowardice",      new CowardiceEnchantment());
        DEFLECT        = registerEnchant("deflect",        new DeflectEnchantment());
        FOOD_RESERVES  = registerEnchant("food_reserves",  new FoodReservesEnchantment());
        FRENZIED       = registerEnchant("frenzied",       new FrenziedEnchantment());
        POTION_BARRIER = registerEnchant("potion_barrier", new PotionBarrierEnchantment());
        RECYCLER       = registerEnchant("recycler",       new RecyclerEnchantment());
        SNOWBALL       = registerEnchant("snowball",       new SnowballEnchantment());
        SURPRISE_GIFT  = registerEnchant("surprise_gift",  new SurpriseGiftEnchantment());
    }

    protected static Enchantment registerEnchant(String id, Enchantment enchant) {
        return Registry.register(Registry.ENCHANTMENT, Mcda.ID(id), enchant);
    }
}
