package reoseah.above.blocks;

import java.util.Random;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import reoseah.above.Above;

public class SkyFarmlandBlock extends Block {
	public static final IntProperty MOISTURE = Properties.MOISTURE;
	protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

	public SkyFarmlandBlock(Block.Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(MOISTURE, 0));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(MOISTURE);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
			world.getBlockTickScheduler().schedule(pos, this, 1);
		} 
		return state;
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockState state2 = world.getBlockState(pos.up());
		return !state2.getMaterial().isSolid() || state2.getBlock() instanceof FenceGateBlock;
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return !this.getDefaultState().canPlaceAt(ctx.getWorld(), ctx.getBlockPos())
				? Above.SKY_SILT.getDefaultState()
				: super.getPlacementState(ctx);
	}

	@Override
	public boolean hasSidedTransparency(BlockState state) {
		return true;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext entityContext) {
		return SHAPE;
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!state.canPlaceAt(world, pos)) {
			setToDirt(state, world, pos);
			return;
		}
		int moisture = state.get(MOISTURE);
		if (!isWaterNearby(world, pos) && !world.hasRain(pos.up())) {
			if (moisture > 0) {
				world.setBlockState(pos, state.with(MOISTURE, moisture - 1), 2);
			} else if (!hasCrop(world, pos)) {
				setToDirt(state, world, pos);
			}
		} else if (moisture < 7) {
			world.setBlockState(pos, state.with(MOISTURE, 7), 2);
		}
	}

	@Override
	public void onLandedUpon(World world, BlockPos pos, Entity entity, float velocity) {
		if (!world.isClient
				&& world.random.nextFloat() < velocity - 0.5F
				&& entity instanceof LivingEntity
				&& (entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.MOB_GRIEFING))
				&& entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
			setToDirt(world.getBlockState(pos), world, pos);
		}

		super.onLandedUpon(world, pos, entity, velocity);
	}

	public static void setToDirt(BlockState state, World world, BlockPos pos) {
		world.setBlockState(pos, pushEntitiesUpBeforeBlockChange(state, Above.SKY_SILT.getDefaultState(), world, pos));
	}

	private static boolean hasCrop(BlockView view, BlockPos pos) {
		Block block = view.getBlockState(pos.up()).getBlock();
		return block instanceof CropBlock || block instanceof StemBlock || block instanceof AttachedStemBlock;
	}

	private static boolean isWaterNearby(WorldView world, BlockPos pos) {
		for (BlockPos p : BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
			if (world.getFluidState(p).matches(FluidTags.WATER)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canPlaceAtSide(BlockState state, BlockView view, BlockPos pos, BlockPlacementEnvironment env) {
		return false;
	}
}
