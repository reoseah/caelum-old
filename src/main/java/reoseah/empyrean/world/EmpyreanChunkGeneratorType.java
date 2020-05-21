package reoseah.empyrean.world;

import java.util.function.Supplier;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

public class EmpyreanChunkGeneratorType extends ChunkGeneratorType<EmpyreanChunkGeneratorConfig, EmpyreanChunkGenerator> {
	public EmpyreanChunkGeneratorType(boolean buffetScreen, Supplier<EmpyreanChunkGeneratorConfig> configSupplier) {
		super(null, buffetScreen, configSupplier);
	}

	@Override
	public EmpyreanChunkGenerator create(IWorld world, BiomeSource biomeSource, EmpyreanChunkGeneratorConfig config) {
		return new EmpyreanChunkGenerator(world, biomeSource, config);
	}
}
