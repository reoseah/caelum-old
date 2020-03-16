package reoseah.above;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.pattern.BlockPattern.TeleportTarget;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import reoseah.above.biomes.SkyForestBiome;
import reoseah.above.blocks.SkyFarmlandBlock;
import reoseah.above.blocks.SkyMossBlock;
import reoseah.above.dimension.SkyDimension;
import reoseah.above.dimension.chunk.SkyChunkGeneratorConfig;
import reoseah.above.dimension.chunk.SkyChunkGeneratorType;
import reoseah.above.dimension.features.AerrackOreFeature;
import reoseah.above.dimension.features.SkyrootBushFeature;
import reoseah.above.dimension.features.SkyrootTreeConfig;
import reoseah.above.dimension.features.SkyrootTreeFeature;
import reoseah.above.items.HoeHelper;
import reoseah.above.items.TeleporterItem;

public class Above implements ModInitializer {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(makeID("main"), () -> new ItemStack(Above.SKY_GRASS));

	public static final Block AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block SKY_GRASS = new SkyMossBlock(FabricBlockSettings.of(Material.ORGANIC).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).build());
	public static final Block SKY_SILT = new Block(FabricBlockSettings.of(Material.EARTH).strength(0.5F, 0.5F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).build());
	public static final Block SKY_FARMLAND = new SkyFarmlandBlock(FabricBlockSettings.of(Material.EARTH).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).build());
	public static final Block CERUCLASE_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(5.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 1).sounds(BlockSoundGroup.STONE).build());

	public static final Block COMMON_SKYROOT_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.copy(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES).breakByHand(true).build());
	public static final Block SILVER_SKYROOT_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.copy(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES).breakByHand(true).build());
	public static final Block DWARF_SKYROOT_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.copy(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES).breakByHand(true).build());
	public static final Block COMMON_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakByHand(true).build());
	public static final Block SILVER_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakByHand(true).build());
	public static final Block DWARF_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakByHand(true).build());

	public static final Block AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 10.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block CHISELED_AERRACK = new PillarBlock(FabricBlockSettings.copy(AERRACK_BRICKS).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block CERUCLASE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.CYAN).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());

	public static final Item TELEPORTER = new TeleporterItem(new Item.Settings().group(null));

	public static final AbstractTreeFeature<TreeFeatureConfig> SKYROOT_BUSH_FEATURE = new SkyrootBushFeature(TreeFeatureConfig::deserialize);
	public static final AbstractTreeFeature<SkyrootTreeConfig> SKYROOT_TREE_FEATURE = new SkyrootTreeFeature(SkyrootTreeConfig::deserialize);
	public static final AerrackOreFeature AERRACK_ORE_FEATURE = new AerrackOreFeature();

	public static final ChunkGeneratorType<?, ?> CHUNK_GENERATOR_TYPE = new SkyChunkGeneratorType(false, SkyChunkGeneratorConfig::new);

	public static final DimensionType DIMENSION_TYPE = FabricDimensionType.builder()
			.factory(SkyDimension::new)
			.defaultPlacer((teleported, destination, portalDir, horizontalOffset, verticalOffset) -> new TeleportTarget(new Vec3d(0, 100, 0), Vec3d.ZERO, 0))
			.biomeAccessStrategy(HorizontalVoronoiBiomeAccessType.INSTANCE)
			.buildAndRegister(makeID("sky"));

	public static final Biome SKY_FOREST = new SkyForestBiome();

	public static Identifier makeID(String name) {
		return new Identifier("above", name);
	}

	private static Settings makeItemSettings() {
		return new Item.Settings().group(GROUP);
	}

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, makeID("aerrack"), AERRACK);
		Registry.register(Registry.BLOCK, makeID("sky_grass"), SKY_GRASS);
		Registry.register(Registry.BLOCK, makeID("sky_silt"), SKY_SILT);
		Registry.register(Registry.BLOCK, makeID("sky_farmland"), SKY_FARMLAND);
		Registry.register(Registry.BLOCK, makeID("ceruclase_ore"), CERUCLASE_ORE);
		Registry.register(Registry.BLOCK, makeID("common_skyroot_log"), COMMON_SKYROOT_LOG);
		Registry.register(Registry.BLOCK, makeID("silver_skyroot_log"), SILVER_SKYROOT_LOG);
		Registry.register(Registry.BLOCK, makeID("dwarf_skyroot_log"), DWARF_SKYROOT_LOG);
		Registry.register(Registry.BLOCK, makeID("common_skyroot_leaves"), COMMON_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, makeID("silver_skyroot_leaves"), SILVER_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, makeID("dwarf_skyroot_leaves"), DWARF_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, makeID("aerrack_bricks"), AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, makeID("chiseled_aerrack"), CHISELED_AERRACK);
		Registry.register(Registry.BLOCK, makeID("ceruclase_block"), CERUCLASE_BLOCK);

		Registry.register(Registry.ITEM, makeID("aerrack"), new BlockItem(AERRACK, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("sky_grass"), new BlockItem(SKY_GRASS, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("sky_silt"), new BlockItem(SKY_SILT, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("sky_farmland"), new BlockItem(SKY_FARMLAND, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("common_skyroot_log"), new BlockItem(COMMON_SKYROOT_LOG, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("silver_skyroot_log"), new BlockItem(SILVER_SKYROOT_LOG, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("dwarf_skyroot_log"), new BlockItem(DWARF_SKYROOT_LOG, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("common_skyroot_leaves"), new BlockItem(COMMON_SKYROOT_LEAVES, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("silver_skyroot_leaves"), new BlockItem(SILVER_SKYROOT_LEAVES, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("dwarf_skyroot_leaves"), new BlockItem(DWARF_SKYROOT_LEAVES, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("ceruclase_ore"), new BlockItem(CERUCLASE_ORE, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("aerrack_bricks"), new BlockItem(AERRACK_BRICKS, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("chiseled_aerrack"), new BlockItem(CHISELED_AERRACK, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("ceruclase_block"), new BlockItem(CERUCLASE_BLOCK, makeItemSettings()));

		Registry.register(Registry.ITEM, makeID("teleporter"), TELEPORTER);

		Registry.register(Registry.FEATURE, makeID("skyroot_bush"), SKYROOT_BUSH_FEATURE);
		Registry.register(Registry.FEATURE, makeID("skyroot_tree"), SKYROOT_TREE_FEATURE);
		Registry.register(Registry.FEATURE, makeID("aerrack_ore"), AERRACK_ORE_FEATURE);

		Registry.register(Registry.CHUNK_GENERATOR_TYPE, makeID("sky"), CHUNK_GENERATOR_TYPE);

		Registry.register(Registry.BIOME, makeID("sky_forest"), SKY_FOREST);

		HoeHelper.registerTilling();
	}
}
