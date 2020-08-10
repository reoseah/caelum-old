package reoseah.caelum;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.items.CaelumArmorMaterials;
import reoseah.caelum.items.CaelumToolMaterials;
import reoseah.caelum.items.SkyrootBucketItem;
import reoseah.caelum.mixins.AxeItemInvoker;
import reoseah.caelum.mixins.HoeItemInvoker;
import reoseah.caelum.mixins.PickaxeItemInvoker;

public class CaelumItems {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier("caelum", "main"), () -> new ItemStack(CaelumBlocks.AERRACK));

	public static final Item SKYROOT_STICK = new Item(itemSettings());
	public static final Item SKYGLASS = new Item(itemSettings());

	public static final Item SKYROOT_SWORD = new SwordItem(CaelumToolMaterials.SKYROOT, 3, -2.4F, itemSettings());
	public static final Item SKYROOT_SHOVEL = new ShovelItem(CaelumToolMaterials.SKYROOT, 1.5F, -3.0F, itemSettings());
	public static final Item SKYROOT_PICKAXE = PickaxeItemInvoker.create(CaelumToolMaterials.SKYROOT, 1, -2.8F, itemSettings());
	public static final Item SKYROOT_AXE = AxeItemInvoker.create(CaelumToolMaterials.SKYROOT, 6.0F, -3.2F, itemSettings());
	public static final Item SKYROOT_HOE = HoeItemInvoker.create(CaelumToolMaterials.SKYROOT, 0, -3.0F, itemSettings());

	public static final Item AERRACK_SWORD = new SwordItem(CaelumToolMaterials.AERRACK, 3, -2.4F, itemSettings());
	public static final Item AERRACK_SHOVEL = new ShovelItem(CaelumToolMaterials.AERRACK, 1.5F, -3.0F, itemSettings());
	public static final Item AERRACK_PICKAXE = PickaxeItemInvoker.create(CaelumToolMaterials.AERRACK, 1, -2.8F, itemSettings());
	public static final Item AERRACK_AXE = AxeItemInvoker.create(CaelumToolMaterials.AERRACK, 6.0F, -3.2F, itemSettings());
	public static final Item AERRACK_HOE = HoeItemInvoker.create(CaelumToolMaterials.AERRACK, -1, -3.0F, itemSettings());

	public static final Item SKYGLASS_SWORD = new SwordItem(CaelumToolMaterials.SKYGLASS, 3, -2.4F, itemSettings());
	public static final Item SKYGLASS_SHOVEL = new ShovelItem(CaelumToolMaterials.SKYGLASS, 1.5F, -3.0F, itemSettings());
	public static final Item SKYGLASS_PICKAXE = PickaxeItemInvoker.create(CaelumToolMaterials.SKYGLASS, 1, -2.8F, itemSettings());
	public static final Item SKYGLASS_AXE = AxeItemInvoker.create(CaelumToolMaterials.SKYGLASS, 6.0F, -3F, itemSettings());
	public static final Item SKYGLASS_HOE = HoeItemInvoker.create(CaelumToolMaterials.SKYGLASS, -2, -3.0F, itemSettings());

	public static final Item SKYGLASS_HELMET = new ArmorItem(CaelumArmorMaterials.SKYGLASS, EquipmentSlot.HEAD, itemSettings());
	public static final Item SKYGLASS_CHESTPLATE = new ArmorItem(CaelumArmorMaterials.SKYGLASS, EquipmentSlot.CHEST, itemSettings());
	public static final Item SKYGLASS_LEGGINGS = new ArmorItem(CaelumArmorMaterials.SKYGLASS, EquipmentSlot.LEGS, itemSettings());
	public static final Item SKYGLASS_BOOTS = new ArmorItem(CaelumArmorMaterials.SKYGLASS, EquipmentSlot.FEET, itemSettings());

	public static final Item SKYROOT_BUCKET = new SkyrootBucketItem(Fluids.EMPTY, itemSettings());
	public static final Item SKYROOT_WATER_BUCKET = new SkyrootBucketItem(Fluids.WATER, itemSettings());
	public static final Item BARLEY_SEEDS = new AliasedBlockItem(CaelumBlocks.BARLEY, itemSettings());
	public static final Item BARLEY = new Item(itemSettings());

	public static final Item BARLEY_BREAD = new Item(itemSettings().food(FoodComponents.BREAD));

	public static void register() {
		Registry.register(Registry.ITEM, "caelum:aerrack", new BlockItem(CaelumBlocks.AERRACK, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:aerrack_bricks", new BlockItem(CaelumBlocks.AERRACK_BRICKS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:aerrack_brick_stairs", new BlockItem(CaelumBlocks.AERRACK_BRICK_STAIRS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:aerrack_brick_slab", new BlockItem(CaelumBlocks.AERRACK_BRICK_SLAB, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:aerrack_pillar", new BlockItem(CaelumBlocks.AERRACK_PILLAR, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:skyglass_ore", new BlockItem(CaelumBlocks.SKYGLASS_ORE, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:caelum_dirt", new BlockItem(CaelumBlocks.CAELUM_DIRT, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:caelum_grass_block", new BlockItem(CaelumBlocks.CAELUM_GRASS_BLOCK, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:caelum_farmland", new BlockItem(CaelumBlocks.CAELUM_FARMLAND, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:skyroot_log", new BlockItem(CaelumBlocks.SKYROOT_LOG, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_leaves", new BlockItem(CaelumBlocks.SKYROOT_LEAVES, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:silver_skyroot_leaves", new BlockItem(CaelumBlocks.SILVER_SKYROOT_LEAVES, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:dwarf_skyroot_leaves", new BlockItem(CaelumBlocks.DWARF_SKYROOT_LEAVES, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_sapling", new BlockItem(CaelumBlocks.SKYROOT_SAPLING, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:silver_skyroot_sapling", new BlockItem(CaelumBlocks.SILVER_SKYROOT_SAPLING, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:dwarf_skyroot_sapling", new BlockItem(CaelumBlocks.DWARF_SKYROOT_SAPLING, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:caelum_grass", new BlockItem(CaelumBlocks.CAELUM_GRASS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:blossoming_caelum_grass", new BlockItem(CaelumBlocks.BLOSSOMING_CAELUM_GRASS, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:stripped_skyroot_log", new BlockItem(CaelumBlocks.STRIPPED_SKYROOT_LOG, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_planks", new BlockItem(CaelumBlocks.SKYROOT_PLANKS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_stairs", new BlockItem(CaelumBlocks.SKYROOT_STAIRS, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_slab", new BlockItem(CaelumBlocks.SKYROOT_SLAB, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_crafting_table", new BlockItem(CaelumBlocks.SKYROOT_CRAFTING_TABLE, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_door", new TallBlockItem(CaelumBlocks.SKYROOT_DOOR, itemSettings()));
		Registry.register(Registry.ITEM, "caelum:skyroot_trapdoor", new BlockItem(CaelumBlocks.SKYROOT_TRAPDOOR, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:caelum_lantern", new BlockItem(CaelumBlocks.CAELUM_LANTERN, itemSettings()));

		Registry.register(Registry.ITEM, "caelum:skyroot_stick", SKYROOT_STICK);
		Registry.register(Registry.ITEM, "caelum:skyglass", SKYGLASS);

		Registry.register(Registry.ITEM, "caelum:skyroot_sword", SKYROOT_SWORD);
		Registry.register(Registry.ITEM, "caelum:skyroot_shovel", SKYROOT_SHOVEL);
		Registry.register(Registry.ITEM, "caelum:skyroot_pickaxe", SKYROOT_PICKAXE);
		Registry.register(Registry.ITEM, "caelum:skyroot_axe", SKYROOT_AXE);
		Registry.register(Registry.ITEM, "caelum:skyroot_hoe", SKYROOT_HOE);

		Registry.register(Registry.ITEM, "caelum:aerrack_sword", AERRACK_SWORD);
		Registry.register(Registry.ITEM, "caelum:aerrack_shovel", AERRACK_SHOVEL);
		Registry.register(Registry.ITEM, "caelum:aerrack_pickaxe", AERRACK_PICKAXE);
		Registry.register(Registry.ITEM, "caelum:aerrack_axe", AERRACK_AXE);
		Registry.register(Registry.ITEM, "caelum:aerrack_hoe", AERRACK_HOE);

		Registry.register(Registry.ITEM, "caelum:skyglass_sword", SKYGLASS_SWORD);
		Registry.register(Registry.ITEM, "caelum:skyglass_shovel", SKYGLASS_SHOVEL);
		Registry.register(Registry.ITEM, "caelum:skyglass_pickaxe", SKYGLASS_PICKAXE);
		Registry.register(Registry.ITEM, "caelum:skyglass_axe", SKYGLASS_AXE);
		Registry.register(Registry.ITEM, "caelum:skyglass_hoe", SKYGLASS_HOE);

		Registry.register(Registry.ITEM, "caelum:skyglass_helmet", SKYGLASS_HELMET);
		Registry.register(Registry.ITEM, "caelum:skyglass_chestplate", SKYGLASS_CHESTPLATE);
		Registry.register(Registry.ITEM, "caelum:skyglass_leggings", SKYGLASS_LEGGINGS);
		Registry.register(Registry.ITEM, "caelum:skyglass_boots", SKYGLASS_BOOTS);

		Registry.register(Registry.ITEM, "caelum:skyroot_bucket", SKYROOT_BUCKET);
		Registry.register(Registry.ITEM, "caelum:skyroot_water_bucket", SKYROOT_WATER_BUCKET);
		
		Registry.register(Registry.ITEM, "caelum:barley_seeds", BARLEY_SEEDS);
		Registry.register(Registry.ITEM, "caelum:barley", BARLEY);
		Registry.register(Registry.ITEM, "caelum:barley_bread", BARLEY_BREAD);
	}

	private static Item.Settings itemSettings() {
		return new Item.Settings().group(CaelumItems.GROUP);
	}
}
