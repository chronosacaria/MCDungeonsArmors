package chronosacaria.mcda.items.armor;

import chronosacaria.mcda.Mcda;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class MysteryArmorItem extends ArmorItem {

    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{
            UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    private final boolean white;
    private final boolean blue;
    private final boolean green;
    private final boolean purple;
    private final boolean red;
    private final int protection;
    private final float toughness;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public MysteryArmorItem(ArmorMaterial armorMaterial, EquipmentSlot slot, Settings settings,
                            boolean white, boolean blue, boolean green, boolean purple, boolean red,
                            String id){
        super(armorMaterial, slot, settings);
        this.white = white;
        this.blue = blue;
        this.green = green;
        this.purple = purple;
        this.red = red;

        this.protection = armorMaterial.getProtectionAmount(slot);
        this.toughness = armorMaterial.getToughness();

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[slot.getEntitySlotId()];
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, "Armor modifier",
                (double)this.protection, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, "Armor toughness",
                (double)this.toughness, EntityAttributeModifier.Operation.ADDITION));
        if(this.knockbackResistance > 0) {
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(uuid, "Armor knockback resistance",
                    (double) this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION));
        }
        /*if(this.white){
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid,
                    "Armor attack damage boost",
                    0.0375D, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        }
        if(this.blue){
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid,
                    "Armor attack damage boost",
                    0.0375D, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        }
        if(this.green){
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid,
                    "Armor attack damage boost",
                    0.0375D, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        }
        if(this.purple){
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid,
                    "Armor attack damage boost",
                    0.0375D, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        }
        if(this.red){
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid,
                    "Armor attack damage boost",
                    0.0375D, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        }*/

        this.attributeModifiers = builder.build();
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, id), this);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot){
        return slot == this.slot ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public Rarity getRarity(ItemStack itemStack){
        if(this.white) return Rarity.RARE;
        if(this.blue) return Rarity.RARE;
        if(this.green) return Rarity.RARE;
        if(this.purple) return Rarity.RARE;
        if(this.red) return Rarity.RARE;
        return Rarity.UNCOMMON;
    }


    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        super.appendTooltip(itemStack, world, tooltip, tooltipContext);

        if(this.white){
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_1"));
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_2"));
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_3"));
        }
        if(this.blue) {
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_1"));
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_2"));
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_3"));
        }
        if(this.green) {
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_1"));
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_2"));
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_3"));
        }
        if(this.purple) {
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_1"));
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_2"));
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_3"));
        }
        if(this.red) {
            tooltip.add(new TranslatableText("item.mcda.beenest_armor.tooltip_1"));
            tooltip.add(new TranslatableText("item.mcda.beenest_armor.tooltip_2"));
            tooltip.add(new TranslatableText("item.mcda.mystery_armor.tooltip_3"));
        }




    }

    /*@Override
    public int getMagicDamage(){
        if(this.unique) return 25;
        else return 0;
    }

    @Override
    public double getArtifactCooldown(){
        return 12.5D;
    }*/
}
