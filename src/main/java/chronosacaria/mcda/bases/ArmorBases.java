package chronosacaria.mcda.bases;

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
    ARCHER("archer",7, new int[]{1,2,3,1},15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    HUNTER("hunter",7, new int[]{2,4,6,2},15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    OCELOT("ocelot", 7, new int[]{2,4,6,2},15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    SHADOW_WALKER("shadowwalker", 7, new int[]{3,5,6,2},15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    SPELUNKER("spelunker", 7, new int[]{2,4,6,2},15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    CAVE_CRAWLER("cave", 7, new int[]{2,5,6,2},15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    WOLF("wolf", 7, new int[]{2,4,6,3},15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BLACK_WOLF("blackwolf", 7, new int[]{2,5,6,2},15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    FOX("fox", 7, new int[]{2,5,6,2},15,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),

    /* * * * |
    | FABRIC |
    | * * * */
    BATTLE("battle", 5, new int[]{1,2,3,1},25,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    SPLENDID("splendid", 5, new int[]{2,3,3,2},25,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    EVOCATION("evocation", 5, new int[]{1,3,3,1},25,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    EMBER("ember", 5, new int[]{1,3,3,1},25,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    VERDANT("verdant", 5, new int[]{1,3,3,1},25,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    THIEF("thief", 5, new int[]{2,4,5,2},15,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    SPIDER("spider", 5, new int[]{2,4,5,2},15,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    SOUL_ROBE("soulrobe", 5, new int[]{1,5,6,1},25,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    SOULDANCER("souldancer", 5, new int[]{2,5,6,2},25,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),

    /* * * |
    | BONE |
    | * * */
    PHANTOM("phantom", 25, new int[]{3,5,6,2},9,SoundEvents.ENTITY_SKELETON_AMBIENT,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    FROST_BITE("frostbite", 25, new int[]{3,6,7,3},9,SoundEvents.ENTITY_SKELETON_AMBIENT,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    GRIM("grim", 25, new int[]{3,5,6,2},9,SoundEvents.ENTITY_SKELETON_AMBIENT, 0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.BONE);}),
    WITHER("wither", 25, new int[]{3,6,7,3},9,SoundEvents.ENTITY_SKELETON_AMBIENT, 0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.BONE);}),

    /* * * * * * *|
    | LIGHT PLATE |
    |* * * * * * */
    SCALE_MAIL("scale", 14, new int[]{2,3,3,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    HIGHLAND("highland", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BEEHIVE("beehive", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BEENEST("beenest", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    GHOSTLY("ghostly", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    GHOST_KINDLER("ghostkindler", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),




    /* * * * * * * |
    | MEDIUM PLATE |
    | * * * * * * */
    REINFORCED_MAIL("reinforced", 30, new int[]{3,4,6,3},9,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    STALWART_MAIL("stalwart", 30, new int[]{3,5,7,3},9,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    GUARDS("guards", 30, new int[]{3,5,7,3},9,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    CURIOUS("curious", 30, new int[]{3,5,7,3},9,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    SNOW("snow", 30, new int[]{3,5,7,3},9,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    FROST("frost", 30, new int[]{3,5,7,3},9,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    MERCENARY("mercenary", 30, new int[]{3,5,7,3},9,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    RENEGADE("renegade", 30, new int[]{3,5,7,3},9,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    HUNGRY_HORROR("hungryhorror", 30, new int[]{3,5,7,3},9,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),

    /* * * * * * *|
    | HEAVY PLATE |
    |* * * * * * */
    DARK("dark", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    TITAN("titans", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    ROYAL("royalguard", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    PLATE("plate", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    FULL_METAL("fullmetal", 36, new int[]{4,6,8,4},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    MYSTERY("whitemystery", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BLUE_MYSTERY("bluemystery", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    GREEN_MYSTERY("greenmystery", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    PURPLE_MYSTERY("purplemystery", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    RED_MYSTERY("redmystery", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    CHAMPION("champions", 36, new int[]{3,6,8,3},15,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    HERO("heros", 40, new int[]{4,7,9,4},25,SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);});



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
