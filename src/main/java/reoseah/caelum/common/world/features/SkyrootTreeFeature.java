package reoseah.caelum.common.world.features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Material;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.blocks.CaelumOysterShroomBlock;

public class SkyrootTreeFeature extends AbstractTreeFeature<SkyrootFeatureConfig> {
	public SkyrootTreeFeature(Function<Dynamic<?>, ? extends SkyrootFeatureConfig> function) {
		super(function);
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, SkyrootFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			this.setLogBlockState(world, random, pos, logPositions, box, config);

			int[] shape = config.skyrootTreeShape.chooseShape(random);
			int trunk = shape.length - 2 - random.nextInt(2);

			placeShape(world, random, pos, logPositions, leavesPositions, box, config, shape, trunk);

			if (config.hasMushrooms && random.nextInt(10) == 0) {
				int dy = random.nextInt(2);
				Direction side = Direction.fromHorizontal(random.nextInt(4));
				BlockPos pos2 = pos.up(dy).offset(side);
				if (isAir(world, pos2) && random.nextInt(3) == 0) {
					world.setBlockState(pos2, CaelumBlocks.CAELUM_OYSTER_SHROOM.getDefaultState().with(CaelumOysterShroomBlock.FACING, side), 19);
				}
			}
		}

		return true;
	}

	protected void placeShape(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, SkyrootFeatureConfig config, int[] shape, int trunk) {
		BlockPos.Mutable p = new BlockPos.Mutable();
		for (int dy = 0; dy < shape.length; dy++) {
			int radius = shape[dy];
			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					p.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
					if (dx == 0 && dz == 0 && dy < trunk) {
						this.setLogBlockState(world, random, p, logPositions, box, config);
					} else if ((Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) == 0)
							&& isAirOrLeaves(world, p)) {
						this.setLeavesBlockState(world, random, p, leavesPositions, box, config);
					}
				}
			}
		}
	}

	@Override
	protected boolean setLogBlockState(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, BlockBox box, TreeFeatureConfig config) {
		if (!isAirOrLeaves(world, pos) && !isAnyPlant(world, pos) && !isWater(world, pos)) {
			return false;
		} else {
			this.setBlockState(world, pos, config.trunkProvider.getBlockState(random, pos), box);
			logPositions.add(pos.toImmutable());
			return true;
		}
	}

	public static boolean isAnyPlant(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, (state) -> {
			Material material = state.getMaterial();
			return material == Material.PLANT || material == Material.REPLACEABLE_PLANT;
		});
	}
}