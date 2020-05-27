package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.common.CaelumBlocks;

public class CaelumVegetationFeature extends Feature<BlockPileFeatureConfig> {
	public CaelumVegetationFeature(Codec<BlockPileFeatureConfig> function) {
		super(function);
	}

	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BlockPileFeatureConfig config) {
		return generate(world, random, pos, config, 8, 4);
	}

	public static boolean generate(WorldAccess world, Random random, BlockPos pos, BlockPileFeatureConfig config, int i, int j) {
		for (Block block = world.getBlockState(pos.down()).getBlock(); block != CaelumBlocks.CAELUM_GRASS && pos.getY() > 0; block = world.getBlockState(pos).getBlock()) {
			pos = pos.down();
		}

		int y = pos.getY();
		if (y >= 1 && y + 1 < 256) {
			int placed = 0;

			for (int m = 0; m < i * i; ++m) {
				BlockPos pos2 = pos.add(random.nextInt(i) - random.nextInt(i), random.nextInt(j) - random.nextInt(j), random.nextInt(i) - random.nextInt(i));
				BlockState state = config.stateProvider.getBlockState(random, pos2);
				if (world.isAir(pos2) && pos2.getY() > 0 && state.canPlaceAt(world, pos2)) {
					world.setBlockState(pos2, state, 2);
					++placed;
				}
			}

			return placed > 0;
		}
		return false;
	}
}
