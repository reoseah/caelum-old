package reoseah.skyland.dimension;

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
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.skyland.blocks.SkyBlocks;
import reoseah.skyland.dimension.decorators.SkyDecorators;
import reoseah.skyland.dimension.features.AerrackOreConfig;
import reoseah.skyland.dimension.features.SkyFeatures;
import reoseah.skyland.dimension.features.SkyrootFeatureConfig;
import reoseah.skyland.dimension.features.SkyrootTreeShape;

public class SkylandBiomesFeatures {
	public static final TernarySurfaceConfig SKY_GRASS_SURFACE_CONFIG = new TernarySurfaceConfig(
			SkyBlocks.SKY_GRASS.getDefaultState(),
			SkyBlocks.SKY_SILT.getDefaultState(),
			SkyBlocks.AERRACK.getDefaultState());

	public static final TernarySurfaceConfig AERRACK_SURFACE_CONFIG = new TernarySurfaceConfig(
			SkyBlocks.AERRACK.getDefaultState(),
			SkyBlocks.AERRACK.getDefaultState(),
			SkyBlocks.AERRACK.getDefaultState());

	public static final SkyrootFeatureConfig COMMON_SKYROOT_CONFIG = new SkyrootFeatureConfig(
			new SimpleBlockStateProvider(SkyBlocks.COMMON_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(SkyBlocks.COMMON_SKYROOT_LEAVES.getDefaultState()),
			5, SkyrootTreeShape.NORMAL);
	public static final SkyrootFeatureConfig TALL_COMMON_SKYROOT_CONFIG = new SkyrootFeatureConfig(
			new SimpleBlockStateProvider(SkyBlocks.COMMON_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(SkyBlocks.COMMON_SKYROOT_LEAVES.getDefaultState()),
			7, SkyrootTreeShape.TALL);
	public static final SkyrootFeatureConfig SILVER_SKYROOT_CONFIG = new SkyrootFeatureConfig(
			new SimpleBlockStateProvider(SkyBlocks.SILVER_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(SkyBlocks.SILVER_SKYROOT_LEAVES.getDefaultState()),
			4, SkyrootTreeShape.SMALL);
	public static final SkyrootFeatureConfig DWARF_SKYROOT_CONFIG = new SkyrootFeatureConfig(
			new SimpleBlockStateProvider(SkyBlocks.DWARF_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(SkyBlocks.DWARF_SKYROOT_LEAVES.getDefaultState()),
			4, SkyrootTreeShape.SMALL);

	public static final AerrackOreConfig CERUCLASE_ORE_CONFIG = new AerrackOreConfig(
			SkyBlocks.CERUCLASE_ORE.getDefaultState(), 9);

	public static final void addSkyOres(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
				SkyFeatures.AERRACK_ORE.configure(CERUCLASE_ORE_CONFIG)
						.createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(20, 24, 0, 72))));
	}

	public static final void addEdgeVegetation(Biome biome) {
		ConfiguredFeature<?, ?> commonBush = SkyFeatures.SKYROOT_GROUND_BUSH.configure(COMMON_SKYROOT_CONFIG);
		ConfiguredFeature<?, ?> silverBush = SkyFeatures.SKYROOT_GROUND_BUSH.configure(SILVER_SKYROOT_CONFIG);
		ConfiguredFeature<?, ?> dwarfBush = SkyFeatures.SKYROOT_GROUND_BUSH.configure(DWARF_SKYROOT_CONFIG);
		ConfiguredFeature<?, ?> dwarfTallBush = SkyFeatures.SKYROOT_TALL_BUSH.configure(DWARF_SKYROOT_CONFIG);

		ConfiguredFeature<?, ?> bushSelector = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(dwarfBush, 0.05F),
						new RandomFeatureEntry<>(dwarfTallBush, 0.1F),
						new RandomFeatureEntry<>(commonBush, 0.15F)),
				silverBush));

		ConfiguredDecorator<?> exposedAerrack = SkyDecorators.EXPOSED_AERRACK_DECORATOR.configure(new CountExtraChanceDecoratorConfig(3, 0.1F, 1));

		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, bushSelector.createDecoratedFeature(exposedAerrack));
	}

	public static final void addSkyForestTrees(Biome biome) {
		RandomFeatureConfig forestTreesConfig = new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.SILVER_SKYROOT_CONFIG), 0.2F),
						new RandomFeatureEntry<>(SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.TALL_COMMON_SKYROOT_CONFIG), 0.15F)),
				SkyFeatures.SKYROOT_TREE.configure(SkylandBiomesFeatures.COMMON_SKYROOT_CONFIG));
		biome.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Feature.RANDOM_SELECTOR
						.configure(forestTreesConfig)
						.createDecoratedFeature(
								Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(3, 0.1F, 1))));

		RandomFeatureConfig forestBushesConfig = new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(SkyFeatures.SKYROOT_GROUND_BUSH.configure(SkylandBiomesFeatures.SILVER_SKYROOT_CONFIG), 0.4F),
						new RandomFeatureEntry<>(SkyFeatures.SKYROOT_GROUND_BUSH.configure(SkylandBiomesFeatures.COMMON_SKYROOT_CONFIG), 0.1F)),
				SkyFeatures.SKYROOT_GROUND_BUSH.configure(SkylandBiomesFeatures.DWARF_SKYROOT_CONFIG));
		biome.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Feature.RANDOM_SELECTOR
						.configure(forestBushesConfig)
						.createDecoratedFeature(
								Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.1F, 1))));
	}

}
