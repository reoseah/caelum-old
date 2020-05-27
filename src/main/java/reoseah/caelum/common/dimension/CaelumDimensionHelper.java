package reoseah.caelum.common.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;

public class CaelumDimensionHelper {
	public static void onEntering(Entity entity, ServerWorld previousWorld, ServerWorld newWorld) {
		if (newWorld.getDimension() == CaelumDimensionType.INSTANCE) {
			BlockPos entityPos = entity.getBlockPos();

			BlockPos islandPos = LargeIslandHelper.locateIsland(newWorld.getSeed(), entityPos, 100);
			int surfaceY = newWorld
					.getChunk(islandPos.getX() >> 4, islandPos.getZ() >> 4)
					.sampleHeightmap(Heightmap.Type.MOTION_BLOCKING,
							islandPos.getX() & 15, islandPos.getZ() & 15);
			entity.updatePositionAndAngles(islandPos.getX(), surfaceY + 2, islandPos.getZ(), entity.yaw, entity.pitch);
		}
	}
}
