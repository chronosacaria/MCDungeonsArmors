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

public class EnchantsRegistry {
    public static final EnumMap<EnchantID, Enchantment> enchants = new EnumMap<>(EnchantID.class);

    public static void init() {
        for (EnchantID enchantID : EnchantID.values()) {

            if (!Mcda.CONFIG.mcdaEnableEnchantAndEffectConfig.enableEnchantment.get(enchantID))
                continue;

            Enchantment enchantment = switch (enchantID) {
                case BAG_OF_SOULS -> new BagOfSoulsEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case BURNING -> new BurningEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case CHILLING -> new ChillingEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case COWARDICE -> new CowardiceEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case DEATH_BARTER -> new DeathBarterEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case DEFLECT -> new DeflectEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case FIRE_FOCUS -> new FireFocusEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case FIRE_TRAIL -> new FireTrailEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET,
                        EquipmentSlot.FEET);
                case RECKLESS -> new RecklessEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case FOOD_RESERVES -> new FoodReservesEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case FRENZIED -> new FrenziedEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case HEAL_ALLIES -> new HealAlliesEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case LUCKY_EXPLORER -> new LuckyExplorerEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET,
                        EquipmentSlot.FEET);
                case POISON_FOCUS -> new PoisonFocusEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case POTION_BARRIER -> new PotionBarrierEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case RECYCLER -> new RecyclerEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case SNOWBALL -> new SnowballEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case SURPRISE_GIFT -> new SurpriseGiftEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR,
                        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
                case SWIFTFOOTED -> new SwiftFootedEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET,
                        EquipmentSlot.FEET);
                //noinspection UnnecessaryDefault
                default -> new ArmorEnchantment(enchantID);
            };

            enchants.put(enchantID, enchantment);
            registerEnchant(enchantID.toString().toLowerCase(), enchantment);
        }
    }

    protected static void registerEnchant(String id, Enchantment enchant) {
        Registry.register(Registry.ENCHANTMENT, Mcda.ID(id), enchant);
    }
}
