package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PortalForcer;
import reoseah.caelum.common.dimension.CaelumDimensionHelper;
import reoseah.caelum.common.dimension.CaelumDimensionType;

@Mixin(PortalForcer.class)
public class PortalForcerMixin {
	@Shadow
	@Final
	private ServerWorld world;

	@Inject(method = "usePortal", at = @At("HEAD"), cancellable = true)
	public void on_usePortal(Entity entity, float yawOffset, CallbackInfoReturnable<Boolean> infoReturnable) {
		if (world.getDimension() == CaelumDimensionType.INSTANCE) {
			CaelumDimensionHelper.onEntityEnters(entity, (ServerWorld) entity.getEntityWorld(), world);
			infoReturnable.setReturnValue(true);
			infoReturnable.cancel();
		}
	}
}
