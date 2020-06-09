package reoseah.caelum.structures;

import java.util.Random;

import net.minecraft.block.BlockState;
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
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.SkyGeneration;

public class SkyPortalGenerator {
	private static final BlockState AERRACK = CaelumBlocks.AERRACK.getDefaultState();

	public static class SkyPortal extends StructurePiece {
		public SkyPortal(StructureManager manager, CompoundTag tag) {
			super(SkyGeneration.SKY_PORTAL_PIECE, tag);
		}

		public SkyPortal(int length, Random random, int x, int z) {
			super(SkyGeneration.SKY_PORTAL_PIECE, length);

			this.boundingBox = new BlockBox(x, 100 + random.nextInt(20), z, x + 5, 128, z + 7);
			this.setOrientation(Direction.fromHorizontal(random.nextInt(4)));
		}

		@Override
		protected void toNbt(CompoundTag tag) {

		}

		@Override
		public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockBox bounds, ChunkPos chunkPos, BlockPos blockPos) {
			for (int dy = 0; dy <= 2; dy++) {
				int width = dy;
				for (int dx = -width; dx <= width; dx++) {
					int length = width + 2;
					for (int dz = -length; dz <= length; dz++) {
						if (Math.abs(dx) != width || Math.abs(dz) != length || random.nextInt(2) == 0) {
							this.addBlock(world, AERRACK, 2 + dx, dy, 3 + dz, bounds);
						}
					}
				}
			}

			return true;
		}
	}
}
