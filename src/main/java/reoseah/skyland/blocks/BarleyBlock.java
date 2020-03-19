package reoseah.skyland.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class BarleyBlock extends CropBlock {
	public BarleyBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return floor.getBlock() == SkyBlocks.SKY_FARMLAND;
	}
}
