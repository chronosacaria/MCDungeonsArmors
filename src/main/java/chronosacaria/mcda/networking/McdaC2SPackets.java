package chronosacaria.mcda.networking;

import chronosacaria.mcda.enchants.EnchantID;
import chronosacaria.mcda.registry.EnchantsRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class McdaC2SPackets {
    public static void registerC2SReceivers(){
        ServerPlayNetworking.registerGlobalReceiver(McdaPackets.FIRE_TRAIL_TOGGLE_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            boolean isFireTrailEnabled = buf.readBoolean();

            server.execute(() -> {
                ItemStack feetStack = player.getEquippedStack(EquipmentSlot.FEET);

                if (EnchantmentHelper.getLevel(EnchantsRegistry.enchants.get(EnchantID.FIRE_TRAIL), feetStack) > 0) {
                    if (feetStack.hasNbt() && feetStack.getNbt().contains("is_fire_trail_enabled")) {
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
