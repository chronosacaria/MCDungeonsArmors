package chronosacaria.mcda.config;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.items.ArmorSets;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
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

    @ConfigEntry.Gui.Excluded
    public EnumMap<ArmorSets, Stats> stats = new EnumMap<>(ArmorSets.class);

    public static class Stats {
        public EnumMap<EquipmentSlot, Integer> armor = new EnumMap<>(EquipmentSlot.class);
        public float toughness;
        public float knockbackRes;

        public double attackDamageBoost;
        public double attackSpeedBoost;
        public double movementSpeedBoost;

        protected Stats setProtection(int head, int chest, int legs, int feet) {
            this.armor.put(HEAD, head);
            this.armor.put(CHEST, chest);
            this.armor.put(LEGS, legs);
            this.armor.put(FEET, feet);
            return this;
        }

        protected Stats setToughness(float toughness) {
            this.toughness = toughness;
            return this;
        }

        protected Stats setKnockbackRes(float knockbackRes) {
            this.knockbackRes = knockbackRes;
            return this;
        }

        public Stats setAttackDamageBoost(double attackDamage) {
            this.attackDamageBoost = attackDamage;
            return this;
        }

        public Stats setAttackSpeedBoost(double attackSpeed) {
            this.attackSpeedBoost = attackSpeed;
            return this;
        }

        public Stats setMovementSpeedBoost(double movement) {
            this.movementSpeedBoost = movement;
            return this;
        }
    }

    protected Stats setProtection(int head, int chest, int legs, int feet, ArmorSets set) {
        return stats.get(set).setProtection(head, chest, legs, feet);
    }

    protected Stats setAttackDamageBoost(double value, ArmorSets set) {
        return stats.get(set).setAttackDamageBoost(value);
    }

    protected Stats setAttackSpeedBoost(double value, ArmorSets set) {
        return stats.get(set).setAttackSpeedBoost(value);
    }

    protected Stats setMovementSpeedBoost(double value, ArmorSets set) {
        return stats.get(set).setMovementSpeedBoost(value);
    }

    public McdaConfig() {
        for (ArmorSets armorSet : ArmorSets.values()) {
            stats.put(armorSet, new Stats());
        }

        for (ArmorSets armorSet : ArmorSets.values()) {
            Stats s = new Stats();
            s.armor = new EnumMap<>(EquipmentSlot.class);
            for (EquipmentSlot slot : EnumSet.of(HEAD, CHEST, LEGS, FEET)) {
                s.armor.put(slot, 0);
            }

            this.stats.put(armorSet, s);
        }

        // Leather Armors
        setProtection(1, 3, 2, 1, HUNTER);
        setProtection(2, 6, 4, 2, ARCHER);
        setProtection(2, 6, 4, 2, OCELOT);
        setProtection(2, 6, 4, 2, SHADOW_WALKER);
        setProtection(2, 6, 4, 2, SPELUNKER);
        setProtection(2, 6, 5, 2, CAVE_CRAWLER);
        setProtection(2, 6, 4, 2, WOLF);
        setProtection(2, 6, 5, 2, BLACK_WOLF);
        setProtection(2, 6, 4, 2, FOX);
        setProtection(1, 6, 5, 1, CLIMBING_GEAR);
        setProtection(2, 6, 5, 2, RUGGED_CLIMBING_GEAR);
        // setProtection(2, 6, 5, 2, GOAT); // TODO
        // setKnockbackRes(0.2F, GOAT);

        // Fabric Armours
        setProtection(1, 3, 2, 1, BATTLE);
        setProtection(2, 3, 2, 2, SPLENDID);
        setProtection(1, 3, 3, 1, EVOCATION);
        setProtection(1, 3, 3, 1, EMBER);
        setProtection(1, 3, 3, 1, VERDANT);
        setProtection(2, 5, 4, 2, THIEF);
        setProtection(2, 5, 4, 2, SPIDER);
        setProtection(1, 6, 5, 1, SOUL_ROBE)
                .setToughness(2.0F);
        setProtection(2, 6, 5, 2, SOULDANCER)
                .setToughness(2.0F);

        // Bone Armours
        setProtection(2, 6, 5, 3, PHANTOM);
        setProtection(3, 7, 5, 3, FROST_BITE);
        setProtection(2, 6, 5, 3, GRIM);
        setProtection(3, 7, 5, 3, WITHER);

        // Light Plate Armor
        setProtection(2, 5, 4, 2, SCALE_MAIL);
        setProtection(2, 6, 5, 2, HIGHLAND);
        setProtection(2, 5, 4, 2, BEENEST);
        setProtection(2, 6, 5, 2, BEEHIVE);
        setProtection(2, 5, 4, 2, GHOSTLY);
        setProtection(2, 6, 5, 2, GHOST_KINDLER);

        // Medium Plate Armor
        setProtection(3, 6, 4, 3, REINFORCED_MAIL)
                .setToughness(1.0F);
        setProtection(3, 7, 5, 3, STALWART_MAIL)
                .setToughness(1.0F);
        setProtection(3, 7, 5, 3, GUARDS);
        setProtection(3, 7, 5, 3, CURIOUS)
                .setToughness(2.0F);
        setProtection(3, 7, 5, 3, SNOW)
                .setToughness(2.0F);
        setProtection(3, 7, 5, 3, FROST)
                .setToughness(2.0F);
        setProtection(3, 5, 4, 3, MERCENARY)
                .setToughness(1.5F);
        setProtection(3, 7, 5, 3, HUNGRY_HORROR)
                .setToughness(2.0F);
        setProtection(3, 7, 5, 3, EMERALD)
                .setToughness(2.0F);
        setProtection(4, 7, 5, 4, OPULENT)
                .setToughness(2.0F);

        // Heavy Plate Armor
        setProtection(3, 8, 6, 3, DARK);
        setProtection(3, 8, 6, 3, TITAN);
        setProtection(3, 8, 6, 3, ROYAL);
        setProtection(3, 8, 6, 3, PLATE);
        setProtection(4, 8, 6, 4, FULL_METAL);
        setProtection(3, 8, 6, 3, MYSTERY);
        setProtection(3, 8, 6, 3, BLUE_MYSTERY);
        setProtection(3, 8, 6, 3, GREEN_MYSTERY);
        setProtection(3, 8, 6, 3, PURPLE_MYSTERY);
        setProtection(3, 8, 6, 3, RED_MYSTERY);
        setProtection(3, 8, 6, 3, CHAMPION);
        setProtection(4, 9, 7, 4, HERO)
                .setToughness(2.0F);
        setProtection(3, 8, 6, 3, GILDED)
                .setToughness(2.0F);

        // Stat Boosts
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

