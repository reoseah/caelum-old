package reoseah.caelum.common.dimension;

import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPattern.TeleportTarget;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import reoseah.caelum.common.CaelumFeatures;

public class CaelumEntityPlacer implements EntityPlacer {
	@Override
	public TeleportTarget placeEntity(Entity entity, ServerWorld world, Direction dim, double offsetX, double offsetZ) {
		BlockPos entityPos = entity.getSenseCenterPos();

		BlockPos structurePos = CaelumFeatures.LARGE_ISLAND.locateStructure(
				(IWorld) world, world.getChunkManager().getChunkGenerator(),
				entityPos, 100, false);

		int surfaceY = world
				.getChunk(structurePos.getX() >> 4, structurePos.getZ() >> 4)
				.sampleHeightmap(Heightmap.Type.MOTION_BLOCKING,
						structurePos.getX() & 15, structurePos.getZ() & 15);

		return new BlockPattern.TeleportTarget(
				new Vec3d(
						structurePos.getX(),
						surfaceY + 2,
						structurePos.getZ()),
				entity.getVelocity(),
				(int) entity.yaw);
	}
}