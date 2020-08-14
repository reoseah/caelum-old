package reoseah.caelum.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class SkyrootTreeFeature extends CaelumTreeFeature<SkyrootConfig> {
	public SkyrootTreeFeature(Codec<SkyrootConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos, SkyrootConfig config) {
		if (!this.canGenerateAt(world, pos)) {
			return false;
		}

		int[] shape = config.shape.chooseShape(random);
		int height = shape.length - 2 - random.nextInt(2);

		placeHardcodedShape(world, random, pos, config, shape, height);
		trySetToDirt(world, pos.down());
		return true;
	}

	protected static void placeHardcodedShape(ServerWorldAccess world, Random random, BlockPos pos, SkyrootConfig config, int[] shape, int trunkHeight) {
		BlockPos.Mutable mpos = new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ());
		for (int dy = 0; dy < shape.length; dy++) {
			int radius = shape[dy];
			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					mpos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
					if (dy < trunkHeight && dx == 0 && dz == 0) {
						world.setBlockState(mpos, config.trunk.getBlockState(random, mpos), 19);
					} else if (Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) == 0) {
						int distance = Math.abs(dx) + Math.abs(dz);
						if (dy >= trunkHeight) {
							distance += dy - trunkHeight + 1;
						}
						trySetLeaves(world, mpos, distance, random, config);
					}
				}
			}
		}
	}
}
