package reoseah.caelum.common.decorators;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;

public class CaelumTreeDecorator extends Decorator<ChanceDecoratorConfig> {
	public CaelumTreeDecorator(Function<Dynamic<?>, ? extends ChanceDecoratorConfig> function) {
		super(function);
	}

	public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, ChanceDecoratorConfig config, BlockPos pos) {
		return IntStream.range(0, 16).mapToObj((i) -> {
			int j = i / 4;
			int k = i % 4;
			int x = j * 4 + 1 + random.nextInt(3) + pos.getX();
			int z = k * 4 + 1 + random.nextInt(3) + pos.getZ();
			int y = world.getTopY(Heightmap.Type.MOTION_BLOCKING, x, z);
			return random.nextInt(config.chance) != 0 ? null : new BlockPos(x, y, z);
		}).filter(bp -> bp != null);
	}
}
