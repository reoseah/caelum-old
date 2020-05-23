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
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.SpringFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumFeatures;
import reoseah.caelum.common.features.AerrackOreConfig;
import reoseah.caelum.common.features.SkyrootFeatureConfig;
import reoseah.caelum.common.features.SkyrootTreeShape;

public abstract class CaelumBiomesFeatures {
	private CaelumBiomesFeatures() {
		throw new UnsupportedOperationException();
	}

	private static final SimpleBlockStateProvider SKYROOT_LOG = new SimpleBlockStateProvider(CaelumBlocks.SKYROOT_LOG.getDefaultState());
	private static final SimpleBlockStateProvider SILVER_SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.SILVER_SKYROOT_LEAVES.getDefaultState());
	private static final SimpleBlockStateProvider SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.SKYROOT_LEAVES.getDefaultState());
	private static final SimpleBlockStateProvider DWARF_SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.DWARF_SKYROOT_LEAVES.getDefaultState());

	public static final TernarySurfaceConfig SKY_GRASS_SURFACE = new TernarySurfaceConfig(
			CaelumBlocks.CAELUM_GRASS.getDefaultState(),
			CaelumBlocks.CAELUM_DIRT.getDefaultState(),
			CaelumBlocks.AERRACK.getDefaultState());

	public static final TernarySurfaceConfig AERRACK_SURFACE = new TernarySurfaceConfig(
			CaelumBlocks.AERRACK.getDefaultState(),
			CaelumBlocks.AERRACK.getDefaultState(),
			CaelumBlocks.AERRACK.getDefaultState());

	public static final SkyrootFeatureConfig SKYROOT = new SkyrootFeatureConfig(SKYROOT_LOG, SKYROOT_LEAVES, 5, SkyrootTreeShape.NORMAL);
	public static final SkyrootFeatureConfig TALL_SKYROOT = new SkyrootFeatureConfig(SKYROOT_LOG, SKYROOT_LEAVES, 7, SkyrootTreeShape.TALL);
	public static final SkyrootFeatureConfig SILVER_SKYROOT = new SkyrootFeatureConfig(SKYROOT_LOG, SILVER_SKYROOT_LEAVES, 4, SkyrootTreeShape.SMALL);
	public static final SkyrootFeatureConfig DWARF_SKYROOT = new SkyrootFeatureConfig(SKYROOT_LOG, DWARF_SKYROOT_LEAVES, 4, SkyrootTreeShape.SMALL);

	public static final AerrackOreConfig CERUCLASE_ORE = new AerrackOreConfig(CaelumBlocks.CERUCLASE_ORE.getDefaultState(), 9);

	public static final SpringFeatureConfig ENCLOSED_SKYLAND_WATER_SPRING = new SpringFeatureConfig(Fluids.WATER.getDefaultState(), false, 5, 0, ImmutableSet.of(CaelumBlocks.AERRACK));

	public static final void addOres(Biome biome) {
		ConfiguredFeature<?, ?> ceruclaseOre = CaelumFeatures.AERRACK_ORE.configure(CERUCLASE_ORE);
		ConfiguredDecorator<?> ceruclaseDecorator = Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(20, 24, 0, 72));

		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ceruclaseOre.createDecoratedFeature(ceruclaseDecorator));
	}

	public static final void addWaterSprings(Biome biome) {
		ConfiguredFeature<?, ?> enclosedSprings = Feature.SPRING_FEATURE.configure(ENCLOSED_SKYLAND_WATER_SPRING);
		ConfiguredDecorator<?> decorator = Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(16, 10, 20, 72));

		biome.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, enclosedSprings.createDecoratedFeature(decorator));
	}

	public static final void addSpecialVegetation(Biome biome) {
		ConfiguredFeature<?, ?> commonBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(SKYROOT);
		ConfiguredFeature<?, ?> silverBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(SILVER_SKYROOT);
		ConfiguredFeature<?, ?> silverBush2 = CaelumFeatures.SKYROOT_BUSH_WITH_SOIL.configure(SILVER_SKYROOT);
		ConfiguredFeature<?, ?> dwarfBush = CaelumFeatures.SKYROOT_BUSH_WITH_SOIL.configure(DWARF_SKYROOT);
		ConfiguredFeature<?, ?> dwarfTallBush = CaelumFeatures.SKYROOT_TALL_BUSH.configure(DWARF_SKYROOT);

		ConfiguredFeature<?, ?> bushSelector = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(silverBush2, 0.01F),
						new RandomFeatureEntry<>(dwarfBush, 0.36F),
						new RandomFeatureEntry<>(dwarfTallBush, 0.05F),
						new RandomFeatureEntry<>(commonBush, 0.10F)),
				silverBush));

		ConfiguredDecorator<?> exposedAerrack = CaelumFeatures.EXPOSED_AERRACK_DECORATOR.configure(new CountExtraChanceDecoratorConfig(3, 0.1F, 1));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, bushSelector.createDecoratedFeature(exposedAerrack));
	}

	public static final void addSkyForestTrees(Biome biome) {
		ConfiguredFeature<?, ?> commonTree = CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.SKYROOT);
		ConfiguredFeature<?, ?> commonTallTree = CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.TALL_SKYROOT);
		ConfiguredFeature<?, ?> silverTree = CaelumFeatures.SKYROOT_TREE.configure(CaelumBiomesFeatures.SILVER_SKYROOT);

		ConfiguredFeature<?, ?> treeSelector = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(silverTree, 0.2F),
						new RandomFeatureEntry<>(commonTallTree, 0.15F)),
				commonTree));

		ConfiguredDecorator<?> treeDecorator = CaelumFeatures.SKYLAND_TREE_DECORATOR.configure(new ChanceDecoratorConfig(4));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, treeSelector.createDecoratedFeature(treeDecorator));

		ConfiguredFeature<?, ?> commonBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(CaelumBiomesFeatures.SKYROOT);
		ConfiguredFeature<?, ?> silverBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(CaelumBiomesFeatures.SILVER_SKYROOT);
		ConfiguredFeature<?, ?> dwarfBush = CaelumFeatures.SKYROOT_GROUND_BUSH.configure(CaelumBiomesFeatures.DWARF_SKYROOT);

		ConfiguredFeature<?, ?> bushSelector = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(silverBush, 0.4F),
						new RandomFeatureEntry<>(commonBush, 0.1F)),
				dwarfBush));

		ConfiguredDecorator<?> bushDecorator = Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.1F, 1));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, bushSelector.createDecoratedFeature(bushDecorator));
	}

	public static final void addSkyStructures(Biome biome) {
		biome.addStructureFeature(CaelumFeatures.LARGE_ISLAND.configure(FeatureConfig.DEFAULT));
	}
}
