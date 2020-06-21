package reoseah.caelum.common.blocks.sapling_generators;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import reoseah.caelum.common.CaelumWorld;
import reoseah.caelum.common.DefaultCaelumFeatures;
import reoseah.caelum.common.features.SkyrootConfig;

public class CommonSkyrootGenerator extends SkyrootGenerator {
	@Override
	protected ConfiguredFeature<?, ?> getFeature(ServerWorld world, BlockPos pos, BlockState state, Random random) {
		boolean hasDeepSoil = this.hasDeepSoil(world, pos);
		if (!hasDeepSoil) {
			return CaelumWorld.SKYROOT_TREE.configure(DefaultCaelumFeatures.SKYROOT_SHRUB);
		}
		SkyrootConfig config = random.nextInt(10) == 0 ? DefaultCaelumFeatures.SKYROOT_TALL_TREE : DefaultCaelumFeatures.SKYROOT_TREE;
		return CaelumWorld.SKYROOT_TREE.configure(config);
	}
}
