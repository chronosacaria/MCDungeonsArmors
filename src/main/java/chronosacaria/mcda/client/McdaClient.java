package chronosacaria.mcda.client;

import chronosacaria.mcda.entities.SummonedBeeRenderer;
import chronosacaria.mcda.registry.SummonedEntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

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
