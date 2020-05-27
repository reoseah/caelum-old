package reoseah.caelum.common.dimension;

import java.util.Optional;
import java.util.OptionalLong;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;

public class CaelumDimensionType extends DimensionType {
	public static final RegistryKey<DimensionType> REGISTRY_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier("caelum:caelum"));

	public static final DimensionType INSTANCE = new CaelumDimensionType();

	public CaelumDimensionType() {
		super("_suffix",
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

	public static void register() {

	}

}
