package reoseah.caelum.common.dimension;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPattern.TeleportTarget;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import reoseah.caelum.Caelum;
import reoseah.caelum.common.CaelumBiomes;
import reoseah.caelum.common.CaelumFeatures;

public class CaelumDimension extends Dimension {
	public CaelumDimension(World world, DimensionType type) {
		super(world, type, 0.1F);
	}

	@Override
	public DimensionType getType() {
		return Caelum.DIMENSION_TYPE;
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return new CaelumChunkGenerator(world,
				new FixedBiomeSource(new FixedBiomeSourceConfig(world.getSeed()).setBiome(CaelumBiomes.BARREN_FOREST)),
				new CaelumChunkGeneratorConfig());
	}

	public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos, boolean forMobs) {
		Random random = new Random(this.world.getSeed());
		BlockPos blockPos = new BlockPos(chunkPos.getStartX() + random.nextInt(15), 0, chunkPos.getEndZ() + random.nextInt(15));
		return this.world.getTopNonAirState(blockPos).getMaterial().blocksMovement() ? blockPos : null;
	}

	public BlockPos getTopSpawningBlockPosition(int x, int z, boolean forMobs) {
		return this.getSpawningBlockInChunk(new ChunkPos(x >> 4, z >> 4), forMobs);
	}

	@Override
	public float getSkyAngle(long timeOfDay, float tickDelta) {
		double double_1 = MathHelper.fractionalPart(timeOfDay / 24000.0D - 0.25D);
		double double_2 = 0.5D - Math.cos(double_1 * 3.141592653589793D) / 2.0D;
		return (float) (double_1 * 2.0D + double_2) / 3.0F;
	}

	@Override
	public boolean hasVisibleSky() {
		return true;
	}

	@Environment(EnvType.CLIENT)
	public Vec3d modifyFogColor(Vec3d color, float tickDelta) {
		return color.multiply((tickDelta * 0.94F + 0.06F), (tickDelta * 0.94F + 0.06F), (tickDelta * 0.91F + 0.09F));
	}

	@Override
	public boolean canPlayersSleep() {
		return true;
	}

	@Override
	public boolean isFogThick(int x, int z) {
		return false;
	}

	@Override
	public float getCloudHeight() {
		return 8;
	}

	@Override
	public double getHorizonShadingRatio() {
		return 1;
	}

	public static TeleportTarget placeEntity(Entity entity, ServerWorld world, Direction dim, double offsetX, double offsetZ) {
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
