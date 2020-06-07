package reoseah.caelum.common.world.decorators;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.decorator.ChanceRangeDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;

public class SealedDungeonDecorator extends Decorator<ChanceRangeDecoratorConfig> {
	public SealedDungeonDecorator(Function<Dynamic<?>, ? extends ChanceRangeDecoratorConfig> deserialize) {
		super(deserialize);
	}

	@Override
	public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, ChanceRangeDecoratorConfig config, BlockPos pos) {
		if (random.nextFloat() <= config.chance) {
			return Stream.empty();
		}
		int x = pos.getX() + random.nextInt(16);
		int z = pos.getZ() + random.nextInt(16);
		int surface = world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, x, z);
		if (surface <= config.bottomOffset) {
			return Stream.empty();
		}
		if (surface > config.top) {
			surface = config.top;
		}
		int y = config.bottomOffset + random.nextInt(surface - config.bottomOffset);

		return Stream.of(new BlockPos(x, y, z));
	}

}
