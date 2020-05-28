package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class CurvedCaelumTreeFeature extends AbstractCaelumTreeFeature<CaelumTreeFeatureConfig> {

	public CurvedCaelumTreeFeature(Codec<CaelumTreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockPos pos, CaelumTreeFeatureConfig config) {
		if (!this.canGenerateAt(world, pos)) {
			return false;
		}
		Direction direction = Direction.fromHorizontal(random.nextInt(4));
		BlockState state2 = world.getBlockState(pos.up().offset(direction));
		if (!state2.isAir() && !state2.isIn(BlockTags.LEAVES) && state2.getMaterial() != Material.REPLACEABLE_PLANT) {
			return false;
		}

		int[] shape = config.shape.chooseShape(random);
		int height = shape.length - 2 - random.nextInt(2);

		world.setBlockState(pos, config.trunk.getBlockState(random, pos), 19);
		placeHardcodedShape(world, random, pos.up().offset(direction), config, shape, height);
		return true;
	}
}
