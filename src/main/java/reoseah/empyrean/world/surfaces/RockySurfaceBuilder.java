package reoseah.empyrean.world.surfaces;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.empyrean.SkyBlocks;
import reoseah.empyrean.SkySurfaceBuilders;
import reoseah.empyrean.world.biomes.SkylandBiomesFeatures;

public class RockySurfaceBuilder extends EmpyreanSurfaceBuilder {
	public static final BlockState AERRACK = SkyBlocks.AERRACK.getDefaultState();
	public static final BlockState SKY_GRASS = SkyBlocks.SKY_GRASS.getDefaultState();
	public static final BlockState SKY_SILT = SkyBlocks.SKY_SILT.getDefaultState();

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
