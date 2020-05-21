package reoseah.empyrean;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.empyrean.world.surfaces.RockySurfaceBuilder;
import reoseah.empyrean.world.surfaces.EmpyreanSurfaceBuilder;

public class SkySurfaceBuilders {
	public static final SurfaceBuilder<TernarySurfaceConfig> DEFAULT = new EmpyreanSurfaceBuilder(TernarySurfaceConfig::deserialize);
	public static final SurfaceBuilder<TernarySurfaceConfig> ROCKY = new RockySurfaceBuilder(TernarySurfaceConfig::deserialize);

	public static void register() {
		register("default", DEFAULT);
		register("rocky", ROCKY);
	}

	private static void register(String name, SurfaceBuilder<?> surfaceBuilder) {
		Registry.register(Registry.SURFACE_BUILDER, Empyrean.makeID(name), surfaceBuilder);
	}
}
