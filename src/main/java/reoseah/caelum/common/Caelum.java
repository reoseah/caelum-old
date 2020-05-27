package reoseah.caelum.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import reoseah.caelum.client.CaelumParticles;
import reoseah.caelum.common.dimension.CaelumDimension;
import reoseah.caelum.common.misc.TillingHelper;

public class Caelum implements ModInitializer {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier("caelum", "main"), () -> new ItemStack(CaelumBlocks.CAELUM_GRASS));

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
