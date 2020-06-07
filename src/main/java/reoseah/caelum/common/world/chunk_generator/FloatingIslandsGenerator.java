package reoseah.caelum.common.world.chunk_generator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import reoseah.caelum.common.world.CaelumDimensionHelper;
import reoseah.caelum.common.world.biomes.FloatingIslandsBiome;
import reoseah.caelum.common.world.chunk_generator.FloatingIslandsGenerator.FloatingIslandData;

public class FloatingIslandsGenerator extends AbstractLandGenerator<FloatingIslandData> {
	protected final BiomeSource biomeSource;
	protected final long seed;

	public FloatingIslandsGenerator(BiomeSource biomeSource, long seed, ChunkRandom random, int worldHeight, int verticalResolution, int horizontalResolution, double verticalScale, double horizontalScale) {
		super(random, worldHeight, verticalResolution, horizontalResolution, verticalScale, horizontalScale);
		this.biomeSource = biomeSource;
		this.seed = seed;
	}

	private static final float[] SMOOTHING_KERNEL;
	static {
		SMOOTHING_KERNEL = new float[25];
		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				SMOOTHING_KERNEL[dx + 2 + (dz + 2) * 5] = 10.0F / MathHelper.sqrt(dx * dx + dz * dz + 0.2F);
			}
		}
	}

	protected static class FloatingIslandData {
		public final double landThresholdModifier;
		public final double spawnIslandDistSq;

		public FloatingIslandData(double landThresholdModifier, double spawnIslandDistSq) {
			this.landThresholdModifier = landThresholdModifier;
			this.spawnIslandDistSq = spawnIslandDistSq;
		}
	}

	@Override
	protected FloatingIslandData createColumnData(int x, int z) {
		float landThreshold = 0.0F;
		float weights = 0.0F;

		float centerThreshold = ((FloatingIslandsBiome) this.biomeSource.getBiomeForNoiseGen(x, 0, z))
				.getLandThresholdModifier();

		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				Biome biome = this.biomeSource.getBiomeForNoiseGen(x + dx, 0, z + dz);
				float biomeThresholdModifier = ((FloatingIslandsBiome) biome).getLandThresholdModifier();

				float biomeWeight = SMOOTHING_KERNEL[dx + 2 + (dz + 2) * 5];
				if (biome.getDepth() > centerThreshold) {
					biomeWeight /= 2.0F;
				}

				landThreshold += biomeThresholdModifier * biomeWeight;
				weights += biomeWeight;
			}
		}
		landThreshold /= weights;

		BlockPos structurePos = CaelumDimensionHelper.locateIsland(this.seed, new BlockPos(x * 2, 0, z * 2), 10);

		int distX = structurePos == null ? 256 : Math.min(256, Math.abs(x - structurePos.getX() / 4));
		int distZ = structurePos == null ? 256 : Math.min(256, Math.abs(z - structurePos.getZ() / 4));

		double islandDistSq = distX * distX + distZ * distZ;

		return new FloatingIslandData(landThreshold, islandDistSq);
	}

	@Override
	protected double modifyNoise(double noise, FloatingIslandData data, int y) {
		double spawnIslandModifier = Math.max(0, 10 - Math.max(2, Math.sqrt(data.spawnIslandDistSq) / 3));
		double heightModifier = 0;
		if (y > 16) {
			heightModifier = Math.max((y - 16) / 6f, 1);
		} else {
			heightModifier = Math.max(y / 16f, 1);
		}
		heightModifier = -heightModifier * heightModifier;

		return noise - 8 + data.landThresholdModifier - 10 * spawnIslandModifier * heightModifier;
	}
}
