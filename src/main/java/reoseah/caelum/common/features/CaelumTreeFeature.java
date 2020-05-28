package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class CaelumTreeFeature extends AbstractCaelumTreeFeature<CaelumTreeFeatureConfig> {
	public CaelumTreeFeature(Codec<CaelumTreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockPos pos, CaelumTreeFeatureConfig config) {
		if (!this.canGenerateAt(world, pos)) {
			return false;
		}

		int[] shape = config.shape.chooseShape(random);
		int height = shape.length - 2 - random.nextInt(2);

		placeHardcodedShape(world, random, pos, config, shape, height);
		trySetToDirt(world, pos.down());
		return true;
	}

}
