package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class SkyrootTreeFeature extends CaelumTreeFeature<SkyrootFeatureConfig> {
	public SkyrootTreeFeature(Codec<SkyrootFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockPos pos, SkyrootFeatureConfig config) {
		if (!this.canGenerateAt(world, pos)) {
			return false;
		}

		int[] shape = config.shape.chooseShape(random);
		int height = shape.length - 2 - random.nextInt(2);

		BlockPos.Mutable mpos = new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ());
		for (int dy = 0; dy < shape.length; dy++) {
			int radius = shape[dy];
			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					mpos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
					if (dy < height) {
						if (dx == 0 && dz == 0) {
							world.setBlockState(mpos, config.trunk.getBlockState(random, mpos), 0);
						} else {
							int distance = Math.abs(dx) + Math.abs(dz);
							this.trySetLeaves(world, mpos, distance, random, config);
						}
					} else if (Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) == 0) {
						int distance = dy - height + 1 + Math.abs(dx) + Math.abs(dz);
						this.trySetLeaves(world, mpos, distance, random, config);
					}
				}
			}
		}
		return true;
	}
}
