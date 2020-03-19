package reoseah.skyland.biomes;

import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import reoseah.skyland.dimension.surfaces.SkySurfaceBuilders;

public class BarrenForestBiome extends Biome implements SkylandBiome {
	public BarrenForestBiome() {
		super(new Biome.Settings()
				.configureSurfaceBuilder(SkySurfaceBuilders.ROCKY, SkylandBiomesFeatures.SKY_GRASS_SURFACE)
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

		SkylandBiomesFeatures.addOres(this);
		SkylandBiomesFeatures.addSpecialVegetation(this);
		SkylandBiomesFeatures.addSkyForestTrees(this);
		DefaultBiomeFeatures.addFrozenTopLayer(this);
	}

	@Override
	public float getIslandsSize() {
		return -0.5F;
	}
}
