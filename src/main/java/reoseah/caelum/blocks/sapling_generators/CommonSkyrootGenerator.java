package reoseah.caelum.blocks.sapling_generators;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import reoseah.caelum.registry.CaelumConfiguredFeatures;

public class CommonSkyrootGenerator extends SkyrootGenerator {
	@Override
	protected ConfiguredFeature<?, ?> getFeature(ServerWorld world, BlockPos pos, BlockState state, Random random) {
		return random.nextInt(10) == 0 ? CaelumConfiguredFeatures.TALL_SKYROOT : CaelumConfiguredFeatures.SKYROOT;
	}
}
