package chronosacaria.mcda;

import chronosacaria.mcda.bases.ArmorBases;
import chronosacaria.mcda.enchants.EnchantsRegistry;
import chronosacaria.mcda.init.ArmorsInit;
import chronosacaria.mcda.init.ItemsInit;
import chronosacaria.mcda.init.LootInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Random;

public class Mcda implements ModInitializer {
    public static final String MOD_ID = "mcda";

    public static final Random random = new Random();

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final ItemGroup ARMORS_GROUP = FabricItemGroupBuilder.build(ID( "armor"),
            () -> new ItemStack(ArmorsInit.armorItems.get(ArmorBases.SPLENDID).get(EquipmentSlot.CHEST)));

    @Override
    public void onInitialize() {
        ArmorsInit.init();
        EnchantsRegistry.init();
        ItemsInit.init();
        LootInit.init();
    }
}
