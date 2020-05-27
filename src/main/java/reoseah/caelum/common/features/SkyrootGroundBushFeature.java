package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class SkyrootGroundBushFeature extends OldTreeThingsFeature<SkyrootFeatureConfig> {
	public SkyrootGroundBushFeature(Codec<SkyrootFeatureConfig> function) {
		super(function);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor accessor, ChunkGenerator generator, Random random, BlockPos pos, SkyrootFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		if (method_27368(world, pos)) {
			pos = pos.up();
			BlockBox box = new BlockBox();
			placeBush(world, random, pos, box, config);
		}

		return true;
	}

	protected void placeBush(ModifiableTestableWorld world, Random random, BlockPos pos, BlockBox box, SkyrootFeatureConfig config) {
		setLogBlockState(world, random, pos, box, config);

		for (int dy = 0; dy <= 2; ++dy) {
			int radius = 2 - dy;

			for (int dx = -radius; dx <= radius; ++dx) {
				for (int dz = -radius; dz <= radius; ++dz) {
					if (Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) != 0) {
						setLeavesBlockState(world, random, new BlockPos(dx + pos.getX(), dy + pos.getY(), dz + pos.getZ()), box, config);
					}
				}
			}
		}
	}


}
