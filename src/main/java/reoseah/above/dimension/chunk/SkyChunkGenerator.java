package reoseah.above.dimension.chunk;

import java.util.Random;
import java.util.stream.IntStream;

import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;

public class SkyChunkGenerator extends SurfaceChunkGenerator<SkyChunkGeneratorConfig> {
	private static final float[] KERNEL = Util.make(new float[25], values -> {
		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				values[dx + 2 + (dz + 2) * 5] = 10.0F / MathHelper.sqrt(dx * dx + dz * dz + 0.2F);
			}
		}
	});

	protected final OctavePerlinNoiseSampler noise1;
	protected final OctavePerlinNoiseSampler noise2;
	protected final OctavePerlinNoiseSampler noiseMask;

	public SkyChunkGenerator(IWorld world, BiomeSource biomeSource, SkyChunkGeneratorConfig config) {
		super(world, biomeSource, 4, 8, 128, config, false);

		this.noise1 = new OctavePerlinNoiseSampler(this.random, IntStream.rangeClosed(-15, 0));
		this.noise2 = new OctavePerlinNoiseSampler(this.random, IntStream.rangeClosed(-15, 0));
		this.noiseMask = new OctavePerlinNoiseSampler(this.random, IntStream.rangeClosed(-7, 0));
	}

	@Override
	protected void sampleNoiseColumn(double[] buffer, int x, int z, double scaleX, double scaleY, double scaleZ, double g, int i, int j) {
		PointInfo pointInfo = this.computePointInfo(x, z);
		double maxHeight = this.method_16409();
		double minHeight = this.method_16410();

		for (int y = 0; y < this.getNoiseSizeY(); ++y) {
			double value = this.sampleNoise(x, y, z, scaleX, scaleY, scaleZ, g);
			value -= this.computeNoiseModifier(pointInfo, y);
			if (y > maxHeight) {
				value = MathHelper.clampedLerp(value, j, (y - maxHeight) / i);
			} else if (y < minHeight) {
				value = MathHelper.clampedLerp(value, -30.0D, (minHeight - y) / (minHeight - 1.0D));
			}

			buffer[y] = value;
		}
	}

	protected static class PointInfo {
		public final double islandSize;
		public final double distSqToPointOfInterest;

		public PointInfo(double islandSize, double distSqToPointOfInterest) {
			this.islandSize = islandSize;
			this.distSqToPointOfInterest = distSqToPointOfInterest;
		}
	}

	protected PointInfo computePointInfo(int x, int z) {
		float depthTotal = 0.0F;
		float weightTotal = 0.0F;
		float centerDepth = this.biomeSource.getBiomeForNoiseGen(x, 0, z).getDepth();

		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				Biome biome = this.biomeSource.getBiomeForNoiseGen(x + dx, 0, z + dz);
				float depth = biome.getDepth();

				float weight = KERNEL[dx + 2 + (dz + 2) * 5];
				if (biome.getDepth() > centerDepth) {
					weight /= 2.0F;
				}

				depthTotal += depth * weight;
				weightTotal += weight;
			}
		}
		depthTotal /= weightTotal;

		// place points each 1000 blocks
		int dX = (Math.abs(x) + 256) % 512 - 256;
		int dZ = (Math.abs(z) + 256) % 512 - 256;

		return new PointInfo(depthTotal, dX * dX + dZ * dZ);
	}

	private double computeNoiseModifier(PointInfo pointInfo, int y) {
		double pointOfInterestEffect = Math.max(0, 10 - Math.sqrt(pointInfo.distSqToPointOfInterest) / 3);
		double heightEffect = 0;
		if (y > 16) {
			heightEffect = Math.max((y - 16) / 6f, 1);
		} else {
			heightEffect = Math.max(y / 16f, 1);
		}
		heightEffect = -heightEffect * heightEffect;

		return 12 - 15 * pointInfo.islandSize + 10 * pointOfInterestEffect * heightEffect;
	}

	protected double sampleNoise(int x, int y, int z, double d, double e, double f, double g) {
		double h = 0.0D;
		double i = 0.0D;
		double j = 0.0D;
		double k = 1.0D;

		for (int l = 0; l < 16; ++l) {
			double m = OctavePerlinNoiseSampler.maintainPrecision(x * d * k);
			double n = OctavePerlinNoiseSampler.maintainPrecision(y * e * k);
			double o = OctavePerlinNoiseSampler.maintainPrecision(z * d * k);
			double p = e * k;
			PerlinNoiseSampler perlinNoiseSampler = this.noise1.getOctave(l);
			if (perlinNoiseSampler != null) {
				h += perlinNoiseSampler.sample(m, n, o, p, y * p) / k;
			}

			PerlinNoiseSampler perlinNoiseSampler2 = this.noise2.getOctave(l);
			if (perlinNoiseSampler2 != null) {
				i += perlinNoiseSampler2.sample(m, n, o, p, y * p) / k;
			}

			if (l < 8) {
				PerlinNoiseSampler perlinNoiseSampler3 = this.noiseMask.getOctave(l);
				if (perlinNoiseSampler3 != null) {
					j += perlinNoiseSampler3.sample(OctavePerlinNoiseSampler.maintainPrecision(x * f * k), OctavePerlinNoiseSampler.maintainPrecision(y * g * k), OctavePerlinNoiseSampler.maintainPrecision(z * f * k), g * k, y * g * k) / k;
				}
			}

			k /= 2.0D;
		}

		return MathHelper.clampedLerp(h / 512.0D, i / 512.0D, (j / 10.0D + 1.0D) / 2.0D);
	}

	@Override
	protected void sampleNoiseColumn(double[] values, int x, int z) {
		this.sampleNoiseColumn(values, x, z, 684.412D, 684.412D * 4, 684.412D / 80, 684.412D / 160, 3, -10);
	}

	@Override
	public int getSpawnHeight() {
		return 50;
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	protected void buildBedrock(Chunk chunk, Random random) {

	}

	@Override
	protected double method_16409() {
		return (this.getNoiseSizeY() - 4) / 2;
	}

	@Override
	protected double method_16410() {
		return 8.0D;
	}

	@Override
	protected double[] computeNoiseRange(int x, int z) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected double computeNoiseFalloff(double depth, double scale, int y) {
		throw new UnsupportedOperationException();
	}
}
