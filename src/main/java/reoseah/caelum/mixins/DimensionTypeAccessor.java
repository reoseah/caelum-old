package reoseah.caelum.mixins;

import java.util.Optional;
import java.util.OptionalLong;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.source.BiomeAccessType;
import net.minecraft.world.dimension.DimensionType;

@Mixin(DimensionType.class)
public interface DimensionTypeAccessor {
	@Invoker(value = "<init>")
	public static DimensionType create(String suffix, OptionalLong fixedTime, boolean hasSkylight, boolean hasCeiling, boolean ultrawarm, boolean natural, boolean shrunk, boolean hasEnderDragonFight, BiomeAccessType biomeAccessType, Optional<RegistryKey<DimensionType>> optional, float ambientLight) {
		return null;
	}
}
