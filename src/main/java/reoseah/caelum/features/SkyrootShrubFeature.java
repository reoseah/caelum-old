package reoseah.caelum.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class SkyrootShrubFeature extends CaelumTreeFeature<SkyrootConfig> {
	public SkyrootShrubFeature(Codec<SkyrootConfig> codec) {
		super(codec);
	}

	public boolean generate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos, SkyrootConfig config) {
		if (!this.canGenerateAt(world, pos)) {
			return false;
		}
		BlockPos.Mutable mpos = new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ());

		this.setBlockState(world, mpos, config.trunk.getBlockState(random, mpos));
		trySetToDirt(world, pos.down());
		for (int dy = 0; dy <= 2; dy++) {
			int radius = 2 - dy;

			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					if (Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) != 0) {
						mpos.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
						int distance = dy + Math.abs(dx) + Math.abs(dz);
						trySetLeaves(world, mpos, distance, random, config);
					}
				}
			}
		}
		return true;
	}
}
