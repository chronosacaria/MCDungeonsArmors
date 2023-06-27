package chronosacaria.mcda.registries;

import chronosacaria.mcda.Mcda;
import chronosacaria.mcda.blocks.FadingObsidianBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class BlocksRegistry {
    public static final Block FADING_OBSIDIAN = registerBlock("fading_obsidian",
            new FadingObsidianBlock(FabricBlockSettings.copyOf(Blocks.CRYING_OBSIDIAN).ticksRandomly().sounds(BlockSoundGroup.GLASS)));

    protected static Block registerBlock(String id, Block block) {
        return Registry.register(Registries.BLOCK, Mcda.ID(id), block);
    }

    public static void register() {
        Mcda.LOGGER.info("Registering Blocks for " + Mcda.MOD_ID);
    }
}
