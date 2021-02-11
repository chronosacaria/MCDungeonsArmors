package chronosacaria.mcda.config;

import chronosacaria.mcda.items.ArmorSets;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;

import java.util.EnumMap;

import static chronosacaria.mcda.items.ArmorSets.*;

@Config(name = "mcda_boosts")
public class McdaBoostsConfig implements ConfigData {

    public static class StatBoosts {
        public double attackDamage;
        public double attackSpeed;
        public double movementSpeed;

        public StatBoosts setAttackDamageBoost(double attackDamage) {
            this.attackDamage = attackDamage;
            return this;
        }

        public StatBoosts setAttackSpeedBoost(double attackSpeed) {
            this.attackSpeed = attackSpeed;
            return this;
        }

        public StatBoosts setMovementSpeedBoost(double movement) {
            this.movementSpeed = movement;
            return this;
        }
    }

    public static final McdaBoostsConfig config;

    static {
        AutoConfig.register(McdaBoostsConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(McdaBoostsConfig.class).getConfig();
    }

    public EnumMap<ArmorSets, StatBoosts> statBoosts = new EnumMap<>(ArmorSets.class);

    protected StatBoosts setAttackDamageBoost(double value, ArmorSets set) {
        return statBoosts.get(set).setAttackDamageBoost(value);
    }

    protected StatBoosts setAttackSpeedBoost(double value, ArmorSets set) {
        return statBoosts.get(set).setAttackSpeedBoost(value);
    }

    protected StatBoosts setMovementSpeedBoost(double value, ArmorSets set) {
        return statBoosts.get(set).setMovementSpeedBoost(value);
    }

    public McdaBoostsConfig() {
        for (ArmorSets armorSet : ArmorSets.values()) {
            statBoosts.put(armorSet, new StatBoosts());
        }

        setAttackDamageBoost(0.075, SPLENDID);
        setAttackDamageBoost(0.0375, BEENEST);
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
        setMovementSpeedBoost(0.075, OCELOT);
        setMovementSpeedBoost(0.075, SHADOW_WALKER);
        setMovementSpeedBoost(0.075, PHANTOM);
        setMovementSpeedBoost(0.075, FROST_BITE);
        setAttackDamageBoost(0.05, PLATE).setMovementSpeedBoost(-0.0375);
        setAttackDamageBoost(0.05, FULL_METAL).setMovementSpeedBoost(-0.0375);
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

