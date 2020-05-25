package reoseah.caelum.common.surface_builders;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.caelum.common.biomes.CaelumBiomesFeatures;

public class BarrenSurfaceBuilder extends CaelumSurfaceBuilder {
	public BarrenSurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> deserialize) {
		super(deserialize);
	}

	@Override
	public void generate(Random random, Chunk chunk, Biome biome,
			int x, int z, int height, double noise,
			BlockState defaultBlock, BlockState defaultFluid,
			int seaLevel, long seed, TernarySurfaceConfig surfaceBlocks) {
		TernarySurfaceConfig config = surfaceBlocks;
		if (noise <= -1 || 1 <= noise) {
			config = CaelumBiomesFeatures.AERRACK_SURFACE;
		} else if (noise <= -0.85 || 0.85 <= noise) {
			int surfaceDepth = (int) (noise / 3.0D + 2D + random.nextDouble() * 0.10D);
			this.generate(random, chunk, biome, x, z, height, surfaceDepth, defaultBlock, defaultFluid, config.getTopMaterial(), config.getUnderMaterial(), config.getUnderwaterMaterial(), seaLevel);
		} else {
			super.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, config);
		}
	}
}
