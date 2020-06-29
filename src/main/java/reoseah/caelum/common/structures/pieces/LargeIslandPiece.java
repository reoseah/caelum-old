package reoseah.caelum.common.structures.pieces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

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

public class LargeIslandPiece extends BaseIslandPiece {
	protected int extraHeight = 3;
	protected int shapeSeed;

	public LargeIslandPiece(StructureManager manager, CompoundTag tag) {
		super(CaelumWorld.LARGE_ISLAND_PIECE, tag);
		this.extraHeight = tag.getInt("ExtraHeight");
		this.shapeSeed = tag.getInt("ShapeSeed");
	}

	public LargeIslandPiece(int length, Random random, BlockBox bounds, int extraHeight) {
		super(CaelumWorld.LARGE_ISLAND_PIECE, length);

		this.extraHeight = extraHeight;
		this.boundingBox = bounds;
		this.shapeSeed = random.nextInt();
		this.setOrientation(Direction.fromHorizontal(random.nextInt(4)));
	}

	public static BlockBox createBounds(Random random, int extraHeight, int x, int z) {
		return new BlockBox(x - 2 - extraHeight, 100 + random.nextInt(20), z - 2 - extraHeight, x + 3 + extraHeight, 128, z + 3 + extraHeight);
	}

	@Override
	protected void toNbt(CompoundTag tag) {
		tag.putInt("ExtraHeight", this.extraHeight);
		tag.putInt("ShapeSeed", this.shapeSeed);
	}

	@Override
	public void placeJigsaw(StructurePiece piece, List<StructurePiece> list, Random random) {
		int pillars = 1 + random.nextInt(2);
		for (int i = 0; i < pillars; i++) {
			double angle = 2 * Math.PI * random.nextDouble();
			double radius = this.boundingBox.getBlockCountX() / 2 + 3 + random.nextInt(4);

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
		Map<Pair<Integer, Integer>, Integer> entries = new HashMap<>();
		entries.put(Pair.of(0, 0), 0);

		Random shapeRandom = new Random(this.shapeSeed);

		for (int dy = 3; dy < 3 + this.extraHeight; dy++) {
			int currentGen = 1 + dy - 3;
			Map<Pair<Integer, Integer>, Integer> next = new HashMap<>();
			for (Entry<Pair<Integer, Integer>, Integer> entry : entries.entrySet()) {
				int dx = entry.getKey().getLeft();
				int dz = entry.getKey().getRight();
				int gen = entry.getValue();
				if (gen < currentGen) {
					if (!entries.containsKey(Pair.of(dx - 1, dz)) && shapeRandom.nextInt(4) != 0) {
						next.put(Pair.of(dx - 1, dz), currentGen);
					}
					if (!entries.containsKey(Pair.of(dx + 1, dz)) && shapeRandom.nextInt(4) != 0) {
						next.put(Pair.of(dx + 1, dz), currentGen);
					}
					if (!entries.containsKey(Pair.of(dx, dz - 1)) && shapeRandom.nextInt(4) != 0) {
						next.put(Pair.of(dx, dz - 1), currentGen);
					}
					if (!entries.containsKey(Pair.of(dx, dz + 1)) && shapeRandom.nextInt(4) != 0) {
						next.put(Pair.of(dx, dz + 1), currentGen);
					}
				}
			}
			entries.putAll(next);
		}

		for (int dy = 3; dy < 3 + this.extraHeight; dy++) {
			int minGen = 1 + dy - 3;
			for (int i = -dy - 2; i <= dy + 2; i++) {
				for (int j = -dy - 2; j < dy + 2; j++) {
					Pair<Integer, Integer> coord = Pair.of(i, j);
					if (entries.containsKey(coord) && entries.get(coord) <= minGen) {
						for (int dx = -2; dx <= 2; dx++) {
							for (int dz = -2; dz <= 2; dz++) {
								if (Math.abs(dx) != 2 || Math.abs(dz) != 2 || entries.get(coord) <= minGen - 1 || random.nextBoolean()) {
									int x = bottomX + i + dx;
									int z = bottomZ + j + dz;
									int y = bottomY + dy;

									this.addBlock(world, AERRACK, x, y, z, blockBox);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
