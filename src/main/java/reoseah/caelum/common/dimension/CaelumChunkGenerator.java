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
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
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
import reoseah.caelum.common.dimension.biome_source.CaelumBiomeSource;

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

		this.surfaceDepthNoise = new OctaveSimplexNoiseSampler(random, IntStream.rangeClosed(-3, 0));

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
		int chunkX = chunkPos.x;
		int chunkZ = chunkPos.z;
		ChunkRandom random = new ChunkRandom();
		random.setTerrainSeed(chunkX, chunkZ);

		int startX = chunkPos.getStartX();
		int startZ = chunkPos.getStartZ();
		BlockPos.Mutable pos = new BlockPos.Mutable();

		for (int dX = 0; dX < 16; ++dX) {
			for (int dZ = 0; dZ < 16; ++dZ) {
				int x = startX + dX;
				int z = startZ + dZ;
				int heightmap = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, dX, dZ) + 1;
				double surfaceDepth = this.surfaceDepthNoise.sample(x * 0.0625D, z * 0.0625D, 0.0625D, dX * 0.0625D) * 15.0D;
				region.getBiome(pos.set(startX + dX, heightmap, startZ + dZ))
						.buildSurface(random, chunk, x, z, heightmap, surfaceDepth, CaelumBlocks.AERRACK.getDefaultState(), Blocks.WATER.getDefaultState(), this.getSeaLevel(), region.getSeed());
			}
		}
	}

	public void populateNoise(WorldAccess world, StructureAccessor structures, Chunk chunk) {
		ChunkPos chunkPos = chunk.getPos();
		int chunkX = chunkPos.x;
		int chunkZ = chunkPos.z;
		int startX = chunkX << 4;
		int startZ = chunkZ << 4;

		double[][][] buffers = new double[2][this.noiseSizeZ + 1][this.noiseSizeY + 1];

		for (int z = 0; z < this.noiseSizeZ + 1; ++z) {
			buffers[0][z] = new double[this.noiseSizeY + 1];
			this.sampleNoiseColumn(buffers[0][z], chunkX * this.noiseSizeX, chunkZ * this.noiseSizeZ + z);
			buffers[1][z] = new double[this.noiseSizeY + 1];
		}

		ProtoChunk protoChunk = (ProtoChunk) chunk;
		Heightmap oceanFloorMap = protoChunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap worldSurfaceMap = protoChunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.Mutable pos = new BlockPos.Mutable();

		for (int regionX = 0; regionX < this.noiseSizeX; ++regionX) {
			for (int regionZ = 0; regionZ < this.noiseSizeZ + 1; ++regionZ) {
				this.sampleNoiseColumn(buffers[1][regionZ], chunkX * this.noiseSizeX + regionX + 1, chunkZ * this.noiseSizeZ + regionZ);
			}

			for (int regionZ = 0; regionZ < this.noiseSizeZ; ++regionZ) {
				ChunkSection chunkSection = protoChunk.getSection(15);
				chunkSection.lock();

				for (int regionY = this.noiseSizeY - 1; regionY >= 0; --regionY) {
					double noiseNW = buffers[0][regionZ][regionY];
					double noiseSW = buffers[0][regionZ + 1][regionY];
					double noiseNE = buffers[1][regionZ][regionY];
					double noiseSE = buffers[1][regionZ + 1][regionY];
					double noiseNextNW = buffers[0][regionZ][regionY + 1];
					double noiseNextSW = buffers[0][regionZ + 1][regionY + 1];
					double noiseNextNE = buffers[1][regionZ][regionY + 1];
					double noiseNextSE = buffers[1][regionZ + 1][regionY + 1];

					for (int dY = this.verticalNoiseResolution - 1; dY >= 0; --dY) {
						int y = regionY * this.verticalNoiseResolution + dY;
						int relativeY = y & 15;
						int sectionY = y >> 4;
						if (chunkSection.getYOffset() >> 4 != sectionY) {
							chunkSection.unlock();
							chunkSection = protoChunk.getSection(sectionY);
							chunkSection.lock();
						}

						double deltaY = (double) dY / (double) this.verticalNoiseResolution;
						double noiseCurrentNW = MathHelper.lerp(deltaY, noiseNW, noiseNextNW);
						double noiseCurrentNE = MathHelper.lerp(deltaY, noiseNE, noiseNextNE);
						double noiseCurrentSW = MathHelper.lerp(deltaY, noiseSW, noiseNextSW);
						double noiseCurrentSE = MathHelper.lerp(deltaY, noiseSE, noiseNextSE);

						for (int dX = 0; dX < this.horizontalNoiseResolution; ++dX) {
							int x = startX + regionX * this.horizontalNoiseResolution + dX;
							int relativeX = x & 15;
							double deltaX = (double) dX / (double) this.horizontalNoiseResolution;
							double noiseCurrentNorth = MathHelper.lerp(deltaX, noiseCurrentNW, noiseCurrentNE);
							double noiseCurrentSouth = MathHelper.lerp(deltaX, noiseCurrentSW, noiseCurrentSE);

							for (int dZ = 0; dZ < this.horizontalNoiseResolution; ++dZ) {
								int z = startZ + regionZ * this.horizontalNoiseResolution + dZ;
								int relativeZ = z & 15;
								double deltaZ = (double) dZ / (double) this.horizontalNoiseResolution;
								double noiseRaw = MathHelper.lerp(deltaZ, noiseCurrentNorth, noiseCurrentSouth);
								double noise = MathHelper.clamp(noiseRaw / 200.0D, -1.0D, 1.0D);

								BlockState state;
								if (noise > 0.0D) {
									state = this.defaultBlock;
								} else {
									state = AIR;
								}

								if (state != AIR) {
									if (state.getLuminance() != 0) {
										pos.set(x, y, z);
										protoChunk.addLightSource(pos);
									}

									chunkSection.setBlockState(relativeX, relativeY, relativeZ, state, false);
									oceanFloorMap.trackUpdate(relativeX, y, relativeZ, state);
									worldSurfaceMap.trackUpdate(relativeX, y, relativeZ, state);
								}
							}
						}
					}
				}

				chunkSection.unlock();
			}

			double[][] temp = buffers[0];
			buffers[0] = buffers[1];
			buffers[1] = temp;
		}
	}

	protected void sampleNoiseColumn(double[] buffer, int x, int z) {
		this.sampleNoiseColumn(buffer, x, z, 684.412D, 684.412D * 4, 684.412D / 80, 684.412D / 160, 64, -3000);
	}

	protected IslandData computeData(int x, int z) {
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

		BlockPos structurePos = CaelumDimensionHelper.locateIsland(this.seed, new BlockPos(x * 4, 0, z * 4), 10);

		float distX = structurePos == null ? 256 : Math.min(256, Math.abs(x - structurePos.getX() / 4F));
		float distZ = structurePos == null ? 256 : Math.min(256, Math.abs(z - structurePos.getZ() / 4F));

		double structureCoeff = distX * distX + distZ * distZ;

		return new IslandData(depthTotal, structureCoeff);
	}

	protected void sampleNoiseColumn(double[] buffer, int x, int z, double d, double e, double f, double g, int i, int j) {
		IslandData islandData = this.computeData(x, z);
		double maxHeight = (double) (this.getNoiseSizeY() - 4) / 2;
		double m = 8;

		for (int n = 0; n < this.getNoiseSizeY(); ++n) {
			double o = this.sampleNoise(x, n, z, d, e, f, g);
			o -= this.computeNoiseModifier(islandData, n);
			if ((double) n > maxHeight) {
				o = MathHelper.clampedLerp(o, (double) j, ((double) n - maxHeight) / (double) i);
			} else if ((double) n < m) {
				o = MathHelper.clampedLerp(o, -30.0D, (m - (double) n) / (m - 1.0D));
			}

			buffer[n] = o;
		}

	}

	protected static class IslandData {
		public final double islandSize;
		public final double structureCoeff;

		public IslandData(double islandSize, double structureCoeff) {
			this.islandSize = islandSize;
			this.structureCoeff = structureCoeff;
		}
	}

	private double computeNoiseModifier(IslandData islandData, int y) {
		double pointOfInterestEffect = Math.max(0, 10 - Math.sqrt(islandData.structureCoeff) / 3);
		double heightEffect = 0;
		if (y > 16) {
			heightEffect = Math.max((y - 16) / 6f, 1);
		} else {
			heightEffect = Math.max(y / 16f, 1);
		}
		heightEffect = -heightEffect * heightEffect;

		return 12 - 15 * islandData.islandSize + 10 * pointOfInterestEffect * heightEffect;
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
