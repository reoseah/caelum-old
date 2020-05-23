package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.common.CaelumBlocks;

@Mixin(Feature.class)
public class FeatureMixin {
	@Inject(at = @At("HEAD"), method = "isDirt", cancellable = true)
	private static void isDirt(Block block, CallbackInfoReturnable<Boolean> callback) {
		// Allows generators to work on sky counterparts of dirt/grass/farmland
		if (block == CaelumBlocks.CAELUM_DIRT || block == CaelumBlocks.CAELUM_GRASS || block == CaelumBlocks.CAELUM_FARMLAND) {
			callback.setReturnValue(true);
		}
	}

	@Inject(at = @At("HEAD"), method = "isStone", cancellable = true)
	private static void isStone(Block block, CallbackInfoReturnable<Boolean> callback) {
		if (block == CaelumBlocks.AERRACK) {
			callback.setReturnValue(true);
		}
	}
}
