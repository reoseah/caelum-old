package reoseah.caelum.common.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import reoseah.caelum.common.CaelumBlocks;

public class SealedDungeonFeature extends Feature<DefaultFeatureConfig> {
	public SealedDungeonFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> deserializer) {
		super(deserializer);
	}

	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (BlockPos p : BlockPos.iterate(x, y, z, x + 7, y + 5, z + 7)) {
			if (world.isAir(p)) {
				return false;
			}
		}

		BlockPos.Mutable mpos = new BlockPos.Mutable();
		Random random2 = new Random();
		long salt1 = random.nextInt();
		long salt2 = random.nextInt();
		long salt3 = random.nextInt();
		long salt4 = random.nextInt();
		for (int j = 0; j < 7; j++) {
			for (int dy = 0; dy < 5; dy++) {
				setWall(world, mpos.set(x + j, y + dy, z), j, dy, random2, salt1);
				setWall(world, mpos.set(x + j, y + dy, z + 6), j, dy, random2, salt2);
				setWall(world, mpos.set(x, y + dy, z + j), j, dy, random2, salt3);
				setWall(world, mpos.set(x + 6, y + dy, z + j), j, dy, random2, salt4);
			}
		}
		for (int dy = 1; dy < 4; dy++) {
			for (int dx = 1; dx < 6; dx++) {
				for (int dz = 1; dz < 6; dz++) {
					world.setBlockState(mpos.set(x + dx, y + dy, z + dz), Blocks.AIR.getDefaultState(), 19);
				}
			}
		}

		for (int j = 1; j < 5; j++) {
			world.setBlockState(mpos.set(x + j, y, z + 1), CaelumBlocks.MOSSY_SEALSTONE.getDefaultState(), 19);
			world.setBlockState(mpos.set(x + j, y, z + 5), CaelumBlocks.MOSSY_SEALSTONE.getDefaultState(), 19);
			world.setBlockState(mpos.set(x + 1, y, z + j), CaelumBlocks.MOSSY_SEALSTONE.getDefaultState(), 19);
			world.setBlockState(mpos.set(x + 5, y, z + j), CaelumBlocks.MOSSY_SEALSTONE.getDefaultState(), 19);
		}

		world.setBlockState(mpos.set(x + 2, y, z + 2), CaelumBlocks.MOSSY_AERRACK_BRICKS.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 2, y, z + 4), CaelumBlocks.MOSSY_AERRACK_BRICKS.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 4, y, z + 2), CaelumBlocks.MOSSY_AERRACK_BRICKS.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 4, y, z + 4), CaelumBlocks.MOSSY_AERRACK_BRICKS.getDefaultState(), 19);

		world.setBlockState(mpos.set(x + 2, y, z + 3), CaelumBlocks.MOSSY_AERRACK_PILLAR.getDefaultState().with(PillarBlock.AXIS, Axis.X), 19);
		world.setBlockState(mpos.set(x + 4, y, z + 3), CaelumBlocks.MOSSY_AERRACK_PILLAR.getDefaultState().with(PillarBlock.AXIS, Axis.X), 19);
		world.setBlockState(mpos.set(x + 3, y, z + 2), CaelumBlocks.MOSSY_AERRACK_PILLAR.getDefaultState().with(PillarBlock.AXIS, Axis.Z), 19);
		world.setBlockState(mpos.set(x + 3, y, z + 4), CaelumBlocks.MOSSY_AERRACK_PILLAR.getDefaultState().with(PillarBlock.AXIS, Axis.Z), 19);

		world.setBlockState(mpos.set(x + 3, y, z + 3), CaelumBlocks.MOSSY_AERRACK.getDefaultState(), 19);

		world.setBlockState(mpos.set(x + 1, y + 1, z + 1), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 1, y + 1, z + 5), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 5, y + 1, z + 1), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 5, y + 1, z + 5), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 1, y + 2, z + 1), CaelumBlocks.AERRACK.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 1, y + 2, z + 5), CaelumBlocks.AERRACK.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 5, y + 2, z + 1), CaelumBlocks.AERRACK.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 5, y + 2, z + 5), CaelumBlocks.AERRACK.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 1, y + 3, z + 1), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 1, y + 3, z + 5), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 5, y + 3, z + 1), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 5, y + 3, z + 5), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);

		for (int j = 1; j < 5; j++) {
			world.setBlockState(mpos.set(x + j, y + 4, z + 1), CaelumBlocks.SEALSTONE.getDefaultState(), 19);
			world.setBlockState(mpos.set(x + j, y + 4, z + 5), CaelumBlocks.SEALSTONE.getDefaultState(), 19);
			world.setBlockState(mpos.set(x + 1, y + 4, z + j), CaelumBlocks.SEALSTONE.getDefaultState(), 19);
			world.setBlockState(mpos.set(x + 5, y + 4, z + j), CaelumBlocks.SEALSTONE.getDefaultState(), 19);
		}

		world.setBlockState(mpos.set(x + 2, y + 4, z + 2), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 2, y + 4, z + 4), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 4, y + 4, z + 2), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);
		world.setBlockState(mpos.set(x + 4, y + 4, z + 4), CaelumBlocks.AERRACK_PILLAR.getDefaultState(), 19);

		world.setBlockState(mpos.set(x + 2, y + 4, z + 3), CaelumBlocks.AERRACK_PILLAR.getDefaultState().with(PillarBlock.AXIS, Axis.Z), 19);
		world.setBlockState(mpos.set(x + 4, y + 4, z + 3), CaelumBlocks.AERRACK_PILLAR.getDefaultState().with(PillarBlock.AXIS, Axis.Z), 19);
		world.setBlockState(mpos.set(x + 3, y + 4, z + 2), CaelumBlocks.AERRACK_PILLAR.getDefaultState().with(PillarBlock.AXIS, Axis.X), 19);
		world.setBlockState(mpos.set(x + 3, y + 4, z + 4), CaelumBlocks.AERRACK_PILLAR.getDefaultState().with(PillarBlock.AXIS, Axis.X), 19);

		world.setBlockState(mpos.set(x + 3, y + 4, z + 3), CaelumBlocks.AERRACK_LIGHTSTONE.getDefaultState(), 19);

		Direction chestDir = Direction.fromHorizontal(random.nextInt(4));
		BlockPos.Mutable chestPos = mpos.set(x + 3, y + 1, z + 3).setOffset(chestDir, 2);

		world.setBlockState(chestPos, Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, chestDir.getOpposite()), 19);
		LootableContainerBlockEntity.setLootTable(world, random, mpos, new Identifier("caelum:chests/sealed_dungeon"));

		return true;
	}

	private static void setWall(IWorld world, BlockPos pos, int u, int w, Random random, long salt) {
		boolean sealstone = false;
		if (w / 2 % 2 == 1) {
			u = u + 1;
		}
		if (u % 2 == 0) {
			random.setSeed((u * 31 + w / 3) * 31 + salt);
			sealstone = w % 2 == random.nextInt(2);
		}
		if (sealstone) {
			world.setBlockState(pos, CaelumBlocks.SEALSTONE.getDefaultState(), 19);
		} else {
			world.setBlockState(pos, CaelumBlocks.AERRACK_BRICKS.getDefaultState(), 19);
		}
	}
}
