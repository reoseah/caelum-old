package reoseah.caelum.common.dimension;

import java.util.Optional;
import java.util.OptionalLong;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import reoseah.caelum.mixins.DimensionTypeAccessor;

public class CaelumDimensionType extends DimensionType {
	public static final RegistryKey<World> WORLD_KEY = RegistryKey.of(Registry.DIMENSION, new Identifier("caelum:caelum"));
	public static final RegistryKey<DimensionType> REGISTRY_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier("caelum:caelum"));

	public static final DimensionType INSTANCE = DimensionTypeAccessor.create("_caelum",
			/* fixedTime */ OptionalLong.empty(),
			/* hasSkylight */ true,
			/* hasCeiling */ false,
			/* ultrawarm */ false,
			/* natural */ false,
			/* shrunk */ false,
			/* hasEnderDragonFight */ false,
			HorizontalVoronoiBiomeAccessType.INSTANCE,
			Optional.of(REGISTRY_KEY),
			/* ambientLight */ 0.1F);
	
	public CaelumDimensionType() {
		super("_caelum",
				/* fixedTime */ OptionalLong.empty(),
				/* hasSkylight */ true,
				/* hasCeiling */ false,
				/* ultrawarm */ false,
				/* natural */ false,
				/* shrunk */ false,
				/* hasEnderDragonFight */ false,
				HorizontalVoronoiBiomeAccessType.INSTANCE,
				Optional.of(REGISTRY_KEY),
				/* ambientLight */ 0.1F);
	}
}
