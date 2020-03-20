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

		if (state.getBlock() == SkyBlocks.COMMON_SKYROOT_LOG) {
			feature = random.nextInt(10) == 0
					? SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.TALL_COMMON_SKYROOT)
					: SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.COMMON_SKYROOT);
		} else if (state.getBlock() == SkyBlocks.SILVER_SKYROOT_LOG) {
			feature = random.nextInt(10) == 0
					? SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.TALL_COMMON_SKYROOT)
					: SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.COMMON_SKYROOT);
		} else if (state.getBlock() == SkyBlocks.DWARF_SKYROOT_LOG) {
			feature = SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.DWARF_SKYROOT);
		} else {
			return false;
		}

		return feature.generate(world, generator, random, pos);
	}

	@Override
	protected ConfiguredFeature<BranchedTreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
		return null;
	}
}
