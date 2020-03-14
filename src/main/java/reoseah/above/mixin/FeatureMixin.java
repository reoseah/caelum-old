package reoseah.above.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.Feature;
import reoseah.above.Above;

@Mixin(Feature.class)
public class FeatureMixin {
	@Inject(at = @At("HEAD"), method = "isDirt", cancellable = true)
	private static void isDirt(Block block, CallbackInfoReturnable<Boolean> callback) {
		if (block == Above.SKY_SILT || block == Above.SKY_MOSS) {
			callback.setReturnValue(true);
		}
	}
}
