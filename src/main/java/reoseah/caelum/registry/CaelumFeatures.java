package reoseah.caelum.registry;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.features.CaelumGrassesFeature;
import reoseah.caelum.features.SkyrootConfig;
import reoseah.caelum.features.SkyrootShrubFeature;
import reoseah.caelum.features.SkyrootStumpFeature;
import reoseah.caelum.features.SkyrootTreeFeature;

public class CaelumFeatures {
	public static final Feature<BlockPileFeatureConfig> CAELUM_GRASSES = new CaelumGrassesFeature(BlockPileFeatureConfig.CODEC);

	public static final Feature<SkyrootConfig> SKYROOT_TREE = new SkyrootTreeFeature(SkyrootConfig.CODEC);
	public static final Feature<SkyrootConfig> SKYROOT_SHRUB = new SkyrootShrubFeature(SkyrootConfig.CODEC);
	public static final Feature<DefaultFeatureConfig> SKYROOT_STUMP = new SkyrootStumpFeature(DefaultFeatureConfig.CODEC);

	public static void register() {
		Registry.register(Registry.FEATURE, "caelum:caelum_grasses", CAELUM_GRASSES);

		Registry.register(Registry.FEATURE, "caelum:skyroot_tree", SKYROOT_TREE);
		Registry.register(Registry.FEATURE, "caelum:skyroot_shrub", SKYROOT_SHRUB);
		Registry.register(Registry.FEATURE, "caelum:skyroot_stump", SKYROOT_STUMP);
	}
}
