package reoseah.caelum.mixins;

import java.util.OptionalLong;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.BiomeAccessType;
import net.minecraft.world.dimension.DimensionType;

@Mixin(DimensionType.class)
public interface DimensionTypeInvoker {
	@Invoker("<init>")
	public static DimensionType create(OptionalLong fixedTime, boolean hasSkylight, boolean hasCeiling, boolean ultrawarm, boolean natural, double coordinateScale, boolean hasEnderDragonFight, boolean piglinSafe, boolean bedWorks, boolean respawnAnchorWorks, boolean hasRaids, int logicalHeight, BiomeAccessType biomeAccessType, Identifier infiniburn, Identifier skyProperties, float ambientLight) {
		return null;
	}
}
