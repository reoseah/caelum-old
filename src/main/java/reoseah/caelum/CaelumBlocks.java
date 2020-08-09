package reoseah.caelum;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.blocks.CaelumBlossomingGrassBlock;
import reoseah.caelum.blocks.CaelumCropBlock;
import reoseah.caelum.blocks.CaelumFarmlandBlock;
import reoseah.caelum.blocks.CaelumGrassBlock;
import reoseah.caelum.blocks.CaelumTallGrassBlock;
import reoseah.caelum.blocks.CeruclaseOreBlock;
import reoseah.caelum.blocks.SkyrootSaplingBlock;
import reoseah.caelum.blocks.sapling_generators.CommonSkyrootGenerator;
import reoseah.caelum.blocks.sapling_generators.DwarfSkyrootGenerator;
import reoseah.caelum.blocks.sapling_generators.SilverSkyrootGenerator;
import reoseah.caelum.mixins.CraftingTableBlockInvoker;
import reoseah.caelum.mixins.FireBlockInvoker;
import reoseah.caelum.mixins.HoeItemInvoker;
import reoseah.caelum.mixins.StairsBlockInvoker;

public class CaelumBlocks {
	public static final Block AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(3F, 9F).breakByTool(FabricToolTags.PICKAXES));
	public static final Block AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(2F, 9F).breakByTool(FabricToolTags.PICKAXES));
	public static final Block AERRACK_BRICK_STAIRS = StairsBlockInvoker.create(AERRACK_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block AERRACK_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block AERRACK_PILLAR = new PillarBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));

	public static final Block SKYGLASS_ORE = new CeruclaseOreBlock(FabricBlockSettings.of(Material.STONE).strength(4F, 10F).breakByTool(FabricToolTags.PICKAXES, 1));

	public static final Block CAELUM_DIRT = new Block(FabricBlockSettings.of(Material.SOIL).strength(0.5F, 0.5F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS));
	public static final Block CAELUM_GRASS_BLOCK = new CaelumGrassBlock(FabricBlockSettings.of(Material.SOIL).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.SHOVELS));
	public static final Block CAELUM_FARMLAND = new CaelumFarmlandBlock(FabricBlockSettings.of(Material.SOIL).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS));

	public static final Block SKYROOT_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES));
	public static final Block SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
	public static final Block SILVER_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
	public static final Block DWARF_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));

	public static final Block STRIPPED_SKYROOT_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG).breakByTool(FabricToolTags.AXES));
	public static final Block SKYROOT_SAPLING = new SkyrootSaplingBlock(new CommonSkyrootGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block SILVER_SKYROOT_SAPLING = new SkyrootSaplingBlock(new SilverSkyrootGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block DWARF_SKYROOT_SAPLING = new SkyrootSaplingBlock(new DwarfSkyrootGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));

	public static final Block CAELUM_GRASS = new CaelumTallGrassBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block BLOSSOMING_CAELUM_GRASS = new CaelumBlossomingGrassBlock(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

	public static final Block SKYROOT_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
	public static final Block SKYROOT_STAIRS = StairsBlockInvoker.create(SKYROOT_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS));
	public static final Block SKYROOT_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB));
	public static final Block SKYROOT_CRAFTING_TABLE = CraftingTableBlockInvoker.create(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES, 0));

	public static final Block BARLEY = new CaelumCropBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));

	public static final Tag<Block> SOILS = TagRegistry.block(new Identifier("caelum:soils"));

	public static void register() {
		Registry.register(Registry.BLOCK, "caelum:aerrack", AERRACK);
		Registry.register(Registry.BLOCK, "caelum:aerrack_bricks", AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_stairs", AERRACK_BRICK_STAIRS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_slab", AERRACK_BRICK_SLAB);
		Registry.register(Registry.BLOCK, "caelum:aerrack_pillar", AERRACK_PILLAR);

		Registry.register(Registry.BLOCK, "caelum:skyglass_ore", SKYGLASS_ORE);

		Registry.register(Registry.BLOCK, "caelum:caelum_dirt", CAELUM_DIRT);
		Registry.register(Registry.BLOCK, "caelum:caelum_grass_block", CAELUM_GRASS_BLOCK);
		Registry.register(Registry.BLOCK, "caelum:caelum_farmland", CAELUM_FARMLAND);

		Registry.register(Registry.BLOCK, "caelum:skyroot_log", SKYROOT_LOG);
		Registry.register(Registry.BLOCK, "caelum:skyroot_leaves", SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, "caelum:silver_skyroot_leaves", SILVER_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, "caelum:dwarf_skyroot_leaves", DWARF_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, "caelum:skyroot_sapling", SKYROOT_SAPLING);
		Registry.register(Registry.BLOCK, "caelum:silver_skyroot_sapling", SILVER_SKYROOT_SAPLING);
		Registry.register(Registry.BLOCK, "caelum:dwarf_skyroot_sapling", DWARF_SKYROOT_SAPLING);

		Registry.register(Registry.BLOCK, "caelum:caelum_grass", CAELUM_GRASS);
		Registry.register(Registry.BLOCK, "caelum:blossoming_caelum_grass", BLOSSOMING_CAELUM_GRASS);

		Registry.register(Registry.BLOCK, "caelum:stripped_skyroot_log", STRIPPED_SKYROOT_LOG);
		Registry.register(Registry.BLOCK, "caelum:skyroot_planks", SKYROOT_PLANKS);
		Registry.register(Registry.BLOCK, "caelum:skyroot_stairs", SKYROOT_STAIRS);
		Registry.register(Registry.BLOCK, "caelum:skyroot_slab", SKYROOT_SLAB);
		Registry.register(Registry.BLOCK, "caelum:skyroot_crafting_table", SKYROOT_CRAFTING_TABLE);

		Registry.register(Registry.BLOCK, "caelum:barley", BARLEY);

		FireBlockInvoker fire = (FireBlockInvoker) Blocks.FIRE;
		fire.callRegisterFlammableBlock(SKYROOT_LOG, 5, 5);
		fire.callRegisterFlammableBlock(SKYROOT_LEAVES, 30, 60);
		fire.callRegisterFlammableBlock(SILVER_SKYROOT_LEAVES, 30, 60);
		fire.callRegisterFlammableBlock(DWARF_SKYROOT_LEAVES, 30, 60);
		fire.callRegisterFlammableBlock(STRIPPED_SKYROOT_LOG, 5, 5);
		fire.callRegisterFlammableBlock(SKYROOT_PLANKS, 5, 20);
		fire.callRegisterFlammableBlock(SKYROOT_SLAB, 5, 20);
		fire.callRegisterFlammableBlock(SKYROOT_STAIRS, 5, 20);
		fire.callRegisterFlammableBlock(SKYROOT_CRAFTING_TABLE, 5, 20);

		HoeItemInvoker.getTilledBlocks().put(CAELUM_DIRT, CAELUM_FARMLAND.getDefaultState());
		HoeItemInvoker.getTilledBlocks().put(CAELUM_GRASS_BLOCK, CAELUM_FARMLAND.getDefaultState());
	}
}
