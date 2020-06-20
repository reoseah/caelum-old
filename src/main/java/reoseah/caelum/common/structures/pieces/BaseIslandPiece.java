package reoseah.caelum.common.structures.pieces;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ServerWorldAccess;
import reoseah.caelum.common.CaelumBlocks;

public abstract class BaseIslandPiece extends StructurePiece {
	protected static final BlockState AERRACK = CaelumBlocks.AERRACK.getDefaultState();
	protected static final BlockState AERRACK_BRICKS = CaelumBlocks.AERRACK_BRICKS.getDefaultState();
	protected static final BlockState AERRACK_BRICK_STAIRS = CaelumBlocks.AERRACK_BRICK_STAIRS.getDefaultState();
	protected static final BlockState CERUCLASE_ORE = CaelumBlocks.CERUCLASE_ORE.getDefaultState();
	protected static final BlockState CAELUM_GRASS = CaelumBlocks.CAELUM_GRASS_BLOCK.getDefaultState();

	public BaseIslandPiece(StructurePieceType type, CompoundTag tag) {
		super(type, tag);
	}

	public BaseIslandPiece(StructurePieceType type, int length) {
		super(type, length);
	}

	protected void placeIsland(int x, int y, int z, int width, int length, ServerWorldAccess world, Random random, BlockBox boundingBox) {
		for (int dy = 0; dy <= 2; dy++) {
			int mindx = -(MathHelper.floor(width / 2F) - 2 + dy);
			int maxdx = MathHelper.ceil(width / 2F) - 3 + dy;
			int mindz = -(MathHelper.floor(length / 2F) - 2 + dy);
			int maxdz = MathHelper.ceil(length / 2F) - 3 + dy;

			for (int dx = mindx; dx <= maxdx; dx++) {
				for (int dz = mindz; dz <= maxdz; dz++) {
					if (dx != mindx && dx != maxdx || dz != mindz && dz != maxdz || random.nextInt(2) == 0) {
						this.placeIslandBlock(world, random, boundingBox, x + dx, y + dy, z + dz, dx, dy, dz, width, length);
					}
				}
			}
		}
	}

	protected void placeIslandBlock(ServerWorldAccess world, Random random, BlockBox boundingBox, int x, int y, int z, int dx, int dy, int dz, int width, int length) {
		this.addBlock(world, AERRACK, x, y, z, boundingBox);
	}
}
