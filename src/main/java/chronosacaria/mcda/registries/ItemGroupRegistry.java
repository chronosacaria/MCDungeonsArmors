package chronosacaria.mcda.registries;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.items.ArmorSets;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ItemGroupRegistry {
    public static final RegistryKey<ItemGroup> ARMOR = RegistryKey.of(RegistryKeys.ITEM_GROUP, Mcda.ID("armor"));

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, ARMOR, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.mcda.armor"))
                .icon(() -> {
                    if (Mcda.CONFIG.mcdaEnableArmorsConfig.ARMORS_SETS_ENABLED.get(ArmorSets.SPLENDID)) {
                        return new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.SPLENDID).get(ArmorItem.Type.CHESTPLATE));
                    }
                    return new ItemStack(ItemsRegistry.UPGRADE_CORE_ARCHER);
                })
                .build());
    }
}
