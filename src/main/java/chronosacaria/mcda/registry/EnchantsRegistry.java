package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.enchants.ArmorEnchantment;
import chronosacaria.mcda.enchants.EnchantID;
import chronosacaria.mcda.enchants.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
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
                case BAG_OF_SOULS:
                    enchantment = new BagOfSoulsEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case BURNING:
                    enchantment = new BurningEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case CHILLING:
                    enchantment = new ChillingEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case COWARDICE:
                    enchantment = new CowardiceEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case DEATH_BARTER:
                    enchantment = new DeathBarterEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case DEFLECT:
                    enchantment = new DeflectEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case FIRE_FOCUS:
                    enchantment = new FireFocusEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case FIRE_TRAIL:
                    enchantment = new FireTrailEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET,
                            EquipmentSlot.FEET);
                    break;
                case RECKLESS:
                    enchantment = new RecklessEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case FOOD_RESERVES:
                    enchantment = new FoodReservesEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case FRENZIED:
                    enchantment = new FrenziedEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case HEAL_ALLIES:
                    enchantment = new HealAlliesEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case LUCKY_EXPLORER:
                    enchantment = new LuckyExplorerEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET,
                            EquipmentSlot.FEET);
                    break;
                case POISON_FOCUS:
                    enchantment = new PoisonFocusEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case POTION_BARRIER:
                    enchantment = new PotionBarrierEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case RECYCLER:
                    enchantment = new RecyclerEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case SNOWBALL:
                    enchantment = new SnowballEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case SURPRISE_GIFT:
                    enchantment = new SurpriseGiftEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                    break;
                case SWIFTFOOTED:
                    enchantment = new SwiftFootedEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET,
                            EquipmentSlot.FEET);
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
