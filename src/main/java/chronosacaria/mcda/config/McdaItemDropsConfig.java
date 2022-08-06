package chronosacaria.mcda.config;

import chronosacaria.mcda.items.itemhelpers.DropHelper;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;

@Config(name = "mcda_item_drops")
public class McdaItemDropsConfig implements ConfigData {
    public EnumMap<DropHelper, Integer> numberOfRollsOnLootTable = new EnumMap<>(DropHelper.class);
    public EnumMap<DropHelper, Float> dropBaseChance = new EnumMap<>(DropHelper.class);
    public EnumMap<DropHelper, Float> dropChancePerLootingLevel = new EnumMap<>(DropHelper.class);

    public McdaItemDropsConfig(){
        for (DropHelper dropHelper : DropHelper.values())
            numberOfRollsOnLootTable.put(dropHelper, 1);
        numberOfRollsOnLootTable.replace(DropHelper.GOAT_PELT, 2);

        dropBaseChance.put(DropHelper.PHANTOM_BONES, 0.35f);
        dropBaseChance.put(DropHelper.PHANTOM_SKIN, 0.10f);
        dropBaseChance.put(DropHelper.OCELOT_PELT, 0.35f);
        dropBaseChance.put(DropHelper.OCELOT_PELT_BLACK, 0.10f);
        dropBaseChance.put(DropHelper.SKELETON_SKULL, 0.05f);
        dropBaseChance.put(DropHelper.WOLF_PELT, 0.25f);
        dropBaseChance.put(DropHelper.WOLF_PELT_BLACK, 0.10f);
        dropBaseChance.put(DropHelper.FOX_PELT, 0.25f);
        dropBaseChance.put(DropHelper.FOX_PELT_ARCTIC, 0.25f);
        dropBaseChance.put(DropHelper.EVOCATION_ROBE, 0.20f);
        dropBaseChance.put(DropHelper.GOAT_PELT, 0.40f);
        dropBaseChance.put(DropHelper.FROST_CRYSTAL, 0.20f);
        dropBaseChance.put(DropHelper.MYSTERY_GEMSTONE, 0.01f);

        dropChancePerLootingLevel.put(DropHelper.PHANTOM_BONES, 0.20f);
        dropChancePerLootingLevel.put(DropHelper.PHANTOM_SKIN, 0.10f);
        dropChancePerLootingLevel.put(DropHelper.OCELOT_PELT, 0.20f);
        dropChancePerLootingLevel.put(DropHelper.OCELOT_PELT_BLACK, 0.10f);
        dropChancePerLootingLevel.put(DropHelper.SKELETON_SKULL, 0.10f);
        dropChancePerLootingLevel.put(DropHelper.WOLF_PELT, 0.20f);
        dropChancePerLootingLevel.put(DropHelper.WOLF_PELT_BLACK, 0.10f);
        dropChancePerLootingLevel.put(DropHelper.FOX_PELT, 0.50f);
        dropChancePerLootingLevel.put(DropHelper.FOX_PELT_ARCTIC, 0.10f);
        dropChancePerLootingLevel.put(DropHelper.EVOCATION_ROBE, 0.15f);
        dropChancePerLootingLevel.put(DropHelper.GOAT_PELT, 0.50f);
    }
}
