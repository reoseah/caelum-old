package reoseah.caelum.common.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class CaelumVegetationFeature extends Feature<BlockPileFeatureConfig> {
	public CaelumVegetationFeature(Function<Dynamic<?>, ? extends BlockPileFeatureConfig> function) {
		super(function);
	}

	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos pos, BlockPileFeatureConfig config) {
		BlockState state = config.stateProvider.getBlockState(random, pos);
		if (world.isAir(pos) && state.canPlaceAt(world, pos)) {
			world.setBlockState(pos, state, 19);
			return true;
		}
		return false;
	}
}
