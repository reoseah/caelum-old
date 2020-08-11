package reoseah.caelum.biomes;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import reoseah.caelum.CaelumBiomes;

public class DefaultCaelumBiomes {
	public static Biome createForest(float depth, float scale) {
		SpawnSettings.Builder builder = new SpawnSettings.Builder().playerSpawnFriendly();
		GenerationSettings.Builder generation = new GenerationSettings.Builder()
				.surfaceBuilder(CaelumBiomes.CAELUM_SURFACE_BUILDER);
		
		return new Biome.Builder()
				.precipitation(Biome.Precipitation.RAIN)
				.category(Biome.Category.FOREST)
				.depth(depth).scale(scale)
				.temperature(0.7F).downfall(0.8F)
				.effects(new BiomeEffects.Builder()
						.waterColor(4159204)
						.waterFogColor(329011)
						.fogColor(12638463)
						.skyColor(getSkyColor(0.7F))
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
