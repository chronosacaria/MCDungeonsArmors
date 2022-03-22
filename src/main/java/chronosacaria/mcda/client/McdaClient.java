package chronosacaria.mcda.client;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.entities.SummonedBeeRenderer;
import chronosacaria.mcda.networking.McdaC2SPackets;
import chronosacaria.mcda.networking.McdaPackets;
import chronosacaria.mcda.registry.SummonedEntityRegistry;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class McdaClient implements ClientModInitializer {

    /*private static final String KEYBIND_CATEGORY = "key.category.firetrail.toggle";
    private static final Identifier TOGGLE_KEYBIND = new Identifier(Mcda.MOD_ID, "toggle");
    private static KeyBinding keyToggle;

    private static boolean enabled;

    public static void handleInputEvent(){
        while (keyToggle.wasPressed()){
            PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
            packetByteBuf.writeBoolean(McdaClient.toggleIsEnabled());
            ClientPlayNetworking.send(McdaPackets.FIRE_TRAIL_TOGGLE_PACKET_ID, packetByteBuf);
        }
    }

    public static boolean toggleIsEnabled(){
        return McdaClient.enabled = !McdaClient.enabled;
    }

    public static boolean isEnabled() {
        return enabled;
    }
*/
    @Override
    public void onInitializeClient() {

        /*KeyBindingRegistryImpl.addCategory(KEYBIND_CATEGORY);
        KeyBindingHelper.registerKeyBinding(keyToggle = new KeyBinding(
                TOGGLE_KEYBIND.toString(), GLFW.GLFW_KEY_SEMICOLON, KEYBIND_CATEGORY));

        //McdaC2SPackets.registerC2SReceivers();*/
        EntityRendererRegistry.register(SummonedEntityRegistry.SUMMONED_BEE_ENTITY, SummonedBeeRenderer::new);
    }
}
