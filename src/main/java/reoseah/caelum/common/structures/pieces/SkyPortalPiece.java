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
import reoseah.caelum.common.CaelumWorld;

public class SkyPortalPiece extends BaseIslandPiece {
	public SkyPortalPiece(StructureManager manager, CompoundTag tag) {
		super(CaelumWorld.SKY_PORTAL_PIECE, tag);
	}

	public SkyPortalPiece(int length, Random random, int x, int z) {
		super(CaelumWorld.SKY_PORTAL_PIECE, length);

		this.boundingBox = new BlockBox(x - 2, 100 + random.nextInt(20), z - 3, x + 3, 128, z + 4);
		this.setOrientation(Direction.fromHorizontal(random.nextInt(4)));
	}

	@Override
	protected void toNbt(CompoundTag tag) {

	}

	@Override
	public void placeJigsaw(StructurePiece piece, List<StructurePiece> list, Random random) {
		int c = 2 + random.nextInt(2);
		for (int i = 0; i < c; i++) {
			double angle = 2 * Math.PI * random.nextDouble();
			double radius = 6 + random.nextInt(4);

			int x = this.boundingBox.minX + this.boundingBox.getBlockCountX() / 2 + (int) (radius * Math.cos(angle));
			int z = this.boundingBox.maxZ + this.boundingBox.getBlockCountZ() / 2 + (int) (radius * Math.sin(angle));

			BlockBox bounds = PillarIslandPiece.createBounds(random, x, z);
			if (StructurePiece.getOverlappingPiece(list, bounds) == null) {
				StructurePiece island = new PillarIslandPiece(0, random, bounds);
				list.add(island);
			}
		}
		int count = 0;
		for (int i = 0; i < 30 && count < 4; i++) {
			double angle = 2 * Math.PI * random.nextDouble();
			double radius = 16;

			int x = this.boundingBox.minX + this.boundingBox.getBlockCountX() / 2 + (int) (radius * Math.cos(angle));
			int z = this.boundingBox.maxZ + this.boundingBox.getBlockCountZ() / 2 + (int) (radius * Math.sin(angle));

			int height = 1 + random.nextInt(5);

			BlockBox bounds = LargeIslandPiece.createBounds(random, height, x, z);
			if (StructurePiece.getOverlappingPiece(list, bounds) == null) {
				StructurePiece island = new LargeIslandPiece(0, random, bounds, height);
				list.add(island);
				count++;
			}
		}
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