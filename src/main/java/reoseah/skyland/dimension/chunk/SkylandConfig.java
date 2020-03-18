package reoseah.skyland.dimension.chunk;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import reoseah.skyland.blocks.SkyBlocks;

public class SkylandConfig extends ChunkGeneratorConfig {
	private static final BlockState AERRACK = SkyBlocks.AERRACK.getDefaultState();

	@Override
	public BlockState getDefaultBlock() {
		return AERRACK;
	}
}