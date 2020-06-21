package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

@Mixin(CraftingScreenHandler.class)
public abstract class CraftingScreenHandlerMixin {
	@Shadow
	@Final
	private ScreenHandlerContext context;

	@Inject(at = @At("HEAD"), method = "canUse", cancellable = true)
	public void canUse(PlayerEntity player, CallbackInfoReturnable<Boolean> ci) {
		if (canUse2(this.context, player)) {
			ci.setReturnValue(true);
			ci.cancel();
		}
	}

	private static boolean canUse2(ScreenHandlerContext ctx, PlayerEntity player) {
		// checks `instanceof CraftingTableBlock` instead of `== Blocks.CRAFTING_TABLE` in vanilla
		return ctx.run((world, pos) -> {
			return world.getBlockState(pos).getBlock() instanceof CraftingTableBlock
					&& player.squaredDistanceTo(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
		}, true);
	}
}
