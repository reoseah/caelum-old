package reoseah.caelum.registry;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

public class CaelumBiomesCreator {
	public static Biome createForest(float depth, float scale) {
		SpawnSettings.Builder builder = new SpawnSettings.Builder().playerSpawnFriendly();
		GenerationSettings.Builder generation = new GenerationSettings.Builder()
				.surfaceBuilder(CaelumSurfaceBuilders.SKYGRASS);

		generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, CaelumConfiguredFeatures.SKYGLASS_ORE);
		generation.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, CaelumConfiguredFeatures.WATER_SPRING);
		generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, CaelumConfiguredFeatures.FOREST_GRASSES);
		generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, CaelumConfiguredFeatures.FOREST_TREES);

		return new Biome.Builder()
				.precipitation(Biome.Precipitation.RAIN)
				.category(Biome.Category.FOREST)
				.depth(depth).scale(scale)
				.temperature(0.25F).downfall(0.25F)
				.effects(new BiomeEffects.Builder()
						.waterColor(0x3D57D6)
						.waterFogColor(0x050533)
						.fogColor(12638463)
						.skyColor(getSkyColor(0.25F))
						.moodSound(BiomeMoodSound.CAVE).build())
				.spawnSettings(builder.build())
				.generationSettings(generation.build()).build();
	}

	private static int getSkyColor(float temperature) {
		float f = temperature / 3.0F;
		f = MathHelper.clamp(f, -1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
	}
}
