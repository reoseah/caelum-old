package reoseah.caelum;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import reoseah.caelum.client.CaelumParticles;
import reoseah.caelum.common.*;
import reoseah.caelum.common.dimension.CaelumDimension;
import reoseah.caelum.common.misc.TillingHelper;

public class Caelum implements ModInitializer {
	public static final String MODID = "caelum";

	public static final DimensionType DIMENSION_TYPE = FabricDimensionType.builder()
			.factory(CaelumDimension::new)
			.defaultPlacer(CaelumDimension::placeEntity)
			.biomeAccessStrategy(HorizontalVoronoiBiomeAccessType.INSTANCE)
			.buildAndRegister(new Identifier("caelum:caelum"));

	@Override
	public void onInitialize() {
		CaelumBlocks.register();
		CaelumItems.register();
		CaelumStructures.register();
		CaelumFeatures.register();
		CaelumBiomes.register();
		CaelumEntities.register();
		CaelumParticles.register();

		TillingHelper.registerTilling();
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
