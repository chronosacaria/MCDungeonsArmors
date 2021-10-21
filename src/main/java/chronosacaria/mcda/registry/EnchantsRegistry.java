package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import chronosacaria.mcda.enchants.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;

import static chronosacaria.mcda.config.McdaConfig.config;

public class EnchantsRegistry {
    public static final EnumMap<EnchantID, Enchantment> enchants = new EnumMap<>(EnchantID.class);

    public static void init() {
        for (EnchantID enchantID : EnchantID.values()) {

            if (!config.enableEnchantment.get(enchantID))
                continue;

            Enchantment enchantment;

            switch (enchantID) {
                case BURNING:
                    enchantment = new BurningEnchantment(enchantID);
                    break;
                case CHILLING:
                    enchantment = new ChillingEnchantment(enchantID);
                    break;
                case COWARDICE:
                    enchantment = new CowardiceEnchantment(enchantID);
                    break;
                case FIRE_TRAIL:
                    enchantment = new FireTrailEnchantment(enchantID);
                    break;
                case RECKLESS:
                    enchantment = new RecklessEnchantment(enchantID);
                    break;
                case RECYCLER:
                    enchantment = new RecyclerEnchantment(enchantID);
                    break;
                case SNOWBALL:
                    enchantment = new SnowballEnchantment(enchantID);
                    break;
                default:
                    enchantment = new ArmorEnchantment(enchantID);
                    break;
            }

            enchants.put(enchantID, enchantment);
            registerEnchant(enchantID.toString().toLowerCase(), enchantment);
        }
    }

    protected static void registerEnchant(String id, Enchantment enchant) {
        Registry.register(Registry.ENCHANTMENT, Mcda.ID(id), enchant);
    }
}
