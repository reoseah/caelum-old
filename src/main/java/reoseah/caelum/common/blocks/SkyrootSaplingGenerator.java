package reoseah.caelum.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumFeatures;
import reoseah.caelum.common.biomes.CaelumBiomesFeatures;

public class SkyrootSaplingGenerator extends SaplingGenerator {
	public boolean generate(IWorld world, ChunkGenerator<?> generator, BlockPos pos, BlockState state, Random random) {
		ConfiguredFeature<?, ?> feature = null;
		if (state.getBlock() == CaelumBlocks.SKYROOT_SAPLING) {
			feature = random.nextInt(10) == 0
					? CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.TALL_SKYROOT)
					: CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.SKYROOT);
		} else if (state.getBlock() == CaelumBlocks.SILVER_SKYROOT_SAPLING) {
			feature = CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.SILVER_SKYROOT);
		} else if (state.getBlock() == CaelumBlocks.DWARF_PINE_SAPLING) {
			feature = random.nextInt(10) == 0
					? CaelumFeatures.DWARF_SKYROOT_TREE.configure(CaelumBiomesFeatures.DWARF_SKYROOT)
					: CaelumFeatures.SKYROOT_TALL_BUSH.configure(CaelumBiomesFeatures.DWARF_SKYROOT);

		} else {
			return false;
		}

		if (feature.generate(world, generator, random, pos)) {
			world.setBlockState(pos, CaelumBlocks.SKYROOT_LOG.getDefaultState(), 4);
			return true;
		}
		return false;
	}

	@Override
	protected ConfiguredFeature<BranchedTreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
		return null;
	}
}
