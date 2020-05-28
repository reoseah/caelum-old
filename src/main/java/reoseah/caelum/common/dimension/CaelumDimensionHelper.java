package reoseah.caelum.common.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.ChunkRandom;

public class CaelumDimensionHelper {
	public static void onEntityEnters(Entity entity, ServerWorld previousWorld, ServerWorld newWorld) {
		if (newWorld.getDimension() == CaelumDimensionType.INSTANCE) {
			BlockPos entityPos = entity.getBlockPos();

			BlockPos islandPos = CaelumDimensionHelper.locateIsland(newWorld.getSeed(), entityPos, 100);
			int surfaceY = newWorld
					.getChunk(islandPos.getX() >> 4, islandPos.getZ() >> 4)
					.sampleHeightmap(Heightmap.Type.MOTION_BLOCKING,
							islandPos.getX() & 15, islandPos.getZ() & 15);
			entity.updatePositionAndAngles(islandPos.getX(), surfaceY + 2, islandPos.getZ(), entity.yaw, entity.pitch);
		}
	}

	public static BlockPos locateIsland(long seed, BlockPos from, int radius) {
		int chunkX = from.getX() >> 4;
		int chunkZ = from.getZ() >> 4;
		ChunkRandom random = new ChunkRandom();
	
		for (int i = 0; i <= radius; ++i) {
			for (int dx = -i; dx <= i; ++dx) {
				boolean isXEdge = dx == -i || dx == i;
	
				for (int dz = -i; dz <= i; ++dz) {
					boolean isZEdge = dz == -i || dz == i;
					if (isXEdge || isZEdge) {
						ChunkPos chunkPos = CaelumDimensionHelper.searchIslandInRegion(random, seed, chunkX, chunkZ, dx, dz);
						return chunkPos.getCenterBlockPos();
					}
				}
	
				if (i == 0) {
					break;
				}
			}
		}
		return null;
	}

	public static ChunkPos searchIslandInRegion(ChunkRandom random, long seed, int centerX, int centerZ, int dX, int dZ) {
		int distance = 80;
		int separation = 20;
		int x = centerX + distance * dX;
		int z = centerZ + distance * dZ;
		int x2 = x < 0 ? x - distance + 1 : x;
		int z2 = z < 0 ? z - distance + 1 : z;
		int regionX = x2 / distance;
		int regionZ = z2 / distance;
		random.setRegionSeed(seed, regionX, regionZ, 10387319);
		int startX = regionX * distance + (random.nextInt(distance - separation) + random.nextInt(distance - separation)) / 2;
		int startZ = regionZ * distance + (random.nextInt(distance - separation) + random.nextInt(distance - separation)) / 2;
		return new ChunkPos(startX, startZ);
	}
}