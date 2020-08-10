package reoseah.caelum;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.GenerationShapeConfig;
import net.minecraft.world.gen.chunk.NoiseSamplingConfig;
import net.minecraft.world.gen.chunk.SlideConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import reoseah.caelum.biomes.DefaultCaelumBiomes;
import reoseah.caelum.mixins.ChunkGeneratorSettingsInvoker;

public class CaelumBiomes {
	public static final RegistryKey<Biome> CAELUM_FOREST = RegistryKey.of(Registry.BIOME_KEY, new Identifier("caelum:forest"));
	public static final RegistryKey<ChunkGeneratorSettings> CAELUM_CHUNK_GENERATOR = RegistryKey.of(Registry.NOISE_SETTINGS_WORLDGEN, new Identifier("caelum:caelum"));

	public static void register() {
		Registry.register(BuiltinRegistries.BIOME, new Identifier("caelum:forest"), DefaultCaelumBiomes.createForest(0, 0));

		Registry.register(BuiltinRegistries.CHUNK_GENERATOR_SETTINGS, new Identifier("caelum:caelum"), createIslandSettings(new StructuresConfig(true), Blocks.STONE.getDefaultState(), Blocks.WATER.getDefaultState(), false, false));
	}

	private static ChunkGeneratorSettings createIslandSettings(StructuresConfig structures, BlockState defaultBlock, BlockState defaultFluid, boolean mobGenerationDisabled, boolean islandNoiseOverride) {
		return ChunkGeneratorSettingsInvoker.create(structures,
				new GenerationShapeConfig(128,
						new NoiseSamplingConfig(2.0D, 1.0D, 80.0D, 160.0D),
						new SlideConfig(-3000, 64, -46),
						new SlideConfig(-30, 7, 1),
						2, 1, 0.0D, 0.0D, true, false, islandNoiseOverride, false),
				defaultBlock, defaultFluid, -10, -10, 0, mobGenerationDisabled);
	}
}
