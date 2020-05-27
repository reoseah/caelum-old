package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public abstract class OldTreeThingsFeature<T extends FeatureConfig> extends Feature<T> {
	public OldTreeThingsFeature(Codec<T> codec) {
		super(codec);
	}
	
	

	public static boolean setLogBlockState(ModifiableTestableWorld world, Random random, BlockPos pos, BlockBox box, SkyrootFeatureConfig config) {
		if (!isAirOrLeaves(world, pos) && !isReplaceablePlant(world, pos) && !isWater(world, pos)) {
			return false;
		} else {
			setBlockState(world, pos, config.trunk.getBlockState(random, pos), box);
			return true;
		}
	}

	protected boolean setLeavesBlockState(ModifiableTestableWorld world, Random random, BlockPos pos, BlockBox box, SkyrootFeatureConfig config) {
		if (!isAirOrLeaves(world, pos) && !isReplaceablePlant(world, pos) && !isWater(world, pos)) {
			return false;
		} else {
			setBlockState(world, pos, config.leaves.getBlockState(random, pos), box);
			return true;
		}
	}

	protected void setBlockState(ModifiableWorld world, BlockPos pos, BlockState state) {
		setBlockStateWithoutUpdatingNeighbors(world, pos, state);
	}

	protected static final void setBlockState(ModifiableWorld world, BlockPos pos, BlockState state, BlockBox box) {
		setBlockStateWithoutUpdatingNeighbors(world, pos, state);
		box.encompass(new BlockBox(pos, pos));
	}

	private static void setBlockStateWithoutUpdatingNeighbors(ModifiableWorld world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, state, 19);
	}

	protected static boolean canTreeReplace(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			Block block = state.getBlock();
			return state.isAir() || state.isIn(BlockTags.LEAVES) || isDirt(block) || block.isIn(BlockTags.LOGS) || block.isIn(BlockTags.SAPLINGS) || block == Blocks.VINE;
		});
	}

	public static boolean isAir(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, AbstractBlock.AbstractBlockState::isAir);
	}

	protected static boolean isNaturalDirt(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			Block block = state.getBlock();
			return isDirt(block) && block != Blocks.GRASS_BLOCK && block != Blocks.MYCELIUM;
		});
	}

	protected static boolean isVine(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			return state.getBlock() == Blocks.VINE;
		});
	}

	public static boolean isWater(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			return state.getBlock() == Blocks.WATER;
		});
	}

	public static boolean isAirOrLeaves(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			return state.isAir() || state.isIn(BlockTags.LEAVES);
		});
	}

	public static boolean isNaturalDirtOrGrass(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			return isDirt(state.getBlock());
		});
	}

	protected static boolean isDirtOrGrass(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			Block block = state.getBlock();
			return isDirt(block) || block == Blocks.FARMLAND;
		});
	}

	public static boolean isReplaceablePlant(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			Material material = state.getMaterial();
			return material == Material.REPLACEABLE_PLANT;
		});
	}

	protected void setToDirt(ModifiableTestableWorld world, BlockPos pos) {
		if (!isNaturalDirt(world, pos)) {
			this.setBlockState(world, pos, Blocks.DIRT.getDefaultState());
		}

	}
}
