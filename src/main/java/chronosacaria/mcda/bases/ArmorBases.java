package chronosacaria.mcda.bases;

import chronosacaria.mcda.config.McdaStatsConfig;
import chronosacaria.mcda.init.ItemsInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ArmorBases implements ArmorMaterial {


    //Armor order: boots, leggings, chestplate, helmet

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
    ARCHER("archer",
            7,
            McdaStatsConfig.config.getArcherArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    HUNTER("hunter",
            7,
            McdaStatsConfig.config.getHunterArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    OCELOT("ocelot",
            7,
            McdaStatsConfig.config.getOcelotArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    SHADOW_WALKER("shadowwalker",
            7,
            McdaStatsConfig.config.getShadowWalkerArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    SPELUNKER("spelunker",
            7,
            McdaStatsConfig.config.getSpelunkerArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    CAVE_CRAWLER("cave",
            7,
            McdaStatsConfig.config.getCaveCrawlerArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    WOLF("wolf",
            7,
            McdaStatsConfig.config.getWolfArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BLACK_WOLF("blackwolf",
            7,
            McdaStatsConfig.config.getBlackWolfArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    FOX("fox",
            7,
            McdaStatsConfig.config.getFoxArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    ARCTIC_FOX("arctic",
            7,
            McdaStatsConfig.config.getFoxArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),

    CLIMBING_GEAR("climbing",
            7,
            McdaStatsConfig.config.getClimbingGearArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    RUGGED_CLIMBING_GEAR("rugged",
            7,
            McdaStatsConfig.config.getRuggedClimbingGearArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    /*
    GOAT("goat",
            7,
            McdaStatsConfig.config.getGoatArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0f,
            2.0f,
            () -> {return Ingredient.ofItems(ItemsInit.GOAT_PELT);}),
    */

    /* * * * |
    | FABRIC |
    | * * * */
    BATTLE("battle",
            5,
            McdaStatsConfig.config.getBattleRobe(),
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(ItemsInit.BOLT_OF_FABRIC_BLACK);}),
    SPLENDID("splendid",
            5,
            McdaStatsConfig.config.getSpendidRobe(),
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(ItemsInit.BOLT_OF_FABRIC_PURPLE);}),
    EVOCATION("evocation",
            5,
            McdaStatsConfig.config.getEvocationRobe(),
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(ItemsInit.BOLT_OF_FABRIC_BLUE);}),
    EMBER("ember",
            5,
            McdaStatsConfig.config.getEmberRobe(),
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(ItemsInit.BOLT_OF_FABRIC_RED);}),
    VERDANT("verdant",
            5,
            McdaStatsConfig.config.getVerdantRobe(),
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(ItemsInit.BOLT_OF_FABRIC_GREEN);}),
    THIEF("thief",
            5,
            McdaStatsConfig.config.getThiefArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(ItemsInit.BOLT_OF_FABRIC_BLACK);}),
    SPIDER("spider",
            5,
            McdaStatsConfig.config.getSpiderArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(ItemsInit.BOLT_OF_FABRIC_BLACK);}),
    SOUL_ROBE("soulrobe",
            5,
            McdaStatsConfig.config.getSoulRobe(),
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    SOULDANCER("souldancer",
            5,
            McdaStatsConfig.config.getSoulDancerRobe(),
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),

    /* * * |
    | BONE |
    | * * */
    PHANTOM("phantom",
            25,
            McdaStatsConfig.config.getPhantomArmor(),
            9,
            SoundEvents.ENTITY_SKELETON_AMBIENT,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(ItemsInit.PHANTOM_BONES);}),
    FROST_BITE("frostbite",
            25,
            McdaStatsConfig.config.getFrostBiteArmor(),
            9,
            SoundEvents.ENTITY_SKELETON_AMBIENT,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(ItemsInit.FROST_CRYSTAL);}),
    GRIM("grim",
            25,
            McdaStatsConfig.config.getGrimArmor(),
            9,
            SoundEvents.ENTITY_SKELETON_AMBIENT,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.BONE);}),
    WITHER("wither",
            25,
            McdaStatsConfig.config.getWitherArmor(),
            9,
            SoundEvents.ENTITY_SKELETON_AMBIENT,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.BONE);}),

    /* * * * * * *|
    | LIGHT PLATE |
    |* * * * * * */
    SCALE_MAIL("scale",
            14,
            McdaStatsConfig.config.getScaleMail(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    HIGHLAND("highland",
            14,
            McdaStatsConfig.config.getHighlandArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BEEHIVE("beehive",
            14,
            McdaStatsConfig.config.getBeehiveArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BEENEST("beenest",
            14,
            McdaStatsConfig.config.getBeenestArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    GHOSTLY("ghostly",
            14,
            McdaStatsConfig.config.getGhostlyArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    GHOST_KINDLER("ghostkindler",
            14,
            McdaStatsConfig.config.getGhostKindlerArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),




    /* * * * * * * |
    | MEDIUM PLATE |
    | * * * * * * */
    REINFORCED_MAIL("reinforced",
            30,
            McdaStatsConfig.config.getReinforcedMail(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            1.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    STALWART_MAIL("stalwart",
            30,
            McdaStatsConfig.config.getStalwartMail(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    GUARDS("guards",
            30,
            McdaStatsConfig.config.getGuardsArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    CURIOUS("curious",
            30,
            McdaStatsConfig.config.getCuriousArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    SNOW("snow",
            30,
            McdaStatsConfig.config.getSnowArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    FROST("frost",
            30,
            McdaStatsConfig.config.getFrostArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    MERCENARY("mercenary",
            30,
            McdaStatsConfig.config.getMercenaryArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            1.5f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    RENEGADE("renegade",
            30,
            McdaStatsConfig.config.getRenegadeArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    HUNGRY_HORROR("hungryhorror",
            30,
            McdaStatsConfig.config.getHungryHorrorArmor(),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    EMERALD("emeraldgear",
            30,
            McdaStatsConfig.config.getEmeraldGearArmor(),
            10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.EMERALD);}),
    OPULENT("opulentarmor",
            30,
            McdaStatsConfig.config.getOpulentArmor(),
            10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.EMERALD);}),

    /* * * * * * *|
    | HEAVY PLATE |
    |* * * * * * */
    DARK("dark",
            36,
            McdaStatsConfig.config.getDarkArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    TITAN("titans",
            36,
            McdaStatsConfig.config.getTitanSroud(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    ROYAL("royalguard",
            36,
            McdaStatsConfig.config.getRoyalGuard(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    PLATE("plate",
            36,
            McdaStatsConfig.config.getPlateArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    FULL_METAL("fullmetal",
            36,
            McdaStatsConfig.config.getFullMetalArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    MYSTERY("whitemystery",
            36,
            McdaStatsConfig.config.getMysteryArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BLUE_MYSTERY("bluemystery",
            36,
            McdaStatsConfig.config.getMysteryArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    GREEN_MYSTERY("greenmystery",
            36,
            McdaStatsConfig.config.getMysteryArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    PURPLE_MYSTERY("purplemystery",
            36,
            McdaStatsConfig.config.getMysteryArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    RED_MYSTERY("redmystery",
            36,
            McdaStatsConfig.config.getMysteryArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            0.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    CHAMPION("champions",
            36,
            McdaStatsConfig.config.getChampionArmor(),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    HERO("heros",
            40,
            McdaStatsConfig.config.getHerosArmor(),
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    GILDED("gildedglory",
            30,
            McdaStatsConfig.config.getGildedGloryArmor(),
            10,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0.0f,
            () -> {return Ingredient.ofItems(Items.EMERALD);}),;



    private static final int[] baseDurability = new int[]{12,14,15,10};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] armorValues;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredient;

    ArmorBases(String name, int durabilityMultiplier, int[] armorValueArray, int enchantability,
               SoundEvent soundEvent, float toughness, float knockbackResistance,
               Supplier<Ingredient> repairIngredient){
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.armorValues = armorValueArray;
        this.enchantability = enchantability;
        this.equipSound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    public int getDurability(EquipmentSlot equipmentSlot_1){
        return baseDurability[equipmentSlot_1.getEntitySlotId()] * this.durabilityMultiplier;
    }

    public int getProtectionAmount(EquipmentSlot equipmentSlot_1) {
        return this.armorValues[equipmentSlot_1.getEntitySlotId()];
    }

    public int getEnchantability(){
        return this.enchantability;
    }

    public SoundEvent getEquipSound(){
        return this.equipSound;
    }

    public Ingredient getRepairIngredient(){
        return this.repairIngredient.get();
    }

    @Environment(EnvType.CLIENT)
    public String getName(){
        return this.name;
    }

    public float getToughness(){
        return this.toughness;
    }

    public float getKnockbackResistance(){
        return this.knockbackResistance;
    }
}
