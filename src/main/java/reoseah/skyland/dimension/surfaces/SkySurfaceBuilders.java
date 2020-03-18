package reoseah.skyland.dimension.surfaces;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.skyland.Skyland;

public class SkySurfaceBuilders {
	public static final SurfaceBuilder<TernarySurfaceConfig> DEFAULT = new SkylandSurfaceBuilder(TernarySurfaceConfig::deserialize);
	public static final SurfaceBuilder<TernarySurfaceConfig> ROCKY = new RockySurfaceBuilder(TernarySurfaceConfig::deserialize);

	public static void register() {
		register("default", DEFAULT);
		register("rocky", ROCKY);
	}

	private static void register(String name, SurfaceBuilder<?> surfaceBuilder) {
		Registry.register(Registry.SURFACE_BUILDER, Skyland.makeID(name), surfaceBuilder);
	}
}
