package reoseah.caelum;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import reoseah.caelum.client.CaelumParticles;
import reoseah.caelum.common.CaelumBiomes;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumEntities;
import reoseah.caelum.common.CaelumFeatures;
import reoseah.caelum.common.CaelumItems;
import reoseah.caelum.common.misc.TillingHelper;
import reoseah.caelum.common.world.CaelumDimension;

public class Caelum implements ModInitializer {
	public static final DimensionType DIMENSION_TYPE = FabricDimensionType.builder()
			.factory(CaelumDimension::new)
			.defaultPlacer(CaelumDimension::placeEntity)
			.biomeAccessStrategy(HorizontalVoronoiBiomeAccessType.INSTANCE)
			.buildAndRegister(new Identifier("caelum:caelum"));

	@Override
	public void onInitialize() {
		CaelumBlocks.register();
		CaelumItems.register();
		CaelumFeatures.register();
		CaelumBiomes.register();
		CaelumEntities.register();
		CaelumParticles.register();

		TillingHelper.registerTilling();
	}
}
