package reoseah.caelum.mixins;

//@Mixin(AbstractTreeFeature.class)
//public abstract class AbstractTreeFeatureMixin extends Feature<DefaultFeatureConfig> {
//	public AbstractTreeFeatureMixin(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
//		super(configDeserializer);
//	}
//
//	@Shadow
//	protected abstract void setBlockState(ModifiableWorld world, BlockPos pos, BlockState state);
//
//	@Inject(at = @At("HEAD"), method = "isNaturalDirt", cancellable = true)
//	private static void isNaturalDirt(TestableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
//		if (world.testBlockState(pos, state -> isDirt(state.getBlock()) && state.getBlock() == CaelumBlocks.CAELUM_GRASS)) {
//			// Sky Grass is not "dirt", it needs to be replaced with Sky Silt
//			// (called by trees when growing)
//			callback.setReturnValue(false);
//		}
//	}
//
//	@Inject(at = @At("HEAD"), method = "setToDirt", cancellable = true)
//	private void setToDirt(ModifiableTestableWorld world, BlockPos pos, CallbackInfo callback) {
//		// Sky Grass is replaced with Sky Silt, not Dirt from Overworld
//		if (world.testBlockState(pos, state -> state.getBlock() == CaelumBlocks.CAELUM_GRASS)) {
//			this.setBlockState(world, pos, CaelumBlocks.CAELUM_DIRT.getDefaultState());
//			callback.cancel();
//		}
//	}
//}
