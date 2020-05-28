package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class CaelumBushFeature extends CaelumTreeFeature<SkyrootFeatureConfig> {
	public CaelumBushFeature(Codec<SkyrootFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor accessor, ChunkGenerator generator, Random random, BlockPos pos, SkyrootFeatureConfig config) {
		if (!this.canGenerateAt(world, pos)) {
			return false;
		}
		BlockPos.Mutable mpos = new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ());

		world.setBlockState(mpos, config.trunk.getBlockState(random, mpos), 0);
		for (int y = 0; y <= 2; y++) {
			int radius = 2 - y;

			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					if (Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) != 0) {
						int distance = y + Math.abs(dx) + Math.abs(dz);
						this.trySetLeaves(world, mpos.set(pos.getX() + dx, pos.getY() + y, pos.getZ() + dz), distance, random, config);
					}
				}
			}
		}
		return true;
	}

}
