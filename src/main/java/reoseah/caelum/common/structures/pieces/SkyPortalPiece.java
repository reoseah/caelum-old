package reoseah.caelum.common.structures.pieces;

import java.util.List;
import java.util.Random;

import net.minecraft.block.StairsBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import reoseah.caelum.common.SkyGeneration;

public class SkyPortalPiece extends BaseIslandPiece {
	public SkyPortalPiece(StructureManager manager, CompoundTag tag) {
		super(SkyGeneration.SKY_PORTAL_PIECE, tag);
	}

	public SkyPortalPiece(int length, Random random, int x, int z) {
		super(SkyGeneration.SKY_PORTAL_PIECE, length);

		this.boundingBox = new BlockBox(x, 100 + random.nextInt(20), z, x + 5, 128, z + 7);
		this.setOrientation(Direction.fromHorizontal(random.nextInt(4)));
	}

	@Override
	protected void toNbt(CompoundTag tag) {

	}

	@Override
	public void placeJigsaw(StructurePiece piece, List<StructurePiece> list, Random random) {
		list.add(new PillarIslandPiece(0, random, this.boundingBox.minX + 10, this.boundingBox.maxZ + 10));
		list.add(new PillarIslandPiece(0, random, this.boundingBox.minX - 10, this.boundingBox.maxZ - 10));
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
		this.placeIsland(2, 0, 3, 5, 6, world, random, boundingBox);

		for (int dy = 3; dy < 3 + 5; dy++) {
			this.addBlock(world, AERRACK_BRICKS, 2, dy, 1, boundingBox);
			this.addBlock(world, AERRACK_BRICKS, 2, dy, 4, boundingBox);
		}
		this.addBlock(world, AERRACK_BRICKS, 2, 3, 2, boundingBox);
		this.addBlock(world, AERRACK_BRICKS, 2, 3, 3, boundingBox);
		this.addBlock(world, AERRACK_BRICKS, 2, 7, 2, boundingBox);
		this.addBlock(world, AERRACK_BRICKS, 2, 7, 3, boundingBox);

		this.addBlock(world, AERRACK_BRICK_STAIRS.with(StairsBlock.FACING, Direction.EAST), 1, 3, 2, boundingBox);
		this.addBlock(world, AERRACK_BRICK_STAIRS.with(StairsBlock.FACING, Direction.EAST), 1, 3, 3, boundingBox);
		this.addBlock(world, AERRACK_BRICK_STAIRS.with(StairsBlock.FACING, Direction.WEST), 3, 3, 2, boundingBox);
		this.addBlock(world, AERRACK_BRICK_STAIRS.with(StairsBlock.FACING, Direction.WEST), 3, 3, 3, boundingBox);

		return true;
	}
}