package reoseah.caelum.common;

import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
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

import static reoseah.caelum.common.CaelumBlocks.*;

public class CaelumItems {
	public static final Item TELEPORTER = new CelestialCrystalItem(new Item.Settings().group(Caelum.GROUP));
	public static final Item CERUCLASE = new Item(new Item.Settings().group(Caelum.GROUP));
	public static final Item SKYROOT_STICK = new Item(new Item.Settings().group(Caelum.GROUP));

	public static final Item SKYROOT_SWORD = new SwordItem(CaelumToolMaterials.SKYROOT, 3, -2.4F, new Item.Settings().group(Caelum.GROUP));
	public static final Item SKYROOT_DIGGING_STICK = new SkyrootDiggingStickItem(CaelumToolMaterials.SKYROOT, 2F, -2.0F, new Item.Settings().group(Caelum.GROUP));

	public static final Item AERRACK_SWORD = new SwordItem(CaelumToolMaterials.AERRACK, 3, -2.4F, new Item.Settings().group(Caelum.GROUP));
	public static final Item AERRACK_SHOVEL = new ShovelItem(CaelumToolMaterials.AERRACK, 1.5F, -3.0F, new Item.Settings().group(Caelum.GROUP));
	public static final Item AERRACK_PICKAXE = new ModPickaxeItem(CaelumToolMaterials.AERRACK, 1, -2.8F, new Item.Settings().group(Caelum.GROUP));
	public static final Item AERRACK_AXE = new ModAxeItem(CaelumToolMaterials.AERRACK, 7.0F, -3.2F, new Item.Settings().group(Caelum.GROUP));
	public static final Item AERRACK_HOE = new ModHoeItem(CaelumToolMaterials.AERRACK, -1, -2.0F, new Item.Settings().group(Caelum.GROUP));

	public static final Item CERUCLASE_SWORD = new SwordItem(CaelumToolMaterials.CERUCLASE, 3, -2.4F, new Item.Settings().group(Caelum.GROUP));
	public static final Item CERUCLASE_ROCKCUTTER = new CeruclaseRockcutterItem(CaelumToolMaterials.CERUCLASE_ROCKCUTTER, 1F, -2.8F, new Item.Settings().group(Caelum.GROUP));
	public static final Item CERUCLASE_DRAWKNIFE = new CeruclaseDrawknifeItem(CaelumToolMaterials.CERUCLASE_DRAWKNIFE, new Item.Settings().group(Caelum.GROUP));
	
	public static final Item SKYROOT_BUCKET = new SkyrootBucketItem(Fluids.EMPTY, new Item.Settings().group(Caelum.GROUP));
	public static final Item SKYROOT_WATER_BUCKET = new SkyrootBucketItem(Fluids.WATER, new Item.Settings().group(Caelum.GROUP));

	public static final Item BARLEY_SEEDS = new AliasedBlockItem(CaelumBlocks.BARLEY, new Item.Settings().group(Caelum.GROUP));
	public static final Item BARLEY = new Item(new Item.Settings().group(Caelum.GROUP));
	public static final Item SKY_APPLE = new Item(new Item.Settings().group(Caelum.GROUP).food(FoodComponents.APPLE));

	public static Item.Settings defaultSettings()
	{
		return new Item.Settings().group(Caelum.GROUP);
	}

	private static SpawnEggItem makeEgg(EntityType<?> type, int background, int dots)
	{
		return new SpawnEggItem(type, background, dots, defaultSettings());
	}

	public static void register() {
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

		Registry.register(Registry.ITEM, "caelum:ceruclase_sword", CERUCLASE_SWORD);
		Registry.register(Registry.ITEM, "caelum:ceruclase_rockcutter", CERUCLASE_ROCKCUTTER);
		Registry.register(Registry.ITEM, "caelum:ceruclase_drawknife", CERUCLASE_DRAWKNIFE);

		Registry.register(Registry.ITEM, "caelum:skyroot_bucket", SKYROOT_BUCKET);
		Registry.register(Registry.ITEM, "caelum:skyroot_water_bucket", SKYROOT_WATER_BUCKET);

		Registry.register(Registry.ITEM, "caelum:barley_seeds", BARLEY_SEEDS);
		Registry.register(Registry.ITEM, "caelum:barley", BARLEY);
		Registry.register(Registry.ITEM, "caelum:sky_apple", SKY_APPLE);
		
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

		Registry.register(Registry.ITEM, "caelum:skyroot_door", new BlockItem(SKYROOT_DOOR, new Item.Settings().group(Caelum.GROUP)));
		Registry.register(Registry.ITEM, "caelum:skyroot_trapdoor", new BlockItem(SKYROOT_TRAPDOOR, new Item.Settings().group(Caelum.GROUP)));
	}
}
