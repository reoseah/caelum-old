package reoseah.caelum.registry;

import java.util.Arrays;

import com.google.common.collect.ImmutableSet;

import net.minecraft.fluid.Fluids;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SpringFeatureConfig;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import reoseah.caelum.features.SkyrootConfig;
import reoseah.caelum.features.SkyrootTreeShape;

public class CaelumConfiguredFeatures {
	public static final ConfiguredFeature<?, ?> SKYGLASS_ORE = Feature.ORE.configure(Configs.SKYGLASS_ORE_CONFIG)
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(24, 24, 60))).spreadHorizontally().repeat(48);
	public static final ConfiguredFeature<?, ?> WATER_SPRING = Feature.SPRING_FEATURE.configure(Configs.CAELUM_SPRING)
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(24, 24, 60))).spreadHorizontally().repeat(16);

	public static final ConfiguredFeature<?, ?> FOREST_GRASSES = CaelumFeatures.CAELUM_GRASSES.configure(Configs.CAELUM_GRASSES)
			.decorate(ConfiguredFeatures.Decorators.HEIGHTMAP_WORLD_SURFACE.spreadHorizontally());

	public static final ConfiguredFeature<?, ?> BLUE_FLOWER_PATCH = Feature.RANDOM_PATCH.configure(Configs.BLUE_FLOWER_PATCH)
			.applyChance(16).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE);
	public static final ConfiguredFeature<?, ?> PURPLE_FLOWER_PATCH = Feature.RANDOM_PATCH.configure(Configs.PURPLE_FLOWER_PATCH)
			.applyChance(32).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE);

	public static final ConfiguredFeature<?, ?> SKYROOT = CaelumFeatures.SKYROOT_TREE.configure(Configs.SKYROOT_TREE);
	public static final ConfiguredFeature<?, ?> TALL_SKYROOT = CaelumFeatures.SKYROOT_TREE.configure(Configs.SKYROOT_TALL_TREE);
	public static final ConfiguredFeature<?, ?> SKYROOT_BUSH = CaelumFeatures.SKYROOT_SHRUB.configure(Configs.SKYROOT_TREE);
	public static final ConfiguredFeature<?, ?> SILVER_SKYROOT = CaelumFeatures.SKYROOT_TREE.configure(Configs.SILVER_SKYROOT_TREE);
	public static final ConfiguredFeature<?, ?> SILVER_SKYROOT_BUSH = CaelumFeatures.SKYROOT_SHRUB.configure(Configs.SILVER_SKYROOT_TREE);
	public static final ConfiguredFeature<?, ?> DWARF_SKYROOT = CaelumFeatures.SKYROOT_SHRUB.configure(Configs.DWARF_SKYROOT_SHRUB);
	public static final ConfiguredFeature<?, ?> SKYROOT_STUMP = CaelumFeatures.SKYROOT_STUMP.configure(FeatureConfig.DEFAULT);

	private static final ConfiguredFeature<?, ?> FOREST_TREE = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(Arrays.asList(new RandomFeatureEntry(TALL_SKYROOT, 0.1F), new RandomFeatureEntry(SILVER_SKYROOT, 0.2F), new RandomFeatureEntry(DWARF_SKYROOT, 0.1F), new RandomFeatureEntry(SKYROOT_STUMP, 0.02F)), SKYROOT));

	public static final ConfiguredFeature<?, ?> FOREST_TREES = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(Arrays.asList(new RandomFeatureEntry(Feature.NO_OP.configure(FeatureConfig.DEFAULT), 0.5F)), FOREST_TREE))
			.decorate(Decorator.DARK_OAK_TREE.configure(DecoratorConfig.DEFAULT));

	public static final ConfiguredFeature<?, ?> SPAWN_PLATFORM = CaelumFeatures.SPAWN_PLATFORM.configure(FeatureConfig.DEFAULT);

	public static void register() {
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:skyglass_ore", SKYGLASS_ORE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:water_spring", WATER_SPRING);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:forest_grasses", FOREST_GRASSES);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:blue_flower_patch", BLUE_FLOWER_PATCH);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:purple_flower_patch", PURPLE_FLOWER_PATCH);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:skyroot", SKYROOT);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:tall_skyroot", TALL_SKYROOT);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:skyroot_bush", SKYROOT_BUSH);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:silver_skyroot", SILVER_SKYROOT);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:silver_skyroot_bush", SILVER_SKYROOT_BUSH);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:dwarf_skyroot", DWARF_SKYROOT);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:skyroot_stump", SKYROOT_STUMP);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:forest_trees", FOREST_TREES);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, "caelum:spawn_platform", SPAWN_PLATFORM);
	}

	public static class States {
		public static final BlockStateProvider SKYROOT_LOG = new SimpleBlockStateProvider(CaelumBlocks.SKYROOT_LOG.getDefaultState());
		public static final BlockStateProvider SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.SKYROOT_LEAVES.getDefaultState());
		public static final BlockStateProvider SILVER_SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.SILVER_SKYROOT_LEAVES.getDefaultState());
		public static final BlockStateProvider DWARF_SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.DWARF_SKYROOT_LEAVES.getDefaultState());

		public static final BlockStateProvider BLUE_FLOWER = new SimpleBlockStateProvider(CaelumBlocks.BLUE_FLOWER.getDefaultState());
		public static final BlockStateProvider PURPLE_FLOWER = new SimpleBlockStateProvider(CaelumBlocks.PURPLE_FLOWER.getDefaultState());

		public static final BlockStateProvider CAELUM_VEGETATION = new WeightedBlockStateProvider()
				.addState(CaelumBlocks.CAELUM_GRASS.getDefaultState(), 30)
				.addState(CaelumBlocks.BLOSSOMING_CAELUM_GRASS.getDefaultState(), 2)
				.addState(CaelumBlocks.BLUE_FLOWER.getDefaultState(), 2)
				.addState(CaelumBlocks.PURPLE_FLOWER.getDefaultState(), 1);
	}

	public static class Configs {
		public static final OreFeatureConfig SKYGLASS_ORE_CONFIG = new OreFeatureConfig(new BlockMatchRuleTest(CaelumBlocks.AERRACK), CaelumBlocks.SKYGLASS_ORE.getDefaultState(), 9);
		public static final SpringFeatureConfig CAELUM_SPRING = new SpringFeatureConfig(Fluids.WATER.getDefaultState(), true, 5, 0, ImmutableSet.of(CaelumBlocks.AERRACK));

		public static final BlockPileFeatureConfig CAELUM_GRASSES = new BlockPileFeatureConfig(States.CAELUM_VEGETATION);

		public static final RandomPatchFeatureConfig BLUE_FLOWER_PATCH = new RandomPatchFeatureConfig.Builder(States.BLUE_FLOWER, new SimpleBlockPlacer()).tries(32).build();
		public static final RandomPatchFeatureConfig PURPLE_FLOWER_PATCH = new RandomPatchFeatureConfig.Builder(States.PURPLE_FLOWER, new SimpleBlockPlacer()).tries(32).build();

		public static final SkyrootConfig SKYROOT_TREE = new SkyrootConfig(States.SKYROOT_LOG, States.SKYROOT_LEAVES, SkyrootTreeShape.NORMAL);
		public static final SkyrootConfig SKYROOT_TALL_TREE = new SkyrootConfig(States.SKYROOT_LOG, States.SKYROOT_LEAVES, SkyrootTreeShape.TALL);
		public static final SkyrootConfig SKYROOT_SHRUB = new SkyrootConfig(States.SKYROOT_LOG, States.SKYROOT_LEAVES, SkyrootTreeShape.SHRUB);
		public static final SkyrootConfig SILVER_SKYROOT_TREE = new SkyrootConfig(States.SKYROOT_LOG, States.SILVER_SKYROOT_LEAVES, SkyrootTreeShape.NORMAL);
		public static final SkyrootConfig SILVER_SKYROOT_SHRUB = new SkyrootConfig(States.SKYROOT_LOG, States.SILVER_SKYROOT_LEAVES, SkyrootTreeShape.SHRUB);
		public static final SkyrootConfig DWARF_SKYROOT_SHRUB = new SkyrootConfig(States.SKYROOT_LOG, States.DWARF_SKYROOT_LEAVES, SkyrootTreeShape.SHRUB);
	}
}