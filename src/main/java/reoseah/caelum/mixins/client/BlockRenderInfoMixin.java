package reoseah.caelum.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.fabric.impl.client.indigo.renderer.render.BlockRenderInfo;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import reoseah.caelum.common.CaelumBlocks;

@Mixin(BlockRenderInfo.class)
public class BlockRenderInfoMixin {
	@Shadow
	boolean defaultAo;

	@Inject(method = "prepareForBlock", at = @At("RETURN"))
	public void after_prepareForBlock(BlockState state, BlockPos pos, boolean modelAO, CallbackInfo ci) {
		if (state.getBlock() == CaelumBlocks.INLAID_AERRACK_BRICKS || state.getBlock() == CaelumBlocks.CERUCLASE_ORE || state.getBlock() == CaelumBlocks.CERUCLASE_BLOCK) {
			this.defaultAo = modelAO && MinecraftClient.isAmbientOcclusionEnabled();
		}
	}
}
