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

public abstract class AbstractCaelumTreeFeature<T extends CaelumTreeFeatureConfig> extends Feature<T> {
	public AbstractCaelumTreeFeature(Codec<T> codec) {
		super(codec);
	}

	protected static boolean isAirOrLeaves(ServerWorldAccess world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		return state.isAir()
				|| state.getMaterial() == Material.LEAVES
				|| state.getMaterial() == Material.REPLACEABLE_PLANT;
	}

	protected boolean canGenerateAt(ServerWorldAccess world, BlockPos pos) {
		if (isAirOrLeaves(world, pos)) {
			BlockState ground = world.getBlockState(pos.down());
			Block groundBlock = ground.getBlock();

			return groundBlock == CaelumBlocks.CAELUM_GRASS
					|| groundBlock == CaelumBlocks.CAELUM_DIRT
					|| groundBlock == CaelumBlocks.CAELUM_FARMLAND;
		}
		return false;
	}

	protected static <T extends CaelumTreeFeatureConfig> void trySetLeaves(ServerWorldAccess world, BlockPos pos, int distance, Random random, T config) {
		if (isAirOrLeaves(world, pos)) {
			setBlockStateWithoutUpdating(world, pos, config.leaves.getBlockState(random, pos)
					.with(LeavesBlock.DISTANCE, distance));
		}
	}

	private static void setBlockStateWithoutUpdating(ServerWorldAccess world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, state, 16 | 2);
	}

	protected static void placeHardcodedShape(ServerWorldAccess world, Random random, BlockPos pos, CaelumTreeFeatureConfig config, int[] shape, int trunkHeight) {
		BlockPos.Mutable mpos = new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ());
		for (int dy = 0; dy < shape.length; dy++) {
			int radius = shape[dy];
			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					mpos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
					if (dy < trunkHeight) {
						if (dx == 0 && dz == 0) {
							world.setBlockState(mpos, config.trunk.getBlockState(random, mpos), 0);
						} else {
							int distance = Math.abs(dx) + Math.abs(dz);
							trySetLeaves(world, mpos, distance, random, config);
						}
					} else if (Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) == 0) {
						int distance = dy - trunkHeight + 1 + Math.abs(dx) + Math.abs(dz);
						trySetLeaves(world, mpos, distance, random, config);
					}
				}
			}
		}
	}
}
