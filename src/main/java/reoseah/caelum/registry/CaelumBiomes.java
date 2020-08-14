package reoseah.caelum.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.caelum.biomes.CaelumBiomesCreator;
import reoseah.caelum.registry.CaelumDimension.SurfaceConfigs;
import reoseah.caelum.surface_builders.CaelumSurfaceBuilder;

public class CaelumBiomes {
	public static final SurfaceBuilder<TernarySurfaceConfig> CAELUM_SURFACE_BUILDER = new CaelumSurfaceBuilder(TernarySurfaceConfig.CODEC);

	public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> SKYGRASS_SURFACE = CAELUM_SURFACE_BUILDER.method_30478(SurfaceConfigs.SKYGRASS_SURFACE_CONFIG);

	public static final RegistryKey<Biome> CAELUM_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("caelum:forest"));
	public static final Biome CAELUM_FOREST = CaelumBiomesCreator.createForest(0, 0);

	public static void register() {
		Registry.register(Registry.SURFACE_BUILDER, new Identifier("caelum:caelum"), CAELUM_SURFACE_BUILDER);

		Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("caelum:caelum"), SKYGRASS_SURFACE);

		Registry.register(BuiltinRegistries.BIOME, new Identifier("caelum:forest"), CAELUM_FOREST);
	}
}
