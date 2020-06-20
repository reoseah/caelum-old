package reoseah.caelum.common;

import com.google.common.collect.Sets;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.common.blocks.CaelumFarmlandBlock;
import reoseah.caelum.common.blocks.CaelumGrassBlock;
import reoseah.caelum.common.blocks.CeruclaseLampBlock;
import reoseah.caelum.common.blocks.CeruclaseOreBlock;
import reoseah.caelum.common.blocks.SkyrootSaplingBlock;
import reoseah.caelum.common.blocks.SkyrootSaplingGenerator;
import reoseah.caelum.common.blocks.entity.GlowInDarkBlockEntity;
import reoseah.caelum.mixins.StairsBlockInvoker;

public class CaelumBlocks {
	public static final Block AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(3F, 9F).breakByTool(FabricToolTags.PICKAXES));
	public static final Block AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(2F, 9F).breakByTool(FabricToolTags.PICKAXES));
	public static final Block AERRACK_BRICK_STAIRS = StairsBlockInvoker.create(AERRACK_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block AERRACK_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block AERRACK_PILLAR = new PillarBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));

	public static final Block CERUCLASE_ORE = new CeruclaseOreBlock(FabricBlockSettings.of(Material.STONE).strength(4F, 10F).breakByTool(FabricToolTags.PICKAXES, 1));
	public static final Block CERUCLASE_LAMP = new CeruclaseLampBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS).lightLevel(13).nonOpaque());
	public static final Block CERUCLASE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.CYAN).lightLevel(15).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));

	public static final Block CAELUM_DIRT = new Block(FabricBlockSettings.of(Material.SOIL).strength(0.5F, 0.5F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS));
	public static final Block CAELUM_GRASS_BLOCK = new CaelumGrassBlock(FabricBlockSettings.of(Material.SOIL).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.SHOVELS));
	public static final Block CAELUM_FARMLAND = new CaelumFarmlandBlock(FabricBlockSettings.of(Material.SOIL).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS));

	public static final Block SKYROOT_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).breakByTool(FabricToolTags.AXES));
	public static final Block STRIPPED_SKYROOT_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG).breakByTool(FabricToolTags.AXES));
	public static final Block SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
	public static final Block SILVER_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
	public static final Block DWARF_SKYROOT_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES));
	public static final Block SKYROOT_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
	public static final Block SKYROOT_STAIRS = StairsBlockInvoker.create(SKYROOT_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS));
	public static final Block SKYROOT_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB));

	private static final SaplingGenerator SAPLING_GENERATOR = new SkyrootSaplingGenerator();
	public static final Block SKYROOT_SAPLING = new SkyrootSaplingBlock(SAPLING_GENERATOR, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block SILVER_SKYROOT_SAPLING = new SkyrootSaplingBlock(SAPLING_GENERATOR, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block DWARF_SKYROOT_SAPLING = new SkyrootSaplingBlock(SAPLING_GENERATOR, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));

	public static final BlockEntityType<GlowInDarkBlockEntity> GLOW_IN_DARK_ENTITY = new BlockEntityType<>(GlowInDarkBlockEntity::new, Sets.newHashSet(CERUCLASE_LAMP), null);

	public static void register() {
		Registry.register(Registry.BLOCK, "caelum:aerrack", AERRACK);
		Registry.register(Registry.BLOCK, "caelum:aerrack_bricks", AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_stairs", AERRACK_BRICK_STAIRS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_slab", AERRACK_BRICK_SLAB);
		Registry.register(Registry.BLOCK, "caelum:aerrack_pillar", AERRACK_PILLAR);

		Registry.register(Registry.BLOCK, "caelum:ceruclase_ore", CERUCLASE_ORE);
		Registry.register(Registry.BLOCK, "caelum:ceruclase_lamp", CERUCLASE_LAMP);
		Registry.register(Registry.BLOCK, "caelum:ceruclase_block", CERUCLASE_BLOCK);

		Registry.register(Registry.BLOCK, "caelum:caelum_dirt", CAELUM_DIRT);
		Registry.register(Registry.BLOCK, "caelum:caelum_grass_block", CAELUM_GRASS_BLOCK);
		Registry.register(Registry.BLOCK, "caelum:caelum_farmland", CAELUM_FARMLAND);

		Registry.register(Registry.BLOCK, "caelum:skyroot_log", SKYROOT_LOG);
		Registry.register(Registry.BLOCK, "caelum:stripped_skyroot_log", STRIPPED_SKYROOT_LOG);
		Registry.register(Registry.BLOCK, "caelum:skyroot_leaves", SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, "caelum:silver_skyroot_leaves", SILVER_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, "caelum:dwarf_skyroot_leaves", DWARF_SKYROOT_LEAVES);
		Registry.register(Registry.BLOCK, "caelum:skyroot_sapling", SKYROOT_SAPLING);
		Registry.register(Registry.BLOCK, "caelum:silver_skyroot_sapling", SILVER_SKYROOT_SAPLING);
		Registry.register(Registry.BLOCK, "caelum:dwarf_skyroot_sapling", DWARF_SKYROOT_SAPLING);
		Registry.register(Registry.BLOCK, "caelum:skyroot_planks", SKYROOT_PLANKS);
		Registry.register(Registry.BLOCK, "caelum:skyroot_stairs", SKYROOT_STAIRS);
		Registry.register(Registry.BLOCK, "caelum:skyroot_slab", SKYROOT_SLAB);

		Registry.register(Registry.BLOCK_ENTITY_TYPE, "caelum:glow_in_dark", GLOW_IN_DARK_ENTITY);
	}
}
