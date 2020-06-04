package reoseah.caelum.common.biomes;

import java.util.Arrays;

import com.google.common.collect.ImmutableSet;

import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.SpringFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumFeatures;
import reoseah.caelum.common.features.AerrackOreConfig;
import reoseah.caelum.common.features.AethererumOreConfig;
import reoseah.caelum.common.features.SkyrootFeatureConfig;
import reoseah.caelum.common.features.SkyrootTreeShape;

public abstract class CaelumBiomesFeatures {
	public static final TernarySurfaceConfig SKY_GRASS_SURFACE = new TernarySurfaceConfig(
			CaelumBlocks.CAELUM_GRASS.getDefaultState(),
			CaelumBlocks.CAELUM_DIRT.getDefaultState(),
			CaelumBlocks.AERRACK.getDefaultState());

	public static final TernarySurfaceConfig AERRACK_SURFACE = new TernarySurfaceConfig(
			CaelumBlocks.AERRACK.getDefaultState(),
			CaelumBlocks.AERRACK.getDefaultState(),
			CaelumBlocks.AERRACK.getDefaultState());

	public static final AerrackOreConfig CERUCLASE_ORE = new AerrackOreConfig(CaelumBlocks.CERUCLASE_ORE.getDefaultState(), 9);
	public static final AethererumOreConfig AETHERERUM_ORE = new AethererumOreConfig(CaelumBlocks.AETHERERUM_ORE.getDefaultState(), 1);

	public static final void addOres(Biome biome) {
		ConfiguredFeature<?, ?> feature = CaelumFeatures.AERRACK_ORE.configure(CERUCLASE_ORE);
		ConfiguredFeature<?, ?> AETHERERUM_ORE_FEATURE = CaelumFeatures.AETHERERUM_ORE.configure(AETHERERUM_ORE);
		ConfiguredDecorator<?> decorator = Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(20, 24, 0, 72));

		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, feature.createDecoratedFeature(decorator));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, AETHERERUM_ORE_FEATURE.createDecoratedFeature(decorator));

	}

	public static final SpringFeatureConfig ENCLOSED_WATER_SPRING = new SpringFeatureConfig(Fluids.WATER.getDefaultState(), false, 5, 0, ImmutableSet.of(CaelumBlocks.AERRACK));

	public static final void addWaterSprings(Biome biome) {
		ConfiguredFeature<?, ?> feature = Feature.SPRING_FEATURE.configure(ENCLOSED_WATER_SPRING);
		ConfiguredDecorator<?> decorator = Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(16, 10, 20, 72));

		biome.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, feature.createDecoratedFeature(decorator));
	}

	private static final SimpleBlockStateProvider SKYROOT_LOG = new SimpleBlockStateProvider(CaelumBlocks.SKYROOT_LOG.getDefaultState());
	private static final SimpleBlockStateProvider SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.SKYROOT_LEAVES.getDefaultState());
	private static final SimpleBlockStateProvider SILVER_SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.SILVER_SKYROOT_LEAVES.getDefaultState());
	private static final SimpleBlockStateProvider DWARF_SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.DWARF_SKYROOT_LEAVES.getDefaultState());

	public static final SkyrootFeatureConfig SKYROOT = new SkyrootFeatureConfig(SKYROOT_LOG, SKYROOT_LEAVES, 5, SkyrootTreeShape.NORMAL);
	public static final SkyrootFeatureConfig TALL_SKYROOT = new SkyrootFeatureConfig(SKYROOT_LOG, SKYROOT_LEAVES, 7, SkyrootTreeShape.TALL);
	public static final SkyrootFeatureConfig SILVER_SKYROOT = new SkyrootFeatureConfig(SKYROOT_LOG, SILVER_SKYROOT_LEAVES, 4, SkyrootTreeShape.SMALL);
	public static final SkyrootFeatureConfig DWARF_SKYROOT = new SkyrootFeatureConfig(SKYROOT_LOG, DWARF_SKYROOT_LEAVES, 4, SkyrootTreeShape.SMALL);

	public static final void addSkyForestTrees(Biome biome) {
		ConfiguredFeature<?, ?> skyroot = CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.SKYROOT);
		ConfiguredFeature<?, ?> tallSkyroot = CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.TALL_SKYROOT);
		ConfiguredFeature<?, ?> silverSkyroot = CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.SILVER_SKYROOT);

		ConfiguredFeature<?, ?> treesFeature = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(silverSkyroot, 0.2F),
						new RandomFeatureEntry<>(tallSkyroot, 0.15F)),
				skyroot));

		ConfiguredDecorator<?> treeDecorator = CaelumFeatures.CAELUM_TREE_DECORATOR.configure(new ChanceDecoratorConfig(4));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, treesFeature.createDecoratedFeature(treeDecorator));

		ConfiguredFeature<?, ?> skyrootBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(CaelumBiomesFeatures.SKYROOT);
		ConfiguredFeature<?, ?> silverSkyrootBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(CaelumBiomesFeatures.SILVER_SKYROOT);
		ConfiguredFeature<?, ?> dwarfSkyrootBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(CaelumBiomesFeatures.DWARF_SKYROOT);

		ConfiguredFeature<?, ?> bushFeature = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(silverSkyrootBush, 0.4F),
						new RandomFeatureEntry<>(skyrootBush, 0.1F)),
				dwarfSkyrootBush));

		ConfiguredDecorator<?> bushDecorator = Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.1F, 1));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, bushFeature.createDecoratedFeature(bushDecorator));
	}

	public static final void addSteepEdgesVegetation(Biome biome) {
		ConfiguredFeature<?, ?> commonBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(SKYROOT);
		ConfiguredFeature<?, ?> silverBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(SILVER_SKYROOT);
		ConfiguredFeature<?, ?> silverBush2 = CaelumFeatures.SKYROOT_BUSH_WITH_SOIL.configure(SILVER_SKYROOT);
		ConfiguredFeature<?, ?> dwarfBush = CaelumFeatures.SKYROOT_BUSH_WITH_SOIL.configure(DWARF_SKYROOT);

		ConfiguredFeature<?, ?> bushFeature = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(silverBush2, 0.05F),
						new RandomFeatureEntry<>(dwarfBush, 0.35F),
						new RandomFeatureEntry<>(commonBush, 0.10F)),
				silverBush));

		ConfiguredDecorator<?> decorator = CaelumFeatures.STEEP_EDGES_DECORATOR.configure(new CountExtraChanceDecoratorConfig(3, 0.1F, 1));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, bushFeature.createDecoratedFeature(decorator));
	}

	public static final BlockPileFeatureConfig CAELUM_VEGETATION = new BlockPileFeatureConfig(
			new WeightedBlockStateProvider()
					.addState(CaelumBlocks.CAELUM_SPROUTS.getDefaultState(), 9)
					.addState(CaelumBlocks.SKY_BLUE_FLOWER.getDefaultState(), 1));

	public static final void addSkyGrass(Biome biome) {
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
				CaelumFeatures.CAELUM_VEGETATION.configure(CAELUM_VEGETATION)
						.createDecoratedFeature(CaelumFeatures.CAELUM_GRASS_DECORATOR
								.configure(new ChanceDecoratorConfig(3))));
	}

	private CaelumBiomesFeatures() {
		throw new UnsupportedOperationException();
	}
}
