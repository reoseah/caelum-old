package reoseah.caelum.common;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.common.blocks.BarleyBlock;
import reoseah.caelum.common.blocks.BlossomingCaelumSproutsBlock;
import reoseah.caelum.common.blocks.CaelumFarmlandBlock;
import reoseah.caelum.common.blocks.CaelumFlowerBlock;
import reoseah.caelum.common.blocks.CaelumGrassBlock;
import reoseah.caelum.common.blocks.CaelumSproutsBlock;
import reoseah.caelum.common.blocks.ModCraftingTableBlock;
import reoseah.caelum.common.blocks.ModDoorBlock;
import reoseah.caelum.common.blocks.ModStairsBlock;
import reoseah.caelum.common.blocks.ModTrapdoorBlock;
import reoseah.caelum.common.blocks.SealstoneBlock;
import reoseah.caelum.common.blocks.SkyrootSaplingBlock;
import reoseah.caelum.common.blocks.SkyrootSaplingGenerator;

public class CaelumBlocks {
	public static final Block AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE));
	public static final Block CERUCLASE_ORE = new Block(FabricBlockSettings.of(Material.STONE).lightLevel(7).nonOpaque().strength(5.0F, 9.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE));
	public static final Block AETHERERUM_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(5.0F, 10.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE));

	public static final Block AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0F, 10.0F).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES, 0));
	public static final Block AERRACK_BRICK_STAIRS = new ModStairsBlock(AERRACK_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block AERRACK_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block AERRACK_PILLAR = new PillarBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block AERRACK_LIGHTSTONE = new Block(FabricBlockSettings.copyOf(AERRACK_BRICKS).lightLevel(13));
	public static final Block CERUCLASE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.CYAN).lightLevel(15).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).breakByTool(FabricToolTags.PICKAXES, 0));

	public static final Block CAELUM_GRASS = new CaelumGrassBlock(FabricBlockSettings.of(Material.ORGANIC).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.SHOVELS));
	public static final Block CAELUM_DIRT = new Block(FabricBlockSettings.of(Material.EARTH).strength(0.5F, 0.5F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS));
	public static final Block CAELUM_FARMLAND = new CaelumFarmlandBlock(FabricBlockSettings.of(Material.EARTH).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS));

	public static final Block SKYROOT_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.copyOf(Blocks.OAK_LOG));
	public static final Block STRIPPED_SKYROOT_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG));

	public static final Block SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
	public static final Block SILVER_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
	public static final Block DWARF_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));

	private static final SkyrootSaplingGenerator SAPLING_GENERATOR = new SkyrootSaplingGenerator();
	public static final Block SKYROOT_SAPLING = new SkyrootSaplingBlock(SAPLING_GENERATOR, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block SILVER_SKYROOT_SAPLING = new SkyrootSaplingBlock(SAPLING_GENERATOR, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block DWARF_SKYROOT_SAPLING = new SkyrootSaplingBlock(SAPLING_GENERATOR, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block STUNTED_SKYROOT_SAPLING = new SkyrootSaplingBlock(SAPLING_GENERATOR, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block STUNTED_SILVER_SKYROOT_SAPLING = new SkyrootSaplingBlock(SAPLING_GENERATOR, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));

	public static final Block CAELUM_SPROUTS = new CaelumSproutsBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block BLOSSOMING_CAELUM_SPROUTS = new BlossomingCaelumSproutsBlock(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block SKY_BLUE_FLOWER = new CaelumFlowerBlock(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

	public static final Block SKYROOT_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES, 0));
	public static final Block SKYROOT_STAIRS = new ModStairsBlock(SKYROOT_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(SKYROOT_PLANKS));
	public static final Block SKYROOT_SLAB = new SlabBlock(FabricBlockSettings.copyOf(SKYROOT_PLANKS));

	public static final Block SKYROOT_CRAFTING_TABLE = new ModCraftingTableBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES, 0));
	public static final Block CELESTIAL_ALTAR = new ModCraftingTableBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));

	public static final Block MOSSY_AERRACK = new Block(FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block MOSSY_AERRACK_BRICKS = new Block(FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block MOSSY_AERRACK_PILLAR = new PillarBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));

	public static final Block SEALSTONE = new SealstoneBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS).lightLevel(8).resistance(15F));
	public static final Block INERT_SEALSTONE = new Block(FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block MOSSY_SEALSTONE = new SealstoneBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS).lightLevel(8).resistance(15F));
	public static final Block INERT_MOSSY_SEALSTONE = new Block(FabricBlockSettings.copyOf(AERRACK_BRICKS));

	public static final Block BARLEY = new BarleyBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

	public static final Block SKYROOT_DOOR = new ModDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block SKYROOT_TRAPDOOR = new ModTrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(3.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque());

	public static final Tag<Block> SEALSTONE_PROTECTED_BLOCKS = TagRegistry.block(new Identifier("caelum:sealstone_protected_blocks"));

	public static void register() {
		Registry.register(Registry.BLOCK, "caelum:aerrack", AERRACK);
		Registry.register(Registry.BLOCK, "caelum:ceruclase_ore", CERUCLASE_ORE);
		Registry.register(Registry.BLOCK, "caelum:aethererum_ore", AETHERERUM_ORE);

		Registry.register(Registry.BLOCK, "caelum:aerrack_bricks", AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_stairs", AERRACK_BRICK_STAIRS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_slab", AERRACK_BRICK_SLAB);
		Registry.register(Registry.BLOCK, "caelum:aerrack_pillar", AERRACK_PILLAR);
		Registry.register(Registry.BLOCK, "caelum:aerrack_lightstone", AERRACK_LIGHTSTONE);
		Registry.register(Registry.BLOCK, "caelum:ceruclase_block", CERUCLASE_BLOCK);
		Registry.register(Registry.BLOCK, "caelum:inert_sealstone", INERT_SEALSTONE);
		Registry.register(Registry.BLOCK, "caelum:mossy_aerrack", MOSSY_AERRACK);
		Registry.register(Registry.BLOCK, "caelum:mossy_aerrack_bricks", MOSSY_AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, "caelum:mossy_aerrack_pillar", MOSSY_AERRACK_PILLAR);
		Registry.register(Registry.BLOCK, "caelum:mossy_inert_sealstone", INERT_MOSSY_SEALSTONE);

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
		Registry.register(Registry.BLOCK, "caelum:stunted_skyroot_sapling", STUNTED_SKYROOT_SAPLING);
		Registry.register(Registry.BLOCK, "caelum:stunted_silver_skyroot_sapling", STUNTED_SILVER_SKYROOT_SAPLING);

		Registry.register(Registry.BLOCK, "caelum:caelum_sprouts", CAELUM_SPROUTS);
		Registry.register(Registry.BLOCK, "caelum:blossoming_caelum_sprouts", BLOSSOMING_CAELUM_SPROUTS);
		Registry.register(Registry.BLOCK, "caelum:sky_blue_flower", SKY_BLUE_FLOWER);

		Registry.register(Registry.BLOCK, "caelum:skyroot_planks", SKYROOT_PLANKS);
		Registry.register(Registry.BLOCK, "caelum:skyroot_stairs", SKYROOT_STAIRS);
		Registry.register(Registry.BLOCK, "caelum:skyroot_slab", SKYROOT_SLAB);

		Registry.register(Registry.BLOCK, "caelum:skyroot_crafting_table", SKYROOT_CRAFTING_TABLE);
		Registry.register(Registry.BLOCK, "caelum:celestial_altar", CELESTIAL_ALTAR);
		Registry.register(Registry.BLOCK, "caelum:sealstone", SEALSTONE);
		Registry.register(Registry.BLOCK, "caelum:mossy_sealstone", MOSSY_SEALSTONE);

		Registry.register(Registry.BLOCK, "caelum:barley", BARLEY);

		Registry.register(Registry.BLOCK, "caelum:skyroot_door", SKYROOT_DOOR);
		Registry.register(Registry.BLOCK, "caelum:skyroot_trapdoor", SKYROOT_TRAPDOOR);
	}
}
