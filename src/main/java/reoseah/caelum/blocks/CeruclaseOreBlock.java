package reoseah.caelum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CeruclaseOreBlock extends Block {
	public CeruclaseOreBlock(Block.Settings settings) {
		super(settings);
	}

	public void onStacksDropped(BlockState state, World world, BlockPos pos, ItemStack stack) {
		super.onStacksDropped(state, world, pos, stack);
		if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			this.dropExperience(world, pos, MathHelper.nextInt(world.random, 1, 3));
		}
	}
}
