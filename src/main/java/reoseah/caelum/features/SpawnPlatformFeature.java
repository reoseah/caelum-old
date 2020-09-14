package reoseah.caelum.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.registry.CaelumBlocks;

public class SpawnPlatformFeature extends Feature<DefaultFeatureConfig> {
	public SpawnPlatformFeature(Codec<DefaultFeatureConfig> configCodec) {
		super(configCodec);
	}

	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig featureConfig) {
		if (pos.getX() == 0 && pos.getZ() == 0) {
			for (int ix = -2; ix <= 2; ix++) {
				for (int iz = -2; iz <= 2; iz++) {
					BlockPos ipos = pos.add(ix, 60, iz);
					if (!world.getBlockState(ipos).getMaterial().isSolid()) {
						world.setBlockState(ipos, CaelumBlocks.AERRACK.getDefaultState(), 2);
					}
					for (int iy = 1; iy < 3; iy++) {
						if (world.getBlockState(ipos.up(iy)).getMaterial().isSolid()) {
							world.setBlockState(ipos.up(iy), Blocks.AIR.getDefaultState(), 2);
						}
					}
				}
			}
			return true;
		}
		return false;
	}
}
