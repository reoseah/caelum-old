package reoseah.skyland.items;

import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.registry.Registry;
import reoseah.skyland.Skyland;

public class SkyItems {
	public static final Item TELEPORTER = new TeleporterItem(new Item.Settings().group(null));
	public static final Item CERUCLASE = new Item(new Item.Settings().group(Skyland.GROUP));
	public static final Item SKYROOT_STICK = new Item(new Item.Settings().group(Skyland.GROUP));
	public static final Item SKYROOT_SWORD = new SwordItem(SkyToolMaterials.SKYROOT, 3, -2.4F, new Item.Settings().group(Skyland.GROUP));
	public static final Item SKYROOT_SHOVEL = new ShovelItem(SkyToolMaterials.SKYROOT, 1.5F, -3.0F, new Item.Settings().group(Skyland.GROUP));
	public static final Item SKYROOT_PICKAXE = new ModdedPickaxeItem(SkyToolMaterials.SKYROOT, 1, -2.8F, new Item.Settings().group(Skyland.GROUP));
	public static final Item SKYROOT_AXE = new ModdedAxeItem(SkyToolMaterials.SKYROOT, 6.0F, -3.2F, new Item.Settings().group(Skyland.GROUP));
	public static final Item SKYROOT_HOE = new ModdedHoeItem(SkyToolMaterials.SKYROOT, 0, -3.0F, new Item.Settings().group(Skyland.GROUP));
	public static final Item AERRACK_SWORD = new SwordItem(SkyToolMaterials.AERRACK, 3, -2.4F, new Item.Settings().group(Skyland.GROUP));
	public static final Item AERRACK_SHOVEL = new ShovelItem(SkyToolMaterials.AERRACK, 1.5F, -3.0F, new Item.Settings().group(Skyland.GROUP));
	public static final Item AERRACK_PICKAXE = new ModdedPickaxeItem(SkyToolMaterials.AERRACK, 1, -2.8F, new Item.Settings().group(Skyland.GROUP));
	public static final Item AERRACK_AXE = new ModdedAxeItem(SkyToolMaterials.AERRACK, 7.0F, -3.2F, new Item.Settings().group(Skyland.GROUP));
	public static final Item AERRACK_HOE = new ModdedHoeItem(SkyToolMaterials.AERRACK, -1, -2.0F, new Item.Settings().group(Skyland.GROUP));
	public static final Item CERUCLASE_SWORD = new SwordItem(SkyToolMaterials.CERUCLASE, 3, -2.4F, new Item.Settings().group(Skyland.GROUP));
	public static final Item CERUCLASE_SHOVEL = new ShovelItem(SkyToolMaterials.CERUCLASE, 1.5F, -3.0F, new Item.Settings().group(Skyland.GROUP));
	public static final Item CERUCLASE_PICKAXE = new ModdedPickaxeItem(SkyToolMaterials.CERUCLASE, 1, -2.8F, new Item.Settings().group(Skyland.GROUP));
	public static final Item CERUCLASE_AXE = new ModdedAxeItem(SkyToolMaterials.CERUCLASE, 6.0F, -3.1F, new Item.Settings().group(Skyland.GROUP));
	public static final Item CERUCLASE_HOE = new ModdedHoeItem(SkyToolMaterials.CERUCLASE, -2, -1.0F, new Item.Settings().group(Skyland.GROUP));
	public static final Item SKYROOT_BUCKET = new SkyrootBucketItem(Fluids.EMPTY, new Item.Settings().group(Skyland.GROUP));
	public static final Item SKYROOT_WATER_BUCKET = new SkyrootBucketItem(Fluids.WATER, new Item.Settings().group(Skyland.GROUP));
	
	public static void register() {
		register("teleporter", SkyItems.TELEPORTER);

		register("ceruclase", SkyItems.CERUCLASE);
		register("skyroot_stick", SkyItems.SKYROOT_STICK);

		register("skyroot_sword", SkyItems.SKYROOT_SWORD);
		register("skyroot_shovel", SkyItems.SKYROOT_SHOVEL);
		register("skyroot_pickaxe", SkyItems.SKYROOT_PICKAXE);
		register("skyroot_axe", SkyItems.SKYROOT_AXE);
		register("skyroot_hoe", SkyItems.SKYROOT_HOE);

		register("aerrack_sword", SkyItems.AERRACK_SWORD);
		register("aerrack_shovel", SkyItems.AERRACK_SHOVEL);
		register("aerrack_pickaxe", SkyItems.AERRACK_PICKAXE);
		register("aerrack_axe", SkyItems.AERRACK_AXE);
		register("aerrack_hoe", SkyItems.AERRACK_HOE);

		register("ceruclase_sword", SkyItems.CERUCLASE_SWORD);
		register("ceruclase_shovel", SkyItems.CERUCLASE_SHOVEL);
		register("ceruclase_pickaxe", SkyItems.CERUCLASE_PICKAXE);
		register("ceruclase_axe", SkyItems.CERUCLASE_AXE);
		register("ceruclase_hoe", SkyItems.CERUCLASE_HOE);

		register("skyroot_bucket", SkyItems.SKYROOT_BUCKET);
		register("skyroot_water_bucket", SkyItems.SKYROOT_WATER_BUCKET);
	}

	private static void register(String name, Item item) {
		Registry.register(Registry.ITEM, Skyland.makeID(name), item);
	}
}
