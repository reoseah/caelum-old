package reoseah.above.biomes;

import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import reoseah.above.Above;
import reoseah.above.SkyFeatures;

public class SkyBarrensBiome extends Biome {
	public SkyBarrensBiome() {
		super(new Biome.Settings()
				.configureSurfaceBuilder(Above.ROCKY_SURFACE_BUILDER, SkyFeatures.SKY_GRASS_SURFACE_CONFIG)
				.precipitation(Biome.Precipitation.RAIN)
				.category(Biome.Category.FOREST)
				.temperature(0.7F)
				.downfall(0.8F)
				.scale(0)
				.depth(0)
				.effects(new BiomeEffects.Builder()
						.fogColor(0xC0D8FF)
						.waterColor(0x3D57D6)
						.waterFogColor(0x050533)
						.moodSound(SoundEvents.AMBIENT_CAVE)
						.build())
				.parent(null));

		SkyFeatures.addSkyOres(this);
		SkyFeatures.addSkyForestTrees(this);
		DefaultBiomeFeatures.addFrozenTopLayer(this);
	}
}
