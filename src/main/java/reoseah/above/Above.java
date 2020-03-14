package reoseah.above;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Above implements ModInitializer {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(makeID("main"), () -> new ItemStack(Above.SKY_MOSS));

	public static final Block AERRACK = new Block(FabricBlockSettings.of(Material.STONE).strength(5.0F, 6.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block CERUCLASE_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(5.0F, 6.0F).breakByTool(FabricToolTags.PICKAXES, 1).sounds(BlockSoundGroup.STONE).build());
	public static final Block AERRACK_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0F, 5.0F).breakByTool(FabricToolTags.PICKAXES, 0).sounds(BlockSoundGroup.STONE).build());
	public static final Block CHISELED_AERRACK = new PillarBlock(FabricBlockSettings.copy(AERRACK_BRICKS).build());
	public static final Block SKY_SILT = new FallingBlock(FabricBlockSettings.copy(Blocks.GRAVEL).build());
	public static final Block SKY_MOSS = new GrassBlock(FabricBlockSettings.copy(Blocks.GRASS_BLOCK).build());

	public static Identifier makeID(String name) {
		return new Identifier("above", name);
	}

	private static Settings makeItemSettings() {
		return new Item.Settings().group(GROUP);
	}

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, makeID("aerrack"), AERRACK);
		Registry.register(Registry.BLOCK, makeID("ceruclase_ore"), CERUCLASE_ORE);
		Registry.register(Registry.BLOCK, makeID("aerrack_bricks"), AERRACK_BRICKS);
		Registry.register(Registry.BLOCK, makeID("chiseled_aerrack"), CHISELED_AERRACK);
		Registry.register(Registry.BLOCK, makeID("sky_silt"), SKY_SILT);
		Registry.register(Registry.BLOCK, makeID("sky_moss"), SKY_MOSS);

		Registry.register(Registry.ITEM, makeID("aerrack"), new BlockItem(AERRACK, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("ceruclase_ore"), new BlockItem(CERUCLASE_ORE, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("aerrack_bricks"), new BlockItem(AERRACK_BRICKS, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("chiseled_aerrack"), new BlockItem(CHISELED_AERRACK, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("sky_silt"), new BlockItem(SKY_SILT, makeItemSettings()));
		Registry.register(Registry.ITEM, makeID("sky_moss"), new BlockItem(SKY_MOSS, makeItemSettings()));
	}
}
