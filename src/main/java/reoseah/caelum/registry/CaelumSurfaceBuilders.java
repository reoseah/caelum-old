package reoseah.caelum.registry;

import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.caelum.surface_builders.CaelumSurfaceBuilder;

public class CaelumSurfaceBuilders {
	public static class SurfaceConfigs {
		private static final BlockState SKY_GRASS = CaelumBlocks.CAELUM_GRASS_BLOCK.getDefaultState();
		private static final BlockState SKY_DIRT = CaelumBlocks.CAELUM_DIRT.getDefaultState();
		private static final BlockState AERRACK = CaelumBlocks.AERRACK.getDefaultState();
		public static final TernarySurfaceConfig SKYGRASS_SURFACE = new TernarySurfaceConfig(SKY_GRASS, SKY_DIRT, AERRACK);
	}

	public static final SurfaceBuilder<TernarySurfaceConfig> CAELUM_SURFACE_BUILDER = new CaelumSurfaceBuilder(TernarySurfaceConfig.CODEC);
	public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> SKYGRASS = CAELUM_SURFACE_BUILDER.method_30478(SurfaceConfigs.SKYGRASS_SURFACE);

	public static void register() {
		Registry.register(Registry.SURFACE_BUILDER, new Identifier("caelum:caelum"), CaelumSurfaceBuilders.CAELUM_SURFACE_BUILDER);

		Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("caelum:skygrass"), CaelumSurfaceBuilders.SKYGRASS);
	}
}
