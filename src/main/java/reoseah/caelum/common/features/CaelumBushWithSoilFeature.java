package reoseah.caelum.common.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import reoseah.caelum.common.CaelumBlocks;

public class CaelumBushWithSoilFeature extends CaelumBushFeature {
	public CaelumBushWithSoilFeature(Codec<SkyrootFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor accessor, ChunkGenerator generator, Random random, BlockPos pos, SkyrootFeatureConfig config) {
		if (super.generate(world, accessor, generator, random, pos, config)) {
			BlockPos.Mutable mpos = new BlockPos.Mutable();
			trySetSoil(world, mpos.set(pos.getX(), pos.getY() - 1, pos.getZ()));
			trySetSoil(world, mpos.set(pos.getX(), pos.getY() - 2, pos.getZ()));
			trySetSoil(world, mpos.set(pos.getX(), pos.getY() - 3, pos.getZ()));

			for (Direction side : Direction.Type.HORIZONTAL) {
				trySetGrassySoil(world, mpos.set(pos.getX(), pos.getY() - 1, pos.getZ()).move(side));
				trySetSoil(world, mpos.set(pos.getX(), pos.getY() - 2, pos.getZ()).move(side));
				if (random.nextBoolean()) {
					trySetSoil(world, mpos.set(pos.getX(), pos.getY() - 3, pos.getZ()).move(side));
				}
			}
			return true;
		}
		return false;
	}

	public void trySetGrassySoil(ServerWorldAccess world, BlockPos pos) {
		BlockState current = world.getBlockState(pos);
		if (!current.isAir()) {
			BlockState above = world.getBlockState(pos);
			if (above.isAir() || above.getMaterial() == Material.LEAVES) {
				world.setBlockState(pos, CaelumBlocks.CAELUM_GRASS.getDefaultState(), 19);
			} else {
				world.setBlockState(pos, CaelumBlocks.CAELUM_DIRT.getDefaultState(), 19);
			}
		}
	}

	public void trySetSoil(ServerWorldAccess world, BlockPos pos) {
		BlockState current = world.getBlockState(pos);
		if (!current.isAir()) {
			world.setBlockState(pos, CaelumBlocks.CAELUM_DIRT.getDefaultState(), 19);
		}
	}

	@Override
	protected boolean canGenerateAt(ServerWorldAccess world, BlockPos pos) {
		if (this.isAirOrLeaves(world, pos)) {
			BlockState ground = world.getBlockState(pos.down());
			Block groundBlock = ground.getBlock();

			if (groundBlock == CaelumBlocks.CAELUM_GRASS
					|| groundBlock == CaelumBlocks.CAELUM_DIRT
					|| groundBlock == CaelumBlocks.CAELUM_FARMLAND) {
				return true;
			}
			if (groundBlock == CaelumBlocks.AERRACK
					|| groundBlock == CaelumBlocks.MOSSY_AERRACK) {
				int freeSpace = 0;
				for (Direction side : Direction.Type.HORIZONTAL) {
					BlockPos sidePos = pos.offset(side);
					if (this.isAirOrLeaves(world, sidePos)) {
						Block sideGround = world.getBlockState(sidePos.down()).getBlock();
						if (sideGround == CaelumBlocks.AERRACK
								|| sideGround == CaelumBlocks.MOSSY_AERRACK) {
							freeSpace += 1;
						}
					}
				}
				if (freeSpace == 1 || freeSpace == 2) {
					return true;
				}
			}
		}
		return false;
	}

}
