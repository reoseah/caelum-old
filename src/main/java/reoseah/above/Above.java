package reoseah.above;

import java.util.Arrays;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import reoseah.above.blocks.SkyFarmlandBlock;
import reoseah.above.blocks.SkyMossBlock;
import reoseah.above.features.SkyrootBushFeature;
import reoseah.above.features.SkyrootTreeFeature;
import reoseah.above.features.SkyrootTreeFeatureConfig;

public class Above implements ModInitializer {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(makeID("main"), () -> new ItemStack(Above.SKY_MOSS));

	public static final Block AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(5.0F, 6.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block CERUCLASE_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(5.0F, 6.0F).breakByTool(FabricToolTags.PICKAXES, 1).sounds(BlockSoundGroup.STONE).build());
	public static final Block AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 5.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block CHISELED_AERRACK = new PillarBlock(FabricBlockSettings.copy(AERRACK_BRICKS).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block SKY_SILT = new FallingBlock(FabricBlockSettings.copy(Blocks.GRAVEL).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).build());
	public static final Block SKY_MOSS = new SkyMossBlock(FabricBlockSettings.copy(Blocks.GRASS_BLOCK).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).build());
	public static final Block SKY_FARMLAND = new SkyFarmlandBlock(FabricBlockSettings.copy(Blocks.FARMLAND).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).build());

	public static final Block COMMON_SKYROOT_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.copy(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES).breakByHand(true).build());
	public static final Block SILVER_SKYROOT_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.copy(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES).breakByHand(true).build());
	public static final Block DWARF_SKYROOT_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.copy(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES).breakByHand(true).build());
	public static final Block COMMON_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakByHand(true).build());
	public static final Block SILVER_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakByHand(true).build());
	public static final Block DWARF_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakByHand(true).build());

	public static final SkyrootTreeFeatureConfig COMMON_SKYROOT_CONFIG = new SkyrootTreeFeatureConfig(
			new SimpleBlockStateProvider(COMMON_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(COMMON_SKYROOT_LEAVES.getDefaultState()),
			SkyrootTreeFeature.Size.NORMAL);

	public static final SkyrootTreeFeatureConfig COMMON_TALL_SKYROOT_CONFIG = new SkyrootTreeFeatureConfig(
			new SimpleBlockStateProvider(COMMON_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(COMMON_SKYROOT_LEAVES.getDefaultState()),
			SkyrootTreeFeature.Size.TALL);

	public static final SkyrootTreeFeatureConfig SILVER_SKYROOT_CONFIG = new SkyrootTreeFeatureConfig(
			new SimpleBlockStateProvider(SILVER_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(SILVER_SKYROOT_LEAVES.getDefaultState()),
			SkyrootTreeFeature.Size.SMALL);

	public static final SkyrootTreeFeatureConfig DWARF_SKYROOT_CONFIG = new SkyrootTreeFeatureConfig(
			new SimpleBlockStateProvider(DWARF_SKYROOT_LOG.getDefaultState()),
			new SimpleBlockStateProvider(DWARF_SKYROOT_LEAVES.getDefaultState()),
			SkyrootTreeFeature.Size.SMALL);

	public static final AbstractTreeFeature<TreeFeatureConfig> SKYROOT_BUSH_FEATURE = new SkyrootBushFeature(TreeFeatureConfig::deserialize);

	public static final AbstractTreeFeature<SkyrootTreeFeatureConfig> SKYROOT_TREE_FEATURE = new SkyrootTreeFeature(SkyrootTreeFeatureConfig::deserialize);

	public static Identifier makeID(String name) {
		return new Identifier("above", name);
	}

	private static Settings makeItemSettings() {
		return new Item.Settings().group(GROUP);
	}

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, makeID("aerrack"), AERRACK);
		Registry.register(Registry.BLOCK, makeID("ceruclase_ore"), CERUCLASE_ORE);
		Registry.register(Registry.BLOCK, makeID("aerrack_bricks"), AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, makeID("chiseled_aerrack"), CHISELED_AERRACK);
		Registry.register(Registry.BLOCK, makeID("sky_silt"), SKY_SILT);
		Registry.register(Registry.BLOCK, makeID("sky_moss"), SKY_MOSS);
		Registry.register(Registry.BLOCK, makeID("sky_farmland"), SKY_FARMLAND);
		Registry.register(Registry.BLOCK, makeID("common_skyroot_log"), COMMON_SKYROOT_LOG);
		Registry.register(Registry.BLOCK, makeID("silver_skyroot_log"), SILVER_SKYROOT_LOG);
		Registry.register(Registry.BLOCK, makeID("dwarf_skyroot_log"), DWARF_SKYROOT_LOG);
		Registry.register(Registry.BLOCK, makeID("common_skyroot_leaves"), COMMON_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, makeID("silver_skyroot_leaves"), SILVER_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, makeID("dwarf_skyroot_leaves"), DWARF_SKYROOT_LEAVES);

		Registry.register(Registry.ITEM, makeID("aerrack"), new BlockItem(AERRACK, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("ceruclase_ore"), new BlockItem(CERUCLASE_ORE, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("aerrack_bricks"), new BlockItem(AERRACK_BRICKS, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("chiseled_aerrack"), new BlockItem(CHISELED_AERRACK, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("sky_silt"), new BlockItem(SKY_SILT, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("sky_moss"), new BlockItem(SKY_MOSS, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("sky_farmland"), new BlockItem(SKY_FARMLAND, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("common_skyroot_log"), new BlockItem(COMMON_SKYROOT_LOG, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("silver_skyroot_log"), new BlockItem(SILVER_SKYROOT_LOG, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("dwarf_skyroot_log"), new BlockItem(DWARF_SKYROOT_LOG, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("common_skyroot_leaves"), new BlockItem(COMMON_SKYROOT_LEAVES, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("silver_skyroot_leaves"), new BlockItem(SILVER_SKYROOT_LEAVES, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("dwarf_skyroot_leaves"), new BlockItem(DWARF_SKYROOT_LEAVES, makeItemSettings()));

		Registry.register(Registry.FEATURE, makeID("skyroot_bush"), SKYROOT_BUSH_FEATURE);
		Registry.register(Registry.FEATURE, makeID("skyroot_tree"), SKYROOT_TREE_FEATURE);

		// TESTING CODE

		RandomFeatureConfig forestTreesConfig = new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(SKYROOT_TREE_FEATURE.configure(SILVER_SKYROOT_CONFIG), 0.2F),
						new RandomFeatureEntry<>(SKYROOT_TREE_FEATURE.configure(COMMON_TALL_SKYROOT_CONFIG), 0.15F)),
				SKYROOT_TREE_FEATURE.configure(COMMON_SKYROOT_CONFIG));
		Biomes.PLAINS.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Feature.RANDOM_SELECTOR
						.configure(forestTreesConfig)
						.createDecoratedFeature(
								Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(3, 0.1F, 1))));

		RandomFeatureConfig forestBushesConfig = new RandomFeatureConfig(
				Arrays.asList(
						new RandomFeatureEntry<>(SKYROOT_BUSH_FEATURE.configure(SILVER_SKYROOT_CONFIG), 0.4F),
						new RandomFeatureEntry<>(SKYROOT_BUSH_FEATURE.configure(COMMON_SKYROOT_CONFIG), 0.1F)),
				SKYROOT_BUSH_FEATURE.configure(DWARF_SKYROOT_CONFIG));
		Biomes.PLAINS.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Feature.RANDOM_SELECTOR
						.configure(forestBushesConfig)
						.createDecoratedFeature(
								Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.1F, 1))));

	}
}
