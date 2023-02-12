package chronosacaria.mcda.items;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.registry.ItemsRegistry;
import com.google.common.base.Suppliers;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;

import java.util.EnumSet;
import java.util.function.Supplier;

import static net.minecraft.entity.EquipmentSlot.*;
import static net.minecraft.sound.SoundEvents.*;

public enum ArmorSets implements ArmorMaterial {

    // Armor order: boots, leggings, chestplate, helmet

    /*
     * Durability:
     * Leather - 5
     * Iron = 14
     * Turtle = 25
     * Diamond = 33
     * Netherite = 37
     * Gold = 7
     *
     * Enchantability:
     * Leather = 15
     * Iron = 9
     * Turtle = 9
     * Diamond = 10
     * Netherite = 15
     * Gold = 25
     *
     * Damage Reduction Amounts (boots, leggings, chestplate, helmet):
     * Leather = 1/2/3/1
     * Chain = 1/4/5/2
     * Iron = 2/5/6/2
     * Diamond = 3/6/8/3
     * Turtle = 2/5/6/2
     * Netherite = 3/6/8/3
     * Gold = 1/3/5/2
     *
     * ChampionArmorItem, - medium
     * DarkArmorItem, - heavy
     * PlateArmorItem, - heavy
     * GrimArmorItem uniq, - bone
     * ScaleMailItem, - medium
     * MercenaryArmorItem, - heavy
     * OcelotArmorItem, - pelt
     * ReinforcedMailItem - medium
     * reduce incoming damage by 35% => +2 armor toughness
     *
     *
     */

