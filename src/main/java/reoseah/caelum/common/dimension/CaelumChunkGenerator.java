package reoseah.caelum.common.dimension;

import java.util.Random;

import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import reoseah.caelum.common.biomes.FloatingIslandsBiome;

public class CaelumChunkGenerator extends SurfaceChunkGenerator<CaelumChunkGeneratorConfig> {
	private static final float[] KERNEL = Util.make(new float[25], values -> {
		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				values[dx + 2 + (dz + 2) * 5] = 10.0F / MathHelper.sqrt(dx * dx + dz * dz + 0.2F);
			}
		}
	});

	public static final int VERTICAL_RESOLUTION = 4;
	public static final int HORIZONTAL_RESOLUTION = 8;

	// same as ones in parent, because they are private
	protected final OctavePerlinNoiseSampler noise1;
	protected final OctavePerlinNoiseSampler noise2;
	protected final OctavePerlinNoiseSampler noiseMask;

	public CaelumChunkGenerator(IWorld world, BiomeSource biomeSource, CaelumChunkGeneratorConfig config) {
		super(world, biomeSource, VERTICAL_RESOLUTION, HORIZONTAL_RESOLUTION, 128, config, true);

		this.noise1 = new OctavePerlinNoiseSampler(this.random, -15, 0);
		this.noise2 = new OctavePerlinNoiseSampler(this.random, -15, 0);
		this.noiseMask = new OctavePerlinNoiseSampler(this.random, -7, 0);
	}

	@Override
	protected void sampleNoiseColumn(double[] buffer, int x, int z, double d, double e, double f, double g, int i, int j) {
		PointInfo pointInfo = this.computePointInfo(x, z);
		double maxHeight = this.method_16409();
		double minHeight = this.method_16410();

		for (int y = 0; y < this.getNoiseSizeY(); ++y) {
			double value = this.customSampleNoise(x, y, z, d, e, f, g);
			value -= this.computeNoiseModifier(pointInfo, y);
			if (y > maxHeight) {
				value = MathHelper.clampedLerp(value, j, (y - maxHeight) / i);
			} else if (y < minHeight) {
				value = MathHelper.clampedLerp(value, -30.0D, (minHeight - y) / (minHeight - 1.0D));
			}

			buffer[y] = value;
		}
	}

	protected double customSampleNoise(int x, int y, int z, double scaleXZ, double scaleY, double maskScaleXZ, double maskScaleY) {
		double sample1 = 0.0D;
		double sample2 = 0.0D;
		double maskSample = 0.0D;

		double amplitude = 1.0D;
		for (int i = 0; i < 16; ++i) {
			double u = OctavePerlinNoiseSampler.maintainPrecision(x * scaleXZ * amplitude);
			double v = OctavePerlinNoiseSampler.maintainPrecision(y * scaleY * amplitude);
			double w = OctavePerlinNoiseSampler.maintainPrecision(z * scaleXZ * amplitude);
			double p = scaleY * amplitude;
			PerlinNoiseSampler sampler1 = this.noise1.getOctave(i);
			if (sampler1 != null) {
				sample1 += sampler1.sample(u, v, w, p, y * p) / amplitude;
			}

			PerlinNoiseSampler sampler2 = this.noise2.getOctave(i);
			if (sampler2 != null) {
				sample2 += sampler2.sample(u, v, w, p, y * p) / amplitude;
			}

			if (i < 8) {
				PerlinNoiseSampler maskSampler = this.noiseMask.getOctave(i);
				if (maskSampler != null) {
					maskSample += maskSampler.sample(OctavePerlinNoiseSampler.maintainPrecision(x * maskScaleXZ * amplitude), OctavePerlinNoiseSampler.maintainPrecision(y * maskScaleY * amplitude), OctavePerlinNoiseSampler.maintainPrecision(z * maskScaleXZ * amplitude), maskScaleY * amplitude, y * maskScaleY * amplitude) / amplitude;
				}
			}

			amplitude /= 2.0D;
		}

		return MathHelper.clampedLerp(sample1 / 512.0D, sample2 / 512.0D, (maskSample / 10.0D + 1.0D) / 2.0D);
	}

	protected static class PointInfo {
		public final double islandSize;
		public final double structureCoeff;

		public PointInfo(double islandSize, double structureCoeff) {
			this.islandSize = islandSize;
			this.structureCoeff = structureCoeff;
		}
	}

	protected PointInfo computePointInfo(int x, int z) {
		float depthTotal = 0.0F;
		float weightTotal = 0.0F;
		float centerDepth = this.biomeSource.getBiomeForNoiseGen(x, 0, z).getDepth();

		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				Biome biome = this.biomeSource.getBiomeForNoiseGen(x + dx, 0, z + dz);
				float size = (biome instanceof FloatingIslandsBiome) ? ((FloatingIslandsBiome) biome).getIslandsModifier() : 0;

				float weight = KERNEL[dx + 2 + (dz + 2) * 5];
				if (biome.getDepth() > centerDepth) {
					weight /= 2.0F;
				}

				depthTotal += size * weight;
				weightTotal += weight;
			}
		}
		depthTotal /= weightTotal;

		BlockPos structurePos = LargeIslandHelper.locateIsland(this.getSeed(), new BlockPos(x * 2, 0, z * 2), 10);

		int distX = structurePos == null ? 256 : Math.min(256, Math.abs(x - structurePos.getX() / 4));
		int distZ = structurePos == null ? 256 : Math.min(256, Math.abs(z - structurePos.getZ() / 4));

		double structureCoeff = distX * distX + distZ * distZ;

		return new PointInfo(depthTotal, structureCoeff);
	}

	private double computeNoiseModifier(PointInfo pointInfo, int y) {
		double pointOfInterestEffect = Math.max(0, 10 - Math.sqrt(pointInfo.structureCoeff) / 3);
		double heightEffect = 0;
		if (y > 16) {
			heightEffect = Math.max((y - 16) / 6f, 1);
		} else {
			heightEffect = Math.max(y / 16f, 1);
		}
		heightEffect = -heightEffect * heightEffect;

		return 12 - 15 * pointInfo.islandSize + 10 * pointOfInterestEffect * heightEffect;
	}

	@Override
	protected void sampleNoiseColumn(double[] buffer, int x, int z) {
		this.sampleNoiseColumn(buffer, x, z, 684.412D, 684.412D * 4, 684.412D / 80, 684.412D / 160, 64, -3000);
	}

	@Override
	public int getSpawnHeight() {
		return 60;
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	protected void buildBedrock(Chunk chunk, Random random) {
		// no op
	}

	@Override
	protected double method_16409() {
		return ((int) super.method_16409()) / 2;
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
