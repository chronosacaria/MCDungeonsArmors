package chronosacaria.mcda;

import chronosacaria.mcda.enchants.EnchantsRegistry;
import chronosacaria.mcda.init.LootInit;
import chronosacaria.mcda.init.ArmorsInit;
import chronosacaria.mcda.init.ItemsInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Mcda implements ModInitializer {

    public static final String MOD_ID = "mcda";

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final ItemGroup ARMORS = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "armor"),
            () -> new ItemStack(ArmorsInit.SPLENDID_ROBE_CHESTPLATE));


    @Override
    public void onInitialize() {


        ArmorsInit.init();
        EnchantsRegistry.init();
        ItemsInit.doRegister();
        LootInit.init();
    }

}
