package reoseah.caelum.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.registry.CaelumBlocks;

public class CaelumGrassesFeature extends Feature<BlockPileFeatureConfig> {
	public CaelumGrassesFeature(Codec<BlockPileFeatureConfig> codec) {
		super(codec);
	}

	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, BlockPileFeatureConfig config) {
		return doGenerate(world, random, blockPos, config, 8, 4);
	}

	public static boolean doGenerate(WorldAccess world, Random random, BlockPos pos, BlockPileFeatureConfig config, int hSpread, int vSpread) {
		Block block = world.getBlockState(pos.down()).getBlock();
		if (!block.is(CaelumBlocks.CAELUM_GRASS_BLOCK)) {
			return false;
		}
		int y = pos.getY();
		if (y >= 1 && y + 1 < 256) {
			int count = 0;

			for (int i = 0; i < hSpread * hSpread; ++i) {
				BlockPos pos2 = pos.add(random.nextInt(hSpread) - random.nextInt(hSpread), random.nextInt(vSpread) - random.nextInt(vSpread), random.nextInt(hSpread) - random.nextInt(hSpread));
				BlockState state2 = config.stateProvider.getBlockState(random, pos2);
				if (world.isAir(pos2) && pos2.getY() > 0 && state2.canPlaceAt(world, pos2)) {
					world.setBlockState(pos2, state2, 2);
					count++;
				}
			}

			return count > 0;
		}
		return false;
	}
}
