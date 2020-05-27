package reoseah.caelum.common.features;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import reoseah.caelum.common.CaelumBlocks;

public class SkyrootBushWithSoilFeature extends SkyrootGroundBushFeature {
	public SkyrootBushWithSoilFeature(Codec<SkyrootFeatureConfig> function) {
		super(function);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockPos pos, SkyrootFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		BlockBox box = new BlockBox();
		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			placeBush(world, random, pos, box, config);
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
				placeBush(world, random, pos, box, config);
			}
		}

		return true;
	}

	protected void placeSiltPatch(ModifiableTestableWorld world, Random random, BlockPos pos, Set<Direction> sides) {
		setToDirt(world, pos);
		setToDirt(world, pos.down());
		setToDirt(world, pos.down(2));

		for (Direction direction : sides) {
			setBlockState(world, pos.offset(direction), CaelumBlocks.CAELUM_GRASS.getDefaultState());
			setToDirt(world, pos.offset(direction).down());
			if (random.nextBoolean()) {
				setToDirt(world, pos.offset(direction).down(2));
			}
		}
	}

	@Override
	protected void setToDirt(ModifiableTestableWorld world, BlockPos pos) {
		if (world.testBlockState(pos, state -> !state.isAir())) {
			setBlockState(world, pos, CaelumBlocks.CAELUM_DIRT.getDefaultState());
			return;
		}
	}

}
