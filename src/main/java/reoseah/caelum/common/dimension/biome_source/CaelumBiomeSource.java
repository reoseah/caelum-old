package reoseah.caelum.common.dimension.biome_source;

import java.util.ArrayList;
import java.util.function.LongFunction;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.ScaleLayer;
import net.minecraft.world.biome.layer.util.CachingLayerContext;
import net.minecraft.world.biome.layer.util.CachingLayerSampler;
import net.minecraft.world.biome.layer.util.LayerFactory;
import net.minecraft.world.biome.source.BiomeLayerSampler;
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

		LongFunction<CachingLayerContext> contexts = salt -> new CachingLayerContext(25, seed, salt);

		// currently just 25% highlands 75% barren forest
		// change to something else when there's more content in the mod
		LayerFactory<CachingLayerSampler> factory = CaelumInitLayer.INSTANCE.create(contexts.apply(1));

		// 1 cell here is about 16x16 cells after, or 128x128 blocks in world
		for (int i = 0; i < 4; i++) {
			factory = ScaleLayer.NORMAL.create(contexts.apply(1000 + i), factory);
		}

		this.finalSampler = new BiomeLayerSampler(factory);
	}

	public final BiomeLayerSampler finalSampler;

	@Override
	public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
		// "Noise Gen" coordinates have lesser resolution than normal blocks
		// In Caelum generation (as well as Nether and End)
		// 1 noise gen unit = 4 world blocks or 1/4 a chunk

//		int worldX = biomeX * 4;
//		int worldZ = biomeZ * 4;
//		BlockPos islandPos = CaelumDimensionHelper.locateIsland(this.seed, new BlockPos(worldX, 60, worldZ), 100);

		return this.finalSampler.sample(biomeX, biomeZ);
	}

	@Override
	protected Codec<? extends BiomeSource> method_28442() {
		return CODEC;
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return new CaelumBiomeSource(seed);
	}
}
