package reoseah.caelum.mixins;

import java.util.LinkedHashMap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.datafixers.util.Pair;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import reoseah.caelum.common.dimension.CaelumBiomeSource;
import reoseah.caelum.common.dimension.CaelumChunkGenerator;
import reoseah.caelum.common.dimension.CaelumDimensionType;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {
	@Inject(method = "method_28517", at = @At("RETURN"))
	private static void method_28517(long seed, CallbackInfoReturnable<LinkedHashMap<RegistryKey<DimensionType>, Pair<DimensionType, ChunkGenerator>>> callback) {
		LinkedHashMap<RegistryKey<DimensionType>, Pair<DimensionType, ChunkGenerator>> map = callback.getReturnValue();
		map.put(CaelumDimensionType.REGISTRY_KEY, Pair.of(CaelumDimensionType.INSTANCE, new CaelumChunkGenerator(new CaelumBiomeSource(seed), seed)));
	}
}
