package reoseah.above.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import reoseah.above.Above;

public class SkyMossBlock extends FallingBlock {
	public static final BooleanProperty SNOWY = Properties.SNOWY;
	// Falling blocks always schedule update after falling and neighbor update
	// But moss must decay into silt like a normal grass - not instantly!
	// As dirty workaround, decaying is delayed by one tick
	public static final IntProperty AGE = Properties.AGE_1;

	public SkyMossBlock(Block.Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(SNOWY, false).with(AGE, 0));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(SNOWY, AGE);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Block block = ctx.getWorld().getBlockState(ctx.getBlockPos().up()).getBlock();
		return this.getDefaultState().with(SNOWY, block == Blocks.SNOW_BLOCK || block == Blocks.SNOW);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		if (facing != Direction.UP) {
			return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
		}
		Block block = neighborState.getBlock();
		return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos)
				.with(SNOWY, block == Blocks.SNOW_BLOCK || block == Blocks.SNOW);
	}

	private static boolean canSurvive(BlockState state, WorldView view, BlockPos pos) {
		BlockPos posAbove = pos.up();
		BlockState stateAbove = view.getBlockState(posAbove);
		if (stateAbove.getBlock() == Blocks.SNOW && stateAbove.get(SnowBlock.LAYERS) == 1) {
			return true;
		}
		int i = ChunkLightProvider.getRealisticOpacity(view, state, pos, stateAbove, posAbove, Direction.UP, stateAbove.getOpacity(view, posAbove));
		return i < view.getMaxLightLevel();
	}

	private static boolean canSpread(BlockState state, WorldView view, BlockPos pos) {
		BlockPos blockPos = pos.up();
		return canSurvive(state, view, pos) && !view.getFluidState(blockPos).matches(FluidTags.WATER);
	}

	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= 0) {
			FallingBlockEntity entity = new FallingBlockEntity(world, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, world.getBlockState(pos));
			this.configureFallingBlockEntity(entity);
			world.spawnEntity(entity);
		} else if (!canSurvive(state, world, pos)) {
			if (state.get(AGE) == 0) {
				world.setBlockState(pos, state.with(AGE, 1));
			} else {
				world.setBlockState(pos, Above.SKY_SILT.getDefaultState());
			}
		} else if (world.getLightLevel(pos.up()) >= 9) {
			for (int i = 0; i < 4; ++i) {
				BlockPos neighborPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				if (world.getBlockState(neighborPos).getBlock() == Above.SKY_SILT
						&& canSpread(this.getDefaultState(), world, neighborPos)) {
					boolean snowy = world.getBlockState(neighborPos.up()).getBlock() == Blocks.SNOW;
					world.setBlockState(neighborPos, this.getDefaultState().with(SNOWY, snowy));
				}
			}
		}
	}
}
