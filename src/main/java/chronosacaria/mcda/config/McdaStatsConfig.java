package chronosacaria.mcda.config;

import chronosacaria.mcda.items.ArmorSets;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.minecraft.entity.EquipmentSlot;

import java.util.EnumMap;
import java.util.EnumSet;

import static chronosacaria.mcda.items.ArmorSets.*;
import static net.minecraft.entity.EquipmentSlot.*;

@Config(name = "mcda_stats")
public class McdaStatsConfig implements ConfigData {
    public static class Stats {
        public EnumMap<EquipmentSlot, Integer> armorMap = new EnumMap<>(EquipmentSlot.class);
        public float toughness;
        public float knockbackRes;

        protected Stats setProtection(int head, int chest, int legs, int feet) {
            this.armorMap.put(HEAD, head);
            this.armorMap.put(CHEST, chest);
            this.armorMap.put(LEGS, legs);
            this.armorMap.put(FEET, feet);
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
    }

    public static final McdaStatsConfig config;

    static {
        AutoConfig.register(McdaStatsConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(McdaStatsConfig.class).getConfig();
    }

    // (set, slot) -> stats
    public EnumMap<ArmorSets, Stats> stats = new EnumMap<>(ArmorSets.class);

    protected Stats setProtection(int head, int chest, int legs, int feet, ArmorSets set) {
        return stats.get(set).setProtection(head, chest, legs, feet);
    }

    public McdaStatsConfig() {
        for (ArmorSets armorSet : ArmorSets.values()) {
            Stats s = new Stats();
            s.armorMap = new EnumMap<>(EquipmentSlot.class);
            for (EquipmentSlot slot : EnumSet.of(HEAD, CHEST, LEGS, FEET)) {
                s.armorMap.put(slot, 0);
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
    }

}

