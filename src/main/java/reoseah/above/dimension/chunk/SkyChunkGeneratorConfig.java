package reoseah.above.dimension.chunk;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import reoseah.above.Above;

public class SkyChunkGeneratorConfig extends ChunkGeneratorConfig {
	private static final BlockState AERRACK = Above.AERRACK.getDefaultState();

	@Override
	public BlockState getDefaultBlock() {
		return AERRACK;
	}
}