package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import reoseah.caelum.registry.CaelumDimension;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {
	@Inject(at = @At("RETURN"), method = "createDefaultDimensionOptions", cancellable = true)
	private static void createDefaultDimensionOptions(Registry<DimensionType> dimensionTypes,
			Registry<Biome> biomes,
			Registry<ChunkGeneratorSettings> chunkGeneratorSettings,
			long seed,
			CallbackInfoReturnable<SimpleRegistry<DimensionOptions>> callback) {
		CaelumDimension.onAddDefaultDimensions(callback.getReturnValue(), biomes, chunkGeneratorSettings, seed);
	}

	@Inject(at = @At("HEAD"), method = "addRegistryDefaults")
	private static void addRegistryDefaults(DynamicRegistryManager.Impl registries, CallbackInfoReturnable<DynamicRegistryManager.Impl> ci) {
		CaelumDimension.onAddRegistryDefaults(registries.get(Registry.DIMENSION_TYPE_KEY));
	}
}
