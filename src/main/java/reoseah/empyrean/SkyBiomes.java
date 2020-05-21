package reoseah.empyrean;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import reoseah.empyrean.world.biomes.BarrenForestBiome;

public class SkyBiomes {
	public static final Biome BARREN_FOREST = new BarrenForestBiome();

	public static void register() {
		register("barren_forest", BARREN_FOREST);
	}

	private static void register(String name, Biome biome) {
		Registry.register(Registry.BIOME, Empyrean.makeID(name), biome);
	}
}
