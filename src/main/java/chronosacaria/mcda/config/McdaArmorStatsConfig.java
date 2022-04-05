package chronosacaria.mcda.config;

import chronosacaria.mcda.items.ArmorSets;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.entity.EquipmentSlot;

import java.util.EnumMap;
import java.util.EnumSet;

import static chronosacaria.mcda.items.ArmorSets.*;
import static net.minecraft.entity.EquipmentSlot.*;

@Config(name = "mcda_armor_stats")
public class McdaArmorStatsConfig implements ConfigData {
    public EnumMap<ArmorSets, ArmorStats> armorStats = new EnumMap<>(ArmorSets.class);

    protected ArmorStats setProtection(int head, int chest, int legs, int feet, ArmorSets set) {
        return armorStats.get(set).setProtection(head, chest, legs, feet);
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

    public McdaArmorStatsConfig(){
        for (ArmorSets armorSet : ArmorSets.values())
            armorStats.put(armorSet, new ArmorStats());

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
        setProtection(3, 7, 5, 3, GOURDIAN).setDurabilityMultiplier(25);

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
        setProtection(2, 6, 5, 2, SWEET_TOOTH).setDurabilityMultiplier(14);
        setProtection(2, 5, 4, 2, VANGUARD).setDurabilityMultiplier(14);

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
        setProtection(3, 8, 6, 3, DARK).setDurabilityMultiplier(36)
                .setToughness(1.0F);
        setProtection(3, 8, 6, 3, TITAN).setDurabilityMultiplier(36)
                .setToughness(2.0F);
        setProtection(3, 8, 6, 3, ROYAL).setDurabilityMultiplier(36)
                .setToughness(1.5F);
        setProtection(3, 8, 6, 3, PLATE).setDurabilityMultiplier(36)
                .setToughness(1.5F);
        setProtection(4, 9, 7, 4, FULL_METAL).setDurabilityMultiplier(36)
                .setToughness(3.0F);
        setProtection(4, 9, 7, 4, CAULDRON).setDurabilityMultiplier(36)
                .setToughness(2.0F);
        setProtection(3, 8, 6, 3, MYSTERY).setDurabilityMultiplier(36)
                .setToughness(1.5F);
        setProtection(3, 8, 6, 3, BLUE_MYSTERY).setDurabilityMultiplier(36)
                .setToughness(1.5F);
        setProtection(3, 8, 6, 3, GREEN_MYSTERY).setDurabilityMultiplier(36)
                .setToughness(1.5F);
        setProtection(3, 8, 6, 3, PURPLE_MYSTERY).setDurabilityMultiplier(36)
                .setToughness(1.5F);
        setProtection(3, 8, 6, 3, RED_MYSTERY).setDurabilityMultiplier(36)
                .setToughness(1.5F);
        setProtection(3, 8, 6, 3, CHAMPION).setDurabilityMultiplier(36)
                .setToughness(1.0F);
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
        setAttackDamageBoost(0.0375, GOURDIAN);
        setAttackDamageBoost(0.05, RENEGADE);
        setAttackDamageBoost(0.05, HUNGRY_HORROR).setMovementSpeedBoost(0.0625);
        setMovementSpeedBoost(0.05, OCELOT);
        setMovementSpeedBoost(0.08, SHADOW_WALKER).setAttackSpeedBoost(0.025);
        setMovementSpeedBoost(0.075, PHANTOM);
        setMovementSpeedBoost(0.075, FROST_BITE);
        setAttackDamageBoost(0.05, PLATE).setMovementSpeedBoost(-0.0375);
        setAttackDamageBoost(0.05, FULL_METAL).setMovementSpeedBoost(-0.025);
        setAttackDamageBoost(0.05, CAULDRON).setMovementSpeedBoost(-0.025);
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
