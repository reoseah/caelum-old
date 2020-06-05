package reoseah.caelum.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShearsItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumSounds;

public class CaelumShearsItem extends ShearsItem {
	public CaelumShearsItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		return tryPruneBlock(context);
	}

	public static ActionResult tryPruneBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		BlockState state = world.getBlockState(pos);
		BlockState pruned = null;
		if (state.getBlock() == CaelumBlocks.SKYROOT_SAPLING) {
			pruned = CaelumBlocks.STUNTED_SKYROOT_SAPLING.getDefaultState();
		} else if (state.getBlock() == CaelumBlocks.SILVER_SKYROOT_SAPLING) {
			pruned = CaelumBlocks.STUNTED_SILVER_SKYROOT_SAPLING.getDefaultState();
		}
		if (pruned != null) {
			PlayerEntity player = context.getPlayer();
			world.playSound(player, pos, CaelumSounds.SHEAR_SOUND, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!world.isClient) {
				world.setBlockState(pos, pruned, 11);
				if (player != null) {
					context.getStack().damage(1, player, p -> {
						p.sendToolBreakStatus(context.getHand());
					});
				}
			}
		}
		return ActionResult.PASS;
	}
}
