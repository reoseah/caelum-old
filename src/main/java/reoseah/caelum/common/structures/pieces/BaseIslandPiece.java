package reoseah.caelum.common.structures.pieces;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
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
}
