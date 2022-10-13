package chronosacaria.mcda;

import chronosacaria.mcda.config.McdaConfig;
import chronosacaria.mcda.data.ConfigItemEnabledCondition;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.items.ItemID;
import chronosacaria.mcda.networking.McdaC2SPackets;
import chronosacaria.mcda.registry.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;


public class Mcda implements ModInitializer {
    public static final String MOD_ID = "mcda";

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static McdaConfig CONFIG;

    public static final ItemGroup ARMORS_GROUP = FabricItemGroupBuilder.build(ID( "armor"),
            () -> {
                if (CONFIG.mcdaEnableArmorsConfig.ARMORS_SETS_ENABLED.get(ArmorSets.SPLENDID)) {
                            new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.SPLENDID).get(EquipmentSlot.CHEST));
                }
                return new ItemStack(ItemsRegistry.items.get(ItemID.UPGRADE_CORE_ARCHER));
            });

    @Override
    public void onInitialize() {
        McdaConfig.init();
        CONFIG = AutoConfig.getConfigHolder(McdaConfig.class).getConfig();
        ConfigItemEnabledCondition.init();
        ArmorsRegistry.init();
        EnchantsRegistry.init();
        ItemsRegistry.init();
        LootRegistry.init();
        SoundsRegistry.init();
        StatusEffectsRegistry.init();
        TradesRegistry.registerVillagerOffers();
        TradesRegistry.registerWanderingTrades();
        SummonedEntityRegistry.register();
        McdaC2SPackets.registerC2SReceivers();
        CompatRegistry.init();
    }
}