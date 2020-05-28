package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.common.CaelumBlocks;

public abstract class CaelumTreeFeature<T extends SkyrootFeatureConfig> extends Feature<T> {
	public CaelumTreeFeature(Codec<T> codec) {
		super(codec);
	}

	protected boolean isAirOrLeaves(ServerWorldAccess world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		return state.isAir()
				|| state.getMaterial() == Material.LEAVES
				|| state.getMaterial() == Material.REPLACEABLE_PLANT;
	}

	protected boolean canGenerateAt(ServerWorldAccess world, BlockPos pos) {
		if (this.isAirOrLeaves(world, pos)) {
			BlockState ground = world.getBlockState(pos.down());
			Block groundBlock = ground.getBlock();

			return groundBlock == CaelumBlocks.CAELUM_GRASS
					|| groundBlock == CaelumBlocks.CAELUM_DIRT
					|| groundBlock == CaelumBlocks.CAELUM_FARMLAND;
		}
		return false;
	}

	protected void trySetLeaves(ServerWorldAccess world, BlockPos pos, int distance, Random random, T config) {
		if (this.isAirOrLeaves(world, pos)) {
			setBlockStateWithoutUpdating(world, pos, config.leaves.getBlockState(random, pos)
					.with(LeavesBlock.DISTANCE, distance));
		}
	}

	private static void setBlockStateWithoutUpdating(ServerWorldAccess world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, state, 16 | 2);
	}
}
