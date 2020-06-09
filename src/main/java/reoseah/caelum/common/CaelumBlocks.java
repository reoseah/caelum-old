package reoseah.caelum.common;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.mixins.StairsBlockInvoker;

public class CaelumBlocks {
	public static final Block AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(3F, 9F).breakByTool(FabricToolTags.PICKAXES));
	public static final Block AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(2F, 9F).breakByTool(FabricToolTags.PICKAXES));
	public static final Block AERRACK_BRICK_STAIRS = StairsBlockInvoker.create(AERRACK_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block AERRACK_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));
	public static final Block AERRACK_PILLAR = new PillarBlock(FabricBlockSettings.copyOf(AERRACK_BRICKS));

	public static void register() {
		Registry.register(Registry.BLOCK, "caelum:aerrack", AERRACK);
		Registry.register(Registry.BLOCK, "caelum:aerrack_bricks", AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_stairs", AERRACK_BRICK_STAIRS);
		Registry.register(Registry.BLOCK, "caelum:aerrack_brick_slab", AERRACK_BRICK_SLAB);
		Registry.register(Registry.BLOCK, "caelum:aerrack_pillar", AERRACK_PILLAR);
	}
}
