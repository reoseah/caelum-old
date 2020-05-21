package reoseah.empyrean.world.features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;

public class DwarfSkyrootTreeFeature extends SkyrootTreeFeature {
	public static final int[][] PATTERNS = {
			{ 2, 1, 2, 1, 0 },
			{ 1, 2, 1, 2, 1, 0 },
	};

	public DwarfSkyrootTreeFeature(Function<Dynamic<?>, ? extends SkyrootFeatureConfig> function) {
		super(function);
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, SkyrootFeatureConfig config) {
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

			int[] shape = PATTERNS[random.nextInt(3)];
			int trunk = shape.length - 2 - random.nextInt(2);

			placeShape(world, random, pos, logPositions, leavesPositions, box, config, shape, trunk);
		}

		return true;
	}

}
