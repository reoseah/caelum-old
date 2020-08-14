package reoseah.caelum.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class CaelumBiomes {
	public static final RegistryKey<Biome> CAELUM_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("caelum:forest"));
	public static final Biome CAELUM_FOREST = CaelumBiomesCreator.createForest(0, 0);

	public static void register() {
		Registry.register(BuiltinRegistries.BIOME, new Identifier("caelum:forest"), CAELUM_FOREST);
	}
}
