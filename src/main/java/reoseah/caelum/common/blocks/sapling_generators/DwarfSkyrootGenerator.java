package reoseah.caelum.common.blocks.sapling_generators;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import reoseah.caelum.common.CaelumWorld;
import reoseah.caelum.common.DefaultCaelumFeatures;

public class DwarfSkyrootGenerator extends SkyrootGenerator {
	@Override
	protected ConfiguredFeature<?, ?> getFeature(ServerWorld world, BlockPos pos, BlockState state, Random random) {
		return CaelumWorld.SKYROOT_TREE.configure(DefaultCaelumFeatures.DWARF_SKYROOT_SHRUB);
	}
}
