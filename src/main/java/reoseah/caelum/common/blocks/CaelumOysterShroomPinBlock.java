package reoseah.caelum.common.blocks;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldView;
import reoseah.caelum.common.CaelumBlocks;

public class CaelumOysterShroomPinBlock extends Block {
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
	public static final IntProperty STAGE = Properties.STAGE;
	private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = new EnumMap<>(Direction.class);
	static {
		BOUNDING_SHAPES.put(Direction.NORTH, Block.createCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D));
		BOUNDING_SHAPES.put(Direction.SOUTH, Block.createCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D));
		BOUNDING_SHAPES.put(Direction.WEST, Block.createCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D));
		BOUNDING_SHAPES.put(Direction.EAST, Block.createCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D));
	}

	public CaelumOysterShroomPinBlock(Block.Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(STAGE, 0));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, STAGE);
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		return facing.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(FACING);
		BlockPos blockPos = pos.offset(direction.getOpposite());
		BlockState substrate = world.getBlockState(blockPos);
		return substrate.isSideSolidFullSquare(world, blockPos, direction)
				&& substrate.matches(CaelumBlocks.CAELUM_OYSTER_SHROOM_SUBSTRATES);
	}

	/* @Nullable */
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState state = this.getDefaultState();
		WorldView worldView = ctx.getWorld();
		BlockPos blockPos = ctx.getBlockPos();
		Direction[] directions = ctx.getPlacementDirections();
		Direction[] directions2 = directions;
		for (int i = 0; i < directions.length; ++i) {
			Direction direction = directions2[i];
			if (direction.getAxis().isHorizontal()) {
				Direction opposite = direction.getOpposite();
				state = state.with(FACING, opposite);
				if (state.canPlaceAt(worldView, blockPos)) {
					return state;
				}
			}
		}

		return null;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return getBoundingShape(state);
	}

	public static VoxelShape getBoundingShape(BlockState state) {
		return BOUNDING_SHAPES.get(state.get(FACING));
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.scheduledTick(state, world, pos, random);
		if (world.getLightLevel(pos.up()) < 14 && random.nextInt(7) == 0) {
			if (state.get(STAGE) == 0) {
				world.setBlockState(pos, state.with(STAGE, 1));
			} else {
				world.setBlockState(pos, CaelumBlocks.CAELUM_OYSTER_SHROOM.getDefaultState().with(FACING, state.get(FACING)));
			}
		}
	}
}
