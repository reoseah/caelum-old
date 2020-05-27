package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class SkyrootTallBushFeature extends OldTreeThingsFeature<SkyrootFeatureConfig> {
	public SkyrootTallBushFeature(Codec<SkyrootFeatureConfig> function) {
		super(function);
	}

	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockPos pos, SkyrootFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		Direction direction = Direction.fromHorizontal(random.nextInt(4));
		if (!canTreeReplace(world, pos.up(2).offset(direction))) {
			direction = Direction.fromHorizontal(random.nextInt(4));
		}
		if (!canTreeReplace(world, pos.up(2).offset(direction))) {
			return false;
		}
		if (isNaturalDirtOrGrass(world, pos)) {
			BlockBox box = new BlockBox();
			pos = pos.up();
			this.setLogBlockState(world, random, pos, box, config);
			pos = pos.up().offset(direction);
			this.setLogBlockState(world, random, pos, box, config);

			for (int dy = 0; dy <= 2; ++dy) {
				int radius = 2 - dy;

				for (int dx = -radius; dx <= radius; ++dx) {
					for (int dz = -radius; dz <= radius; ++dz) {
						if (Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) != 0) {
							this.setLeavesBlockState(world, random, new BlockPos(dx + pos.getX(), dy + pos.getY(), dz + pos.getZ()), box, config);
						}
					}
				}
			}
		}

		return true;
	}
}
