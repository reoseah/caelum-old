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

public class LargeIslandPiece extends BaseIslandPiece {
	protected int extraHeight = 3;

	public LargeIslandPiece(StructureManager manager, CompoundTag tag) {
		super(CaelumWorld.LARGE_ISLAND_PIECE, tag);
		this.extraHeight = tag.getInt("ExtraHeight");
	}

	public LargeIslandPiece(int length, Random random, BlockBox bounds, int extraHeight) {
		super(CaelumWorld.LARGE_ISLAND_PIECE, length);

		this.extraHeight = extraHeight;
		this.boundingBox = bounds;
		this.setOrientation(Direction.fromHorizontal(random.nextInt(4)));
	}

	public static BlockBox createBounds(Random random, int extraHeight, int x, int z) {
		return new BlockBox(x - 2 - extraHeight, 100 + random.nextInt(20), z - 2 - extraHeight, x + 3 + extraHeight, 128, z + 3 + extraHeight);
	}

	@Override
	protected void toNbt(CompoundTag tag) {
		tag.putInt("ExtraHeight", this.extraHeight);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structures, ChunkGenerator generator, Random random, BlockBox blockBox, ChunkPos chunkPos, BlockPos blockPos) {
		int bottomX = this.boundingBox.getBlockCountX() / 2;
		int bottomZ = this.boundingBox.getBlockCountZ() / 2;
		int bottomY = 0;

		for (int dy = 0; dy <= 2; dy++) {
			int rad = dy;
			for (int dx = -rad; dx <= rad; dx++) {
				for (int dz = -rad; dz <= rad; dz++) {
					if (Math.abs(dx) != rad || Math.abs(dz) != rad || random.nextBoolean()) {
						this.addBlock(world, AERRACK, bottomX + dx, bottomY + dy, bottomZ + dz, blockBox);
					}
				}
			}
		}
		int size = 5 + this.extraHeight * 2;
		int[][] prev = new int[size][size];
		int center = size / 2;

		prev[center][center] = 1;

		for (int dy = 3; dy < 3 + this.extraHeight; dy++) {
			int[][] map = new int[size][size];
			for (int i = 1; i < size - 1; i++) {
				for (int j = 1; j < size - 1; j++) {
					if (prev[i][j] > 0) {
						if (prev[i - 1][j] == 0 && random.nextBoolean()) {
							map[i - 1][j] = 1;
						}
						if (prev[i + 1][j] == 0 && random.nextBoolean()) {
							map[i + 1][j] = 1;
						}
						if (prev[i][j - 1] == 0 && random.nextBoolean()) {
							map[i][j - 1] = 1;
						}
						if (prev[i][j + 1] == 0 && random.nextBoolean()) {
							map[i][j + 1] = 1;
						}
						map[i][j] = prev[i][j] + 1;
					}
				}
			}
			for (int i = 2; i < size - 2; i++) {
				for (int j = 2; j < size - 2; j++) {
					if (map[i][j] > 0) {
						for (int dx = -2; dx <= 2; dx++) {
							for (int dz = -2; dz <= 2; dz++) {
								if (Math.abs(dx) != 2 || Math.abs(dz) != 2 || map[i][j] > 1 || random.nextBoolean()) {
									int x = bottomX + i - center + dx;
									int z = bottomZ + j - center + dz;
									int y = bottomY + dy;

									this.addBlock(world, AERRACK, x, y, z, blockBox);
								}
							}
						}
					}
				}
			}

			prev = map;
		}

		return true;
	}
}
