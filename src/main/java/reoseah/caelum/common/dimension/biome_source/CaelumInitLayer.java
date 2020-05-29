package reoseah.caelum.common.dimension.biome_source;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.type.InitLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import reoseah.caelum.common.CaelumBiomes;

public enum CaelumInitLayer implements InitLayer {
	INSTANCE;

	private static final int BARREN_FOREST = Registry.BIOME.getRawId(CaelumBiomes.BARREN_FOREST);
	private static final int CAELUM_HIGHLANDS = Registry.BIOME.getRawId(CaelumBiomes.CAELUM_HIGHLANDS);

	@Override
	public int sample(LayerRandomnessSource context, int x, int y) {
		return context.nextInt(4) == 0 ? CAELUM_HIGHLANDS : BARREN_FOREST;
	}
}
