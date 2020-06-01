package reoseah.caelum.common.decorators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;

public class CaelumGrassDecorator extends Decorator<ChanceDecoratorConfig> {
	public CaelumGrassDecorator(Function<Dynamic<?>, ? extends ChanceDecoratorConfig> deserializer) {
		super(deserializer);
	}

	@Override
	public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, ChanceDecoratorConfig config, BlockPos pos) {
		List<BlockPos> positions = new ArrayList<>();

		for (int dx = 0; dx < 16; dx++) {
			for (int dz = 0; dz < 16; dz++) {
				if (random.nextInt(config.chance) == 0) {
					BlockPos pos2 = new BlockPos(pos.getX() + dx, 0, pos.getZ() + dz);
					positions.add(world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos2));
				}
			}
		}

		return positions.stream();
	}
}
