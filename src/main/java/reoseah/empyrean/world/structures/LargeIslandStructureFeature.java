package reoseah.empyrean.world.structures;

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
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class LargeIslandStructureFeature extends StructureFeature<DefaultFeatureConfig> {
	public LargeIslandStructureFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	public BlockPos locateStructure(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, BlockPos pos, int i, boolean skipExistingChunks) {
		if (!chunkGenerator.getBiomeSource().hasStructureFeature(this)) {
			return null;
		}
		int centerX = pos.getX() >> 4;
		int centerZ = pos.getZ() >> 4;
		ChunkRandom chunkRandom = new ChunkRandom();

		for (int radius = 0; radius <= i; ++radius) {
			for (int dx = -radius; dx <= radius; ++dx) {
				boolean isXEdge = dx == -radius || dx == radius;

				for (int dz = -radius; dz <= radius; ++dz) {
					boolean isZEdge = dz == -radius || dz == radius;
					if (isXEdge || isZEdge) {
						ChunkPos chunkPos = this.getStart(chunkGenerator, chunkRandom, centerX, centerZ, dx, dz);
//						StructureStart structureStart = world.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS)
//								.getStructureStart(this.getName());
//
//						if (structureStart != null && structureStart.hasChildren()) {
							return chunkPos.getCenterBlockPos();
//						}
					}
				}

				if (radius == 0) {
					break;
				}
			}
		}

		return null;
	}

	@Override
	public boolean shouldStartAt(BiomeAccess biomeAccess, ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, Biome biome) {
		ChunkPos chunkPos = this.getStart(chunkGenerator, random, chunkX, chunkZ, 0, 0);
		if (chunkX == chunkPos.x && chunkZ == chunkPos.z) {
			return chunkGenerator.hasStructure(biome, this);
		}
		return false;
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
		((ChunkRandom) random).setRegionSeed(generator.getSeed(), regionX, regionZ, 10387319);
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
}
