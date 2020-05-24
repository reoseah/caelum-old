package reoseah.caelum.common.dimension;

import java.util.HashSet;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import reoseah.caelum.common.CaelumBiomes;

public class CaelumBiomeSource extends BiomeSource {
	public CaelumBiomeSource() {
		super(new HashSet<>());
		this.biomes.add(CaelumBiomes.BARREN_FOREST);
	}

	@Override
	public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
		int worldX = biomeX * (16 / CaelumChunkGenerator.HORIZONTAL_RESOLUTION);
		int worldZ = biomeZ * (16 / CaelumChunkGenerator.HORIZONTAL_RESOLUTION);
		
		return CaelumBiomes.BARREN_FOREST;
	}
}
