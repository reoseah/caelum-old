package reoseah.caelum.common.biomes;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import reoseah.caelum.common.CaelumFeatures;

public class CaelumHighlandsBiome extends Biome implements FloatingIslandsBiome {
	public CaelumHighlandsBiome() {
		super(new Biome.Settings()
				.configureSurfaceBuilder(CaelumFeatures.BARREN_SURFACE, CaelumBiomesFeatures.SKY_GRASS_SURFACE)
				.precipitation(Biome.Precipitation.RAIN)
				.category(Biome.Category.FOREST)
				.temperature(0.25F)
				.downfall(0.8F)
				.scale(0)
				.depth(0)
				.effects(new BiomeEffects.Builder()
						.fogColor(0xC0D8FF)
						.waterColor(0x3D57D6)
						.waterFogColor(0x050533)
						.moodSound(BiomeMoodSound.CAVE)
						.build())
				.parent(null));

		CaelumBiomesFeatures.addOres(this);
		CaelumBiomesFeatures.addWaterSprings(this);
		CaelumBiomesFeatures.addBarrensVegetation(this);
		CaelumBiomesFeatures.addSkyGrass(this);
		DefaultBiomeFeatures.addFrozenTopLayer(this);
	}

	@Override
	public float getIslandsModifier() {
		return -0.5F;
	}
}
