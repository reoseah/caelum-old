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

public class DwarfSkyrootTreeFeature extends OldSkyrootTreeFeature {
	public static final int[][] PATTERNS = {
			{ 2, 1, 2, 1, 0 },
			{ 1, 2, 1, 2, 1, 0 },
	};

	public DwarfSkyrootTreeFeature(Codec<SkyrootFeatureConfig> function) {
		super(function);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator chunkGenerator, Random random, BlockPos pos, SkyrootFeatureConfig config) {
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
			OldTreeThingsFeature.setLogBlockState(world, random, pos,  box, config);
			pos = pos.up().offset(direction);
			OldTreeThingsFeature.setLogBlockState(world, random, pos,  box, config);

			int[] shape = PATTERNS[random.nextInt(3)];
			int trunk = shape.length - 2 - random.nextInt(2);

			placeShape(world, random, pos,  box, config, shape, trunk);
		}

		return true;
	}

}
