package reoseah.caelum.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import reoseah.caelum.common.CaelumBlocks;

public class SkyrootSaplingBlock extends SaplingBlock {
	public SkyrootSaplingBlock(SaplingGenerator generator, Block.Settings settings) {
		super(generator, settings);
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		Block block = floor.getBlock();
		return block == CaelumBlocks.CAELUM_GRASS_BLOCK
				|| block == CaelumBlocks.CAELUM_DIRT
				|| block == CaelumBlocks.CAELUM_FARMLAND;
	}
}
