package reoseah.caelum.common;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import reoseah.caelum.common.biomes.BarrenForestBiome;
import reoseah.caelum.common.biomes.CaelumHighlandsBiome;

public class CaelumBiomes {
	public static final Biome BARREN_FOREST = new BarrenForestBiome();
	public static final Biome CAELUM_HIGHLANDS = new CaelumHighlandsBiome();

	public static void register() {
		Registry.register(Registry.BIOME, "caelum:barren_forest", BARREN_FOREST);
		Registry.register(Registry.BIOME, "caelum:caelum_highlands", CAELUM_HIGHLANDS);
	}
}
