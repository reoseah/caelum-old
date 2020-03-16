package reoseah.above;

import java.util.Arrays;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import reoseah.above.dimension.features.AerrackOreConfig;
import reoseah.above.dimension.features.SkyrootTreeConfig;
import reoseah.above.dimension.features.SkyrootTreeShape;

public class SkyFeatures {
	public static final TernarySurfaceConfig SKY_GRASS_SURFACE_CONFIG = new TernarySurfaceConfig(
			Above.SKY_GRASS.getDefaultState(),
			Above.SKY_SILT.getDefaultState(),
			Above.AERRACK.getDefaultState());
	
	public static final TernarySurfaceConfig AERRACK_SURFACE_CONFIG = new TernarySurfaceConfig(
			Above.AERRACK.getDefaultState(),
			Above.AERRACK.getDefaultState(),
			Above.AERRACK.getDefaultState());

	public static final SkyrootTreeConfig COMMON_SKYROOT_CONFIG = new SkyrootTreeConfig(
			new SimpleBlockStateProvider(Above.COMMON_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(Above.COMMON_SKYROOT_LEAVES.getDefaultState()),
			5, SkyrootTreeShape.NORMAL);
	public static final SkyrootTreeConfig TALL_COMMON_SKYROOT_CONFIG = new SkyrootTreeConfig(
			new SimpleBlockStateProvider(Above.COMMON_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(Above.COMMON_SKYROOT_LEAVES.getDefaultState()),
			7, SkyrootTreeShape.TALL);
	public static final SkyrootTreeConfig SILVER_SKYROOT_CONFIG = new SkyrootTreeConfig(
			new SimpleBlockStateProvider(Above.SILVER_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(Above.SILVER_SKYROOT_LEAVES.getDefaultState()),
			4, SkyrootTreeShape.SMALL);
	public static final SkyrootTreeConfig DWARF_SKYROOT_CONFIG = new SkyrootTreeConfig(
			new SimpleBlockStateProvider(Above.DWARF_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(Above.DWARF_SKYROOT_LEAVES.getDefaultState()),
			4, SkyrootTreeShape.SMALL);

	public static final AerrackOreConfig CERUCLASE_ORE_CONFIG = new AerrackOreConfig(
			Above.CERUCLASE_ORE.getDefaultState(), 9);

	public static final void addSkyOres(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
				Above.AERRACK_ORE_FEATURE.configure(CERUCLASE_ORE_CONFIG)
						.createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(20, 24, 0, 72))));
	}

	public static final void addSkyForestTrees(Biome biome) {
		RandomFeatureConfig forestTreesConfig = new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(Above.SKYROOT_TREE_FEATURE.configure(SkyFeatures.SILVER_SKYROOT_CONFIG), 0.2F),
						new RandomFeatureEntry<>(Above.SKYROOT_TREE_FEATURE.configure(SkyFeatures.TALL_COMMON_SKYROOT_CONFIG), 0.15F)),
				Above.SKYROOT_TREE_FEATURE.configure(SkyFeatures.COMMON_SKYROOT_CONFIG));
		biome.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Feature.RANDOM_SELECTOR
						.configure(forestTreesConfig)
						.createDecoratedFeature(
								Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(3, 0.1F, 1))));

		RandomFeatureConfig forestBushesConfig = new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(Above.SKYROOT_BUSH_FEATURE.configure(SkyFeatures.SILVER_SKYROOT_CONFIG), 0.4F),
						new RandomFeatureEntry<>(Above.SKYROOT_BUSH_FEATURE.configure(SkyFeatures.COMMON_SKYROOT_CONFIG), 0.1F)),
				Above.SKYROOT_BUSH_FEATURE.configure(SkyFeatures.DWARF_SKYROOT_CONFIG));
		biome.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Feature.RANDOM_SELECTOR
						.configure(forestBushesConfig)
						.createDecoratedFeature(
								Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.1F, 1))));
	}

}
