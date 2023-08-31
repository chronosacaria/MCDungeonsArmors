package chronosacaria.mcda.config;

import net.minecraft.entity.EquipmentSlot;

import java.util.EnumMap;

import static net.minecraft.entity.EquipmentSlot.*;

public class ArmorStats {
    public EnumMap<EquipmentSlot, Integer> protection = new EnumMap<>(EquipmentSlot.class);
    public float toughness;
    public float knockbackRes;

    public double attackDamageBoost;
    public double attackSpeedBoost;
    public double movementSpeedBoost;

    public int durabilityMultiplier;

    public String[] repairIngredients;

    public ArmorStats setProtection(int head, int chest, int legs, int feet) {
        this.protection.put(HEAD, head);
        this.protection.put(CHEST, chest);
        this.protection.put(LEGS, legs);
        this.protection.put(FEET, feet);
        return this;
    }

    // chainable setters:
    public ArmorStats setToughness(float toughness) {
        this.toughness = toughness;
        return this;
    }

    public ArmorStats setKnockbackRes(float knockbackRes) {
        this.knockbackRes = knockbackRes;
        return this;
    }

    public ArmorStats setAttackDamageBoost(double attackDamage) {
        this.attackDamageBoost = attackDamage;
        return this;
    }

    public ArmorStats setAttackSpeedBoost(double attackSpeed) {
        this.attackSpeedBoost = attackSpeed;
        return this;
    }

    public ArmorStats setMovementSpeedBoost(double movement) {
        this.movementSpeedBoost = movement;
        return this;
    }

    public ArmorStats setDurabilityMultiplier(int durabilityMultiplier){
        this.durabilityMultiplier = durabilityMultiplier;
        return this;
    }

    public ArmorStats setRepairIngredients(String[] repairIngredients) {
        this.repairIngredients = repairIngredients;
        return this;
    }
}
