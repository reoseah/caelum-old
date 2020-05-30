package reoseah.caelum.common.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.NoiseSampler;
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import reoseah.caelum.common.biomes.FloatingIslandsBiome;

public class CaelumChunkGenerator extends ChunkGenerator<CaelumChunkGeneratorConfig> {
	private static final float[] KERNEL;
	static {
		KERNEL = new float[25];
		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				KERNEL[dx + 2 + (dz + 2) * 5] = 10.0F / MathHelper.sqrt(dx * dx + dz * dz + 0.2F);
			}
		}
	}

	protected final SimpleLandHelper land;

	private final NoiseSampler surfaceDepthNoise;

	public CaelumChunkGenerator(IWorld world, BiomeSource biomeSource, CaelumChunkGeneratorConfig config) {
		super(world, biomeSource, config);

		ChunkRandom random = new ChunkRandom(this.seed);
		this.land = new SimpleLandHelper(random, 128, 8, 4, 684.412D * 2, 684.412D);

		this.surfaceDepthNoise = new OctaveSimplexNoiseSampler(random, 3, 0);
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
				float size = biome instanceof FloatingIslandsBiome ? ((FloatingIslandsBiome) biome).getLandThresholdModifier() : 0;

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
	public int getSpawnHeight() {
		return 60;
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public int getHeightOnGround(int x, int z, Heightmap.Type type) {
		return this.land.getHeightOnGround(x, z, type);
	}

	@Override
	public void buildSurface(ChunkRegion chunkRegion, Chunk chunk) {
		ChunkPos chunkPos = chunk.getPos();
		int chunkX = chunkPos.x;
		int chunkZ = chunkPos.z;
		ChunkRandom random = new ChunkRandom();
		random.setSeed(chunkX, chunkZ);
		int startX = chunkPos.getStartX();
		int startZ = chunkPos.getStartZ();

		BlockPos.Mutable mpos = new BlockPos.Mutable();

		double noiseScale = 0.0625D;
		for (int dX = 0; dX < 16; ++dX) {
			for (int dZ = 0; dZ < 16; ++dZ) {
				int x = startX + dX;
				int z = startZ + dZ;
				int y = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, dX, dZ) + 1;
				double e = this.surfaceDepthNoise.sample(x * noiseScale, z * noiseScale, noiseScale, dX * noiseScale) * 15.0D;
				chunkRegion.getBiome(mpos.set(startX + dX, y, startZ + dZ))
						.buildSurface(random, chunk, x, z, y, e, this.getConfig().getDefaultBlock(), this.getConfig().getDefaultFluid(), this.getSeaLevel(), this.world.getSeed());
			}
		}

	}

	@Override
	public void populateNoise(IWorld world, Chunk chunk) {
		ChunkPos chunkPos = chunk.getPos();

		ProtoChunk protoChunk = (ProtoChunk) chunk;
		Heightmap oceanFloorMap = protoChunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap worldSurfaceMap = protoChunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);

		this.land.placeIslands(chunkPos, protoChunk, oceanFloorMap, worldSurfaceMap);
	}
}
