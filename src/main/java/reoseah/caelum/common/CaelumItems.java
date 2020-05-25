package reoseah.caelum.common;

import net.minecraft.fluid.Fluids;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.Caelum;
import reoseah.caelum.common.items.CaelumToolMaterials;
import reoseah.caelum.common.items.ModAxeItem;
import reoseah.caelum.common.items.ModHoeItem;
import reoseah.caelum.common.items.ModPickaxeItem;
import reoseah.caelum.common.items.SkyrootBucketItem;
import reoseah.caelum.common.items.CelestialCrystalItem;

public class CaelumItems {
	public static final Item TELEPORTER = new CelestialCrystalItem(new Item.Settings().group(Caelum.GROUP));
	public static final Item CERUCLASE = new Item(new Item.Settings().group(Caelum.GROUP));
	public static final Item SKYROOT_STICK = new Item(new Item.Settings().group(Caelum.GROUP));

	public static final Item SKYROOT_SWORD = new SwordItem(CaelumToolMaterials.SKYROOT, 3, -2.4F, new Item.Settings().group(Caelum.GROUP));
	public static final Item SKYROOT_DIGGING_STICK = new ShovelItem(CaelumToolMaterials.SKYROOT, 2F, -2.0F, new Item.Settings().group(Caelum.GROUP));

	public static final Item AERRACK_SWORD = new SwordItem(CaelumToolMaterials.AERRACK, 3, -2.4F, new Item.Settings().group(Caelum.GROUP));
	public static final Item AERRACK_SHOVEL = new ShovelItem(CaelumToolMaterials.AERRACK, 1.5F, -3.0F, new Item.Settings().group(Caelum.GROUP));
	public static final Item AERRACK_PICKAXE = new ModPickaxeItem(CaelumToolMaterials.AERRACK, 1, -2.8F, new Item.Settings().group(Caelum.GROUP));
	public static final Item AERRACK_AXE = new ModAxeItem(CaelumToolMaterials.AERRACK, 7.0F, -3.2F, new Item.Settings().group(Caelum.GROUP));
	public static final Item AERRACK_HOE = new ModHoeItem(CaelumToolMaterials.AERRACK, -1, -2.0F, new Item.Settings().group(Caelum.GROUP));

	public static final Item CERUCLASE_SWORD = new SwordItem(CaelumToolMaterials.CERUCLASE, 3, -2.4F, new Item.Settings().group(Caelum.GROUP));
	public static final Item CERUCLASE_SHOVEL = new ShovelItem(CaelumToolMaterials.CERUCLASE, 1.5F, -3.0F, new Item.Settings().group(Caelum.GROUP));
	public static final Item CERUCLASE_PICKAXE = new ModPickaxeItem(CaelumToolMaterials.CERUCLASE, 1, -2.8F, new Item.Settings().group(Caelum.GROUP));
	public static final Item CERUCLASE_AXE = new ModAxeItem(CaelumToolMaterials.CERUCLASE, 6.0F, -3.1F, new Item.Settings().group(Caelum.GROUP));
	public static final Item CERUCLASE_HOE = new ModHoeItem(CaelumToolMaterials.CERUCLASE, -2, -1.0F, new Item.Settings().group(Caelum.GROUP));
	public static final Item SKYROOT_BUCKET = new SkyrootBucketItem(Fluids.EMPTY, new Item.Settings().group(Caelum.GROUP));
	public static final Item SKYROOT_WATER_BUCKET = new SkyrootBucketItem(Fluids.WATER, new Item.Settings().group(Caelum.GROUP));

	public static final Item BARLEY_SEEDS = new AliasedBlockItem(CaelumBlocks.BARLEY, new Item.Settings().group(Caelum.GROUP));
	public static final Item BARLEY = new Item(new Item.Settings().group(Caelum.GROUP));
	public static final Item SKY_APPLE = new Item(new Item.Settings().group(Caelum.GROUP).food(FoodComponents.APPLE));

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
		Registry.register(Registry.ITEM, "caelum:ceruclase_shovel", CERUCLASE_SHOVEL);
		Registry.register(Registry.ITEM, "caelum:ceruclase_pickaxe", CERUCLASE_PICKAXE);
		Registry.register(Registry.ITEM, "caelum:ceruclase_axe", CERUCLASE_AXE);
		Registry.register(Registry.ITEM, "caelum:ceruclase_hoe", CERUCLASE_HOE);

		Registry.register(Registry.ITEM, "caelum:skyroot_bucket", SKYROOT_BUCKET);
		Registry.register(Registry.ITEM, "caelum:skyroot_water_bucket", SKYROOT_WATER_BUCKET);

		Registry.register(Registry.ITEM, "caelum:barley_seeds", BARLEY_SEEDS);
		Registry.register(Registry.ITEM, "caelum:barley", BARLEY);
		Registry.register(Registry.ITEM, "caelum:sky_apple", SKY_APPLE);
	}
}
