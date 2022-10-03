package chronosacaria.mcda.items;

import chronosacaria.mcda.Mcda;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Lazy;
import net.minecraft.util.Rarity;

import java.util.function.Supplier;

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
    ARCHER("mcda:archer", "archers",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    HUNTER("mcda:hunter", "hunters",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    OCELOT("mcda:ocelot", "ocelot_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    SHADOW_WALKER("mcda:shadowwalker", "shadow_walker_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    SPELUNKER("mcda:spelunker", "spelunker_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    CAVE_CRAWLER("mcda:cave", "cave_crawler",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    WOLF("mcda:wolf", "wolf_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    BLACK_WOLF("mcda:blackwolf", "black_wolf_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    FOX("mcda:fox", "fox_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    ARCTIC_FOX("mcda:arctic", "arctic_fox_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    CLIMBING_GEAR("mcda:climbing", "climbing_gear",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    RUGGED_CLIMBING_GEAR("mcda:rugged", "rugged_climbing_gear",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    SQUID("mcda:squid", "squid_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    GLOW_SQUID("mcda:glowsquid", "glow_squid_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),

    GOAT("mcda:goat", "goat_gear",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(ItemID.GOAT_PELT)),


    /* * * * |
    | FABRIC |
    | * * * */
    BATTLE("mcda:battle", "battle_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_BLACK)),
    SPLENDID("mcda:splendid", "splendid_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_PURPLE)),
    EVOCATION("mcda:evocation", "evocation_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_BLUE)),
    EMBER("mcda:ember", "ember_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_RED)),
    VERDANT("mcda:verdant", "verdant_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_GREEN)),
    THIEF("mcda:thief", "thief_armor",
            15,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_BLACK)),
    SPIDER("mcda:spider", "spider_armor",
            15,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_BLACK)),
    SOUL_ROBE("mcda:soulrobe", "soul_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    SOULDANCER("mcda:souldancer", "souldancer_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    SPROUT("mcda:sprout", "sprout_armor",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_CYAN)),
    LIVING_VINES("mcda:livingvines", "living_vines_armor",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_RED)),
    PIGLIN("mcda:piglin", "piglin_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.LEATHER)),
    ENTERTAINER("mcda:entertainer", "entertainer_garb",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_CYAN)),
    TROUBADOUR("mcda:troubadour", "troubadour_armor",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_MAGENTA)),
    TELEPORTATION("mcda:teleportation", "teleportation_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_PURPLE)),
    UNSTABLE("mcda:mcda:unstable", "unstable_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_PURPLE)),
    /* * * |
    | BONE |
    | * * */
    PHANTOM("mcda:phantom", "phantom_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            () -> Ingredient.ofItems(ItemID.PHANTOM_BONES)),
    FROST_BITE("mcda:frostbite", "frost_bite_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            () -> Ingredient.ofItems(ItemID.FROST_CRYSTAL)),
    GRIM("mcda:grim", "grim_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            () -> Ingredient.ofItems(Items.BONE)),
    WITHER("mcda:wither", "wither_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            () -> Ingredient.ofItems(Items.BONE)),
    TURTLE("mcda:turtle", "turtle_armor",
            9,
            ITEM_ARMOR_EQUIP_TURTLE,
            () -> Ingredient.ofItems(Items.SCUTE)),
    NIMBLE_TURTLE("mcda:nimbleturtle", "nimble_turtle_armor",
            9,
            ITEM_ARMOR_EQUIP_TURTLE,
            () -> Ingredient.ofItems(Items.SCUTE)),

    /* * * * * * *|
    | LIGHT PLATE |
    |* * * * * * */
    SCALE_MAIL("mcda:scale", "scale_mail",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(ItemID.IRON_SCALE)),
    HIGHLAND("mcda:highland", "highland_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(ItemID.IRON_SCALE)),
    BEEHIVE("mcda:beehive", "beehive_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.HONEYCOMB)),
    BEENEST("mcda:beenest", "beenest_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.HONEYCOMB)),
    GHOSTLY("mcda:ghostly", "ghostly_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.LEATHER)),
    GHOST_KINDLER("mcda:ghostkindler", "ghost_kindler",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.BLAZE_POWDER)),
    GOLDEN_PIGLIN("mcda:goldenpiglin", "golden_piglin_armor",
            25,
            ITEM_ARMOR_EQUIP_GOLD,
            () -> Ingredient.ofItems(Items.GOLD_INGOT)),
    SWEET_TOOTH("mcda:sweettooth", "sweet_tooth",
            9,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(Items.CAKE)),
    VANGUARD("mcda:vanguard", "vanguard_armor",
                       9,
               ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(ItemID.IRON_SCALE)),

    /* * * * * * * |
    | MEDIUM PLATE |
    | * * * * * * */
    REINFORCED_MAIL("mcda:reinforced", "reinforced_mail",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    STALWART_MAIL("mcda:stalwart", "stalwart_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    GUARDS("mcda:guards", "guards_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    CURIOUS("mcda:curious", "curious_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    SNOW("mcda:snow", "snow_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.SNOW_BLOCK)),
    FROST("mcda:frost", "frost_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.FROST_CRYSTAL)),
    MERCENARY("mcda:mercenary", "mercenary_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    RENEGADE("mcda:renegade", "renegade_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    HUNGRY_HORROR("mcda:hungryhorror", "hungry_horror",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.GLUT_CHARM)),
    EMERALD("mcda:emeraldgear", "emerald_gear",
            10,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.EMERALD)),
    OPULENT("mcda:opulentarmor", "opulent_armor",
            10,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.EMERALD)),

    /* * * * * * *|
    | HEAVY PLATE |
    |* * * * * * */
    DARK("mcda:dark", "dark_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.NETHERITE_SCRAP)),
    TITAN("mcda:titans", "titans_shroud",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.NETHERITE_SCRAP)),
    ROYAL("mcda:royalguard", "royal_guard_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.NETHERITE_SCRAP)),
    PLATE("mcda:plate", "plate_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    FULL_METAL("mcda:fullmetal", "full_metal_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    MYSTERY("mcda:whitemystery", "white_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    BLUE_MYSTERY("mcda:bluemystery", "blue_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    GREEN_MYSTERY("mcda:greenmystery", "green_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    PURPLE_MYSTERY("mcda:purplemystery", "purple_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    RED_MYSTERY("mcda:redmystery", "red_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    CHAMPION("mcda:champions", "champions_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.NETHERITE_SCRAP)),
    HERO("mcda:heros", "heros_armor",
            25,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.NETHERITE_SCRAP)),
    GILDED("mcda:gildedglory", "gilded_glory",
            10,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.EMERALD)),
    SHULKER("mcda:shulker", "shulker_armor",
                   10,
            BLOCK_SHULKER_BOX_CLOSE,
            () -> Ingredient.ofItems(Items.SHULKER_SHELL)),
    STURDY_SHULKER("mcda:sturdyshulker", "sturdy_shulker_armor",
                   10,
            BLOCK_SHULKER_BOX_CLOSE,
            () -> Ingredient.ofItems(Items.SHULKER_SHELL)),
    CAULDRON("mcda:cauldron", "cauldron_armor",
            15,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    GOURDIAN("mcda:gourdian", "gourdian_armor",
            15,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_BLACK));

    private static final int[] baseDurability = new int[]{12, 14, 15, 10};
    private final String textureName;
    private final String setName;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final Lazy<Ingredient> repairIngredient;

    ArmorSets(String textureName, String setName, int enchantability,
              SoundEvent soundEvent, Supplier<Ingredient> repairIngredient) {
        this.textureName = textureName;
        this.setName = setName;
        this.enchantability = enchantability;
        this.equipSound = soundEvent;
        this.repairIngredient = new Lazy<>(repairIngredient);
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
}