    /* * * * *|
    | LEATHER |
    |* * * * */
    ARCHER("archer", "archers",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    HUNTER("hunter", "hunters",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    OCELOT("ocelot", "ocelot_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    SHADOW_WALKER("shadowwalker", "shadow_walker_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    SPELUNKER("spelunker", "spelunker_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    CAVE_CRAWLER("cave", "cave_crawler",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    WOLF("wolf", "wolf_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    BLACK_WOLF("blackwolf", "black_wolf_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    FOX("fox", "fox_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    ARCTIC_FOX("arctic", "arctic_fox_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    CLIMBING_GEAR("climbing", "climbing_gear",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    RUGGED_CLIMBING_GEAR("rugged", "rugged_climbing_gear",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    SQUID("squid", "squid_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),
    GLOW_SQUID("glowsquid", "glow_squid_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            Items.LEATHER),

    GOAT("goat", "goat_gear",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            ItemsRegistry.GOAT_PELT),


    /* * * * |
    | FABRIC |
    | * * * */
    BATTLE("battle", "battle_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_BLACK),
    SPLENDID("splendid", "splendid_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_PURPLE),
    EVOCATION("evocation", "evocation_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_BLUE),
    EMBER("ember", "ember_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_RED),
    VERDANT("verdant", "verdant_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_GREEN),
    THIEF("thief", "thief_armor",
            15,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_BLACK),
    SPIDER("spider", "spider_armor",
            15,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_BLACK),
    SOUL_ROBE("soulrobe", "soul_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            Items.IRON_INGOT),
    SOULDANCER("souldancer", "souldancer_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            Items.IRON_INGOT),
    SPROUT("sprout", "sprout_armor",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_CYAN),
    LIVING_VINES("livingvines", "living_vines_armor",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_RED),
    PIGLIN("piglin", "piglin_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            Items.LEATHER),
    ENTERTAINER("entertainer", "entertainer_garb",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_CYAN),
    TROUBADOUR("troubadour", "troubadour_armor",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_MAGENTA),
    TELEPORTATION("teleportation", "teleportation_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_PURPLE),
    UNSTABLE("unstable", "unstable_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_PURPLE),
    /* * * |
    | BONE |
    | * * */
    PHANTOM("phantom", "phantom_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            ItemsRegistry.PHANTOM_BONES),
    FROST_BITE("frostbite", "frost_bite_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            ItemsRegistry.FROST_CRYSTAL),
    GRIM("grim", "grim_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            Items.BONE),
    WITHER("wither", "wither_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            Items.BONE),
    TURTLE("turtle", "turtle_armor",
            9,
            ITEM_ARMOR_EQUIP_TURTLE,
           Items.SCUTE),
    NIMBLE_TURTLE("nimbleturtle", "nimble_turtle_armor",
            9,
            ITEM_ARMOR_EQUIP_TURTLE,
           Items.SCUTE),

    /* * * * * * *|
    | LIGHT PLATE |
    |* * * * * * */
    SCALE_MAIL("scale", "scale_mail",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            ItemsRegistry.IRON_SCALE),
    HIGHLAND("highland", "highland_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            ItemsRegistry.IRON_SCALE),
    BEEHIVE("beehive", "beehive_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
           Items.HONEYCOMB),
    BEENEST("beenest", "beenest_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
           Items.HONEYCOMB),
    GHOSTLY("ghostly", "ghostly_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
           Items.LEATHER),
    GHOST_KINDLER("ghostkindler", "ghost_kindler",
            9,
            ITEM_ARMOR_EQUIP_IRON,
           Items.BLAZE_POWDER),
    GOLDEN_PIGLIN("goldenpiglin", "golden_piglin_armor",
            25,
            ITEM_ARMOR_EQUIP_GOLD,
           Items.GOLD_INGOT),
    SWEET_TOOTH("sweettooth", "sweet_tooth",
            9,
            ITEM_ARMOR_EQUIP_ELYTRA,
           Items.CAKE),
    VANGUARD("vanguard", "vanguard_armor",
                       9,
               ITEM_ARMOR_EQUIP_IRON,
            ItemsRegistry.IRON_SCALE),

    /* * * * * * * |
    | MEDIUM PLATE |
    | * * * * * * */
    REINFORCED_MAIL("reinforced", "reinforced_mail",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            ItemsRegistry.IRON_PLATE),
    STALWART_MAIL("stalwart", "stalwart_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            ItemsRegistry.IRON_PLATE),
    GUARDS("guards", "guards_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
           Items.IRON_INGOT),
    CURIOUS("curious", "curious_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
           Items.IRON_INGOT),
    SNOW("snow", "snow_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
           Items.SNOW_BLOCK),
    FROST("frost", "frost_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            ItemsRegistry.FROST_CRYSTAL),
    MERCENARY("mercenary", "mercenary_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            ItemsRegistry.IRON_PLATE),
    RENEGADE("renegade", "renegade_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            ItemsRegistry.IRON_PLATE),
    HUNGRY_HORROR("hungryhorror", "hungry_horror",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            ItemsRegistry.GLUT_CHARM),
    EMERALD("emeraldgear", "emerald_gear",
            10,
            ITEM_ARMOR_EQUIP_DIAMOND,
           Items.EMERALD),
    OPULENT("opulentarmor", "opulent_armor",
            10,
            ITEM_ARMOR_EQUIP_DIAMOND,
           Items.EMERALD),

    /* * * * * * *|
    | HEAVY PLATE |
    |* * * * * * */
    DARK("dark", "dark_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.NETHERITE_SCRAP),
    TITAN("titans", "titans_shroud",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.NETHERITE_SCRAP),
    ROYAL("royalguard", "royal_guard_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.NETHERITE_SCRAP),
    PLATE("plate", "plate_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            ItemsRegistry.IRON_PLATE),
    FULL_METAL("fullmetal", "full_metal_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            ItemsRegistry.IRON_PLATE),
    MYSTERY("whitemystery", "white_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.IRON_INGOT),
    BLUE_MYSTERY("bluemystery", "blue_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.IRON_INGOT),
    GREEN_MYSTERY("greenmystery", "green_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.IRON_INGOT),
    PURPLE_MYSTERY("purplemystery", "purple_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.IRON_INGOT),
    RED_MYSTERY("redmystery", "red_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.IRON_INGOT),
    CHAMPION("champions", "champions_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.NETHERITE_SCRAP),
    HERO("heros", "heros_armor",
            25,
            ITEM_ARMOR_EQUIP_NETHERITE,
           Items.NETHERITE_SCRAP),
    GILDED("gildedglory", "gilded_glory",
            10,
            ITEM_ARMOR_EQUIP_DIAMOND,
           Items.EMERALD),
    SHULKER("shulker", "shulker_armor",
                   10,
            BLOCK_SHULKER_BOX_CLOSE,
           Items.SHULKER_SHELL),
    STURDY_SHULKER("sturdyshulker", "sturdy_shulker_armor",
                   10,
            BLOCK_SHULKER_BOX_CLOSE,
           Items.SHULKER_SHELL),
    CAULDRON("cauldron", "cauldron_armor",
            15,
            ITEM_ARMOR_EQUIP_IRON,
            ItemsRegistry.IRON_PLATE),
    GOURDIAN("gourdian", "gourdian_armor",
            15,
            ITEM_ARMOR_EQUIP_ELYTRA,
            ItemsRegistry.FABRIC_BOLT_BLACK);

    private static final int[] baseDurability = new int[]{12, 14, 15, 10};
    private final String textureName;
    private final String setName;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final Supplier<Ingredient> repairIngredient;

    ArmorSets(String textureName, String setName, int enchantability,
              SoundEvent soundEvent, Item... items) {
        this.textureName = textureName;
        this.setName = setName;
        this.enchantability = enchantability;
        this.equipSound = soundEvent;
        this.repairIngredient =  Suppliers.memoize(() -> Ingredient.ofItems(items));
    }

    public String getSetName() {
        return setName;
    }

    public Rarity getRarity() {
        return switch (this) {
            case SPLENDID, BEEHIVE, HERO, TITAN, ROYAL, EMBER, VERDANT, GHOST_KINDLER, WITHER, CURIOUS, ARCHER, RENEGADE, HUNGRY_HORROR, MYSTERY, BLUE_MYSTERY, GREEN_MYSTERY, PURPLE_MYSTERY, RED_MYSTERY, FROST_BITE, FULL_METAL, STALWART_MAIL, HIGHLAND, FROST, SOULDANCER, CAVE_CRAWLER, SPIDER, BLACK_WOLF, ARCTIC_FOX, OPULENT, GILDED, RUGGED_CLIMBING_GEAR, GOLDEN_PIGLIN, GOAT, TROUBADOUR, UNSTABLE, STURDY_SHULKER -> Rarity.RARE;
            default -> Rarity.UNCOMMON;
        };
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return baseDurability[slot.getEntitySlotId()] * Mcda.CONFIG.mcdaArmorStatsConfig.armorStats.get(this).durabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return Mcda.CONFIG.mcdaArmorStatsConfig.armorStats.get(this).protection.get(slot);
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.textureName;
    }

    @Override
    public float getToughness() {
        return  Mcda.CONFIG.mcdaArmorStatsConfig.armorStats.get(this).toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return  Mcda.CONFIG.mcdaArmorStatsConfig.armorStats.get(this).knockbackRes;
    }

    public boolean isOf(ArmorSets armorSets) {
        return this == armorSets;
    }

    public boolean isOf(ArmorSets... armorSets) {
        for (ArmorSets armorSet : armorSets)
            if (this == armorSet)
                return true;
        return false;
    }

    public EnumSet<EquipmentSlot> getSlots() {
        return switch (this) {
            case BATTLE, SPLENDID -> EnumSet.of(CHEST, LEGS);
            case EVOCATION, EMBER, VERDANT, VANGUARD -> EnumSet.of(HEAD, CHEST, LEGS);
            case SCALE_MAIL -> EnumSet.of(CHEST, LEGS, FEET);
            case HUNTER -> EnumSet.of(CHEST);
            default -> EnumSet.of(HEAD, CHEST, LEGS, FEET);
        };
    }
}
