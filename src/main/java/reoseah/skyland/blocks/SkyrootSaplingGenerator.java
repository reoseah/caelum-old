package reoseah.skyland.blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import reoseah.skyland.biomes.SkylandBiomesFeatures;
import reoseah.skyland.dimension.features.SkyFeatures;

public class SkyrootSaplingGenerator extends SaplingGenerator {
	public boolean generate(IWorld world, ChunkGenerator<?> generator, BlockPos pos, BlockState state, Random random) {
		ConfiguredFeature<?, ?> feature = null;
		BlockState trunk = null;

		if (state.getBlock() == SkyBlocks.COMMON_SKYROOT_SAPLING) {
			feature = random.nextInt(10) == 0
					? SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.TALL_COMMON_SKYROOT)
					: SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.COMMON_SKYROOT);
			trunk = SkyBlocks.COMMON_SKYROOT_LOG.getDefaultState();
		} else if (state.getBlock() == SkyBlocks.SILVER_SKYROOT_SAPLING) {
			feature = SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.SILVER_SKYROOT);
			trunk = SkyBlocks.SILVER_SKYROOT_LOG.getDefaultState();
		} else if (state.getBlock() == SkyBlocks.DWARF_SKYROOT_SAPLING) {
			feature = random.nextInt(10) == 0
					? SkyFeatures.DWARF_SKYROOT_TREE.configure(SkylandBiomesFeatures.DWARF_SKYROOT)
					: SkyFeatures.SKYROOT_TALL_BUSH.configure(SkylandBiomesFeatures.DWARF_SKYROOT);
			trunk = SkyBlocks.DWARF_SKYROOT_LOG.getDefaultState();
		} else {
			return false;
		}

		if (feature.generate(world, generator, random, pos)) {
			world.setBlockState(pos, trunk, 4);
			return true;
		}
		return false;
	}

	@Override
	protected ConfiguredFeature<BranchedTreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
		return null;
	}
}
