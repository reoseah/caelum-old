package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

@Mixin(Biomes.class)
public interface BiomesInvoker {
	@Accessor("BIOMES")
	public static Int2ObjectMap<RegistryKey<Biome>> getBIOMES() {
		return null;
	}
}
