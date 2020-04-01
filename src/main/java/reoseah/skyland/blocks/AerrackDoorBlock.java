package reoseah.skyland.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class AerrackDoorBlock extends DoorBlock {
	protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
	protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);

	public AerrackDoorBlock(Block.Settings settings) {
		super(settings);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, EntityContext context) {
		Direction direction = state.get(FACING);
		boolean closed = !state.get(OPEN);
		boolean side = state.get(HINGE) == DoorHinge.RIGHT;
		switch (direction) {
		case EAST:
		default:
			return closed ? WEST_SHAPE : (side ? SOUTH_SHAPE : NORTH_SHAPE);
		case SOUTH:
			return closed ? NORTH_SHAPE : (side ? WEST_SHAPE : EAST_SHAPE);
		case WEST:
			return closed ? EAST_SHAPE : (side ? NORTH_SHAPE : SOUTH_SHAPE);
		case NORTH:
			return closed ? SOUTH_SHAPE : (side ? EAST_SHAPE : WEST_SHAPE);
		}
	}
}
