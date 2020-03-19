package reoseah.skyland.biomes;

import java.util.Arrays;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.skyland.blocks.SkyBlocks;
import reoseah.skyland.dimension.decorators.SkyDecorators;
import reoseah.skyland.dimension.features.AerrackOreConfig;
import reoseah.skyland.dimension.features.SkyFeatures;
import reoseah.skyland.dimension.features.SkyrootFeatureConfig;
import reoseah.skyland.dimension.features.SkyrootTreeShape;

public class SkylandBiomesFeatures {
	private static final SimpleBlockStateProvider DWARF_SKYROOT_LEAVES = new SimpleBlockStateProvider(SkyBlocks.DWARF_SKYROOT_LEAVES.getDefaultState());
	private static final SimpleBlockStateProvider DWARF_SKYROOT_LOG = new SimpleBlockStateProvider(SkyBlocks.DWARF_SKYROOT_LOG.getDefaultState());
	private static final SimpleBlockStateProvider SILVER_SKYROOT_LEAVES = new SimpleBlockStateProvider(SkyBlocks.SILVER_SKYROOT_LEAVES.getDefaultState());
	private static final SimpleBlockStateProvider SILVER_SKYROOT_LOG = new SimpleBlockStateProvider(SkyBlocks.SILVER_SKYROOT_LOG.getDefaultState());
	private static final SimpleBlockStateProvider COMMON_SKYROOT_LEAVES = new SimpleBlockStateProvider(SkyBlocks.COMMON_SKYROOT_LEAVES.getDefaultState());
	private static final SimpleBlockStateProvider COMMON_SKYROOT_LOG = new SimpleBlockStateProvider(SkyBlocks.COMMON_SKYROOT_LOG.getDefaultState());

	public static final TernarySurfaceConfig SKY_GRASS_SURFACE = new TernarySurfaceConfig(
			SkyBlocks.SKY_GRASS.getDefaultState(),
			SkyBlocks.SKY_SILT.getDefaultState(),
			SkyBlocks.AERRACK.getDefaultState());

	public static final TernarySurfaceConfig AERRACK_SURFACE = new TernarySurfaceConfig(
			SkyBlocks.AERRACK.getDefaultState(),
			SkyBlocks.AERRACK.getDefaultState(),
			SkyBlocks.AERRACK.getDefaultState());

	public static final SkyrootFeatureConfig COMMON_SKYROOT = new SkyrootFeatureConfig(COMMON_SKYROOT_LOG, COMMON_SKYROOT_LEAVES, 5, SkyrootTreeShape.NORMAL);
	public static final SkyrootFeatureConfig TALL_COMMON_SKYROOT = new SkyrootFeatureConfig(COMMON_SKYROOT_LOG, COMMON_SKYROOT_LEAVES, 7, SkyrootTreeShape.TALL);
	public static final SkyrootFeatureConfig SILVER_SKYROOT = new SkyrootFeatureConfig(SILVER_SKYROOT_LOG, SILVER_SKYROOT_LEAVES, 4, SkyrootTreeShape.SMALL);
	public static final SkyrootFeatureConfig DWARF_SKYROOT = new SkyrootFeatureConfig(DWARF_SKYROOT_LOG, DWARF_SKYROOT_LEAVES, 4, SkyrootTreeShape.SMALL);
	public static final AerrackOreConfig CERUCLASE_ORE = new AerrackOreConfig(SkyBlocks.CERUCLASE_ORE.getDefaultState(), 9);

	public static final void addOres(Biome biome) {
		ConfiguredFeature<AerrackOreConfig, ?> ceruclaseOre = SkyFeatures.AERRACK_ORE.configure(CERUCLASE_ORE);
		ConfiguredDecorator<RangeDecoratorConfig> ceruclaseDecorator = Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(20, 24, 0, 72));

		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ceruclaseOre.createDecoratedFeature(ceruclaseDecorator));
	}

	public static final void addSpecialVegetation(Biome biome) {
		ConfiguredFeature<?, ?> commonBush = SkyFeatures.SKYROOT_GROUND_BUSH.configure(COMMON_SKYROOT);
		ConfiguredFeature<?, ?> silverBush = SkyFeatures.SKYROOT_GROUND_BUSH.configure(SILVER_SKYROOT);
		ConfiguredFeature<?, ?> dwarfBush = SkyFeatures.SKYROOT_BUSH_WITH_SOIL.configure(DWARF_SKYROOT);
		ConfiguredFeature<?, ?> dwarfTallBush = SkyFeatures.SKYROOT_TALL_BUSH.configure(DWARF_SKYROOT);

		ConfiguredFeature<?, ?> bushSelector = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(dwarfBush, 0.35F),
						new RandomFeatureEntry<>(dwarfTallBush, 0.05F),
						new RandomFeatureEntry<>(commonBush, 0.10F)),
				silverBush));

		ConfiguredDecorator<?> exposedAerrack = SkyDecorators.EXPOSED_AERRACK_DECORATOR.configure(new CountExtraChanceDecoratorConfig(3, 0.1F, 1));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, bushSelector.createDecoratedFeature(exposedAerrack));
	}

	public static final void addSkyForestTrees(Biome biome) {
		ConfiguredFeature<SkyrootFeatureConfig, ?> commonTree = SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.COMMON_SKYROOT);
		ConfiguredFeature<SkyrootFeatureConfig, ?> commonTallTree = SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.TALL_COMMON_SKYROOT);
		ConfiguredFeature<SkyrootFeatureConfig, ?> silverTree = SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.SILVER_SKYROOT);

		ConfiguredFeature<RandomFeatureConfig, ?> treeSelector = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(silverTree, 0.2F),
						new RandomFeatureEntry<>(commonTallTree, 0.15F)),
				commonTree));

		ConfiguredDecorator<CountExtraChanceDecoratorConfig> treeDecorator = Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(3, 0.1F, 1));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, treeSelector.createDecoratedFeature(treeDecorator));

		ConfiguredFeature<TreeFeatureConfig, ?> commonBush = SkyFeatures.SKYROOT_GROUND_BUSH.configure(SkylandBiomesFeatures.COMMON_SKYROOT);
		ConfiguredFeature<TreeFeatureConfig, ?> silverBush = SkyFeatures.SKYROOT_GROUND_BUSH.configure(SkylandBiomesFeatures.SILVER_SKYROOT);
		ConfiguredFeature<TreeFeatureConfig, ?> dwarfBush = SkyFeatures.SKYROOT_GROUND_BUSH.configure(SkylandBiomesFeatures.DWARF_SKYROOT);

		ConfiguredFeature<RandomFeatureConfig, ?> bushSelector = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(silverBush, 0.4F),
						new RandomFeatureEntry<>(commonBush, 0.1F)),
				dwarfBush));

		ConfiguredDecorator<CountExtraChanceDecoratorConfig> bushDecorator = Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.1F, 1));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, bushSelector.createDecoratedFeature(bushDecorator));
	}

}
