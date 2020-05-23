package reoseah.empyrean.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import reoseah.empyrean.SkyBlocks;
import reoseah.empyrean.SkyItems;

public class BarleyBlock extends CropBlock {
	public BarleyBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return floor.getBlock() == SkyBlocks.SKY_FARMLAND;
	}

	@Environment(EnvType.CLIENT)
	@Override
	protected ItemConvertible getSeedsItem() {
		return SkyItems.BARLEY_SEEDS;
	}
}
