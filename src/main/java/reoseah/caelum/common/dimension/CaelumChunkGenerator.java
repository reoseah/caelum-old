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
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
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

	private final int verticalNoiseResolution;
	private final int horizontalNoiseResolution;
	private final int noiseSizeX;
	private final int noiseSizeY;
	private final int noiseSizeZ;
	protected final BlockState defaultBlock;
	protected final BlockState defaultFluid;
	private static final BlockState AIR = Blocks.AIR.getDefaultState();
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

		int worldHeight = 128;

		this.verticalNoiseResolution = 8;
		this.horizontalNoiseResolution = 4;

		this.noiseSizeX = 16 / this.horizontalNoiseResolution;
		this.noiseSizeY = worldHeight / this.verticalNoiseResolution;
		this.noiseSizeZ = 16 / this.horizontalNoiseResolution;

		this.defaultBlock = CaelumBlocks.AERRACK.getDefaultState();
		this.defaultFluid = Blocks.WATER.getDefaultState();
	}

	@Override
	protected Codec<? extends ChunkGenerator> method_28506() {
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return new CaelumChunkGenerator(new CaelumBiomeSource(seed), seed);
	}

	@Override
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

	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
		int i = this.getSeaLevel();
		ChunkPos chunkPos = chunk.getPos();
		int j = chunkPos.x;
		int k = chunkPos.z;
		int l = j << 4;
		int m = k << 4;

		double[][][] ds = new double[2][this.noiseSizeZ + 1][this.noiseSizeY + 1];

		for (int q = 0; q < this.noiseSizeZ + 1; ++q) {
			ds[0][q] = new double[this.noiseSizeY + 1];
			this.sampleNoiseColumn(ds[0][q], j * this.noiseSizeX, k * this.noiseSizeZ + q);
			ds[1][q] = new double[this.noiseSizeY + 1];
		}

		ProtoChunk protoChunk = (ProtoChunk) chunk;
		Heightmap heightmap = protoChunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap heightmap2 = protoChunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for (int r = 0; r < this.noiseSizeX; ++r) {
			int t;
			for (t = 0; t < this.noiseSizeZ + 1; ++t) {
				this.sampleNoiseColumn(ds[1][t], j * this.noiseSizeX + r + 1, k * this.noiseSizeZ + t);
			}

			for (t = 0; t < this.noiseSizeZ; ++t) {
				ChunkSection chunkSection = protoChunk.getSection(15);
				chunkSection.lock();

				for (int u = this.noiseSizeY - 1; u >= 0; --u) {
					double d = ds[0][t][u];
					double e = ds[0][t + 1][u];
					double f = ds[1][t][u];
					double g = ds[1][t + 1][u];
					double h = ds[0][t][u + 1];
					double v = ds[0][t + 1][u + 1];
					double w = ds[1][t][u + 1];
					double x = ds[1][t + 1][u + 1];

					for (int y = this.verticalNoiseResolution - 1; y >= 0; --y) {
						int z = u * this.verticalNoiseResolution + y;
						int aa = z & 15;
						int ab = z >> 4;
						if (chunkSection.getYOffset() >> 4 != ab) {
							chunkSection.unlock();
							chunkSection = protoChunk.getSection(ab);
							chunkSection.lock();
						}

						double ac = (double) y / (double) this.verticalNoiseResolution;
						double ad = MathHelper.lerp(ac, d, h);
						double ae = MathHelper.lerp(ac, f, w);
						double af = MathHelper.lerp(ac, e, v);
						double ag = MathHelper.lerp(ac, g, x);

						for (int ah = 0; ah < this.horizontalNoiseResolution; ++ah) {
							int ai = l + r * this.horizontalNoiseResolution + ah;
							int aj = ai & 15;
							double ak = (double) ah / (double) this.horizontalNoiseResolution;
							double al = MathHelper.lerp(ak, ad, ae);
							double am = MathHelper.lerp(ak, af, ag);

							for (int an = 0; an < this.horizontalNoiseResolution; ++an) {
								int ao = m + t * this.horizontalNoiseResolution + an;
								int ap = ao & 15;
								double aq = (double) an / (double) this.horizontalNoiseResolution;
								double ar = MathHelper.lerp(aq, al, am);
								double as = MathHelper.clamp(ar / 200.0D, -1.0D, 1.0D);

								int ax;
								int ay;
								int av;

								BlockState blockState3;
								if (as > 0.0D) {
									blockState3 = this.defaultBlock;
								} else if (z < i) {
									blockState3 = this.defaultFluid;
								} else {
									blockState3 = AIR;
								}

								if (blockState3 != AIR) {
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

	protected void sampleNoiseColumn(double[] buffer, int x, int z) {
		this.sampleNoiseColumn(buffer, x, z, 684.412D, 684.412D * 4, 684.412D / 80, 684.412D / 160, 64, -3000);
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

	protected void sampleNoiseColumn(double[] buffer, int x, int z, double d, double e, double f, double g, int i, int j) {
		PointInfo pointInfo = this.computePointInfo(x, z);
		double l = (double) (this.getNoiseSizeY() - 4) / 2;
		double m = 8;

		for (int n = 0; n < this.getNoiseSizeY(); ++n) {
			double o = this.sampleNoise(x, n, z, d, e, f, g);
			o -= this.computeNoiseModifier(pointInfo, n);
			if ((double) n > l) {
				o = MathHelper.clampedLerp(o, (double) j, ((double) n - l) / (double) i);
			} else if ((double) n < m) {
				o = MathHelper.clampedLerp(o, -30.0D, (m - (double) n) / (m - 1.0D));
			}

			buffer[n] = o;
		}

	}

	protected static class PointInfo {
		public final double islandSize;
		public final double structureCoeff;

		public PointInfo(double islandSize, double structureCoeff) {
			this.islandSize = islandSize;
			this.structureCoeff = structureCoeff;
		}
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

	private double sampleNoise(int x, int y, int z, double d, double e, double f, double g) {
		double h = 0.0D;
		double i = 0.0D;
		double j = 0.0D;
		double k = 1.0D;

		for (int l = 0; l < 16; ++l) {
			double m = OctavePerlinNoiseSampler.maintainPrecision((double) x * d * k);
			double n = OctavePerlinNoiseSampler.maintainPrecision((double) y * e * k);
			double o = OctavePerlinNoiseSampler.maintainPrecision((double) z * d * k);
			double p = e * k;
			PerlinNoiseSampler perlinNoiseSampler = this.noise1.getOctave(l);
			if (perlinNoiseSampler != null) {
				h += perlinNoiseSampler.sample(m, n, o, p, (double) y * p) / k;
			}

			PerlinNoiseSampler perlinNoiseSampler2 = this.noise2.getOctave(l);
			if (perlinNoiseSampler2 != null) {
				i += perlinNoiseSampler2.sample(m, n, o, p, (double) y * p) / k;
			}

			if (l < 8) {
				PerlinNoiseSampler perlinNoiseSampler3 = this.noiseMask.getOctave(l);
				if (perlinNoiseSampler3 != null) {
					j += perlinNoiseSampler3.sample(OctavePerlinNoiseSampler.maintainPrecision((double) x * f * k), OctavePerlinNoiseSampler.maintainPrecision((double) y * g * k), OctavePerlinNoiseSampler.maintainPrecision((double) z * f * k), g * k, (double) y * g * k) / k;
				}
			}

			k /= 2.0D;
		}

		return MathHelper.clampedLerp(h / 512.0D, i / 512.0D, (j / 10.0D + 1.0D) / 2.0D);
	}

	public int getNoiseSizeY() {
		return this.noiseSizeY + 1;
	}

	@Override
	public int getHeight(int x, int z, Type heightmapType) {
		return 0;
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public BlockView getColumnSample(int x, int z) {
		return null;
	}
}
