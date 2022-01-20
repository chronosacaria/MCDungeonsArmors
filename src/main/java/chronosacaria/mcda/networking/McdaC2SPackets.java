package chronosacaria.mcda.networking;

import chronosacaria.mcda.client.McdaClient;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;

public class McdaC2SPackets {


    public static void registerC2SReceivers(){
        ClientPlayNetworking.registerGlobalReceiver(McdaPackets.FIRE_TRAIL_TOGGLE_PACKET_ID, (client, handler, buf,
                                                                                   responseSender) -> {
               PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
               packetByteBuf.writeBoolean(McdaClient.isEnabled());
               ClientPlayNetworking.send(McdaPackets.FIRE_TRAIL_TOGGLE_PACKET_ID, packetByteBuf);
        });
    }
}
