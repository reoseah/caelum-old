package reoseah.empyrean;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import reoseah.empyrean.world.EmpyreanEntityPlacer;
import reoseah.empyrean.misc.HoeHelper;
import reoseah.empyrean.world.EmpyreanChunkGeneratorConfig;
import reoseah.empyrean.world.EmpyreanChunkGeneratorType;
import reoseah.empyrean.world.EmpyreanDimension;

public class Empyrean implements ModInitializer {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(makeID("main"), () -> new ItemStack(SkyBlocks.SKY_GRASS));

	public static final DimensionType DIMENSION_TYPE = FabricDimensionType.builder()
			.factory(EmpyreanDimension::new)
			.defaultPlacer(new EmpyreanEntityPlacer())
			.biomeAccessStrategy(HorizontalVoronoiBiomeAccessType.INSTANCE)
			.buildAndRegister(makeID("sky"));

	public static Identifier makeID(String name) {
		return new Identifier("empyrean", name);
	}

	@Override
	public void onInitialize() {
		SkyBlocks.register();
		SkyItems.register();

		SkyFeatures.register();
		SkyDecorators.register();
		SkySurfaceBuilders.register();

		SkyBiomes.register();

		Registry.register(Registry.CHUNK_GENERATOR_TYPE, makeID("sky"), new EmpyreanChunkGeneratorType(false, EmpyreanChunkGeneratorConfig::new));

		HoeHelper.registerTilling();
	}
}
