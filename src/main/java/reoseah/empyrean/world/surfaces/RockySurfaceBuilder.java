package reoseah.empyrean.world.surfaces;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.empyrean.SkySurfaceBuilders;
import reoseah.empyrean.SkylandBiomesFeatures;

public class RockySurfaceBuilder extends EmpyreanSurfaceBuilder {
	public RockySurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> deserialize) {
		super(deserialize);
	}

	@Override
	public void generate(Random random, Chunk chunk, Biome biome,
			int x, int z, int height, double noise,
			BlockState defaultBlock, BlockState defaultFluid,
			int seaLevel, long seed, TernarySurfaceConfig surfaceBlocks) {
		TernarySurfaceConfig config = surfaceBlocks;
		if (noise <= -1 || 1 <= noise) {
			config = SkylandBiomesFeatures.AERRACK_SURFACE;
		}
		SkySurfaceBuilders.DEFAULT.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, config);
	}
}
