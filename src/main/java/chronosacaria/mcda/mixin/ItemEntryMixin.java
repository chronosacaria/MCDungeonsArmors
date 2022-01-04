package chronosacaria.mcda.mixin;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.items.ArmorSets;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.function.Consumer;

@Mixin(ItemEntry.class)
public class ItemEntryMixin {
    @Shadow @Final
    Item item;
    private final Random random = new Random();

    @Inject(method = "generateLoot", at = @At("HEAD"), cancellable = true)
    public void onMysteryArmorGeneration(Consumer<ItemStack> lootConsumer, LootContext context, CallbackInfo ci){
        ItemStack itemStack = new ItemStack(item);
        if (CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.MYSTERY)
                || CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.RED_MYSTERY)
                || CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.GREEN_MYSTERY)
                || CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.BLUE_MYSTERY)
                || CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.PURPLE_MYSTERY)){
            itemStack.getOrCreateNbt().putInt("dominance", random.nextInt(99));

            if (CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.MYSTERY)) {
                itemStack.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }

            if (CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.RED_MYSTERY)) {
                itemStack.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.RED_ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }

            if (CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.GREEN_MYSTERY)) {
                itemStack.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.GREEN_ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }

            if (CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.BLUE_MYSTERY)) {
                itemStack.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.BLUE_ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }

            if (CleanlinessHelper.isMysteryArmor(itemStack.getItem(), ArmorSets.PURPLE_MYSTERY)) {
                itemStack.getOrCreateNbt().putInt("mystery_effect",
                        random.nextInt(ArmorEffects.PURPLE_ARMOR_EFFECT_ID_LIST.size() - 1) + 1);
            }

        }
        lootConsumer.accept(itemStack);
        ci.cancel();
    }
}
