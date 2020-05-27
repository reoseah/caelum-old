package reoseah.caelum.common.decorators;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import reoseah.caelum.common.CaelumBlocks;

public class ExposedAerrackDecorator extends Decorator<CountExtraChanceDecoratorConfig> {
	public ExposedAerrackDecorator(Function<Dynamic<?>, ? extends CountExtraChanceDecoratorConfig> deserialize) {
		super(deserialize);
	}

	@Override
	public Stream<BlockPos> getPositions(WorldAccess world, ChunkGenerator generator, Random random, CountExtraChanceDecoratorConfig config, BlockPos pos) {
		int count = config.count;
		if (random.nextFloat() < config.extraChance) {
			count += config.extraCount;
		}

		return IntStream.range(0, count)
				.mapToObj(i -> {
					for (int attempt = 0; attempt < 5; attempt++) {
						int dx = random.nextInt(16);
						int dz = random.nextInt(16);
						BlockPos top = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, pos.add(dx, 0, dz));
						boolean hasAir = false;
						boolean hasAerrack = false;
						for (Direction direction : Direction.Type.HORIZONTAL) {
							BlockPos neighborPos = top.offset(direction);
							if (world.isAir(neighborPos)) {
								hasAir = true;
							} else {
								Block block = world.getBlockState(neighborPos).getBlock();
								if (block == CaelumBlocks.AERRACK || block == CaelumBlocks.CAELUM_DIRT) {
									hasAerrack = true;
								}
							}
						}
						if (hasAir && hasAerrack) {
							return top;
						}
					}
					return null;
				}).filter(p -> p != null);
	}
}
