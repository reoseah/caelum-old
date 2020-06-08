package reoseah.caelum.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumFeatures;
import reoseah.caelum.common.world.biomes.CaelumBiomesFeatures;

public class SkyrootSaplingGenerator extends SaplingGenerator {
	@Override
	public boolean generate(IWorld world, ChunkGenerator<?> generator, BlockPos pos, BlockState state, Random random) {
		ConfiguredFeature<?, ?> feature = null;
		Block block = state.getBlock();
		if (block == CaelumBlocks.SKYROOT_SAPLING) {
			if (world.getBlockState(pos.down(2)).getBlock() == CaelumBlocks.CAELUM_DIRT) {
				feature = random.nextInt(10) == 0
						? CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.TALL_SKYROOT)
						: CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.SKYROOT);
			} else {
				feature = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(CaelumBiomesFeatures.SKYROOT);
			}
		} else if (block == CaelumBlocks.SILVER_SKYROOT_SAPLING) {
			if (world.getBlockState(pos.down(2)).getBlock() == CaelumBlocks.CAELUM_DIRT) {
				feature = CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.SILVER_SKYROOT);
			} else {
				feature = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(CaelumBiomesFeatures.SILVER_SKYROOT);
			}
		} else if (block == CaelumBlocks.DWARF_SKYROOT_SAPLING) {
			feature = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(CaelumBiomesFeatures.DWARF_SKYROOT);
		} else {
			return false;
		}
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
		if (feature.generate(world, generator, random, pos)) {
			return true;
		}

		world.setBlockState(pos, state, 4);
		return false;
	}

	@Override
	protected ConfiguredFeature<BranchedTreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
		return null;
	}
}
