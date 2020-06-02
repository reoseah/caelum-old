package reoseah.caelum.common;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.common.items.CaelumToolMaterials;
import reoseah.caelum.common.items.CelestialCrystalItem;
import reoseah.caelum.common.items.CeruclaseDrawknifeItem;
import reoseah.caelum.common.items.CeruclaseRockcutterItem;
import reoseah.caelum.common.items.ModAxeItem;
import reoseah.caelum.common.items.ModHoeItem;
import reoseah.caelum.common.items.ModPickaxeItem;
import reoseah.caelum.common.items.SkyrootBucketItem;
import reoseah.caelum.common.items.SkyrootDiggingStickItem;

public class CaelumItems {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier("caelum", "main"), () -> new ItemStack(CaelumBlocks.CAELUM_GRASS));

	private static final Item.Settings SETTINGS = new Item.Settings().group(CaelumItems.GROUP);

	public static final Item TELEPORTER = new CelestialCrystalItem(SETTINGS);
	public static final Item CERUCLASE = new Item(SETTINGS);
	public static final Item SKYROOT_STICK = new Item(SETTINGS);

	public static final Item SKYROOT_SWORD = new SwordItem(CaelumToolMaterials.SKYROOT, 3, -2.4F, SETTINGS);
	public static final Item SKYROOT_DIGGING_STICK = new SkyrootDiggingStickItem(CaelumToolMaterials.SKYROOT, 2F, -2.0F, SETTINGS);

	public static final Item AERRACK_SWORD = new SwordItem(CaelumToolMaterials.AERRACK, 3, -2.4F, SETTINGS);
	public static final Item AERRACK_SHOVEL = new ShovelItem(CaelumToolMaterials.AERRACK, 1.5F, -3.0F, SETTINGS);
	public static final Item AERRACK_PICKAXE = new ModPickaxeItem(CaelumToolMaterials.AERRACK, 1, -2.8F, SETTINGS);
	public static final Item AERRACK_AXE = new ModAxeItem(CaelumToolMaterials.AERRACK, 7.0F, -3.2F, SETTINGS);
	public static final Item AERRACK_HOE = new ModHoeItem(CaelumToolMaterials.AERRACK, -1, -2.0F, SETTINGS);

	public static final Item CERUCLASE_FALX = new SwordItem(CaelumToolMaterials.CERUCLASE, 3, -2.4F, SETTINGS);
	public static final Item CERUCLASE_ROCKCUTTER = new CeruclaseRockcutterItem(CaelumToolMaterials.CERUCLASE_ROCKCUTTER, 1F, -2.8F, SETTINGS);
	public static final Item CERUCLASE_DRAWKNIFE = new CeruclaseDrawknifeItem(CaelumToolMaterials.CERUCLASE_DRAWKNIFE, SETTINGS);
	public static final Item CERUCLASE_GARDEN_SHEARS = new ShearsItem(SETTINGS);

	public static final Item SKYROOT_BUCKET = new SkyrootBucketItem(Fluids.EMPTY, SETTINGS);
	public static final Item SKYROOT_WATER_BUCKET = new SkyrootBucketItem(Fluids.WATER, SETTINGS);

	public static final Item BARLEY_SEEDS = new AliasedBlockItem(CaelumBlocks.BARLEY, SETTINGS);
	public static final Item BARLEY = new Item(SETTINGS);
	public static final Item SKY_APPLE = new Item(new Item.Settings().group(CaelumItems.GROUP).food(FoodComponents.APPLE));

	public static void register() {
		Registry.register(Registry.ITEM, "caelum:aerrack", new BlockItem(CaelumBlocks.AERRACK, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:ceruclase_ore", new BlockItem(CaelumBlocks.CERUCLASE_ORE, SETTINGS));

		Registry.register(Registry.ITEM, "caelum:aerrack_bricks", new BlockItem(CaelumBlocks.AERRACK_BRICKS, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:aerrack_brick_stairs", new BlockItem(CaelumBlocks.AERRACK_BRICK_STAIRS, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:aerrack_brick_slab", new BlockItem(CaelumBlocks.AERRACK_BRICK_SLAB, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:aerrack_pillar", new BlockItem(CaelumBlocks.AERRACK_PILLAR, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:aerrack_lightstone", new BlockItem(CaelumBlocks.AERRACK_LIGHTSTONE, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:ceruclase_block", new BlockItem(CaelumBlocks.CERUCLASE_BLOCK, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:inert_sealstone", new BlockItem(CaelumBlocks.INERT_SEALSTONE, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:mossy_aerrack", new BlockItem(CaelumBlocks.MOSSY_AERRACK, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:mossy_aerrack_bricks", new BlockItem(CaelumBlocks.MOSSY_AERRACK_BRICKS, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:mossy_aerrack_pillar", new BlockItem(CaelumBlocks.MOSSY_AERRACK_PILLAR, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:mossy_inert_sealstone", new BlockItem(CaelumBlocks.INERT_MOSSY_SEALSTONE, SETTINGS));

		Registry.register(Registry.ITEM, "caelum:caelum_grass", new BlockItem(CaelumBlocks.CAELUM_GRASS, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:caelum_dirt", new BlockItem(CaelumBlocks.CAELUM_DIRT, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:caelum_farmland", new BlockItem(CaelumBlocks.CAELUM_FARMLAND, SETTINGS));

		Registry.register(Registry.ITEM, "caelum:skyroot_log", new BlockItem(CaelumBlocks.SKYROOT_LOG, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:stripped_skyroot_log", new BlockItem(CaelumBlocks.STRIPPED_SKYROOT_LOG, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:skyroot_leaves", new BlockItem(CaelumBlocks.SKYROOT_LEAVES, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:silver_skyroot_leaves", new BlockItem(CaelumBlocks.SILVER_SKYROOT_LEAVES, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:dwarf_skyroot_leaves", new BlockItem(CaelumBlocks.DWARF_SKYROOT_LEAVES, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:skyroot_sapling", new BlockItem(CaelumBlocks.SKYROOT_SAPLING, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:silver_skyroot_sapling", new BlockItem(CaelumBlocks.SILVER_SKYROOT_SAPLING, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:dwarf_skyroot_sapling", new BlockItem(CaelumBlocks.DWARF_SKYROOT_SAPLING, SETTINGS));

		Registry.register(Registry.ITEM, "caelum:caelum_sprouts", new BlockItem(CaelumBlocks.CAELUM_SPROUTS, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:blossoming_caelum_sprouts", new BlockItem(CaelumBlocks.BLOSSOMING_CAELUM_SPROUTS, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:sky_blue_flower", new BlockItem(CaelumBlocks.SKY_BLUE_FLOWER, SETTINGS));

		Registry.register(Registry.ITEM, "caelum:skyroot_planks", new BlockItem(CaelumBlocks.SKYROOT_PLANKS, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:skyroot_stairs", new BlockItem(CaelumBlocks.SKYROOT_STAIRS, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:skyroot_slab", new BlockItem(CaelumBlocks.SKYROOT_SLAB, SETTINGS));

		Registry.register(Registry.ITEM, "caelum:skyroot_crafting_table", new BlockItem(CaelumBlocks.SKYROOT_CRAFTING_TABLE, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:celestial_altar", new BlockItem(CaelumBlocks.CELESTIAL_ALTAR, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:sealstone", new BlockItem(CaelumBlocks.SEALSTONE, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:mossy_sealstone", new BlockItem(CaelumBlocks.MOSSY_SEALSTONE, SETTINGS));

		Registry.register(Registry.ITEM, "caelum:skyroot_door", new BlockItem(CaelumBlocks.SKYROOT_DOOR, SETTINGS));
		Registry.register(Registry.ITEM, "caelum:skyroot_trapdoor", new BlockItem(CaelumBlocks.SKYROOT_TRAPDOOR, SETTINGS));

		Registry.register(Registry.ITEM, "caelum:celestial_crystal", TELEPORTER);

		Registry.register(Registry.ITEM, "caelum:ceruclase", CERUCLASE);
		Registry.register(Registry.ITEM, "caelum:skyroot_stick", SKYROOT_STICK);

		Registry.register(Registry.ITEM, "caelum:skyroot_sword", SKYROOT_SWORD);
		Registry.register(Registry.ITEM, "caelum:skyroot_digging_stick", SKYROOT_DIGGING_STICK);

		Registry.register(Registry.ITEM, "caelum:aerrack_sword", AERRACK_SWORD);
		Registry.register(Registry.ITEM, "caelum:aerrack_shovel", AERRACK_SHOVEL);
		Registry.register(Registry.ITEM, "caelum:aerrack_pickaxe", AERRACK_PICKAXE);
		Registry.register(Registry.ITEM, "caelum:aerrack_axe", AERRACK_AXE);
		Registry.register(Registry.ITEM, "caelum:aerrack_hoe", AERRACK_HOE);

		Registry.register(Registry.ITEM, "caelum:ceruclase_falx", CERUCLASE_FALX);
		Registry.register(Registry.ITEM, "caelum:ceruclase_rockcutter", CERUCLASE_ROCKCUTTER);
		Registry.register(Registry.ITEM, "caelum:ceruclase_drawknife", CERUCLASE_DRAWKNIFE);
		Registry.register(Registry.ITEM, "caelum:ceruclase_garden_shears", CERUCLASE_GARDEN_SHEARS);

		Registry.register(Registry.ITEM, "caelum:skyroot_bucket", SKYROOT_BUCKET);
		Registry.register(Registry.ITEM, "caelum:skyroot_water_bucket", SKYROOT_WATER_BUCKET);

		Registry.register(Registry.ITEM, "caelum:barley_seeds", BARLEY_SEEDS);
		Registry.register(Registry.ITEM, "caelum:barley", BARLEY);
		Registry.register(Registry.ITEM, "caelum:sky_apple", SKY_APPLE);
	}
}
