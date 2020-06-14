package reoseah.caelum.common;

import com.google.common.collect.Sets;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.common.blocks.CaelumFarmlandBlock;
import reoseah.caelum.common.blocks.CaelumGrassBlock;
import reoseah.caelum.common.blocks.CeruclaseLampBlock;
import reoseah.caelum.common.blocks.CeruclaseOreBlock;
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
	public static final Block CAELUM_GRASS = new CaelumGrassBlock(FabricBlockSettings.of(Material.SOIL).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.SHOVELS));
	public static final Block CAELUM_FARMLAND = new CaelumFarmlandBlock(FabricBlockSettings.of(Material.SOIL).ticksRandomly().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL).breakByTool(FabricToolTags.SHOVELS));

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
		Registry.register(Registry.BLOCK, "caelum:caelum_grass", CAELUM_GRASS);
		Registry.register(Registry.BLOCK, "caelum:caelum_farmland", CAELUM_FARMLAND);

		Registry.register(Registry.BLOCK_ENTITY_TYPE, "caelum:glow_in_dark", GLOW_IN_DARK_ENTITY);
	}
}
