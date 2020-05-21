package reoseah.empyrean.world;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import reoseah.empyrean.SkyBlocks;

public class EmpyreanChunkGeneratorConfig extends ChunkGeneratorConfig {
	private static final BlockState AERRACK = SkyBlocks.AERRACK.getDefaultState();

	@Override
	public BlockState getDefaultBlock() {
		return AERRACK;
	}
}