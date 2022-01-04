package chronosacaria.mcda.client;

import chronosacaria.mcda.entities.SummonedBeeRenderer;
import chronosacaria.mcda.registry.SummonedEntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class McdaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SummonedEntityRegistry.register();
        EntityRendererRegistry.register(SummonedEntityRegistry.SUMMONED_BEE_ENTITY, SummonedBeeRenderer::new);
    }
}
