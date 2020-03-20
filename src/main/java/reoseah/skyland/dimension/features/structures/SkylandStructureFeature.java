package reoseah.skyland.dimension.features.structures;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public abstract class SkylandStructureFeature extends StructureFeature<DefaultFeatureConfig> {
	public SkylandStructureFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	@Override
	public boolean shouldStartAt(BiomeAccess biomeAccess, ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, Biome biome) {
		if (chunkX == 0 && chunkZ == 0) {
			// FIXME testing stuff
			return true;
		}

		int i = chunkX / 16;
		int j = chunkZ / 16;
		random.setSeed(i ^ (j * 16) ^ chunkGenerator.getSeed());
		if (random.nextInt(3) != 0) {
			return false;
		}
		if (chunkX != (i * 16) + 4 + random.nextInt(8)) {
			return false;
		}
		if (chunkZ != (j * 16) + 4 + random.nextInt(8)) {
			return false;
		}
		return chunkGenerator.hasStructure(biome, this);
	}
}
