package reoseah.caelum.common.dimension;

import java.util.ArrayList;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import reoseah.caelum.common.CaelumBiomes;

public class CaelumBiomeSource extends BiomeSource {
	public static final Codec<CaelumBiomeSource> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group(Codec.LONG.fieldOf("seed").stable().forGetter(biomeSource -> {
			return biomeSource.seed;
		})).apply(instance, instance.stable(CaelumBiomeSource::new));
	});

	protected final long seed;

	public CaelumBiomeSource(long seed) {
		super(new ArrayList<>());
		this.biomes.add(CaelumBiomes.BARREN_FOREST);
		this.seed = seed;
	}

	@Override
	public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
		int worldX = biomeX * 2;
		int worldZ = biomeZ * 2;

		BlockPos islandPos = LargeIslandHelper.locateIsland(this.seed, new BlockPos(worldX, 60, worldZ), 100);

		if (Math.abs(worldX - islandPos.getX()) <= 8 && Math.abs(worldZ - islandPos.getZ()) <= 8) {
			return CaelumBiomes.BARREN_FOREST;
		}

		return CaelumBiomes.BARREN_FOREST;
	}

	@Override
	protected Codec<? extends BiomeSource> method_28442() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return new CaelumBiomeSource(seed);
	}
}
