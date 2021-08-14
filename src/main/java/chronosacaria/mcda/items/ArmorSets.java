package chronosacaria.mcda.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import net.minecraft.util.Rarity;

import java.util.EnumMap;
import java.util.function.Supplier;

import static chronosacaria.mcda.config.McdaConfig.config;
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
     * Damage Reduction Amounts (boots, leggings, chestplate, helmet:
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
            () -> Ingredient.ofItems(Items.LEATHER)),
    HUNTER("hunter", "hunters",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    OCELOT("ocelot", "ocelot_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    SHADOW_WALKER("shadowwalker", "shadow_walker_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    SPELUNKER("spelunker", "spelunker_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    CAVE_CRAWLER("cave", "cave_crawler",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    WOLF("wolf", "wolf_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    BLACK_WOLF("blackwolf", "black_wolf_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    FOX("fox", "fox_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    ARCTIC_FOX("arctic", "arctic_fox_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    CLIMBING_GEAR("climbing", "climbing_gear",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    RUGGED_CLIMBING_GEAR("rugged", "rugged_climbing_gear",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    SQUID("squid", "squid_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),
    GLOW_SQUID("glowsquid", "glow_squid_armor",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(Items.LEATHER)),

    GOAT("goat", "goat_gear",
            15,
            ITEM_ARMOR_EQUIP_LEATHER,
            () -> Ingredient.ofItems(ItemID.GOAT_PELT)),


    /* * * * |
    | FABRIC |
    | * * * */
    BATTLE("battle", "battle_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_BLACK)),
    SPLENDID("splendid", "splendid_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_PURPLE)),
    EVOCATION("evocation", "evocation_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_BLUE)),
    EMBER("ember", "ember_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_RED)),
    VERDANT("verdant", "verdant_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_GREEN)),
    THIEF("thief", "thief_armor",
            15,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_BLACK)),
    SPIDER("spider", "spider_armor",
            15,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_BLACK)),
    SOUL_ROBE("soulrobe", "soul_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    SOULDANCER("souldancer", "souldancer_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    SPROUT("sprout", "sprout_armor",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_CYAN)),
    LIVING_VINES("livingvines", "living_vines_armor",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_RED)),
    PIGLIN("piglin", "piglin_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.LEATHER)),
    ENTERTAINER("entertainer", "entertainer_garb",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_CYAN)),
    TROUBADOUR("troubadour", "troubadour_armor",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_MAGENTA)),
    TELEPORTATION("teleportation", "teleportation_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_PURPLE)),
    UNSTABLE("unstable", "unstable_robe",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> Ingredient.ofItems(ItemID.FABRIC_BOLT_PURPLE)),
    /* * * |
    | BONE |
    | * * */
    PHANTOM("phantom", "phantom_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            () -> Ingredient.ofItems(ItemID.PHANTOM_BONES)),
    FROST_BITE("frostbite", "frost_bite_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            () -> Ingredient.ofItems(ItemID.FROST_CRYSTAL)),
    GRIM("grim", "grim_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            () -> Ingredient.ofItems(Items.BONE)),
    WITHER("wither", "wither_armor",
            9,
            ENTITY_SKELETON_AMBIENT,
            () -> Ingredient.ofItems(Items.BONE)),
    TURTLE("turtle", "turtle_armor",
            9,
            ITEM_ARMOR_EQUIP_TURTLE,
            () -> Ingredient.ofItems(Items.SCUTE)),
    NIMBLE_TURTLE("nimbleturtle", "nimble_turtle_armor",
            9,
            ITEM_ARMOR_EQUIP_TURTLE,
            () -> Ingredient.ofItems(Items.SCUTE)),

    /* * * * * * *|
    | LIGHT PLATE |
    |* * * * * * */
    SCALE_MAIL("scale", "scale_mail",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(ItemID.IRON_SCALE)),
    HIGHLAND("highland", "highland_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(ItemID.IRON_SCALE)),
    BEEHIVE("beehive", "beehive_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.HONEYCOMB)),
    BEENEST("beenest", "beenest_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.HONEYCOMB)),
    GHOSTLY("ghostly", "ghostly_armor",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.LEATHER)),
    GHOST_KINDLER("ghostkindler", "ghost_kindler",
            9,
            ITEM_ARMOR_EQUIP_IRON,
            () -> Ingredient.ofItems(Items.BLAZE_POWDER)),
    GOLDEN_PIGLIN("goldenpiglin", "golden_piglin_armor",
            25,
            ITEM_ARMOR_EQUIP_GOLD,
            () -> Ingredient.ofItems(Items.GOLD_INGOT)),

    /* * * * * * * |
    | MEDIUM PLATE |
    | * * * * * * */
    REINFORCED_MAIL("reinforced", "reinforced_mail",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    STALWART_MAIL("stalwart", "stalwart_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    GUARDS("guards", "guards_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    CURIOUS("curious", "curious_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    SNOW("snow", "snow_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.SNOW_BLOCK)),
    FROST("frost", "frost_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.FROST_CRYSTAL)),
    MERCENARY("mercenary", "mercenary_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    RENEGADE("renegade", "renegade_armor",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    HUNGRY_HORROR("hungryhorror", "hungry_horror",
            9,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(ItemID.GLUT_CHARM)),
    EMERALD("emeraldgear", "emerald_gear",
            10,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.EMERALD)),
    OPULENT("opulentarmor", "opulent_armor",
            10,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.EMERALD)),

    /* * * * * * *|
    | HEAVY PLATE |
    |* * * * * * */
    DARK("dark", "dark_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    TITAN("titans", "titans_shroud",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    ROYAL("royalguard", "royal_guard_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    PLATE("plate", "plate_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    FULL_METAL("fullmetal", "full_metal_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    MYSTERY("whitemystery", "white_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    BLUE_MYSTERY("bluemystery", "blue_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    GREEN_MYSTERY("greenmystery", "green_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    PURPLE_MYSTERY("purplemystery", "purple_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    RED_MYSTERY("redmystery", "red_mystery_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    CHAMPION("champions", "champions_armor",
            15,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    HERO("heros", "heros_armor",
            25,
            ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(ItemID.IRON_PLATE)),
    GILDED("gildedglory", "gilded_glory",
            10,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(Items.EMERALD)),
    SHULKER("shulker", "shulker_armor",
                   10,
            BLOCK_SHULKER_BOX_CLOSE,
            () -> Ingredient.ofItems(Items.SHULKER_SHELL)),
    STURDY_SHULKER("sturdyshulker", "sturdy_shulker_armor",
                   10,
            BLOCK_SHULKER_BOX_CLOSE,
            () -> Ingredient.ofItems(Items.SHULKER_SHELL));

    private static final int[] baseDurability = new int[]{12, 14, 15, 10};
    private final String textureName;
    private final String setName;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final Lazy<Ingredient> repairIngredient;

    EnumMap<EquipmentSlot, Integer> armorMap;

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
        switch (this) {
            case SPLENDID:
            case BEEHIVE:
            case HERO:
            case TITAN:
            case ROYAL:
            case EMBER:
            case VERDANT:
            case GHOST_KINDLER:
            case WITHER:
            case CURIOUS:
            case ARCHER:
            case RENEGADE:
            case HUNGRY_HORROR:
            case MYSTERY:
            case BLUE_MYSTERY:
            case GREEN_MYSTERY:
            case PURPLE_MYSTERY:
            case RED_MYSTERY:
            case FROST_BITE:
            case FULL_METAL:
            case STALWART_MAIL:
            case HIGHLAND:
            case FROST:
            case SOULDANCER:
            case CAVE_CRAWLER:
            case SPIDER:
            case BLACK_WOLF:
            case ARCTIC_FOX:
            case OPULENT:
            case GILDED:
            case RUGGED_CLIMBING_GEAR:
            case GOLDEN_PIGLIN:
            case GOAT:
            case TROUBADOUR:
            case UNSTABLE:
            case STURDY_SHULKER:
                return Rarity.RARE;
            default:
                return Rarity.UNCOMMON;
        }
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return baseDurability[slot.getEntitySlotId()] * config.armorStats.get(this).durabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return config.armorStats.get(this).protection.get(slot);
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

    @Environment(EnvType.CLIENT)
    @Override
    public String getName() {
        return this.textureName;
    }

    @Override
    public float getToughness() {
        return  config.armorStats.get(this).toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return  config.armorStats.get(this).knockbackRes;
    }
}
