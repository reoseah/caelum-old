package reoseah.skyland.dimension.features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

public class SkyrootTreeFeature extends AbstractTreeFeature<SkyrootFeatureConfig> {
	public SkyrootTreeFeature(Function<Dynamic<?>, ? extends SkyrootFeatureConfig> function) {
		super(function);
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, SkyrootFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			this.setLogBlockState(world, random, pos, logPositions, box, config);

			int[] shape = config.skyrootTreeShape.chooseShape(random);
			int trunk = shape.length - 2 - random.nextInt(2);

			placeShape(world, random, pos, logPositions, leavesPositions, box, config, shape, trunk);
		}

		return true;
	}

	protected void placeShape(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, SkyrootFeatureConfig config, int[] shape, int trunk) {
		BlockPos.Mutable p = new BlockPos.Mutable();
		for (int dy = 0; dy < shape.length; dy++) {
			int radius = shape[dy];
			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					p.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
					if (dx == 0 && dz == 0 && dy < trunk) {
						this.setLogBlockState(world, random, p, logPositions, box, config);
					} else if ((Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) == 0)
							&& isAirOrLeaves(world, p)) {
						this.setLeavesBlockState(world, random, p, leavesPositions, box, config);
					}
				}
			}
		}
	}
}
