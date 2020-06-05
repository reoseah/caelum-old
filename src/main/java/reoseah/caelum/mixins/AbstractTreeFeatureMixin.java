package reoseah.caelum.mixins;

import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.common.CaelumBlocks;

@Mixin(AbstractTreeFeature.class)
public abstract class AbstractTreeFeatureMixin extends Feature<DefaultFeatureConfig> {
	public AbstractTreeFeatureMixin(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
		super(configDeserializer);
	}

	@Override
	@Shadow
	protected abstract void setBlockState(ModifiableWorld world, BlockPos pos, BlockState state);

	@Inject(at = @At("HEAD"), method = "isNaturalDirt", cancellable = true)
	private static void isNaturalDirt(TestableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		if (world.testBlockState(pos, state -> isDirt(state.getBlock()) && state.getBlock() == CaelumBlocks.CAELUM_GRASS)) {
			// Sky Grass is not "dirt", it needs to be replaced with Sky Silt
			// (called by trees when growing)
			callback.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "setToDirt", cancellable = true)
	private void setToDirt(ModifiableTestableWorld world, BlockPos pos, CallbackInfo callback) {
		// Sky Grass is replaced with Sky Silt, not Dirt from Overworld
		if (world.testBlockState(pos, state -> state.getBlock() == CaelumBlocks.CAELUM_GRASS)) {
			this.setBlockState(world, pos, CaelumBlocks.CAELUM_DIRT.getDefaultState());
			callback.cancel();
		}
	}
}
