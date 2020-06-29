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
		int count = 0;
		for (int i = 0; i < 30 && count < 4; i++) {
			double angle = 2 * Math.PI * random.nextDouble();
			double radius = 16 + random.nextInt(8);

			int x = this.boundingBox.minX + this.boundingBox.getBlockCountX() / 2 + (int) (radius * Math.cos(angle));
			int z = this.boundingBox.minZ + this.boundingBox.getBlockCountZ() / 2 + (int) (radius * Math.sin(angle));

			int height = 1 + random.nextInt(2);

			BlockBox bounds = LargeIslandPiece.createBounds(random, height, x, z);
			if (StructurePiece.getOverlappingPiece(list, bounds) == null) {
				StructurePiece island = new LargeIslandPiece(0, random, bounds, height);
				list.add(island);
				island.placeJigsaw(this, list, random);
				count++;
			}
		}

		int pillars = 2 + random.nextInt(2);
		for (int i = 0; i < pillars; i++) {
			double angle = 2 * Math.PI * random.nextDouble();
			double radius = 10 + random.nextInt(4);

			int x = this.boundingBox.minX + this.boundingBox.getBlockCountX() / 2 + (int) (radius * Math.cos(angle));
			int z = this.boundingBox.minZ + this.boundingBox.getBlockCountZ() / 2 + (int) (radius * Math.sin(angle));

			BlockBox bounds = PillarIslandPiece.createBounds(random, x, z);
			if (StructurePiece.getOverlappingPiece(list, bounds) == null) {
				StructurePiece island = new PillarIslandPiece(0, random, bounds);
				list.add(island);
			}
		}

	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
		int centerX = this.boundingBox.getBlockCountX() / 2;
		int centerZ = this.boundingBox.getBlockCountZ() / 2;

		for (int dy = 0; dy <= 2; dy++) {
			for (int dx = -dy; dx <= dy; dx++) {
				for (int dz = -dy - 2; dz <= dy + 2; dz++) {
					if (Math.abs(dx) != dy || Math.abs(dz) != dy + 2 || random.nextBoolean()) {
						this.addBlock(world, AERRACK, centerX + dx, dy, centerZ + dz, boundingBox);
					}
				}
			}
		}

		int floor = 3;

		for (int dy = floor; dy <= floor + 4; dy++) {
			this.addBlock(world, AERRACK_BRICKS, centerX, dy, centerZ - 1, boundingBox);
			this.addBlock(world, AERRACK_BRICKS, centerX, dy, centerZ + 2, boundingBox);
		}
		this.addBlock(world, AERRACK_BRICKS, centerX, floor, centerZ, boundingBox);
		this.addBlock(world, AERRACK_BRICKS, centerX, floor, centerZ + 1, boundingBox);
		this.addBlock(world, AERRACK_BRICKS, centerX, floor + 4, centerZ, boundingBox);
		this.addBlock(world, AERRACK_BRICKS, centerX, floor + 4, centerZ + 1, boundingBox);

		this.addBlock(world, AERRACK_BRICK_STAIRS.with(StairsBlock.FACING, Direction.EAST), centerX - 1, floor, centerZ, boundingBox);
		this.addBlock(world, AERRACK_BRICK_STAIRS.with(StairsBlock.FACING, Direction.EAST), centerX - 1, floor, centerZ + 1, boundingBox);
		this.addBlock(world, AERRACK_BRICK_STAIRS.with(StairsBlock.FACING, Direction.WEST), centerX + 1, floor, centerZ, boundingBox);
		this.addBlock(world, AERRACK_BRICK_STAIRS.with(StairsBlock.FACING, Direction.WEST), centerX + 1, floor, centerZ + 1, boundingBox);

		return true;
	}
}