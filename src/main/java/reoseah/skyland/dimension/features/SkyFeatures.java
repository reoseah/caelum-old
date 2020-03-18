package reoseah.skyland.dimension.features;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import reoseah.skyland.Skyland;

public class SkyFeatures {
	public static final Feature<TreeFeatureConfig> SKYROOT_GROUND_BUSH = new SkyrootGroundBushFeature(TreeFeatureConfig::deserialize);
	public static final Feature<TreeFeatureConfig> SKYROOT_TALL_BUSH = new SkyrootTallBushFeature(TreeFeatureConfig::deserialize);
	public static final Feature<SkyrootFeatureConfig> SKYROOT_TREE = new SkyrootTreeFeature(SkyrootFeatureConfig::deserialize);
	public static final Feature<AerrackOreConfig> AERRACK_ORE = new AerrackOreFeature(AerrackOreConfig::deserialize);

	public static void register() {
		register("ground_bush", SkyFeatures.SKYROOT_GROUND_BUSH);
		register("tall_bush", SkyFeatures.SKYROOT_TALL_BUSH);
		register("sky_tree", SkyFeatures.SKYROOT_TREE);
		register("aerrack_ore", SkyFeatures.AERRACK_ORE);
	}

	private static void register(String name, Feature<?> feature) {
		Registry.register(Registry.FEATURE, Skyland.makeID(name), feature);
	}
}
