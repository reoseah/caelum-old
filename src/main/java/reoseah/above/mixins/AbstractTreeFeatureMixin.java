package reoseah.above.mixins;

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
import reoseah.above.Above;

@Mixin(AbstractTreeFeature.class)
public abstract class AbstractTreeFeatureMixin extends Feature<DefaultFeatureConfig> {
	public AbstractTreeFeatureMixin(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
		super(configDeserializer);
	}

	@Shadow
	protected abstract void setBlockState(ModifiableWorld world, BlockPos pos, BlockState state);

	@Inject(at = @At("HEAD"), method = "isNaturalDirt", cancellable = true)
	private static void isNaturalDirt(TestableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		if (world.testBlockState(pos, state -> isDirt(state.getBlock()) && state.getBlock() == Above.SKY_MOSS)) {
			// Sky Moss is not "dirt", it needs to be replaced with Sky Silt,
			// where trees replace Grass with Dirt
			callback.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "setToDirt", cancellable = true)
	private void setToDirt(ModifiableTestableWorld world, BlockPos pos, CallbackInfo callback) {
		// Sky Moss is replaced with Sky Silt, not Dirt
		if (world.testBlockState(pos, state -> state.getBlock() == Above.SKY_MOSS)) {
			this.setBlockState(world, pos, Above.SKY_SILT.getDefaultState());
			callback.cancel();
		}
	}
}
