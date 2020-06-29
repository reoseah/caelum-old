package reoseah.caelum.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reoseah.caelum.common.CaelumItems;

public class PortalstoneBlock extends Block {
	public static final BooleanProperty LIT = Properties.LIT;

	public PortalstoneBlock(Block.Settings settings) {
		super(settings);

		this.setDefaultState(this.getDefaultState().with(LIT, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}

	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack stack = player.getStackInHand(hand);
		if (stack.getItem() == CaelumItems.ENERGIZED_CERUCLASE && player.canModifyBlocks()) {
			world.setBlockState(pos, state.with(LIT, true), 2);
			if (!player.isCreative()) {
				stack.decrement(1);
			}

			return ActionResult.success(world.isClient);
		}
		return ActionResult.PASS;
	}
}
