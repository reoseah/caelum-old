package reoseah.caelum.common.features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class SkyrootTallBushFeature extends AbstractTreeFeature<TreeFeatureConfig> {
	public SkyrootTallBushFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> function) {
		super(function);
	}

	public boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, TreeFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		Direction direction = Direction.fromHorizontal(random.nextInt(4));
		if (!canTreeReplace(world, pos.up(2).offset(direction))) {
			direction = Direction.fromHorizontal(random.nextInt(4));
		}
		if (!canTreeReplace(world, pos.up(2).offset(direction))) {
			return false;
		}
		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			this.setLogBlockState(world, random, pos, logPositions, box, config);
			pos = pos.up().offset(direction);
			this.setLogBlockState(world, random, pos, logPositions, box, config);

			for (int dy = 0; dy <= 2; ++dy) {
				int radius = 2 - dy;

				for (int dx = -radius; dx <= radius; ++dx) {
					for (int dz = -radius; dz <= radius; ++dz) {
						if (Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) != 0) {
							this.setLeavesBlockState(world, random, new BlockPos(dx + pos.getX(), dy + pos.getY(), dz + pos.getZ()), leavesPositions, box, config);
						}
					}
				}
			}
		}

		return true;
	}
}
