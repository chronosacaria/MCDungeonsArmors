package chronosacaria.mcda.config;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.effects.ArmorEffectID;
import chronosacaria.mcda.enchants.EnchantID;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.itemhelpers.DropHelper;
import chronosacaria.mcda.items.itemhelpers.ItemSettingsHelper;
import chronosacaria.mcda.items.itemhelpers.SpawnHelper;
import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.entity.EquipmentSlot;

import java.util.*;

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

    public LinkedHashMap<ItemSettingsHelper, Boolean> armorsEnabledInLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, ArrayList<String>> commonLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, ArrayList<String>> uncommonLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, ArrayList<String>> rareLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ItemSettingsHelper, ArrayList<String>> epicLootTables = new LinkedHashMap<>();
    public LinkedHashMap<ArmorSets, Float> armorLootTableSpawnRates = new LinkedHashMap<>();

    @Comment("Controlled Teleportation for Unstable Robes, default = true")
    public boolean controlledTeleportation = true;

    // convenience methods:
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

    // set config defaults
    public McdaConfig() {
        for (EnchantID enchantID : EnchantID.values())
            enableEnchantment.put(enchantID, true);

        for (ArmorEffectID armorEffectID : ArmorEffectID.values())
            enableArmorEffect.put(armorEffectID, true);

        for (DropHelper dropHelper : DropHelper.values())
            maxDropAmounts.put(dropHelper, 1.0F);
        maxDropAmounts.replace(DropHelper.GOAT_PELT, 2.0F);

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
        armorSpawnRates.put(SpawnHelper.VANGUARD, 0.05f);

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

        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()){
            armorsEnabledInLootTables.put(ItemSettingsHelper.ENABLE_ARMOR_IN_LOOT_TABLES, true);
        }

        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()){
            commonLootTables.put(ItemSettingsHelper.COMMON_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/abandoned_mineshaft",
                    "minecraft:chests/shipwreck_supply",
                    "minecraft:chests/shipwreck_treasure",
                    "minecraft:chests/desert_pyramid",
                    "minecraft:chests/village/village_weaponsmith"));
        }
        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()) {
            uncommonLootTables.put(ItemSettingsHelper.UNCOMMON_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/jungle_temple",
                    "minecraft:chests/nether_bridge",
                    "minecraft:chests/bastion_bridge",
                    "minecraft:chests/bastion_other",
                    "minecraft:chests/bastion_treasure"));
        }
        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()) {
            rareLootTables.put(ItemSettingsHelper.RARE_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/underwater_ruin_small",
                    "minecraft:chests/underwater_ruin_big",
                    "minecraft:chests/ruined_portal",
                    "minecraft:chests/simple_dungeon",
                    "minecraft:chests/igloo_chest"));
        }
        for (ItemSettingsHelper ignored : ItemSettingsHelper.values()) {
            epicLootTables.put(ItemSettingsHelper.EPIC_LOOT_TABLES, Lists.newArrayList(
                    "minecraft:chests/stronghold_corridor",
                    "minecraft:chests/stronghold_crossing",
                    "minecraft:chests/stronghold_library",
                    "minecraft:chests/pillager_outpost",
                    "minecraft:chests/end_city_treasure"));
        }

        for (ArmorSets armorSets : ArmorSets.values()){
            armorLootTableSpawnRates.put(armorSets, 0.10f);
        }
        armorLootTableSpawnRates.replace(SPLENDID, 0.05f);
        armorLootTableSpawnRates.replace(BEEHIVE, 0.05f);
        armorLootTableSpawnRates.replace(HERO, 0.01f);
        armorLootTableSpawnRates.replace(RUGGED_CLIMBING_GEAR, 0.05f);
        armorLootTableSpawnRates.replace(TITAN, 0.01f);
        armorLootTableSpawnRates.replace(ROYAL, 0.05f);
        armorLootTableSpawnRates.replace(OPULENT, 0.05f);
        armorLootTableSpawnRates.replace(GILDED, 0.01f);
        armorLootTableSpawnRates.replace(TROUBADOUR, 0.00f);
        armorLootTableSpawnRates.replace(EMBER, 0.05f);
        armorLootTableSpawnRates.replace(VERDANT, 0.05f);
        armorLootTableSpawnRates.replace(GHOST_KINDLER, 0.05f);
        armorLootTableSpawnRates.replace(WITHER, 0.05f);
        armorLootTableSpawnRates.replace(GOURDIAN, 0.05f);
        armorLootTableSpawnRates.replace(CURIOUS, 0.05f);
        armorLootTableSpawnRates.replace(ARCHER, 0.05f);
        armorLootTableSpawnRates.replace(RENEGADE, 0.05f);
        armorLootTableSpawnRates.replace(HUNGRY_HORROR, 0.01f);
        armorLootTableSpawnRates.replace(RED_MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(GREEN_MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(BLUE_MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(PURPLE_MYSTERY, 0.00f);
        armorLootTableSpawnRates.replace(SHADOW_WALKER, 0.05f);
        armorLootTableSpawnRates.replace(FROST_BITE, 0.05f);
        armorLootTableSpawnRates.replace(GOLDEN_PIGLIN, 0.05f);
        armorLootTableSpawnRates.replace(FULL_METAL, 0.05f);
        armorLootTableSpawnRates.replace(CAULDRON, 0.05f);
        armorLootTableSpawnRates.replace(STALWART_MAIL, 0.05f);
        armorLootTableSpawnRates.replace(HIGHLAND, 0.05f);
        armorLootTableSpawnRates.replace(STURDY_SHULKER, 0.05f);
        armorLootTableSpawnRates.replace(FROST, 0.05f);
        armorLootTableSpawnRates.replace(SOULDANCER, 0.05f);
        armorLootTableSpawnRates.replace(CAVE_CRAWLER, 0.05f);
        armorLootTableSpawnRates.replace(GLOW_SQUID, 0.05f);
        armorLootTableSpawnRates.replace(UNSTABLE, 0.05f);
        armorLootTableSpawnRates.replace(SPIDER, 0.05f);
        armorLootTableSpawnRates.replace(NIMBLE_TURTLE, 0.05f);
        armorLootTableSpawnRates.replace(BLACK_WOLF, 0.05f);
        armorLootTableSpawnRates.replace(FOX, 0.01f);
        armorLootTableSpawnRates.replace(ARCTIC_FOX, 0.01f);


    }
}

