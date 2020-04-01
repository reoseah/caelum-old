package reoseah.skyland.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class AerrackTrapdoorBlock extends TrapdoorBlock {
	public static final VoxelShape BOTTOM_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 5, 16);
	public static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0, 11, 0, 16, 16, 16);

	protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0, 0, 0, 5, 16, 16);
	protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(11, 0, 0, 16, 16, 16);
	protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 5);
	protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0, 0, 11, 16, 16, 16);

	public AerrackTrapdoorBlock(Block.Settings settings) {
		super(settings);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, EntityContext context) {
		if (!state.get(OPEN)) {
			return state.get(HALF) == BlockHalf.TOP ? TOP_SHAPE : BOTTOM_SHAPE;
		} else {
			switch (state.get(FACING)) {
			case NORTH:
			default:
				return NORTH_SHAPE;
			case SOUTH:
				return SOUTH_SHAPE;
			case WEST:
				return WEST_SHAPE;
			case EAST:
				return EAST_SHAPE;
			}
		}
	}
}
