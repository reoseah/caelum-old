package reoseah.caelum.common.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class StrippingHelper extends AxeItem {
	private StrippingHelper(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(null, 0, 0, null);
	}

	public static ActionResult tryStripBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		BlockState state = world.getBlockState(pos);
		Block block = STRIPPED_BLOCKS.get(state.getBlock());
		if (block != null) {
			PlayerEntity player = context.getPlayer();
			world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!world.isClient) {
				world.setBlockState(pos, block.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)), 11);
				if (player != null) {
					context.getStack().damage(1, player, p -> {
						p.sendToolBreakStatus(context.getHand());
					});
				}
			}
			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}
}
