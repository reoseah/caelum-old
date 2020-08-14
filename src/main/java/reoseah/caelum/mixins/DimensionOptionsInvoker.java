package reoseah.caelum.mixins;

import java.util.LinkedHashSet;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionOptions;

@Mixin(DimensionOptions.class)
public interface DimensionOptionsInvoker {
	@Accessor("BASE_DIMENSIONS")
	public static LinkedHashSet<RegistryKey<DimensionOptions>> getBASE_DIMENSIONS() {
		return null;
	}
}
