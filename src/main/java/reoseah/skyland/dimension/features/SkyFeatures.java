package reoseah.skyland.dimension.features;

import java.util.Locale;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import reoseah.skyland.Skyland;
import reoseah.skyland.dimension.features.structures.LargeIslandStructureFeature;

public class SkyFeatures {
	public static final Feature<TreeFeatureConfig> SKYROOT_GROUND_BUSH = new SkyrootGroundBushFeature(TreeFeatureConfig::deserialize);
	public static final Feature<TreeFeatureConfig> SKYROOT_TALL_BUSH = new SkyrootTallBushFeature(TreeFeatureConfig::deserialize);
	public static final Feature<SkyrootFeatureConfig> SKYROOT_TREE = new SkyrootTreeFeature(SkyrootFeatureConfig::deserialize);
	public static final Feature<TreeFeatureConfig> SKYROOT_BUSH_WITH_SOIL = new SkyrootBushWithSoilFeature(TreeFeatureConfig::deserialize);
	public static final Feature<SkyrootFeatureConfig> DWARF_SKYROOT_TREE = new DwarfSkyrootTreeFeature(SkyrootFeatureConfig::deserialize);
	public static final Feature<AerrackOreConfig> AERRACK_ORE = new AerrackOreFeature(AerrackOreConfig::deserialize);

	public static final LargeIslandStructureFeature LARGE_ISLAND = new LargeIslandStructureFeature(DefaultFeatureConfig::deserialize);

	public static void register() {
		register("ground_bush", SKYROOT_GROUND_BUSH);
		register("tall_bush", SKYROOT_TALL_BUSH);
		register("sky_tree", SKYROOT_TREE);
		register("bush_with_soil", SKYROOT_BUSH_WITH_SOIL);
		register("dwarf_sky_tree", DWARF_SKYROOT_TREE);
		register("aerrack_ore", AERRACK_ORE);

		registerStructure("large_island", LARGE_ISLAND);
		Feature.STRUCTURES.put("Large_Island".toLowerCase(Locale.ROOT), LARGE_ISLAND);
	}

	private static void register(String name, Feature<?> feature) {
		Registry.register(Registry.FEATURE, Skyland.makeID(name), feature);
	}

	private static void registerStructure(String name, StructureFeature<?> feature) {
		Registry.register(Registry.STRUCTURE_FEATURE, Skyland.makeID(name), feature);
	}
}
