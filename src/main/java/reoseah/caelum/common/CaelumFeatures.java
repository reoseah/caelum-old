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
import reoseah.caelum.common.features.DwarfSkyrootTreeFeature;
import reoseah.caelum.common.features.SkyrootFeatureConfig;
import reoseah.caelum.common.features.SkyrootTallBushFeature;
import reoseah.caelum.common.features.SkyrootTreeFeature;
import reoseah.caelum.common.surface_builders.BarrenSurfaceBuilder;
import reoseah.caelum.common.surface_builders.CaelumSurfaceBuilder;

public class CaelumFeatures {
	public static final SurfaceBuilder<TernarySurfaceConfig> DEFAULT_SURFACE = new CaelumSurfaceBuilder(TernarySurfaceConfig.CODEC);
	public static final SurfaceBuilder<TernarySurfaceConfig> BARREN_SURFACE = new BarrenSurfaceBuilder(TernarySurfaceConfig.CODEC);

	public static final Decorator<CountExtraChanceDecoratorConfig> EXPOSED_AERRACK_DECORATOR = new ExposedAerrackDecorator(CountExtraChanceDecoratorConfig.CODEC);
	public static final Decorator<ChanceDecoratorConfig> CAELUM_TREE_DECORATOR = new SkylandTreeDecorator(ChanceDecoratorConfig.field_24980);

	public static final Feature<SkyrootFeatureConfig> SKYROOT_GROUND_BUSH = new CaelumBushFeature(SkyrootFeatureConfig.field_24921);
	public static final Feature<SkyrootFeatureConfig> SKYROOT_TALL_BUSH = new SkyrootTallBushFeature(SkyrootFeatureConfig.field_24921);
	public static final Feature<SkyrootFeatureConfig> SKYROOT_TREE = new SkyrootTreeFeature(SkyrootFeatureConfig.field_24921);
	public static final Feature<SkyrootFeatureConfig> SKYROOT_BUSH_WITH_SOIL = new CaelumBushWithSoilFeature(SkyrootFeatureConfig.field_24921);
	public static final Feature<SkyrootFeatureConfig> DWARF_SKYROOT_TREE = new DwarfSkyrootTreeFeature(SkyrootFeatureConfig.field_24921);
	public static final Feature<AerrackOreConfig> AERRACK_ORE = new AerrackOreFeature(AerrackOreConfig.CODEC);
	public static final Feature<BlockPileFeatureConfig> CAELUM_VEGETATION = new CaelumVegetationFeature(BlockPileFeatureConfig.field_24873);

	public static void register() {
		Registry.register(Registry.SURFACE_BUILDER, "caelum:default", CaelumFeatures.DEFAULT_SURFACE);
		Registry.register(Registry.SURFACE_BUILDER, "caelum:barren", CaelumFeatures.BARREN_SURFACE);

		Registry.register(Registry.DECORATOR, "caelum:exposed_aerrack", CaelumFeatures.EXPOSED_AERRACK_DECORATOR);
		Registry.register(Registry.DECORATOR, "caelum:caelum_tree", CaelumFeatures.CAELUM_TREE_DECORATOR);

		Registry.register(Registry.FEATURE, "caelum:ground_bush", SKYROOT_GROUND_BUSH);
		Registry.register(Registry.FEATURE, "caelum:tall_bush", SKYROOT_TALL_BUSH);
		Registry.register(Registry.FEATURE, "caelum:sky_tree", SKYROOT_TREE);
		Registry.register(Registry.FEATURE, "caelum:bush_with_soil", SKYROOT_BUSH_WITH_SOIL);
		Registry.register(Registry.FEATURE, "caelum:dwarf_sky_tree", DWARF_SKYROOT_TREE);
		Registry.register(Registry.FEATURE, "caelum:aerrack_ore", AERRACK_ORE);
		Registry.register(Registry.FEATURE, "caelum:vegetation", CAELUM_VEGETATION);
	}
}
