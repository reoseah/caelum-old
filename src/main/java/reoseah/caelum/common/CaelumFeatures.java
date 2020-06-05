package reoseah.caelum.common;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.ChanceRangeDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.caelum.common.decorators.CaelumGrassDecorator;
import reoseah.caelum.common.decorators.CaelumTreeDecorator;
import reoseah.caelum.common.decorators.SealedDungeonDecorator;
import reoseah.caelum.common.decorators.SteepEdgesDecorator;
import reoseah.caelum.common.dimension.chunk_generator.CaelumChunkGeneratorConfig;
import reoseah.caelum.common.dimension.chunk_generator.CaelumChunkGeneratorType;
import reoseah.caelum.common.features.AerrackOreConfig;
import reoseah.caelum.common.features.AerrackOreFeature;
import reoseah.caelum.common.features.CaelumVegetationFeature;
import reoseah.caelum.common.features.SealedDungeonFeature;
import reoseah.caelum.common.features.SkyrootBushWithSoilFeature;
import reoseah.caelum.common.features.SkyrootFeatureConfig;
import reoseah.caelum.common.features.SkyrootGroundBushFeature;
import reoseah.caelum.common.features.SkyrootStumpFeature;
import reoseah.caelum.common.features.SkyrootTreeFeature;
import reoseah.caelum.common.surface_builders.BarrenSurfaceBuilder;
import reoseah.caelum.common.surface_builders.CaelumSurfaceBuilder;

public class CaelumFeatures {
	public static final SurfaceBuilder<TernarySurfaceConfig> DEFAULT_SURFACE = new CaelumSurfaceBuilder(TernarySurfaceConfig::deserialize);
	public static final SurfaceBuilder<TernarySurfaceConfig> BARREN_SURFACE = new BarrenSurfaceBuilder(TernarySurfaceConfig::deserialize);

	public static final Decorator<CountExtraChanceDecoratorConfig> STEEP_EDGES_DECORATOR = new SteepEdgesDecorator(CountExtraChanceDecoratorConfig::deserialize);
	public static final Decorator<ChanceDecoratorConfig> CAELUM_TREE_DECORATOR = new CaelumTreeDecorator(ChanceDecoratorConfig::deserialize);
	public static final Decorator<ChanceDecoratorConfig> CAELUM_GRASS_DECORATOR = new CaelumGrassDecorator(ChanceDecoratorConfig::deserialize);
	public static final Decorator<ChanceRangeDecoratorConfig> SEALED_DUNGEON_DECORATOR = new SealedDungeonDecorator(ChanceRangeDecoratorConfig::deserialize);

	public static final Feature<TreeFeatureConfig> SKYROOT_GROUND_BUSH = new SkyrootGroundBushFeature(TreeFeatureConfig::deserialize);
	public static final Feature<SkyrootFeatureConfig> SKYROOT_TREE = new SkyrootTreeFeature(SkyrootFeatureConfig::deserialize);
	public static final Feature<TreeFeatureConfig> SKYROOT_BUSH_WITH_SOIL = new SkyrootBushWithSoilFeature(TreeFeatureConfig::deserialize);
	public static final Feature<AerrackOreConfig> AERRACK_ORE = new AerrackOreFeature(AerrackOreConfig::deserialize);
	public static final Feature<BlockPileFeatureConfig> CAELUM_VEGETATION = new CaelumVegetationFeature(BlockPileFeatureConfig::deserialize);
	public static final Feature<DefaultFeatureConfig> SKYROOT_STUMP = new SkyrootStumpFeature(DefaultFeatureConfig::deserialize);

	public static final Feature<DefaultFeatureConfig> SEALED_DUNGEON = new SealedDungeonFeature(DefaultFeatureConfig::deserialize);

	public static void register() {
		Registry.register(Registry.CHUNK_GENERATOR_TYPE, "caelum:sky", new CaelumChunkGeneratorType(false, CaelumChunkGeneratorConfig::new));

		Registry.register(Registry.SURFACE_BUILDER, "caelum:default", DEFAULT_SURFACE);
		Registry.register(Registry.SURFACE_BUILDER, "caelum:barren", BARREN_SURFACE);

		Registry.register(Registry.DECORATOR, "caelum:steep_edges_decorator", STEEP_EDGES_DECORATOR);
		Registry.register(Registry.DECORATOR, "caelum:caelum_trees_decorator", CAELUM_TREE_DECORATOR);
		Registry.register(Registry.DECORATOR, "caelum:caelum_grass_decorator", CAELUM_GRASS_DECORATOR);
		Registry.register(Registry.DECORATOR, "caelum:sealed_dungeon_decorator", SEALED_DUNGEON_DECORATOR);

		Registry.register(Registry.FEATURE, "caelum:ground_bush", SKYROOT_GROUND_BUSH);
		Registry.register(Registry.FEATURE, "caelum:sky_tree", SKYROOT_TREE);
		Registry.register(Registry.FEATURE, "caelum:bush_with_soil", SKYROOT_BUSH_WITH_SOIL);
		Registry.register(Registry.FEATURE, "caelum:aerrack_ore", AERRACK_ORE);
		Registry.register(Registry.FEATURE, "caelum:caelum_vegetation", CAELUM_VEGETATION);
		Registry.register(Registry.FEATURE, "caelum:skyroot_stump", SKYROOT_STUMP);

		Registry.register(Registry.FEATURE, "caelum:sealed_dungeon", SEALED_DUNGEON);
	}
}
