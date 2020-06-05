package reoseah.caelum.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import reoseah.caelum.common.CaelumBlocks;

public class MuskmelonStemBlock extends StemBlock {
	public MuskmelonStemBlock(GourdBlock gourdBlock, Block.Settings settings) {
		super(gourdBlock, settings);
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return floor.getBlock() == CaelumBlocks.CAELUM_FARMLAND;
	}
}
