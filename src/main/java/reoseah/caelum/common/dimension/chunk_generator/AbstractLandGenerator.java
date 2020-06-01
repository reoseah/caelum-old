package reoseah.caelum.common.dimension.chunk_generator;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.ChunkRandom;
import reoseah.caelum.common.CaelumBlocks;

public abstract class AbstractLandGenerator<T> {
	protected final OctavePerlinNoiseSampler noise1;
	protected final OctavePerlinNoiseSampler noise2;
	protected final OctavePerlinNoiseSampler noiseMask;

	private final int verticalNoiseResolution;
	private final int horizontalNoiseResolution;
	private final int noiseSizeX;
	private final int noiseSizeY;
	private final int noiseSizeZ;

	private final double verticalScale;
	private final double horizontalScale;

	public AbstractLandGenerator(ChunkRandom random, int worldHeight, int verticalResolution, int horizontalResolution, double verticalScale, double horizontalScale) {
		this.noise1 = new OctavePerlinNoiseSampler(random, 15, 0);
		this.noise2 = new OctavePerlinNoiseSampler(random, 15, 0);
		this.noiseMask = new OctavePerlinNoiseSampler(random, 7, 0);

		this.verticalNoiseResolution = 8;
		this.horizontalNoiseResolution = 4;
		this.noiseSizeX = 16 / this.horizontalNoiseResolution;
		this.noiseSizeY = worldHeight / this.verticalNoiseResolution;
		this.noiseSizeZ = 16 / this.horizontalNoiseResolution;

		this.verticalScale = verticalScale;
		this.horizontalScale = horizontalScale;
	}

