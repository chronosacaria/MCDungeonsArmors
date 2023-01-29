package chronosacaria.mcda.mixin;

import chronosacaria.mcda.effects.ArmorEffectID;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSetItem;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.entry.ItemEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(ItemEntry.class)
public class ItemEntryMixin {

    @ModifyArgs(method = "generateLoot", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    public void gen(Args args) {
        if (args.get(0) instanceof ItemStack stack) {
            if (stack.getItem() instanceof ArmorSetItem armorItem) {

                ArrayList<ArmorSets> sets = new ArrayList<>(List.of(ArmorSets.MYSTERY, ArmorSets.RED_MYSTERY,
                        ArmorSets.GREEN_MYSTERY, ArmorSets.BLUE_MYSTERY, ArmorSets.PURPLE_MYSTERY));
                ArrayList<List<ArmorEffectID>> effects = new ArrayList<>(List.of(ArmorEffects.ARMOR_EFFECT_ID_LIST,
                        ArmorEffects.RED_ARMOR_EFFECT_ID_LIST, ArmorEffects.GREEN_ARMOR_EFFECT_ID_LIST,
                        ArmorEffects.BLUE_ARMOR_EFFECT_ID_LIST, ArmorEffects.PURPLE_ARMOR_EFFECT_ID_LIST));

                if (sets.contains(armorItem.getSet())) {
                    stack.getOrCreateNbt().putInt("dominance", new Random().nextInt(99));
                    stack.getOrCreateNbt().putInt("mystery_effect",
                            new Random().nextInt(effects.get(sets.indexOf(armorItem.getSet())).size() - 1) + 1);
                    args.set(0, stack);
                }
            }
        }
    }
}
