package reoseah.above.dimension;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.Nullable;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import reoseah.above.Above;
import reoseah.above.dimension.chunk.SkyChunkGenerator;
import reoseah.above.dimension.chunk.SkyChunkGeneratorConfig;

public class SkyDimension extends Dimension {
	public SkyDimension(World world, DimensionType type) {
		super(world, type, 0.1F);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return new SkyChunkGenerator(world,
				new FixedBiomeSource(new FixedBiomeSourceConfig(world.getSeed()).setBiome(Above.SKY_FOREST)),
				new SkyChunkGeneratorConfig());
	}

	public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos, boolean checkMobSpawnValidity) {
		Random random = new Random(this.world.getSeed());
		BlockPos blockPos = new BlockPos(chunkPos.getStartX() + random.nextInt(15), 0, chunkPos.getEndZ() + random.nextInt(15));
		return this.world.getTopNonAirState(blockPos).getMaterial().blocksMovement() ? blockPos : null;
	}

	public BlockPos getTopSpawningBlockPosition(int x, int z, boolean checkMobSpawnValidity) {
		return this.getSpawningBlockInChunk(new ChunkPos(x >> 4, z >> 4), checkMobSpawnValidity);
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

	@Override
	public DimensionType getType() {
		return Above.DIMENSION_TYPE;
	}

	@Override
	public BlockPos getForcedSpawnPoint() {
		return new BlockPos(0, 100, 0);
	}

	@Environment(EnvType.CLIENT)
	public Vec3d modifyFogColor(Vec3d vec3d, float tickDelta) {
		return vec3d.multiply((tickDelta * 0.94F + 0.06F), (tickDelta * 0.94F + 0.06F), (tickDelta * 0.91F + 0.09F));
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

}
