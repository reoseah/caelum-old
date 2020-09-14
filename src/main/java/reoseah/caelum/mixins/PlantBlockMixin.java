package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import reoseah.caelum.registry.CaelumBlocks;

@Mixin(PlantBlock.class)
public class PlantBlockMixin {
	@Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
	protected void canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
		if (floor.isOf(CaelumBlocks.CAELUM_GRASS_BLOCK) || floor.isOf(CaelumBlocks.CAELUM_DIRT) || floor.isOf(CaelumBlocks.CAELUM_FARMLAND)) {
			ci.setReturnValue(true);
		}
	}
}
