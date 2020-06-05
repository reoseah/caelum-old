package reoseah.caelum.common.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.blocks.CaelumOysterShroomBlock;

public class SkyrootStumpFeature extends Feature<DefaultFeatureConfig> {
	public SkyrootStumpFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> deserializer) {
		super(deserializer);
	}

	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		if (world.getBlockState(pos.down()).getBlock() == CaelumBlocks.CAELUM_GRASS) {
			int height = 1 + random.nextInt(3);
			for (int dy = 0; dy < height; dy++) {
				world.setBlockState(pos.up(dy), CaelumBlocks.SKYROOT_LOG.getDefaultState(), 19);
				for (Direction side : Direction.Type.HORIZONTAL) {
					BlockPos pos2 = pos.up(dy).offset(side);
					if (world.isAir(pos2) && random.nextInt(3) == 0) {
						world.setBlockState(pos2, CaelumBlocks.CAELUM_OYSTER_SHROOM.getDefaultState().with(CaelumOysterShroomBlock.FACING, side), 19);
					}
				}
			}
			world.setBlockState(pos.down(), CaelumBlocks.CAELUM_DIRT.getDefaultState(), 19);
			return true;
		}
		return false;
	}

}
