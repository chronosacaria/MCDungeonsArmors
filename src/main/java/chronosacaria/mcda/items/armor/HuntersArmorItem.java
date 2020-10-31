package chronosacaria.mcda.items.armor;

import chronosacaria.mcda.Mcda;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
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

public class HuntersArmorItem extends ArmorItem {

    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{
            UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    private final boolean unique;
    private final int protection;
    private final float toughness;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public HuntersArmorItem(ArmorMaterial armorMaterial, EquipmentSlot slot, Settings settings, boolean unique,
                            String id){
        super(armorMaterial, slot, settings);
        this.unique = unique;

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
        if(this.unique){ /*15% speed boost with full set*/
            builder.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "Armor speed boost",
                    0.05D, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        }
        this.attributeModifiers = builder.build();
        Registry.register(Registry.ITEM, new Identifier(Mcda.MOD_ID, id), this);
    }

    /*@Override
    public String getArmourTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type){
        if (this.unique) return Mcda.MOD_ID + ":textures/models/armor/archers_armor.png";
        return Mcda.MOD_ID + ":textures/models/armor/hunters_armor.png";
    }*/

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot){
        return slot == this.slot ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public Rarity getRarity(ItemStack itemStack){
        if(this.unique) return Rarity.RARE;
        return Rarity.UNCOMMON;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        super.appendTooltip(itemStack, world, tooltip, tooltipContext);

        if(this.unique) {
            tooltip.add(new TranslatableText("item.mcda.archers_armor.tooltip_1"));
            tooltip.add(new TranslatableText("item.mcda.archers_armor.tooltip_2"));
            tooltip.add(new TranslatableText("item.mcda.archers_armor.tooltip_3"));
        }
        else {
            tooltip.add(new TranslatableText("item.mcda.hunters_armor.tooltip_1"));
            tooltip.add(new TranslatableText("item.mcda.hunters_armor.tooltip_2"));
        }
    }

    /*@Override
    public int getArrowsPerBundle(){
        return 4;
    }

    @Override
    public double getRangedDamage(){
        return 15;
    }*/
}
