package chronosacaria.mcda;

import chronosacaria.mcda.api.CleanlinessHelper;
import chronosacaria.mcda.api.McdaEnchantmentHelper;
import chronosacaria.mcda.config.McdaConfig;
import chronosacaria.mcda.enchants.EnchantID;
import chronosacaria.mcda.items.ArmorSets;
import chronosacaria.mcda.networking.McdaC2SPackets;
import chronosacaria.mcda.registry.*;
import com.blamejared.clumps.api.events.ClumpsEvents;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static chronosacaria.mcda.effects.ArmorEffectID.SOULDANCER_EXPERIENCE;
import static chronosacaria.mcda.enchants.EnchantID.BAG_OF_SOULS;


public class Mcda implements ModInitializer {
    public static final String MOD_ID = "mcda";

    public static Identifier ID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final ItemGroup ARMORS_GROUP = FabricItemGroupBuilder.build(ID( "armor"),
            () -> new ItemStack(ArmorsRegistry.armorItems.get(ArmorSets.SPLENDID).get(EquipmentSlot.CHEST)));

    public static McdaConfig CONFIG;

    @Override
    public void onInitialize() {
        McdaConfig.init();
        CONFIG = AutoConfig.getConfigHolder(McdaConfig.class).getConfig();

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