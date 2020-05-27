package reoseah.caelum.common;

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
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.common.blocks.*;
import reoseah.caelum.common.tab.CreativeTab;

public class CaelumBlocks {

	// Natural //
	public static final Block AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block CERUCLASE_ORE = new Block(FabricBlockSettings.of(Material.STONE).lightLevel(7).nonOpaque().strength(5.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());


	public static final Block AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 10.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block AERRACK_BRICK_STAIRS = new ModStairsBlock(AERRACK_BRICKS.getDefaultState(), FabricBlockSettings.copy(AERRACK_BRICKS).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block AERRACK_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(AERRACK_BRICKS).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block AERRACK_PILLAR = new PillarBlock(FabricBlockSettings.copy(AERRACK_BRICKS).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block AERRACK_LIGHTSTONE = new Block(FabricBlockSettings.of(Material.STONE).lightLevel(13).strength(4.0F, 10.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block CERUCLASE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.CYAN).lightLevel(15).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 0).build());

	public static final Block CAELUM_GRASS = new CaelumGrassBlock(FabricBlockSettings.of(Material.ORGANIC).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.SHOVELS).build());
	public static final Block CAELUM_DIRT = new Block(FabricBlockSettings.of(Material.EARTH).strength(0.5F, 0.5F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS).build());
	public static final Block CAELUM_FARMLAND = new CaelumFarmlandBlock(FabricBlockSettings.of(Material.EARTH).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS).build());

