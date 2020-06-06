package reoseah.caelum.common.world.chunk_generator;

import java.util.function.Supplier;

import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

public class CaelumChunkGeneratorType extends ChunkGeneratorType<CaelumChunkGeneratorConfig, CaelumChunkGenerator> {
	public CaelumChunkGeneratorType(boolean buffetScreen, Supplier<CaelumChunkGeneratorConfig> configSupplier) {
		super(null, buffetScreen, configSupplier);
	}

	@Override
	public CaelumChunkGenerator create(World world, BiomeSource biomeSource, CaelumChunkGeneratorConfig config) {
		return new CaelumChunkGenerator(world, biomeSource, config);
	}
}
