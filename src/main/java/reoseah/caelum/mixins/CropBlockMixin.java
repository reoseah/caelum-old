package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import reoseah.caelum.common.CaelumBlocks;

@Mixin(CropBlock.class)
public class CropBlockMixin {
	@Redirect(method = "getAvailableMoisture", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"))
	private static Block getBlock(BlockState state) {
		// work around hard coded `block == Blocks.FARMLAND` check
		if (state.getBlock() == CaelumBlocks.CAELUM_FARMLAND) {
			return Blocks.FARMLAND;
		}
		return state.getBlock();
	}
}