	public static final Block SKYROOT_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.copy(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES).build());
	public static final Block STRIPPED_SKYROOT_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES).build());

	public static final Block SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).build());
	public static final Block SILVER_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).build());
	public static final Block DWARF_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).build());
	public static final Block SKYROOT_SAPLING = new SkyrootSaplingBlock(new SkyrootSaplingGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
	public static final Block SILVER_SKYROOT_SAPLING = new SkyrootSaplingBlock(new SkyrootSaplingGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
	public static final Block DWARF_SKYROOT_SAPLING = new SkyrootSaplingBlock(new SkyrootSaplingGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());

	public static final Block CAELUM_SPROUTS = new CaelumSproutsBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
	public static final Block BLOSSOMING_CAELUM_SPROUTS = new BlossomingCaelumSproutsBlock(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
	public static final Block SKY_BLUE_FLOWER = new CaelumFlowerBlock(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).build());

	public static final Block SKYROOT_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES, 0).build());
	public static final Block SKYROOT_STAIRS = new ModStairsBlock(SKYROOT_PLANKS.getDefaultState(), FabricBlockSettings.copy(SKYROOT_PLANKS).breakByTool(FabricToolTags.AXES, 0).build());
	public static final Block SKYROOT_SLAB = new SlabBlock(FabricBlockSettings.copy(SKYROOT_PLANKS).breakByTool(FabricToolTags.AXES, 0).build());
	public static final Block SKYROOT_DOOR = registerBlock("skyroot_door", new ModDoorBlock(SKYROOT_PLANKS));

	public static final Block CAELUM_CRAFTING_TABLE = new ModCraftingTableBlock(FabricBlockSettings.of(Material.STONE).strength(4.0F, 10.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block CELESTIAL_ALTAR = new ModCraftingTableBlock(FabricBlockSettings.of(Material.STONE).strength(4.0F, 10.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).build());

	public static final Block MOSSY_AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block MOSSY_AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 10.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0).build());
	public static final Block MOSSY_AERRACK_PILLAR = new PillarBlock(FabricBlockSettings.copy(AERRACK_BRICKS).breakByTool(FabricToolTags.PICKAXES, 0).build());

	public static final Block SEALSTONE = new SealstoneBlock(FabricBlockSettings.of(Material.STONE).lightLevel(8).strength(4.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block INERT_SEALSTONE = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block MOSSY_SEALSTONE = new SealstoneBlock(FabricBlockSettings.of(Material.STONE).lightLevel(8).strength(4.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block INERT_MOSSY_SEALSTONE = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());

	public static final Block BARLEY = new BarleyBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).build());

	private static Block registerBlock(String name, Block block)
	{
		Registry.register(Registry.BLOCK, new Identifier(Caelum.MOD_ID, name), block);
		CaelumItems.registerItem(name, new BlockItem(block, new Item.Settings().group(CreativeTab.CAELUM_TAB)));

		return block;
	}

	private static Block registerBlockNI(String name, Block block)
	{
		Registry.register(Registry.BLOCK, new Identifier(Caelum.MOD_ID, name), block);

		return block;
	}

	public static void register() {
		Registry.register(Registry.BLOCK, "caelum:aerrack", AERRACK);
		Registry.register(Registry.BLOCK, "caelum:ceruclase_ore", CERUCLASE_ORE);

		Registry.register(Registry.BLOCK, "caelum:aerrack_bricks", AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_stairs", AERRACK_BRICK_STAIRS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_slab", AERRACK_BRICK_SLAB);
		Registry.register(Registry.BLOCK, "caelum:aerrack_pillar", AERRACK_PILLAR);
		Registry.register(Registry.BLOCK, "caelum:aerrack_lightstone", AERRACK_LIGHTSTONE);
		Registry.register(Registry.BLOCK, "caelum:ceruclase_block", CERUCLASE_BLOCK);

		Registry.register(Registry.BLOCK, "caelum:caelum_grass", CAELUM_GRASS);
		Registry.register(Registry.BLOCK, "caelum:caelum_dirt", CAELUM_DIRT);
		Registry.register(Registry.BLOCK, "caelum:caelum_farmland", CAELUM_FARMLAND);

		Registry.register(Registry.BLOCK, "caelum:skyroot_log", SKYROOT_LOG);
		Registry.register(Registry.BLOCK, "caelum:stripped_skyroot_log", STRIPPED_SKYROOT_LOG);
		Registry.register(Registry.BLOCK, "caelum:skyroot_leaves", SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, "caelum:silver_skyroot_leaves", SILVER_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, "caelum:dwarf_skyroot_leaves", DWARF_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, "caelum:skyroot_sapling", SKYROOT_SAPLING);
		Registry.register(Registry.BLOCK, "caelum:silver_skyroot_sapling", SILVER_SKYROOT_SAPLING);
		Registry.register(Registry.BLOCK, "caelum:dwarf_skyroot_sapling", DWARF_SKYROOT_SAPLING);

		Registry.register(Registry.BLOCK, "caelum:caelum_sprouts", CAELUM_SPROUTS);
		Registry.register(Registry.BLOCK, "caelum:blossoming_caelum_sprouts", BLOSSOMING_CAELUM_SPROUTS);
		Registry.register(Registry.BLOCK, "caelum:sky_blue_flower", SKY_BLUE_FLOWER);

		Registry.register(Registry.BLOCK, "caelum:skyroot_planks", SKYROOT_PLANKS);
		Registry.register(Registry.BLOCK, "caelum:skyroot_stairs", SKYROOT_STAIRS);
		Registry.register(Registry.BLOCK, "caelum:skyroot_slab", SKYROOT_SLAB);

		Registry.register(Registry.BLOCK, "caelum:caelum_crafting_table", CAELUM_CRAFTING_TABLE);
		Registry.register(Registry.BLOCK, "caelum:celestial_altar", CELESTIAL_ALTAR);

		Registry.register(Registry.BLOCK, "caelum:mossy_aerrack", MOSSY_AERRACK);
		Registry.register(Registry.BLOCK, "caelum:mossy_aerrack_bricks", MOSSY_AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, "caelum:mossy_aerrack_pillar", MOSSY_AERRACK_PILLAR);

		Registry.register(Registry.BLOCK, "caelum:sealstone", SEALSTONE);
		Registry.register(Registry.BLOCK, "caelum:inert_sealstone", INERT_SEALSTONE);
		Registry.register(Registry.BLOCK, "caelum:mossy_sealstone", MOSSY_SEALSTONE);
		Registry.register(Registry.BLOCK, "caelum:inert_mossy_sealstone", INERT_MOSSY_SEALSTONE);

		Registry.register(Registry.BLOCK, "caelum:barley", BARLEY);

		Registry.register(Registry.ITEM, "caelum:aerrack", new BlockItem(AERRACK, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:ceruclase_ore", new BlockItem(CERUCLASE_ORE, new Item.Settings().group(Caelum.GROUP)));

		Registry.register(Registry.ITEM, "caelum:aerrack_bricks", new BlockItem(AERRACK_BRICKS, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:aerrack_brick_stairs", new BlockItem(AERRACK_BRICK_STAIRS, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:aerrack_brick_slab", new BlockItem(AERRACK_BRICK_SLAB, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:aerrack_pillar", new BlockItem(AERRACK_PILLAR, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:aerrack_lightstone", new BlockItem(AERRACK_LIGHTSTONE, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:ceruclase_block", new BlockItem(CERUCLASE_BLOCK, new Item.Settings().group(Caelum.GROUP)));

		Registry.register(Registry.ITEM, "caelum:caelum_grass", new BlockItem(CAELUM_GRASS, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:caelum_dirt", new BlockItem(CAELUM_DIRT, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:caelum_farmland", new BlockItem(CAELUM_FARMLAND, new Item.Settings().group(Caelum.GROUP)));

		Registry.register(Registry.ITEM, "caelum:skyroot_log", new BlockItem(SKYROOT_LOG, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:stripped_skyroot_log", new BlockItem(STRIPPED_SKYROOT_LOG, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:skyroot_leaves", new BlockItem(SKYROOT_LEAVES, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:silver_skyroot_leaves", new BlockItem(SILVER_SKYROOT_LEAVES, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:dwarf_skyroot_leaves", new BlockItem(DWARF_SKYROOT_LEAVES, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:skyroot_sapling", new BlockItem(SKYROOT_SAPLING, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:silver_skyroot_sapling", new BlockItem(SILVER_SKYROOT_SAPLING, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:dwarf_skyroot_sapling", new BlockItem(DWARF_SKYROOT_SAPLING, new Item.Settings().group(Caelum.GROUP)));

		Registry.register(Registry.ITEM, "caelum:caelum_sprouts", new BlockItem(CAELUM_SPROUTS, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:blossoming_caelum_sprouts", new BlockItem(BLOSSOMING_CAELUM_SPROUTS, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:sky_blue_flower", new BlockItem(SKY_BLUE_FLOWER, new Item.Settings().group(Caelum.GROUP)));

		Registry.register(Registry.ITEM, "caelum:skyroot_planks", new BlockItem(SKYROOT_PLANKS, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:skyroot_stairs", new BlockItem(SKYROOT_STAIRS, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:skyroot_slab", new BlockItem(SKYROOT_SLAB, new Item.Settings().group(Caelum.GROUP)));

		Registry.register(Registry.ITEM, "caelum:caelum_crafting_table", new BlockItem(CAELUM_CRAFTING_TABLE, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:celestial_altar", new BlockItem(CELESTIAL_ALTAR, new Item.Settings().group(Caelum.GROUP)));

		Registry.register(Registry.ITEM, "caelum:mossy_aerrack", new BlockItem(MOSSY_AERRACK, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:mossy_aerrack_bricks", new BlockItem(MOSSY_AERRACK_BRICKS, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:mossy_aerrack_pillar", new BlockItem(MOSSY_AERRACK_PILLAR, new Item.Settings().group(Caelum.GROUP)));

		Registry.register(Registry.ITEM, "caelum:sealstone", new BlockItem(SEALSTONE, new Item.Settings().group(null)));
		Registry.register(Registry.ITEM, "caelum:inert_sealstone", new BlockItem(INERT_SEALSTONE, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:mossy_sealstone", new BlockItem(MOSSY_SEALSTONE, new Item.Settings().group(null)));
		Registry.register(Registry.ITEM, "caelum:inert_mossy_sealstone", new BlockItem(INERT_MOSSY_SEALSTONE, new Item.Settings().group(Caelum.GROUP)));
	}
}
