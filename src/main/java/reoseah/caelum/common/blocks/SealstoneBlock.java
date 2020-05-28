package reoseah.caelum.common.blocks;

import java.util.List;
import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import reoseah.caelum.client.CaelumParticles;
import reoseah.caelum.common.CaelumBlocks;

public class SealstoneBlock extends Block {
	public static final IntProperty LEVEL = Properties.LEVEL_15;

	public SealstoneBlock(Block.Settings settings) {
		super(settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(LEVEL, 0));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(LEVEL);
	}

	@Override
	public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		spawnParticles(world, pos);
		world.getBlockTickScheduler().schedule(pos, this, 2);
		super.onBlockBreakStart(state, world, pos, player);
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
		if (!world.isClient) {
			if (CaelumBlocks.SEALSTONE_PROTECTED_BLOCKS.contains(block)) {
				world.getBlockTickScheduler().schedule(pos, this, 2);
			}
		}
		super.neighborUpdate(state, world, pos, block, fromPos, notify);
	}

	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (state.get(LEVEL) == 0) {
			world.setBlockState(pos, state.with(LEVEL, 15), 3);
		}
		Box box = new Box(pos).expand(4.5);
		List<PlayerEntity> players = world.getNonSpectatingEntities(PlayerEntity.class, box);
		for (PlayerEntity player : players) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 50, 3, true, true));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 50, 3, true, true));
		}
		if (players.isEmpty()) {
			int time = state.get(LEVEL);
			if (time != 0) {
				world.setBlockState(pos, state.with(LEVEL, time - 1), 3);
			}
			if (time <= 1) {
				return;
			}
		}
		world.getBlockTickScheduler().schedule(pos, this, 20);
	}

	public boolean hasRandomTicks(BlockState state) {
		return state.get(LEVEL) > 0;
	}

	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (state.get(LEVEL) > 0 && !world.getBlockTickScheduler().isScheduled(pos, this)) {
			world.getBlockTickScheduler().schedule(pos, this, 2);
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (state.get(LEVEL) > 0) {
			spawnParticles(world, pos);
		}
	}

	private static void spawnParticles(World world, BlockPos pos) {
		Random random = world.random;
		Direction[] var5 = Direction.values();
		int var6 = var5.length;

		for (int var7 = 0; var7 < var6; ++var7) {
			Direction direction = var5[var7];
			BlockPos blockPos = pos.offset(direction);
			if (!world.getBlockState(blockPos).isFullCube(world, blockPos)) {
				Direction.Axis axis = direction.getAxis();
				double x = axis == Direction.Axis.X ? 0.5D + 0.5625D * direction.getOffsetX() : random.nextFloat();
				double y = axis == Direction.Axis.Y ? 0.5D + 0.5625D * direction.getOffsetY() : random.nextFloat();
				double z = axis == Direction.Axis.Z ? 0.5D + 0.5625D * direction.getOffsetZ() : random.nextFloat();
				world.addParticle(CaelumParticles.GLOWING_DUST, pos.getX() + x, pos.getY() + y, pos.getZ() + z, 0.0D, 0.0D, 0.0D);
			}
		}
	}
}
