package chronosacaria.mcda.items;

import chronosacaria.mcda.registry.ItemsRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

// Careful: Names directly correspond to item ids and should therefore never change
public enum ItemID implements ItemConvertible {
    // Mob Drops
    BONE_RIB_CAGE,
    PHANTOM_BONES,
    PHANTOM_SKIN,
    OCELOT_PELT,
    OCELOT_PELT_BLACK,
    WOLF_PELT,
    WOLF_PELT_BLACK,
    FOX_PELT,
    FOX_PELT_ARCTIC,
    GOAT_PELT,

    // Block Drops
    FROST_CRYSTAL,

    // Crafting
    GLUT_CHARM,
    IRON_PLATE,
    IRON_SCALE,
    FABRIC_BOLT_WHITE,
    FABRIC_BOLT_BLACK,
    FABRIC_BOLT_RED,
    FABRIC_BOLT_GREEN,
    FABRIC_BOLT_BROWN,
    FABRIC_BOLT_BLUE,
    FABRIC_BOLT_PURPLE,
    FABRIC_BOLT_CYAN,
    FABRIC_BOLT_LIGHT_GRAY,
    FABRIC_BOLT_GRAY,
    FABRIC_BOLT_PINK,
    FABRIC_BOLT_LIME,
    FABRIC_BOLT_YELLOW,
    FABRIC_BOLT_LIGHT_BLUE,
    FABRIC_BOLT_MAGENTA,
    FABRIC_BOLT_ORANGE,

    // Upgrades
    UPGRADE_CORE,
    UPGRADE_CORE_ARCHER,
    UPGRADE_CORE_DEPTH,
    UPGRADE_CORE_EMBER,
    UPGRADE_CORE_FOX,
    UPGRADE_CORE_FROST,
    UPGRADE_CORE_GLUT,
    UPGRADE_CORE_GOLDEN,
    UPGRADE_CORE_HIGHLAND,
    UPGRADE_CORE_HIVE,
    UPGRADE_CORE_MAGICKED,
    UPGRADE_CORE_METAL,
    UPGRADE_CORE_OCEANIC,
    UPGRADE_CORE_SHADOWS,
    UPGRADE_CORE_SPIDER,
    UPGRADE_CORE_SOUL,
    UPGRADE_CORE_VERDANT,
    UPGRADE_CORE_WITHER,
    UPGRADE_CORE_WOLF;

    public Item asItem() {
        return ItemsRegistry.items.get(this);
    }
}
