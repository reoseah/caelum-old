package reoseah.caelum.common.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class CaelumDimensionHelper {
	public static void onEntering(Entity entity, ServerWorld previousWorld, ServerWorld newWorld) {
		if (newWorld.getDimension() == CaelumDimensionType.INSTANCE) {
			BlockPos spawnPos = new BlockPos(0, 100, 0);
			entity.updatePositionAndAngles(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0);
		}
	}
}
