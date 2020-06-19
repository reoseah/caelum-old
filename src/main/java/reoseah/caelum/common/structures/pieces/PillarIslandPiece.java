package reoseah.caelum.common.structures.pieces;

import java.util.Random;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import reoseah.caelum.common.CaelumWorld;

public class PillarIslandPiece extends BaseIslandPiece {
	public PillarIslandPiece(StructureManager manager, CompoundTag tag) {
		super(CaelumWorld.PILLAR_ISLAND_PIECE, tag);
	}

	public PillarIslandPiece(int length, Random random, BlockBox bounds) {
		super(CaelumWorld.PILLAR_ISLAND_PIECE, length);

		this.boundingBox = bounds;
		this.setOrientation(Direction.fromHorizontal(random.nextInt(4)));
	}

	public static BlockBox createBounds(Random random, int x, int z) {
		return new BlockBox(x - 3, 100 + random.nextInt(20), z - 3, x + 2, 128, z + 2);
	}

	@Override
	protected void toNbt(CompoundTag tag) {

	}

	@Override
	protected void placeIslandBlock(ServerWorldAccess world, Random random, BlockBox boundingBox, int x, int y, int z, int dx, int dy, int dz, int width, int length) {
		if (dy == 2 && Math.abs(dx) + Math.abs(dz) <= 1) {
			this.addBlock(world, AERRACK_BRICKS, x, y, z, boundingBox);
		} else if (random.nextInt(20) == 0) {
			this.addBlock(world, CERUCLASE_ORE, x, y, z, boundingBox);
		} else {
			this.addBlock(world, AERRACK, x, y, z, boundingBox);
		}
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
		this.placeIsland(2, 0, 2, 5, 5, world, random, boundingBox);

		this.addBlock(world, AERRACK_BRICKS, 2, 3, 2, boundingBox);
		this.addBlock(world, AERRACK_BRICKS, 2, 4, 2, boundingBox);
		if (random.nextInt(2) == 0) {
			this.addBlock(world, AERRACK_BRICKS, 2, 5, 2, boundingBox);
		}
		return true;
	}
}