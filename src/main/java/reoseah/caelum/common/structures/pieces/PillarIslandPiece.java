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
import reoseah.caelum.common.CaelumFeatures;

public class PillarIslandPiece extends BaseIslandPiece {
	public PillarIslandPiece(StructureManager manager, CompoundTag tag) {
		super(CaelumFeatures.PILLAR_ISLAND_PIECE, tag);
	}

	public PillarIslandPiece(int length, Random random, BlockBox bounds) {
		super(CaelumFeatures.PILLAR_ISLAND_PIECE, length);

		this.boundingBox = bounds;
		this.setOrientation(Direction.fromHorizontal(random.nextInt(4)));
	}

	public static BlockBox createBounds(Random random, int x, int z) {
		return new BlockBox(x - 2, 100 + random.nextInt(5), z - 2, x + 3, 128, z + 3);
	}

	@Override
	protected void toNbt(CompoundTag tag) {

	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
		int centerX = this.boundingBox.getBlockCountX() / 2;
		int centerZ = this.boundingBox.getBlockCountZ() / 2;

		for (int dy = 0; dy <= 2; dy++) {
			for (int dx = -dy; dx <= dy; dx++) {
				for (int dz = -dy; dz <= dy; dz++) {
					if (Math.abs(dx) != dy || Math.abs(dz) != dy || random.nextBoolean()) {
						this.addBlock(world, AERRACK, centerX + dx, dy, centerZ + dz, boundingBox);
					}
				}
			}
		}

		this.addBlock(world, AERRACK_BRICKS, centerX, 3, centerZ, boundingBox);
		this.addBlock(world, AERRACK_BRICKS, centerX, 4, centerZ, boundingBox);
		if (random.nextInt(2) == 0) {
			this.addBlock(world, AERRACK_BRICKS, centerX, 5, centerZ, boundingBox);
		}
		return true;
	}
}