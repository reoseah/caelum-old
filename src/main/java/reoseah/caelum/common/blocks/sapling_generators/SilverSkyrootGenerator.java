package reoseah.caelum.common.blocks.sapling_generators;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import reoseah.caelum.common.CaelumFeatures;
import reoseah.caelum.common.dimension.DefaultCaelumFeatures;

public class SilverSkyrootGenerator extends SkyrootGenerator {
	@Override
	protected ConfiguredFeature<?, ?> getFeature(ServerWorld world, BlockPos pos, BlockState state, Random random) {
		return CaelumFeatures.SKYROOT_TREE.configure(DefaultCaelumFeatures.SILVER_SKYROOT_TREE);
	}
}
