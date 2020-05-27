package reoseah.caelum.common.features;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;

import net.minecraft.block.Material;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class SkyrootTreeFeature extends OldTreeThingsFeature<SkyrootFeatureConfig> {
	public SkyrootTreeFeature(Codec<SkyrootFeatureConfig> function) {
		super(function);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockPos pos, SkyrootFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			BlockBox box = new BlockBox();
			OldTreeThingsFeature.setLogBlockState(world, random, pos, box, config);

			int[] shape = config.shape.chooseShape(random);
			int trunk = shape.length - 2 - random.nextInt(2);

			placeShape(world, random, pos, box, config, shape, trunk);
		}

		return true;
	}

	protected void placeShape(ModifiableTestableWorld world, Random random, BlockPos pos, BlockBox box, SkyrootFeatureConfig config, int[] shape, int trunk) {
		BlockPos.Mutable p = new BlockPos.Mutable();
		for (int dy = 0; dy < shape.length; dy++) {
			int radius = shape[dy];
			for (int dx = -radius; dx <= radius; dx++) {
				for (int dz = -radius; dz <= radius; dz++) {
					p.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
					if (dx == 0 && dz == 0 && dy < trunk) {
						OldTreeThingsFeature.setLogBlockState(world, random, p, box, config);
					} else if ((Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) == 0)
							&& isAirOrLeaves(world, p)) {
						this.setLeavesBlockState(world, random, p, box, config);
					}
				}
			}
		}
	}

	protected boolean setLogBlockState(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, BlockBox box, TreeFeatureConfig config) {
		if (!isAirOrLeaves(world, pos) && !isAnyPlant(world, pos) && !isWater(world, pos)) {
			return false;
		} else {
			OldTreeThingsFeature.setBlockState(world, pos, config.trunkProvider.getBlockState(random, pos), box);
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
