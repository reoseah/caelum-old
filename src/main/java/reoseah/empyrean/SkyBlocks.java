package reoseah.empyrean;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import reoseah.empyrean.blocks.BarleyBlock;
import reoseah.empyrean.blocks.ModStairsBlock;
import reoseah.empyrean.blocks.SkyFarmlandBlock;
import reoseah.empyrean.blocks.SkyGrassBlock;
import reoseah.empyrean.blocks.SkyrootSaplingBlock;
import reoseah.empyrean.blocks.SkyrootSaplingGenerator;

public class SkyBlocks {
	public static final Block AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block CERUCLASE_ORE = new Block(FabricBlockSettings.of(Material.STONE).lightLevel(7).nonOpaque().strength(5.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 10.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block AERRACK_BRICK_STAIRS = new ModStairsBlock(AERRACK_BRICKS.getDefaultState(), FabricBlockSettings.copy(AERRACK_BRICKS).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block AERRACK_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(AERRACK_BRICKS).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block CHISELED_AERRACK = new PillarBlock(FabricBlockSettings.copy(AERRACK_BRICKS).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block INLAID_AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).lightLevel(13).strength(4.0F, 10.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block CERUCLASE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.CYAN).lightLevel(15).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block SKY_GRASS = new SkyGrassBlock(FabricBlockSettings.of(Material.ORGANIC).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).build());
	public static final Block SKY_SILT = new Block(FabricBlockSettings.of(Material.EARTH).strength(0.5F, 0.5F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).build());
	public static final Block SKY_FARMLAND = new SkyFarmlandBlock(FabricBlockSettings.of(Material.EARTH).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).build());
	public static final Block SKYROOT_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.copy(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES).breakByHand(true).build());
	public static final Block SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakByHand(true).build());
	public static final Block SILVER_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakByHand(true).build());
	public static final Block DWARF_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).breakByHand(true).build());
	public static final Block SKYROOT_SAPLING = new SkyrootSaplingBlock(new SkyrootSaplingGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
	public static final Block SILVER_SKYROOT_SAPLING = new SkyrootSaplingBlock(new SkyrootSaplingGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
	public static final Block DWARF_SKYROOT_SAPLING = new SkyrootSaplingBlock(new SkyrootSaplingGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());

	public static final Block SKYROOT_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES, 0).build());
	public static final Block SKYROOT_STAIRS = new ModStairsBlock(SKYROOT_PLANKS.getDefaultState(), FabricBlockSettings.copy(SKYROOT_PLANKS).breakByTool(FabricToolTags.AXES, 0).build());
	public static final Block SKYROOT_SLAB = new SlabBlock(FabricBlockSettings.copy(SKYROOT_PLANKS).breakByTool(FabricToolTags.AXES, 0).build());
	
	public static final Block BARLEY = new BarleyBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).build());

	public static void register() {
		register("aerrack", AERRACK);
		register("ceruclase_ore", CERUCLASE_ORE);
		register("aerrack_bricks", AERRACK_BRICKS);
		register("aerrack_brick_stairs", AERRACK_BRICK_STAIRS);
		register("aerrack_brick_slab", AERRACK_BRICK_SLAB);
		register("chiseled_aerrack", CHISELED_AERRACK);
		register("inlaid_aerrack_bricks", INLAID_AERRACK_BRICKS);
		register("ceruclase_block", CERUCLASE_BLOCK);
		register("sky_grass", SKY_GRASS);
		register("sky_silt", SKY_SILT);
		register("sky_farmland", SKY_FARMLAND);
		register("skyroot_log", SKYROOT_LOG);
		register("skyroot_leaves", SKYROOT_LEAVES);
		register("silver_skyroot_leaves", SILVER_SKYROOT_LEAVES);
		register("dwarf_skyroot_leaves", DWARF_SKYROOT_LEAVES);
		register("skyroot_sapling", SKYROOT_SAPLING);
		register("silver_skyroot_sapling", SILVER_SKYROOT_SAPLING);
		register("dwarf_skyroot_sapling", DWARF_SKYROOT_SAPLING);

		register("skyroot_planks", SKYROOT_PLANKS);
		register("skyroot_stairs", SKYROOT_STAIRS);
		register("skyroot_slab", SKYROOT_SLAB);
		register("barley", BARLEY);

		register("aerrack", new BlockItem(AERRACK, new Item.Settings().group(Empyrean.GROUP)));
		register("ceruclase_ore", new BlockItem(CERUCLASE_ORE, new Item.Settings().group(Empyrean.GROUP)));
		register("aerrack_bricks", new BlockItem(AERRACK_BRICKS, new Item.Settings().group(Empyrean.GROUP)));
		register("aerrack_brick_stairs", new BlockItem(AERRACK_BRICK_STAIRS, new Item.Settings().group(Empyrean.GROUP)));
		register("aerrack_brick_slab", new BlockItem(AERRACK_BRICK_SLAB, new Item.Settings().group(Empyrean.GROUP)));
		register("chiseled_aerrack", new BlockItem(CHISELED_AERRACK, new Item.Settings().group(Empyrean.GROUP)));
		register("inlaid_aerrack_bricks", new BlockItem(INLAID_AERRACK_BRICKS, new Item.Settings().group(Empyrean.GROUP)));
		register("ceruclase_block", new BlockItem(CERUCLASE_BLOCK, new Item.Settings().group(Empyrean.GROUP)));
		register("sky_grass", new BlockItem(SKY_GRASS, new Item.Settings().group(Empyrean.GROUP)));
		register("sky_silt", new BlockItem(SKY_SILT, new Item.Settings().group(Empyrean.GROUP)));
		register("sky_farmland", new BlockItem(SKY_FARMLAND, new Item.Settings().group(Empyrean.GROUP)));
		register("skyroot_log", new BlockItem(SKYROOT_LOG, new Item.Settings().group(Empyrean.GROUP)));
		register("skyroot_leaves", new BlockItem(SKYROOT_LEAVES, new Item.Settings().group(Empyrean.GROUP)));
		register("silver_skyroot_leaves", new BlockItem(SILVER_SKYROOT_LEAVES, new Item.Settings().group(Empyrean.GROUP)));
		register("dwarf_skyroot_leaves", new BlockItem(DWARF_SKYROOT_LEAVES, new Item.Settings().group(Empyrean.GROUP)));
		register("skyroot_sapling", new BlockItem(SKYROOT_SAPLING, new Item.Settings().group(Empyrean.GROUP)));
		register("silver_skyroot_sapling", new BlockItem(SILVER_SKYROOT_SAPLING, new Item.Settings().group(Empyrean.GROUP)));
		register("dwarf_skyroot_sapling", new BlockItem(DWARF_SKYROOT_SAPLING, new Item.Settings().group(Empyrean.GROUP)));
		register("skyroot_planks", new BlockItem(SKYROOT_PLANKS, new Item.Settings().group(Empyrean.GROUP)));
		register("skyroot_stairs", new BlockItem(SKYROOT_STAIRS, new Item.Settings().group(Empyrean.GROUP)));
		register("skyroot_slab", new BlockItem(SKYROOT_SLAB, new Item.Settings().group(Empyrean.GROUP)));
	}

	private static void register(String name, Block block) {
		Registry.register(Registry.BLOCK, Empyrean.makeID(name), block);
	}

	private static void register(String name, Item item) {
		Registry.register(Registry.ITEM, Empyrean.makeID(name), item);
	}
}
