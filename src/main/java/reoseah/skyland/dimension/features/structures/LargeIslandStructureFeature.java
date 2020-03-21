package reoseah.skyland.dimension.features.structures;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class LargeIslandStructureFeature extends SkylandStructureFeature {
	public LargeIslandStructureFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	public BlockPos locateStructure(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, BlockPos blockPos, int i, boolean skipExistingChunks) {
		// same as version in parent, but take IWorld instead
		if (!chunkGenerator.getBiomeSource().hasStructureFeature(this)) {
			return null;
		} else {
			int j = blockPos.getX() >> 4;
			int k = blockPos.getZ() >> 4;
			int l = 0;

			for (ChunkRandom chunkRandom = new ChunkRandom(); l <= i; ++l) {
				for (int m = -l; m <= l; ++m) {
					boolean bl = m == -l || m == l;

					for (int n = -l; n <= l; ++n) {
						boolean bl2 = n == -l || n == l;
						if (bl || bl2) {
							ChunkPos chunkPos = this.getStart(chunkGenerator, chunkRandom, j, k, m, n);
							StructureStart structureStart = world.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS).getStructureStart(this.getName());
							if (structureStart != null && structureStart.hasChildren()) {
								if (skipExistingChunks && structureStart.isInExistingChunk()) {
									structureStart.incrementReferences();
									return structureStart.getPos();
								}

								if (!skipExistingChunks) {
									return structureStart.getPos();
								}
							}

							if (l == 0) {
								break;
							}
						}
					}

					if (l == 0) {
						break;
					}
				}
			}

			return null;
		}
	}

	@Override
	protected ChunkPos getStart(ChunkGenerator<?> generator, Random random, int centerX, int centerZ, int dX, int dZ) {
		int distance = 80;
		int separation = 20;
		int rawX = centerX + distance * dX;
		int rawZ = centerZ + distance * dZ;
		int posX = rawX < 0 ? rawX - distance + 1 : rawX;
		int posZ = rawZ < 0 ? rawZ - distance + 1 : rawZ;
		int regionX = posX / distance;
		int regionZ = posZ / distance;
		((ChunkRandom) random).setRegionSeed(generator.getSeed(), regionX, regionZ, this.getStructureSalt());
		int startX = regionX * distance + (random.nextInt(distance - separation) + random.nextInt(distance - separation)) / 2;
		int startZ = regionZ * distance + (random.nextInt(distance - separation) + random.nextInt(distance - separation)) / 2;
		return new ChunkPos(startX, startZ);
	}

	@Override
	public StructureStartFactory getStructureStartFactory() {
		return new StructureStartFactory() {
			@Override
			public StructureStart create(StructureFeature<?> feature, int x, int z, BlockBox box, int references, long chunk) {
				return new StructureStart(feature, x, z, box, references, chunk) {
					@Override
					public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome) {
						System.out.println("initialize " + getName() + " at " + x + " " + z);
					}

					@Override
					public boolean hasChildren() {
						// hack hack, we are not a real structure
						return true;
					}
				};
			}
		};
	}

	@Override
	public String getName() {
		return "Large_Island";
	}

	@Override
	public int getRadius() {
		return 8;
	}

	@Override
	protected int getStructureSalt() {
		return 10387319;
	}
}
