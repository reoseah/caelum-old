package reoseah.caelum.common.blocks.sapling_generators;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public abstract class SkyrootGenerator extends SaplingGenerator {
	@Override
	public boolean generate(ServerWorld world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random) {
		ConfiguredFeature<?, ?> feature = this.getFeature(world, pos, state, random);
		if (feature == null) {
			return false;
		}

		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
		if (feature.generate(world, world.getStructureAccessor(), generator, random, pos)) {
			return true;
		}
		world.setBlockState(pos, state, 4);
		return false;
	}

	protected abstract ConfiguredFeature<?, ?> getFeature(ServerWorld world, BlockPos pos, BlockState state, Random random);

	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
		return null;
	}
}
