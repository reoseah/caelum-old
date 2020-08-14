package reoseah.caelum.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.registry.CaelumBlocks;

public class SkyrootStumpFeature extends Feature<DefaultFeatureConfig> {
	public SkyrootStumpFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		if (world.getBlockState(pos.down()).getBlock() == CaelumBlocks.CAELUM_GRASS_BLOCK) {
			int height = 2 + random.nextInt(3);
			for (int dy = 0; dy < height; dy++) {
				world.setBlockState(pos.up(dy), CaelumBlocks.SKYROOT_LOG.getDefaultState(), 19);
			}
			world.setBlockState(pos.down(), CaelumBlocks.CAELUM_DIRT.getDefaultState(), 19);
			return true;
		}
		return false;
	}

}
