package chronosacaria.mcda.networking;

import chronosacaria.mcda.enchants.EnchantID;
import chronosacaria.mcda.registry.EnchantsRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class McdaS2CPackets {
    public static void registerS2CReceivers(){
        ServerPlayNetworking.registerGlobalReceiver(McdaPackets.FIRE_TRAIL_TOGGLE_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            boolean isFireTrailEnabled = buf.readBoolean();

            server.execute(() -> {
                ItemStack feetStack = player.getEquippedStack(EquipmentSlot.FEET);

                if (EnchantmentHelper.getLevel(EnchantsRegistry.enchants.get(EnchantID.FIRE_TRAIL), feetStack) > 0) {
                    if (feetStack.getNbt().get("is_fire_trail_enabled") == null) {
                        feetStack.getOrCreateNbt().putBoolean("is_fire_trail_enabled", isFireTrailEnabled);
                    } else {
                        feetStack.getOrCreateNbt().putBoolean("is_fire_trail_enabled",
                                !feetStack.getNbt().getBoolean("is_fire_trail_enabled"));
                    }
                }
            });
        });
    }
}
