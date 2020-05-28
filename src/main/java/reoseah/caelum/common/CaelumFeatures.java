package reoseah.caelum.common;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.caelum.common.decorators.ExposedAerrackDecorator;
import reoseah.caelum.common.decorators.SkylandTreeDecorator;
import reoseah.caelum.common.features.AerrackOreConfig;
import reoseah.caelum.common.features.AerrackOreFeature;
import reoseah.caelum.common.features.CaelumBushFeature;
import reoseah.caelum.common.features.CaelumBushWithSoilFeature;
import reoseah.caelum.common.features.CaelumVegetationFeature;
import reoseah.caelum.common.features.CurvedCaelumTreeFeature;
import reoseah.caelum.common.features.CaelumTreeFeatureConfig;
import reoseah.caelum.common.features.CaelumTreeFeature;
import reoseah.caelum.common.surface_builders.BarrenSurfaceBuilder;
import reoseah.caelum.common.surface_builders.CaelumSurfaceBuilder;

public class CaelumFeatures {
	public static final SurfaceBuilder<TernarySurfaceConfig> DEFAULT_SURFACE = new CaelumSurfaceBuilder(TernarySurfaceConfig.CODEC);
	public static final SurfaceBuilder<TernarySurfaceConfig> BARREN_SURFACE = new BarrenSurfaceBuilder(TernarySurfaceConfig.CODEC);

	public static final Decorator<CountExtraChanceDecoratorConfig> EXPOSED_AERRACK_DECORATOR = new ExposedAerrackDecorator(CountExtraChanceDecoratorConfig.CODEC);
	public static final Decorator<ChanceDecoratorConfig> CAELUM_TREE_DECORATOR = new SkylandTreeDecorator(ChanceDecoratorConfig.field_24980);

	public static final Feature<CaelumTreeFeatureConfig> CAELUM_BUSH = new CaelumBushFeature(CaelumTreeFeatureConfig.CODEC);
	public static final Feature<CaelumTreeFeatureConfig> CAELUM_TREE = new CaelumTreeFeature(CaelumTreeFeatureConfig.CODEC);
	public static final Feature<CaelumTreeFeatureConfig> CAELUM_BUSH_WITH_SOIL = new CaelumBushWithSoilFeature(CaelumTreeFeatureConfig.CODEC);
	public static final Feature<CaelumTreeFeatureConfig> CURVED_CAELUM_TREE = new CurvedCaelumTreeFeature(CaelumTreeFeatureConfig.CODEC);

	public static final Feature<AerrackOreConfig> AERRACK_ORE = new AerrackOreFeature(AerrackOreConfig.CODEC);
	public static final Feature<BlockPileFeatureConfig> CAELUM_VEGETATION = new CaelumVegetationFeature(BlockPileFeatureConfig.field_24873);

	public static void register() {
		Registry.register(Registry.SURFACE_BUILDER, "caelum:default", CaelumFeatures.DEFAULT_SURFACE);
		Registry.register(Registry.SURFACE_BUILDER, "caelum:barren", CaelumFeatures.BARREN_SURFACE);

		Registry.register(Registry.DECORATOR, "caelum:exposed_aerrack", CaelumFeatures.EXPOSED_AERRACK_DECORATOR);
		Registry.register(Registry.DECORATOR, "caelum:caelum_tree", CaelumFeatures.CAELUM_TREE_DECORATOR);

		Registry.register(Registry.FEATURE, "caelum:bush", CAELUM_BUSH);
		Registry.register(Registry.FEATURE, "caelum:tree", CAELUM_TREE);
		Registry.register(Registry.FEATURE, "caelum:bush_with_soil", CAELUM_BUSH_WITH_SOIL);
		Registry.register(Registry.FEATURE, "caelum:curved_tree", CURVED_CAELUM_TREE);
		Registry.register(Registry.FEATURE, "caelum:aerrack_ore", AERRACK_ORE);
		Registry.register(Registry.FEATURE, "caelum:vegetation", CAELUM_VEGETATION);
	}
}
