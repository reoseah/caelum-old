package reoseah.skyland.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import reoseah.skyland.blocks.SkyBlocks;

@Mixin(CropBlock.class)
public class CropBlockMixin {
	@Redirect(method = "getAvailableMoisture", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"))
	private static Block getBlock(BlockState state) {
		if (state.getBlock() == SkyBlocks.SKY_FARMLAND) {
			return Blocks.FARMLAND;
		}
		return state.getBlock();
	}
}
