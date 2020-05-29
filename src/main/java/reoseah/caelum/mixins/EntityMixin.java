package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import reoseah.caelum.common.misc.CelestialCrystalUser;

@Mixin(Entity.class)
public class EntityMixin {
	@Inject(at = @At("HEAD"), method = "tickNetherPortal", cancellable = true)
	protected void before_tickNetherPortal(CallbackInfo ci) {
		Entity entity = (Entity) (Object) this;
		if (entity instanceof CelestialCrystalUser) {
			if (((CelestialCrystalUser) entity).onNetherPortalTick()) {
				ci.cancel();
			}
		}
	}
}
