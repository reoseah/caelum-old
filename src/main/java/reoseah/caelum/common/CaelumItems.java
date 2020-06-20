package reoseah.caelum.common;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CaelumItems {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier("caelum", "main"), () -> new ItemStack(CaelumBlocks.AERRACK));

	public static final Item CERUCLASE = new Item(itemSettings());

	public static void register() {
		Registry.register(Registry.ITEM, "caelum:aerrack", new BlockItem(CaelumBlocks.AERRACK, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:aerrack_bricks", new BlockItem(CaelumBlocks.AERRACK_BRICKS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:aerrack_brick_stairs", new BlockItem(CaelumBlocks.AERRACK_BRICK_STAIRS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:aerrack_brick_slab", new BlockItem(CaelumBlocks.AERRACK_BRICK_SLAB, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:aerrack_pillar", new BlockItem(CaelumBlocks.AERRACK_PILLAR, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:ceruclase_ore", new BlockItem(CaelumBlocks.CERUCLASE_ORE, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:ceruclase_lamp", new BlockItem(CaelumBlocks.CERUCLASE_LAMP, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:ceruclase_block", new BlockItem(CaelumBlocks.CERUCLASE_BLOCK, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:caelum_dirt", new BlockItem(CaelumBlocks.CAELUM_DIRT, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:caelum_grass", new BlockItem(CaelumBlocks.CAELUM_GRASS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:caelum_farmland", new BlockItem(CaelumBlocks.CAELUM_FARMLAND, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:skyroot_log", new BlockItem(CaelumBlocks.SKYROOT_LOG, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:stripped_skyroot_log", new BlockItem(CaelumBlocks.STRIPPED_SKYROOT_LOG, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_leaves", new BlockItem(CaelumBlocks.SKYROOT_LEAVES, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:silver_skyroot_leaves", new BlockItem(CaelumBlocks.SILVER_SKYROOT_LEAVES, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:dwarf_skyroot_leaves", new BlockItem(CaelumBlocks.DWARF_SKYROOT_LEAVES, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_sapling", new BlockItem(CaelumBlocks.SKYROOT_SAPLING, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:silver_skyroot_sapling", new BlockItem(CaelumBlocks.SILVER_SKYROOT_SAPLING, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:dwarf_skyroot_sapling", new BlockItem(CaelumBlocks.DWARF_SKYROOT_SAPLING, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_planks", new BlockItem(CaelumBlocks.SKYROOT_PLANKS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_stairs", new BlockItem(CaelumBlocks.SKYROOT_STAIRS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_slab", new BlockItem(CaelumBlocks.SKYROOT_SLAB, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:ceruclase", CERUCLASE);
	}

	private static Item.Settings itemSettings() {
		return new Item.Settings().group(CaelumItems.GROUP);
	}
}
