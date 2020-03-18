package reoseah.skyland.dimension.chunk;

import java.util.function.Supplier;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

public class SkylandGeneratorType extends ChunkGeneratorType<SkylandConfig, SkylandGenerator> {
	public SkylandGeneratorType(boolean buffetScreen, Supplier<SkylandConfig> configSupplier) {
		super(null, buffetScreen, configSupplier);
	}

	@Override
	public SkylandGenerator create(IWorld world, BiomeSource biomeSource, SkylandConfig config) {
		return new SkylandGenerator(world, biomeSource, config);
	}

}
