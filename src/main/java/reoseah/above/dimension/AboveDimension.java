package reoseah.above.dimension;

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
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;
import reoseah.above.Above;

public class AboveDimension extends Dimension {
	public AboveDimension(World world, DimensionType type) {
		super(world, type, 0.1F);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		// FIXME testing code
		return new OverworldChunkGenerator(world,
				new FixedBiomeSource(new FixedBiomeSourceConfig(world.getSeed())),
				new OverworldChunkGeneratorConfig());
	}

	@Override
	public BlockPos getSpawningBlockInChunk(ChunkPos var1, boolean var2) {
		return null;
	}

	@Override
	public BlockPos getTopSpawningBlockPosition(int var1, int var2, boolean var3) {
		return null;
	}

	@Override
	public float getSkyAngle(long long_1, float float_1) {
		double double_1 = MathHelper.fractionalPart(long_1 / 24000.0D - 0.25D);
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
