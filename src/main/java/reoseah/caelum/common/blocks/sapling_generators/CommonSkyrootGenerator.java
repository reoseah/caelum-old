package reoseah.caelum.common.blocks.sapling_generators;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import reoseah.caelum.common.CaelumFeatures;
import reoseah.caelum.common.dimension.DefaultCaelumFeatures;
import reoseah.caelum.common.features.SkyrootConfig;

public class CommonSkyrootGenerator extends SkyrootGenerator {
	@Override
	protected ConfiguredFeature<?, ?> getFeature(ServerWorld world, BlockPos pos, BlockState state, Random random) {
		SkyrootConfig config = random.nextInt(10) == 0 ? DefaultCaelumFeatures.SKYROOT_TALL_TREE : DefaultCaelumFeatures.SKYROOT_TREE;
		return CaelumFeatures.SKYROOT_TREE.configure(config);
	}
}
