package reoseah.caelum.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumWorld;
import reoseah.caelum.common.DefaultCaelumFeatures;

public class SkyrootSaplingGenerator extends SaplingGenerator {
	@Override
	public boolean generate(ServerWorld world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random) {
		ConfiguredFeature<?, ?> feature = this.getFeature(world, pos, state, random);
		if (feature == null) {
			return false;
		}

		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
		if (feature.generate(world, world.getStructureAccessor(), generator, random, pos)) {
			return true;
		} else {
			world.setBlockState(pos, state, 4);
			return false;
		}
	}

	private ConfiguredFeature<?, ?> getFeature(ServerWorld world, BlockPos pos, BlockState state, Random random) {
		Block block = state.getBlock();
		boolean hasDeepSoil = world.getBlockState(pos.down(2)).getBlock() == CaelumBlocks.CAELUM_DIRT;
		if (block == CaelumBlocks.SKYROOT_SAPLING) {
			if (hasDeepSoil) {
				return random.nextInt(10) == 0
						? CaelumWorld.SKYROOT_TREE.configure(DefaultCaelumFeatures.SKYROOT_TALL_TREE)
						: CaelumWorld.SKYROOT_TREE.configure(DefaultCaelumFeatures.SKYROOT_TREE);
			} else {
				return CaelumWorld.SKYROOT_TREE.configure(DefaultCaelumFeatures.SKYROOT_SHRUB);
			}
		} else if (block == CaelumBlocks.SILVER_SKYROOT_SAPLING) {
			if (hasDeepSoil) {
				return CaelumWorld.SKYROOT_TREE.configure(DefaultCaelumFeatures.SILVER_SKYROOT_TREE);
			} else {
				return CaelumWorld.SKYROOT_TREE.configure(DefaultCaelumFeatures.SILVER_SKYROOT_SHRUB);
			}
		} else if (block == CaelumBlocks.DWARF_SKYROOT_SAPLING) {
			return CaelumWorld.SKYROOT_TREE.configure(DefaultCaelumFeatures.DWARF_SKYROOT_SHRUB);
		}
		return null;
	}

	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
		return null;
	}
}
