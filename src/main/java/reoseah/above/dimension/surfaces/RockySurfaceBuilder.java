package reoseah.above.dimension.surfaces;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.above.SkyFeatures;

public class RockySurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {
	public RockySurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> deserialize) {
		super(deserialize);
	}

	@Override
	public void generate(Random random,
			Chunk chunk,
			Biome biome,
			int x,
			int z,
			int y,
			double depthNoise,
			BlockState defaultBlock,
			BlockState defaultFluid,
			int seaLevel,
			long seed,
			TernarySurfaceConfig config) {
		if (-2 <= depthNoise && depthNoise <= 2) {
			SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, y,
					depthNoise, defaultBlock, defaultFluid, seaLevel, seed, config);
		} else {
			SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, y,
					depthNoise, defaultBlock, defaultFluid, seaLevel, seed, SkyFeatures.AERRACK_SURFACE_CONFIG);
		}
	}
}
