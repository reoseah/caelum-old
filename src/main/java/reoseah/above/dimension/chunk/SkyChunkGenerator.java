package reoseah.above.dimension.chunk;

import java.util.Random;

import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;

public class SkyChunkGenerator extends SurfaceChunkGenerator<SkyChunkGeneratorConfig> {
	private static final float[] KERNEL = Util.make(new float[25], values -> {
		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				values[dx + 2 + (dz + 2) * 5] = 10.0F / MathHelper.sqrt(dx * dx + dz * dz + 0.2F);
			}
		}
	});

	public SkyChunkGenerator(IWorld world, BiomeSource biomeSource, SkyChunkGeneratorConfig config) {
		super(world, biomeSource, 4, 8, 128, config, false);
	}

	@Override
	protected double[] computeNoiseRange(int x, int z) {
		float depthTotal = 0.0F;
		float weightTotal = 0.0F;
		float centerDepth = this.biomeSource.getBiomeForNoiseGen(x, 0, z).getDepth();

		for (int dx = -2; dx <= 2; ++dx) {
			for (int dz = -2; dz <= 2; ++dz) {
				Biome biome = this.biomeSource.getBiomeForNoiseGen(x + dx, 0, z + dz);
				float depth = biome.getDepth();

				float weight = KERNEL[dx + 2 + (dz + 2) * 5];
				if (biome.getDepth() > centerDepth) {
					weight /= 2.0F;
				}

				depthTotal += depth * weight;
				weightTotal += weight;
			}
		}

		int dX = (Math.abs(x) + 256) % 512 - 256;
		int dZ = (Math.abs(z) + 256) % 512 - 256;

		depthTotal /= weightTotal;
		return new double[] { depthTotal, dX * dX + dZ * dZ };
	}

	@Override
	protected double computeNoiseFalloff(double depth, double sqDistance, int y) {
		double spawnEffect = Math.max(0, 10 - Math.sqrt(sqDistance) / 3);
		double heightEffect = 0;
		if (y > 16) {
			float over = Math.max((y - 16) / 6f, 1);
			heightEffect = -over * over;
		} else {
			float under = Math.max(y / 16f, 1);
			heightEffect = -under * under;
		}
		return 12 - 15 * depth + 10 * spawnEffect * heightEffect;
	}

	@Override
	protected void sampleNoiseColumn(double[] values, int x, int z) {
		this.sampleNoiseColumn(values, x, z, 684.412D, 684.412D * 4, 684.412D / 80, 684.412D / 160, 3, -10);
	}

	@Override
	public int getSpawnHeight() {
		return 50;
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	protected void buildBedrock(Chunk chunk, Random random) {

	}

	@Override
	protected double method_16409() {
		return (this.getNoiseSizeY() - 4) / 2;
	}

	@Override
	protected double method_16410() {
		return 8.0D;
	}
}

