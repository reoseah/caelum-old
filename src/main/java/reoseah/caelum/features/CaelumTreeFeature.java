package reoseah.caelum.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.registry.CaelumBlocks;

public abstract class CaelumTreeFeature<T extends SkyrootConfig> extends Feature<T> {
	public CaelumTreeFeature(Codec<T> codec) {
		super(codec);
	}

	protected static boolean isAirOrLeaves(ServerWorldAccess world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		return state.isAir()
				|| state.getMaterial() == Material.LEAVES
				|| state.getMaterial() == Material.REPLACEABLE_PLANT
				|| state.isIn(BlockTags.SAPLINGS);
	}

	protected boolean canGenerateAt(ServerWorldAccess world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		if (state.isAir()
				|| state.getMaterial() == Material.LEAVES
				|| state.getMaterial() == Material.REPLACEABLE_PLANT
				|| state.isIn(BlockTags.SAPLINGS)) {
			BlockState ground = world.getBlockState(pos.down());

			return ground.isIn(CaelumBlocks.SOILS);
		}
		return false;
	}

	protected static <T extends SkyrootConfig> void trySetLeaves(ServerWorldAccess world, BlockPos pos, int distance, Random random, T config) {
		BlockState state = world.getBlockState(pos);
		if (state.isAir()
				|| (state.getMaterial() == Material.LEAVES && state.get(LeavesBlock.DISTANCE) > distance)
				|| state.getMaterial() == Material.REPLACEABLE_PLANT) {
			setBlockState(world, pos, config.leaves.getBlockState(random, pos).with(LeavesBlock.DISTANCE, distance));
		}
	}

	protected static void trySetToDirt(ServerWorldAccess world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		if (state.isOf(CaelumBlocks.CAELUM_GRASS_BLOCK)
				|| state.isOf(CaelumBlocks.CAELUM_FARMLAND)) {
			setBlockState(world, pos, CaelumBlocks.CAELUM_DIRT.getDefaultState());
		}
	}

	private static void setBlockState(ServerWorldAccess world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, state, 16 | 2 | 1);
	}
}