	public void generate(ChunkPos chunkPos, ProtoChunk protoChunk, Heightmap oceanFloorMap, Heightmap worldSurfaceMap) {
		int chunkX = chunkPos.x;
		int chunkZ = chunkPos.z;
		int startX = chunkX << 4;
		int startZ = chunkZ << 4;

		double[][][] buffers = new double[2][this.noiseSizeZ + 1][this.noiseSizeY + 1];

		for (int dZ = 0; dZ < this.noiseSizeZ + 1; ++dZ) {
			buffers[0][dZ] = new double[this.noiseSizeY + 1];
			this.sampleNoiseColumn(buffers[0][dZ], chunkX * this.noiseSizeX, chunkZ * this.noiseSizeZ + dZ);
			buffers[1][dZ] = new double[this.noiseSizeY + 1];
		}

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

					for (int interpY = this.verticalNoiseResolution - 1; interpY >= 0; --interpY) {
						int y = regionY * this.verticalNoiseResolution + interpY;
						int relativeY = y & 15;
						int sectionY = y >> 4;
						if (chunkSection.getYOffset() >> 4 != sectionY) {
							chunkSection.unlock();
							chunkSection = protoChunk.getSection(sectionY);
							chunkSection.lock();
						}

						double deltaY = (double) interpY / (double) this.verticalNoiseResolution;
						double noiseCurrentNW = MathHelper.lerp(deltaY, noiseNW, noiseNextNW);
						double noiseCurrentNE = MathHelper.lerp(deltaY, noiseNE, noiseNextNE);
						double noiseCurrentSW = MathHelper.lerp(deltaY, noiseSW, noiseNextSW);
						double noiseCurrentSE = MathHelper.lerp(deltaY, noiseSE, noiseNextSE);

						for (int interpX = 0; interpX < this.horizontalNoiseResolution; ++interpX) {
							int x = startX + regionX * this.horizontalNoiseResolution + interpX;
							int relativeX = x & 15;
							double deltaX = (double) interpX / (double) this.horizontalNoiseResolution;
							double noiseCurrentNorth = MathHelper.lerp(deltaX, noiseCurrentNW, noiseCurrentNE);
							double noiseCurrentSouth = MathHelper.lerp(deltaX, noiseCurrentSW, noiseCurrentSE);

							for (int innterpZ = 0; innterpZ < this.horizontalNoiseResolution; ++innterpZ) {
								int z = startZ + regionZ * this.horizontalNoiseResolution + innterpZ;
								int relativeZ = z & 15;
								double deltaZ = (double) innterpZ / (double) this.horizontalNoiseResolution;
								double noiseRaw = MathHelper.lerp(deltaZ, noiseCurrentNorth, noiseCurrentSouth);
								double noise = MathHelper.clamp(noiseRaw / 200.0D, -1.0D, 1.0D);

								BlockState state = Blocks.AIR.getDefaultState();
								if (noise > 0.0D) {
									state = CaelumBlocks.AERRACK.getDefaultState();
								}

								if (state != Blocks.AIR.getDefaultState()) {
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
		this.sampleNoiseColumn(buffer, x, z, this.horizontalScale, this.verticalScale * 2, 684.412D / 80, 684.412D / 160, 64, -3000);
	}

	protected void sampleNoiseColumn(double[] buffer, int x, int z, double horizontalScale, double verticalScale, double horizontalScale2, double verticalScale2, int i, int j) {
		T columnData = this.createColumnData(x, z);
		double maxHeight = (double) (this.noiseSizeY - 3) / 2;
		double minHeight = 8.0D;

		for (int y = 0; y < this.noiseSizeY + 1; ++y) {
			double noise = this.sampleNoise(x, y, z, horizontalScale, verticalScale, horizontalScale2, verticalScale2);
			noise = this.modifyNoise(noise, columnData, y);

			if (y > maxHeight) {
				noise = MathHelper.clampedLerp(noise, j, (y - maxHeight) / i);
			} else if (y < minHeight) {
				noise = MathHelper.clampedLerp(noise, -30.0D, (minHeight - y) / (minHeight - 1.0D));
			}

			buffer[y] = noise;
		}
	}

	protected abstract T createColumnData(int x, int z);

	protected abstract double modifyNoise(double noise, T data, int y);

	protected double sampleNoise(int x, int y, int z, double horizontalScale, double verticalScale, double maskVerticalScale, double maskHorizontalScale) {
		double sample1 = 0.0D;
		double sample2 = 0.0D;
		double maskSample = 0.0D;

		double amplitude = 1.0D;
		for (int i = 0; i < 16; ++i) {
			double u = OctavePerlinNoiseSampler.maintainPrecision(x * horizontalScale * amplitude);
			double v = OctavePerlinNoiseSampler.maintainPrecision(y * verticalScale * amplitude);
			double w = OctavePerlinNoiseSampler.maintainPrecision(z * horizontalScale * amplitude);
			double p = verticalScale * amplitude;
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
					maskSample += maskSampler.sample(OctavePerlinNoiseSampler.maintainPrecision(x * maskVerticalScale * amplitude), OctavePerlinNoiseSampler.maintainPrecision(y * maskHorizontalScale * amplitude), OctavePerlinNoiseSampler.maintainPrecision(z * maskVerticalScale * amplitude), maskHorizontalScale * amplitude, y * maskHorizontalScale * amplitude) / amplitude;
				}
			}

			amplitude /= 2.0D;
		}

		return MathHelper.clampedLerp(sample1 / 512.0D + 4, sample2 / 512.0D, (maskSample / 10.0D + 1.0D) / 2.0D);
	}

	public int getHeightOnGround(int x, int z, Heightmap.Type heightmapType) {
		int regionX = Math.floorDiv(x, this.horizontalNoiseResolution);
		int regionZ = Math.floorDiv(z, this.horizontalNoiseResolution);
		int interpX = Math.floorMod(x, this.horizontalNoiseResolution);
		int interpZ = Math.floorMod(z, this.horizontalNoiseResolution);
		double deltaX = (double) interpX / (double) this.horizontalNoiseResolution;
		double deltaZ = (double) interpZ / (double) this.horizontalNoiseResolution;
		double[][] buffers = new double[][] { this.sampleNoiseColumn(regionX, regionZ), this.sampleNoiseColumn(regionX, regionZ + 1), this.sampleNoiseColumn(regionX + 1, regionZ), this.sampleNoiseColumn(regionX + 1, regionZ + 1) };

		for (int regionY = this.noiseSizeY - 1; regionY >= 0; regionY--) {
			double noiseNW = buffers[0][regionY];
			double noiseSW = buffers[1][regionY];
			double noiseNE = buffers[2][regionY];
			double noiseSE = buffers[3][regionY];
			double noiseNextNW = buffers[0][regionY + 1];
			double noiseNextSW = buffers[1][regionY + 1];
			double noiseNextNE = buffers[2][regionY + 1];
			double noiseNextSE = buffers[3][regionY + 1];

			for (int interpY = this.verticalNoiseResolution - 1; interpY >= 0; interpY--) {
				double deltaY = (double) interpY / (double) this.verticalNoiseResolution;
				double noise = MathHelper.lerp3(deltaY, deltaX, deltaZ, noiseNW, noiseNextNW, noiseNE, noiseNextNE, noiseSW, noiseNextSW, noiseSE, noiseNextSE);
				int y = regionY * this.verticalNoiseResolution + interpY;
				if (noise > 0.0D) {
					BlockState state = Blocks.AIR.getDefaultState();
					if (noise > 0.0D) {
						state = CaelumBlocks.AERRACK.getDefaultState();
					}

					if (heightmapType.getBlockPredicate().test(state)) {
						return y + 1;
					}
				}
			}
		}

		return 0;
	}

	protected double[] sampleNoiseColumn(int x, int z) {
		double[] ds = new double[this.noiseSizeY + 1];
		this.sampleNoiseColumn(ds, x, z);
		return ds;
	}
}
