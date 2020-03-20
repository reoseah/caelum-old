package reoseah.skyland.dimension.features.structures;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public abstract class SkylandStructureFeature extends StructureFeature<DefaultFeatureConfig> {
	public SkylandStructureFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	protected ChunkPos getStart(ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, int offsetX, int offsetZ) {
		int distance = 80;
		int separation = 20;
		int o = chunkX + distance * offsetX;
		int p = chunkZ + distance * offsetZ;
		int posX = o < 0 ? o - distance + 1 : o;
		int posZ = p < 0 ? p - distance + 1 : p;
		int regionX = posX / distance;
		int regionZ = posZ / distance;
		((ChunkRandom) random).setRegionSeed(chunkGenerator.getSeed(), regionX, regionZ, this.getStructureSalt());
		int startX = posX + (random.nextInt(distance - separation) + random.nextInt(distance - separation)) / 2;
		int startZ = posZ + (random.nextInt(distance - separation) + random.nextInt(distance - separation)) / 2;
		return new ChunkPos(startX, startZ);
	}

	@Override
	public boolean shouldStartAt(BiomeAccess biomeAccess, ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, Biome biome) {
		ChunkPos chunkPos = this.getStart(chunkGenerator, random, chunkX, chunkZ, 0, 0);
		if (chunkX == chunkPos.x && chunkZ == chunkPos.z) {
			return chunkGenerator.hasStructure(biome, this);
		}
		return false;
	}
	
	protected abstract int getStructureSalt();
}
