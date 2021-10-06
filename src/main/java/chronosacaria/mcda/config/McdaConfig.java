package chronosacaria.mcda.config;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.effects.ArmorEffectID;
import chronosacaria.mcda.enchants.EnchantID;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.itemhelpers.DropHelper;
import chronosacaria.mcda.items.itemhelpers.SpawnHelper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.entity.EquipmentSlot;

import java.util.EnumMap;
import java.util.EnumSet;

import static chronosacaria.mcda.items.ArmorSets.*;
import static net.minecraft.entity.EquipmentSlot.*;

@Config(name = Mcda.MOD_ID)
public class McdaConfig implements ConfigData {

    public static final McdaConfig config;

    static {
        AutoConfig.register(McdaConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(McdaConfig.class).getConfig();
    }

    // config contents:
    public EnumMap<EnchantID, Boolean> enableEnchantment = new EnumMap<>(EnchantID.class);
    public EnumMap<ArmorEffectID, Boolean> enableArmorEffect = new EnumMap<>(ArmorEffectID.class);
    public EnumMap<DropHelper, Float> maxDropAmounts = new EnumMap<>(DropHelper.class);
    public EnumMap<SpawnHelper, Float> armorSpawnRates = new EnumMap<>(SpawnHelper.class);
    public EnumMap<ArmorSets, ArmorStats> armorStats = new EnumMap<>(ArmorSets.class);

    @Comment("Controlled Teleportation for Teleportation and Unstable Robes, default = false")
    public boolean controlledTeleportation = false;

    //@Comment("Armor Set Spawn Rates")
    //public float ghostlyArmor = 0.05f;
    //public float ghostlyKindler = 0.01f;
    //public float grimArmor = 0.05f;
    //public float darkArmor = 0.10f;
    //public float thiefArmor = 0.10f;
    //public float royalArmor = 0.05f;
    //public float titansShroud = 0.025f;
    //public float plateArmor = 0.05f;
    //public float fullMetalArmor = 0.01f;
    //public float snowArmor = 0.03f;
    //public float wolfArmor = 0.05f;
    //public float foxArmor = 0.01f;
    //public float reinforcedMail = 0.05f;
    //public float stalwartMail = 0.01f;
    //public float scaleMail = 0.05f;
    //public float mercenaryArmor = 0.05f;
    //public float spelunkerArmor = 0.05f;
    //public float caveCrawler = 0.01f;
    //public float heroArmor = 0.10f;
    //public float gildedArmor = 0.10f;
    //public float teleportationRobes = 0.10f;
    //public float unstableRobes = 0.01f;
    //public float mysteryArmors = 0.05f;

    // convenience methods:
    protected ArmorStats setProtection(int head, int chest, int legs, int feet, ArmorSets set) {
        return armorStats.get(set).setProtection(head, chest, legs, feet);
    }

    protected ArmorStats setDurabilityMultiplier(int value, ArmorSets set){
        return armorStats.get(set).setDurabilityMultiplier(value);
    }

    protected ArmorStats setAttackDamageBoost(double value, ArmorSets set) {
        return armorStats.get(set).setAttackDamageBoost(value);
    }

    protected ArmorStats setAttackSpeedBoost(double value, ArmorSets set) {
        return armorStats.get(set).setAttackSpeedBoost(value);
    }

    protected ArmorStats setMovementSpeedBoost(double value, ArmorSets set) {
        return armorStats.get(set).setMovementSpeedBoost(value);
    }

    protected ArmorStats setKnockbackResistance(float value, ArmorSets set) {
        return armorStats.get(set).setKnockbackRes(value);
    }

    // set config defaults
    public McdaConfig() {
        for (EnchantID enchantID : EnchantID.values()) {
            enableEnchantment.put(enchantID, true);
        }

        for (ArmorEffectID armorEffectID : ArmorEffectID.values()){
            enableArmorEffect.put(armorEffectID, true);
        }

        for (DropHelper dropHelper : DropHelper.values()){
            maxDropAmounts.put(dropHelper, 1.0F);
        }

        for (SpawnHelper spawnHelper : SpawnHelper.values()){
            armorSpawnRates.put(SpawnHelper.GHOSTLY, 0.05f);
            armorSpawnRates.put(SpawnHelper.GHOST_KINDLER, 0.01f);
            armorSpawnRates.put(SpawnHelper.GRIM, 0.05f);
            armorSpawnRates.put(SpawnHelper.DARK, 0.1f);
            armorSpawnRates.put(SpawnHelper.ROYAL, 0.05f);
            armorSpawnRates.put(SpawnHelper.TITAN, 0.025f);
            armorSpawnRates.put(SpawnHelper.THIEF, 0.1f);
            armorSpawnRates.put(SpawnHelper.PLATE, 0.05f);
            armorSpawnRates.put(SpawnHelper.FULL_METAL, 0.01f);
            armorSpawnRates.put(SpawnHelper.SNOW, 0.03f);
            armorSpawnRates.put(SpawnHelper.WOLF, 0.05f);
            armorSpawnRates.put(SpawnHelper.FOX, 0.01f);
            armorSpawnRates.put(SpawnHelper.REINFORCED, 0.05f);
            armorSpawnRates.put(SpawnHelper.STALWART, 0.01f);
            armorSpawnRates.put(SpawnHelper.SCALE, 0.05f);
            armorSpawnRates.put(SpawnHelper.MERCENARY, 0.05f);
            armorSpawnRates.put(SpawnHelper.SPELUNKER, 0.05f);
            armorSpawnRates.put(SpawnHelper.CAVE_CRAWLER, 0.01f);
            armorSpawnRates.put(SpawnHelper.HERO, 0.1f);
            armorSpawnRates.put(SpawnHelper.GILDED, 0.1f);
            armorSpawnRates.put(SpawnHelper.TELEPORTATION, 0.1f);
            armorSpawnRates.put(SpawnHelper.UNSTABLE, 0.01f);
            armorSpawnRates.put(SpawnHelper.MYSTERY, 0.05f);
        }

        for (ArmorSets armorSet : ArmorSets.values()) {
            armorStats.put(armorSet, new ArmorStats());
        }

        for (ArmorSets armorSet : ArmorSets.values()) {
            ArmorStats s = new ArmorStats();
            s.protection = new EnumMap<>(EquipmentSlot.class);
            for (EquipmentSlot slot : EnumSet.of(HEAD, CHEST, LEGS, FEET)) {
                s.protection.put(slot, 0);
            }

            this.armorStats.put(armorSet, s);
        }

        // Leather Armors
        setProtection(1, 3, 2, 1, HUNTER).setDurabilityMultiplier(15);
        setProtection(2, 6, 4, 2, ARCHER).setDurabilityMultiplier(15);
        setProtection(1, 5, 3, 1, OCELOT).setDurabilityMultiplier(15);
        setProtection(2, 6, 4, 2, SHADOW_WALKER).setDurabilityMultiplier(15);
        setProtection(2, 6, 4, 2, SPELUNKER).setDurabilityMultiplier(15);
        setProtection(2, 6, 5, 2, CAVE_CRAWLER).setDurabilityMultiplier(15);
        setProtection(2, 5, 4, 2, WOLF).setDurabilityMultiplier(15);
        setProtection(2, 6, 5, 2, BLACK_WOLF).setDurabilityMultiplier(15);
        setProtection(2, 6, 4, 2, FOX).setDurabilityMultiplier(15);
        setProtection(2, 6, 4, 2, ARCTIC_FOX).setDurabilityMultiplier(15);
        setProtection(1, 6, 5, 1, CLIMBING_GEAR).setDurabilityMultiplier(15);
        setProtection(2, 6, 5, 2, RUGGED_CLIMBING_GEAR).setDurabilityMultiplier(15);
        setProtection(1, 5, 3, 1, SQUID).setDurabilityMultiplier(15);
        setProtection(2, 6, 4, 2, GLOW_SQUID).setDurabilityMultiplier(15);
        setProtection(2, 6, 5, 2, GOAT).setDurabilityMultiplier(15)
                .setKnockbackRes(0.2F);

        // Fabric Armours
        setProtection(1, 3, 2, 1, BATTLE).setDurabilityMultiplier(5);
        setProtection(2, 3, 2, 2, SPLENDID).setDurabilityMultiplier(5);
        setProtection(1, 3, 3, 1, EVOCATION).setDurabilityMultiplier(5);
        setProtection(1, 3, 3, 1, EMBER).setDurabilityMultiplier(5);
        setProtection(1, 3, 3, 1, VERDANT).setDurabilityMultiplier(5);
        setProtection(2, 5, 4, 2, THIEF).setDurabilityMultiplier(5);
        setProtection(2, 5, 4, 2, SPIDER).setDurabilityMultiplier(5);
        setProtection(1, 6, 5, 1, SOUL_ROBE).setDurabilityMultiplier(5)
                .setToughness(2.0F);
        setProtection(2, 6, 5, 2, SOULDANCER).setDurabilityMultiplier(5)
                .setToughness(2.0F);
        setProtection(2, 3, 2, 2, SPROUT).setDurabilityMultiplier(5);
        setProtection(2, 3, 2, 2, LIVING_VINES).setDurabilityMultiplier(5);
        setProtection(2, 4, 2, 2, PIGLIN).setDurabilityMultiplier(5);
        setProtection(2, 4, 3, 2, ENTERTAINER).setDurabilityMultiplier(5);
        setProtection(3, 5, 4, 3, TROUBADOUR).setDurabilityMultiplier(5);
        setProtection(2, 4, 3, 2, TELEPORTATION).setDurabilityMultiplier(5);
        setProtection(3, 5, 4, 3, UNSTABLE).setDurabilityMultiplier(5);

        // Bone Armours
        setProtection(2, 6, 4, 2, PHANTOM).setDurabilityMultiplier(25);
        setProtection(3, 7, 5, 3, FROST_BITE).setDurabilityMultiplier(25);
        setProtection(2, 6, 4, 2, GRIM).setDurabilityMultiplier(25);
        setProtection(3, 7, 5, 3, WITHER).setDurabilityMultiplier(25);
        setProtection(2, 6, 4, 2, TURTLE).setDurabilityMultiplier(25);
        setProtection(3, 7, 5, 3, NIMBLE_TURTLE).setDurabilityMultiplier(25);

        // Light Plate Armor
        setProtection(2, 5, 4, 2, SCALE_MAIL).setDurabilityMultiplier(14);
        setProtection(2, 6, 5, 2, HIGHLAND).setDurabilityMultiplier(14);
        setProtection(2, 5, 4, 2, BEENEST).setDurabilityMultiplier(14);
        setProtection(2, 6, 5, 2, BEEHIVE).setDurabilityMultiplier(14);
        setProtection(2, 5, 4, 2, GHOSTLY).setDurabilityMultiplier(14);
        setProtection(2, 6, 5, 2, GHOST_KINDLER).setDurabilityMultiplier(14);
        setProtection(2, 6, 5, 2, GOLDEN_PIGLIN).setDurabilityMultiplier(14);

        // Medium Plate Armor
        setProtection(3, 6, 4, 3, REINFORCED_MAIL).setDurabilityMultiplier(30)
                .setToughness(1.0F);
        setProtection(3, 7, 5, 3, STALWART_MAIL).setDurabilityMultiplier(30)
                .setToughness(1.0F);
        setProtection(3, 7, 5, 3, GUARDS).setDurabilityMultiplier(30);
        setProtection(3, 7, 5, 3, CURIOUS).setDurabilityMultiplier(30)
                .setToughness(2.0F);
        setProtection(3, 7, 5, 3, SNOW).setDurabilityMultiplier(30)
                .setToughness(2.0F);
        setProtection(3, 7, 5, 3, FROST).setDurabilityMultiplier(30)
                .setToughness(2.0F);
        setProtection(3, 5, 4, 3, MERCENARY).setDurabilityMultiplier(30)
                .setToughness(1.5F);
        setProtection(3, 6, 5, 3, RENEGADE).setDurabilityMultiplier(30)
                .setToughness(1.75F);
        setProtection(3, 7, 5, 3, HUNGRY_HORROR).setDurabilityMultiplier(30)
                .setToughness(2.0F);
        setProtection(3, 7, 5, 3, EMERALD).setDurabilityMultiplier(30)
                .setToughness(2.0F);
        setProtection(4, 7, 5, 4, OPULENT).setDurabilityMultiplier(30)
                .setToughness(2.0F);

        // Heavy Plate Armor
        setProtection(3, 8, 6, 3, DARK).setDurabilityMultiplier(36);
        setProtection(3, 8, 6, 3, TITAN).setDurabilityMultiplier(36);
        setProtection(3, 8, 6, 3, ROYAL).setDurabilityMultiplier(36);
        setProtection(3, 8, 6, 3, PLATE).setDurabilityMultiplier(36);
        setProtection(4, 9, 7, 4, FULL_METAL).setDurabilityMultiplier(36);
        setProtection(3, 8, 6, 3, MYSTERY).setDurabilityMultiplier(36);
        setProtection(3, 8, 6, 3, BLUE_MYSTERY).setDurabilityMultiplier(36);
        setProtection(3, 8, 6, 3, GREEN_MYSTERY).setDurabilityMultiplier(36);
        setProtection(3, 8, 6, 3, PURPLE_MYSTERY).setDurabilityMultiplier(36);
        setProtection(3, 8, 6, 3, RED_MYSTERY).setDurabilityMultiplier(36);
        setProtection(3, 8, 6, 3, CHAMPION).setDurabilityMultiplier(36);
        setProtection(4, 9, 7, 4, HERO).setDurabilityMultiplier(36)
                .setToughness(2.0F);
        setProtection(3, 8, 6, 3, GILDED).setDurabilityMultiplier(36)
                .setToughness(2.0F);
        setProtection(3, 7, 5, 3, SHULKER).setDurabilityMultiplier(36)
                .setToughness(1.0F);
        setProtection(3, 8, 6, 3, STURDY_SHULKER).setDurabilityMultiplier(36)
                .setToughness(3.0F).setKnockbackRes(0.2F);

        // Stat Boosts
        setAttackDamageBoost(0.075, SPLENDID);
        setAttackDamageBoost(0.0375, BEENEST);
        setAttackDamageBoost(0.0375, BEEHIVE);
        setAttackDamageBoost(0.0375, CHAMPION);
        setAttackDamageBoost(0.075, HERO).setAttackSpeedBoost(0.0375);
        setAttackDamageBoost(0.0375, DARK);
        setAttackDamageBoost(0.075, TITAN).setAttackSpeedBoost(0.0375);
        setAttackDamageBoost(0.075, ROYAL);
        setAttackDamageBoost(0.05, EVOCATION);
        setAttackDamageBoost(0.05, EMBER);
        setAttackDamageBoost(0.05, VERDANT);
        setAttackDamageBoost(0.025, FOX);
        setAttackDamageBoost(0.025, ARCTIC_FOX);
        setAttackDamageBoost(0.0375, GHOSTLY);
        setAttackDamageBoost(0.0375, GHOST_KINDLER);
        setAttackDamageBoost(0.0375, GRIM);
        setAttackDamageBoost(0.0375, WITHER);
        setAttackDamageBoost(0.05, RENEGADE);
        setAttackDamageBoost(0.05, HUNGRY_HORROR).setMovementSpeedBoost(0.0625);
        setMovementSpeedBoost(0.05, OCELOT);
        setMovementSpeedBoost(0.08, SHADOW_WALKER).setAttackSpeedBoost(0.025);
        setMovementSpeedBoost(0.075, PHANTOM);
        setMovementSpeedBoost(0.075, FROST_BITE);
        setAttackDamageBoost(0.05, PLATE).setMovementSpeedBoost(-0.0375);
        setAttackDamageBoost(0.05, FULL_METAL).setMovementSpeedBoost(-0.025);
        setAttackDamageBoost(0.0375, SCALE_MAIL);
        setAttackDamageBoost(0.0375, HIGHLAND);
        setMovementSpeedBoost(0.025, CAVE_CRAWLER);
        setAttackSpeedBoost(0.0375, SPIDER);
        setAttackDamageBoost(0.025, WOLF).setAttackSpeedBoost(0.025);
        setAttackDamageBoost(0.025, BLACK_WOLF).setAttackSpeedBoost(0.025);
        setAttackSpeedBoost(0.05, EMERALD);
        setAttackSpeedBoost(0.05, OPULENT);
        setAttackSpeedBoost(0.05, GILDED);
    }
}

