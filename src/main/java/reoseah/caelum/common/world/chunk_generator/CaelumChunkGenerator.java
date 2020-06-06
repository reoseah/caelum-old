package reoseah.caelum.common.world.chunk_generator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.noise.NoiseSampler;
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class CaelumChunkGenerator extends ChunkGenerator<CaelumChunkGeneratorConfig> {
	protected final AbstractLandGenerator<?> land;

	private final NoiseSampler surfaceDepthNoise;

	public CaelumChunkGenerator(IWorld world, BiomeSource biomeSource, CaelumChunkGeneratorConfig config) {
		super(world, biomeSource, config);

		ChunkRandom random = new ChunkRandom(this.seed);
		this.land = new FloatingIslandsGenerator(biomeSource, this.seed, random, 128, 8, 4, 684.412D * 2, 684.412D);

		this.surfaceDepthNoise = new OctaveSimplexNoiseSampler(random, 3, 0);
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
	public void populateNoise(IWorld world, Chunk chunk) {
		ChunkPos chunkPos = chunk.getPos();

		ProtoChunk protoChunk = (ProtoChunk) chunk;
		Heightmap oceanFloorMap = protoChunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap worldSurfaceMap = protoChunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);

		this.land.generate(chunkPos, protoChunk, oceanFloorMap, worldSurfaceMap);
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

}
