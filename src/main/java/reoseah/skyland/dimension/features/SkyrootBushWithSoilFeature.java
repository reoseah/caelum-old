package reoseah.skyland.dimension.features;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import reoseah.skyland.blocks.SkyBlocks;

public class SkyrootBushWithSoilFeature extends SkyrootGroundBushFeature {
	public SkyrootBushWithSoilFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> function) {
		super(function);
	}

	public boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, TreeFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			placeBush(world, random, pos, logPositions, leavesPositions, box, config);
		} else if (world.testBlockState(pos, state -> isStone(state.getBlock()))) {
			Set<Direction> flatNeighbors = EnumSet.noneOf(Direction.class);
			for (Direction side : Direction.values()) {
				if (world.testBlockState(pos.offset(side), state -> isStone(state.getBlock()))
						&& isAirOrLeaves(world, pos.up().offset(side))) {
					flatNeighbors.add(side);
				}
			}

			if (flatNeighbors.size() == 1 || flatNeighbors.size() == 2) {
				placeSiltPatch(world, random, pos, flatNeighbors);

				pos = pos.up();
				placeBush(world, random, pos, logPositions, leavesPositions, box, config);
			}
		}

		return true;
	}

	protected void placeSiltPatch(ModifiableTestableWorld world, Random random, BlockPos pos, Set<Direction> flatNeighbors) {
		setBlockState(world, pos, SkyBlocks.SKY_SILT.getDefaultState());
		setBlockState(world, pos.down(), SkyBlocks.SKY_SILT.getDefaultState());
		setBlockState(world, pos.down(2), SkyBlocks.SKY_SILT.getDefaultState());

		for (Direction direction : flatNeighbors) {
			setBlockState(world, pos.offset(direction), SkyBlocks.SKY_GRASS.getDefaultState());
			setBlockState(world, pos.offset(direction).down(), SkyBlocks.SKY_SILT.getDefaultState());
			if (random.nextBoolean()) {
				setBlockState(world, pos.offset(direction).down(2), SkyBlocks.SKY_SILT.getDefaultState());
			}
		}
	}

}
