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

    ARCHER("archer",14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    HUNTER("hunter",14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BATTLE("battle", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    SPLENDID("splendid", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    EVOCATION("evocation", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    EMBER("ember", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    VERDANT("verdant", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    PELT("pelt", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.LEATHER);}),
    BONE("bone", 14, new int[]{2,5,6,2},9,SoundEvents.ENTITY_SKELETON_AMBIENT, 0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.BONE);}),
    LIGHT_PLATE("light_plate", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    MEDIUM_PLATE("medium_plate", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    CHAMPION("champions", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    HERO("heros", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    HEAVY_PLATE("heavy_plate", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    DARK("dark", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f,0.0f,
            () -> {return Ingredient.ofItems(Items.IRON_INGOT);}),
    TITAN("titans", 14, new int[]{2,5,6,2},9,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f,0.0f,
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
