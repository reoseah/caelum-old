package reoseah.caelum.common.world.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceRangeDecoratorConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import reoseah.caelum.common.CaelumFeatures;

public class BarrenForestBiome extends Biome implements FloatingIslandsBiome {
	public BarrenForestBiome() {
		super(new Biome.Settings()
				.configureSurfaceBuilder(CaelumFeatures.BARREN_SURFACE, CaelumBiomesFeatures.SKY_GRASS_SURFACE)
				.precipitation(Biome.Precipitation.RAIN)
				.category(Biome.Category.FOREST)
				.temperature(0.7F)
				.downfall(0.8F)
				.scale(0)
				.depth(0)
				.waterColor(0x3D57D6)
				.waterFogColor(0x050533)
//				.moodSound(SoundEvents.AMBIENT_CAVE)
				.parent(null));
		CaelumBiomesFeatures.addOres(this);

		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION,
				CaelumFeatures.SEALED_DUNGEON.configure(FeatureConfig.DEFAULT)
						.createDecoratedFeature(CaelumFeatures.SEALED_DUNGEON_DECORATOR.configure(new ChanceRangeDecoratorConfig(0.625F, 35, 0, 50))));

		CaelumBiomesFeatures.addWaterSprings(this);
		CaelumBiomesFeatures.addSteepEdgesVegetation(this);
		CaelumBiomesFeatures.addSkyForestTrees(this);
		CaelumBiomesFeatures.addSkyGrass(this);
		DefaultBiomeFeatures.addFrozenTopLayer(this);

	}

	@Override
	public float getLandThresholdModifier() {
		return -7.5F;
	}
}
