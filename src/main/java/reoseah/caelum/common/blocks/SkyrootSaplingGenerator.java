package reoseah.caelum.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumFeatures;
import reoseah.caelum.common.biomes.CaelumBiomesFeatures;

public class SkyrootSaplingGenerator extends SaplingGenerator {
	@Override
	public boolean generate(ServerWorld world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random) {
		ConfiguredFeature<?, ?> feature = null;
		if (state.getBlock() == CaelumBlocks.SKYROOT_SAPLING) {
			feature = random.nextInt(10) == 0
					? CaelumFeatures.CAELUM_TREE.configure(CaelumBiomesFeatures.TALL_SKYROOT)
					: CaelumFeatures.CAELUM_TREE.configure(CaelumBiomesFeatures.SKYROOT);
		} else if (state.getBlock() == CaelumBlocks.SILVER_SKYROOT_SAPLING) {
			feature = CaelumFeatures.CAELUM_TREE.configure(CaelumBiomesFeatures.SILVER_SKYROOT);
		} else if (state.getBlock() == CaelumBlocks.DWARF_SKYROOT_SAPLING) {
			feature = CaelumFeatures.CURVED_CAELUM_TREE.configure(CaelumBiomesFeatures.DWARF_SKYROOT);
		} else {
			return false;
		}

		if (feature.generate(world, world.getStructureAccessor(), generator, random, pos)) {
			world.setBlockState(pos, CaelumBlocks.SKYROOT_LOG.getDefaultState(), 4);
			return true;
		}
		return false;
	}

	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
		return null;
	}
}
