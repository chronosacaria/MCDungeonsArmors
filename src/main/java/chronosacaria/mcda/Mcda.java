package chronosacaria.mcda;

import chronosacaria.mcda.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Mcda implements ModInitializer {

    public static final String MOD_ID = "mcda";

    public static final ItemGroup ARMORS = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "armor"),
            () -> new ItemStack(BattleRobeArmors.SPLENDID_ROBE_CHESTPLATE));
    @Override
    public void onInitialize() {

        HuntersArmors.init();
        BattleRobeArmors.init();
        ChampionsArmors.init();
        DarkArmors.init();
        EvocationRobeArmors.init();
    }
}
