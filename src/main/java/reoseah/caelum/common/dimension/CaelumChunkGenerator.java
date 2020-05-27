package reoseah.caelum.common.dimension;

import java.util.stream.IntStream;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.PrimitiveCodec;

import net.minecraft.class_5311;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.NoiseSampler;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.biomes.FloatingIslandsBiome;

public class CaelumChunkGenerator extends ChunkGenerator {
	public static final Codec<CaelumChunkGenerator> CODEC = PrimitiveCodec.LONG.fieldOf("seed")
			.xmap(
					seed -> new CaelumChunkGenerator(new CaelumBiomeSource(seed), seed),
					generator -> generator.seed)
			.codec();

	private static final float[] KERNEL;

	static {
		KERNEL = new float[25];
		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				KERNEL[dx + 2 + (dz + 2) * 5] = 10.0F / MathHelper.sqrt(dx * dx + dz * dz + 0.2F);
			}
		}
	}

	public static final int VERTICAL_RESOLUTION = 4;
	public static final int HORIZONTAL_RESOLUTION = 8;

	protected final long seed;

	protected final OctavePerlinNoiseSampler noise1;
	protected final OctavePerlinNoiseSampler noise2;
	protected final OctavePerlinNoiseSampler noiseMask;

	private final NoiseSampler surfaceDepthNoise;

	public CaelumChunkGenerator(BiomeSource biomeSource, long seed) {
		super(biomeSource, new class_5311(false));
		this.seed = seed;

		ChunkRandom random = new ChunkRandom(this.seed);
		this.noise1 = new OctavePerlinNoiseSampler(random, IntStream.rangeClosed(-15, 0));
		this.noise2 = new OctavePerlinNoiseSampler(random, IntStream.rangeClosed(-15, 0));
		this.noiseMask = new OctavePerlinNoiseSampler(random, IntStream.rangeClosed(-7, 0));

		this.surfaceDepthNoise = new OctavePerlinNoiseSampler(random, IntStream.rangeClosed(-3, 0));

	}

	@Override
	protected Codec<? extends ChunkGenerator> method_28506() {
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return new CaelumChunkGenerator(new CaelumBiomeSource(seed), seed);
	}

	public void buildSurface(ChunkRegion region, Chunk chunk) {
		ChunkPos chunkPos = chunk.getPos();
		int i = chunkPos.x;
		int j = chunkPos.z;
		ChunkRandom chunkRandom = new ChunkRandom();
		chunkRandom.setTerrainSeed(i, j);
		ChunkPos chunkPos2 = chunk.getPos();
		int k = chunkPos2.getStartX();
		int l = chunkPos2.getStartZ();
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for (int m = 0; m < 16; ++m) {
			for (int n = 0; n < 16; ++n) {
				int o = k + m;
				int p = l + n;
				int q = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, m, n) + 1;
				double e = this.surfaceDepthNoise.sample((double) o * 0.0625D, (double) p * 0.0625D, 0.0625D, (double) m * 0.0625D) * 15.0D;
				region.getBiome(mutable.set(k + m, q, l + n))
						.buildSurface(chunkRandom, chunk, o, p, q, e, CaelumBlocks.AERRACK.getDefaultState(), Blocks.WATER.getDefaultState(), this.getSeaLevel(), region.getSeed());
			}
		}
	}

	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
		int noiseSizeZ = 2, noiseSizeX = 2, noiseSizeY = 128 / 4;

		ChunkPos chunkPos = chunk.getPos();
		int j = chunkPos.x;
		int k = chunkPos.z;
		int l = j << 4;
		int m = k << 4;

		double[][][] ds = new double[2][noiseSizeZ + 1][noiseSizeY + 1];

		for (int q = 0; q < noiseSizeZ + 1; ++q) {
			ds[0][q] = new double[noiseSizeY + 1];
			this.getColumnSample(ds[0][q], j * noiseSizeX, k * noiseSizeZ + q);
			ds[1][q] = new double[noiseSizeY + 1];
		}

		ProtoChunk protoChunk = (ProtoChunk) chunk;
		Heightmap heightmap = protoChunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap heightmap2 = protoChunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for (int r = 0; r < noiseSizeX; ++r) {
			int t;
			for (t = 0; t < noiseSizeZ + 1; ++t) {
				this.getColumnSample(ds[1][t], j * noiseSizeX + r + 1, k * noiseSizeZ + t);
			}

			for (t = 0; t < noiseSizeZ; ++t) {
				ChunkSection chunkSection = protoChunk.getSection(15);
				chunkSection.lock();

				for (int u = noiseSizeY - 1; u >= 0; --u) {
					double d = ds[0][t][u];
					double e = ds[0][t + 1][u];
					double f = ds[1][t][u];
					double g = ds[1][t + 1][u];
					double h = ds[0][t][u + 1];
					double v = ds[0][t + 1][u + 1];
					double w = ds[1][t][u + 1];
					double x = ds[1][t + 1][u + 1];

					for (int y = VERTICAL_RESOLUTION - 1; y >= 0; --y) {
						int z = u * VERTICAL_RESOLUTION + y;
						int aa = z & 15;
						int ab = z >> 4;
						if (chunkSection.getYOffset() >> 4 != ab) {
							chunkSection.unlock();
							chunkSection = protoChunk.getSection(ab);
							chunkSection.lock();
						}

						double ac = (double) y / (double) VERTICAL_RESOLUTION;
						double ad = MathHelper.lerp(ac, d, h);
						double ae = MathHelper.lerp(ac, f, w);
						double af = MathHelper.lerp(ac, e, v);
						double ag = MathHelper.lerp(ac, g, x);

						for (int ah = 0; ah < HORIZONTAL_RESOLUTION; ++ah) {
							int ai = l + r * HORIZONTAL_RESOLUTION + ah;
							int aj = ai & 15;
							double ak = (double) ah / (double) HORIZONTAL_RESOLUTION;
							double al = MathHelper.lerp(ak, ad, ae);
							double am = MathHelper.lerp(ak, af, ag);

							for (int an = 0; an < HORIZONTAL_RESOLUTION; ++an) {
								int ao = m + t * HORIZONTAL_RESOLUTION + an;
								int ap = ao & 15;
								double aq = (double) an / (double) HORIZONTAL_RESOLUTION;
								double ar = MathHelper.lerp(aq, al, am);
								double as = MathHelper.clamp(ar / 200.0D, -1.0D, 1.0D);

								BlockState blockState3;
								if (as > 0.0D) {
									blockState3 = CaelumBlocks.AERRACK.getDefaultState();
								} else {
									blockState3 = Blocks.AIR.getDefaultState();
								}

								if (blockState3 != Blocks.AIR.getDefaultState()) {
									if (blockState3.getLuminance() != 0) {
										mutable.set(ai, z, ao);
										protoChunk.addLightSource(mutable);
									}

									chunkSection.setBlockState(aj, aa, ap, blockState3, false);
									heightmap.trackUpdate(aj, z, ap, blockState3);
									heightmap2.trackUpdate(aj, z, ap, blockState3);
								}
							}
						}
					}
				}

				chunkSection.unlock();
			}

			double[][] es = ds[0];
			ds[0] = ds[1];
			ds[1] = es;
		}

	}

	@Override
	public int getHeight(int x, int z, Heightmap.Type type) {
		return 10;
	}

	public BlockView getColumnSample(int x, int z) {
		BlockState[] blockStates = new BlockState[128];
		double[] buffer = new double[128];
		this.sampleNoiseColumn(buffer, x, z, 684.412D, 684.412D * 4, 684.412D / 80, 684.412D / 160, 64, -3000);
		for (int i = 0; i < 128; i++) {
			if (buffer[i] > 0) {
				blockStates[i] = CaelumBlocks.AERRACK.getDefaultState();
			} else {
				blockStates[i] = Blocks.AIR.getDefaultState();
			}
		}
		return new VerticalBlockSample(blockStates);
	}

	public void getColumnSample(double[] buffer, int x, int z) {
		this.sampleNoiseColumn(buffer, x, z, 684.412D, 684.412D * 4, 684.412D / 80, 684.412D / 160, 64, -3000);
	}

	protected void sampleNoiseColumn(double[] buffer, int x, int z, double d, double e, double f, double g, int i, int j) {
		PointInfo pointInfo = this.computePointInfo(x, z);
		double maxHeight = 64;
		double minHeight = 8;

		for (int y = 0; y < 128 / VERTICAL_RESOLUTION; ++y) {
			double value = this.sampleNoise(x, y, z, d, e, f, g);
			value -= this.computeNoiseModifier(pointInfo, y);
			if (y > maxHeight) {
				value = MathHelper.clampedLerp(value, j, (y - maxHeight) / i);
			} else if (y < minHeight) {
				value = MathHelper.clampedLerp(value, -30.0D, (minHeight - y) / (minHeight - 1.0D));
			}

			buffer[y] = value;
		}
	}

	protected double sampleNoise(int x, int y, int z, double scaleXZ, double scaleY, double maskScaleXZ, double maskScaleY) {
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

		BlockPos structurePos = LargeIslandHelper.locateIsland(this.seed, new BlockPos(x * 2, 0, z * 2), 10);

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
	public int getSpawnHeight() {
		return 60;
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

}
