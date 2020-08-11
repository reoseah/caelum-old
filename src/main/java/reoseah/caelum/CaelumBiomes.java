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
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.caelum.biomes.DefaultCaelumBiomes;
import reoseah.caelum.mixins.ChunkGeneratorSettingsInvoker;

public class CaelumBiomes {
	private static final BlockState SKY_GRASS = CaelumBlocks.CAELUM_GRASS_BLOCK.getDefaultState();
	private static final BlockState SKY_DIRT = CaelumBlocks.CAELUM_DIRT.getDefaultState();
	private static final BlockState AERRACK = CaelumBlocks.AERRACK.getDefaultState();
	public static final TernarySurfaceConfig CAELUM_SURFACE_CONFIG = new TernarySurfaceConfig(SKY_GRASS, SKY_DIRT, AERRACK);

	public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> CAELUM_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.method_30478(CAELUM_SURFACE_CONFIG);

	public static final RegistryKey<Biome> CAELUM_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("caelum:forest"));
	public static final Biome CAELUM_FOREST = DefaultCaelumBiomes.createForest(0, 0);

	public static final RegistryKey<ChunkGeneratorSettings> CAELUM_CHUNK_GENERATOR_KEY = RegistryKey.of(Registry.NOISE_SETTINGS_WORLDGEN, new Identifier("caelum:caelum"));

	public static void register() {
		Registry.register(BuiltinRegistries.BIOME, new Identifier("caelum:forest"), CAELUM_FOREST);

		Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("caelum:caelum"), CAELUM_SURFACE_BUILDER);

		Registry.register(BuiltinRegistries.CHUNK_GENERATOR_SETTINGS, new Identifier("caelum:caelum"), createIslandSettings(new StructuresConfig(true), CaelumBlocks.AERRACK.getDefaultState(), Blocks.WATER.getDefaultState(), false, false));
	}

	private static ChunkGeneratorSettings createIslandSettings(StructuresConfig structures, BlockState defaultBlock, BlockState defaultFluid, boolean mobGenerationDisabled, boolean islandNoiseOverride) {
		return ChunkGeneratorSettingsInvoker.create(structures,
				new GenerationShapeConfig(128,
						new NoiseSamplingConfig(1D, 0.5D, 40.0D, 80.0D),
						new SlideConfig(-300, 16, -8),
						new SlideConfig(-30, 16, 6),
						2, 1, 0.0D, 0.0D, true, false, islandNoiseOverride, false),
				defaultBlock, defaultFluid, -10, -10, 0, mobGenerationDisabled);
	}
}
