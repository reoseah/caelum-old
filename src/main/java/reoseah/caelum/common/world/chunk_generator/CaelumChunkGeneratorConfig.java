package reoseah.caelum.common.world.chunk_generator;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import reoseah.caelum.common.CaelumBlocks;

public class CaelumChunkGeneratorConfig extends ChunkGeneratorConfig {
	private static final BlockState AERRACK = CaelumBlocks.AERRACK.getDefaultState();

	@Override
	public BlockState getDefaultBlock() {
		return AERRACK;
	}
}