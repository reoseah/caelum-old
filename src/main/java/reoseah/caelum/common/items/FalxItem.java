package reoseah.caelum.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.BlockTags;

public class FalxItem extends SwordItem {
	public FalxItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public float getMiningSpeed(ItemStack stack, BlockState state) {
		Block block = state.getBlock();
		if (block == Blocks.COBWEB) {
			return 15.0F;
		}
		Material material = state.getMaterial();
		if (material == Material.PLANT || material == Material.REPLACEABLE_PLANT || material == Material.UNUSED_PLANT || state.matches(BlockTags.LEAVES) || material == Material.PUMPKIN) {
			return 7.5F;
		}
		return 1.0F;
	}
}
