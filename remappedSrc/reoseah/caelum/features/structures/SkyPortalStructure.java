package reoseah.caelum.features.structures;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import reoseah.caelum.features.structures.pieces.SkyPortalPiece;

public class SkyPortalStructure extends StructureFeature<DefaultFeatureConfig> {
	public SkyPortalStructure() {
		super(DefaultFeatureConfig.CODEC);
	}

	@Override
	public GenerationStep.Feature getGenerationStep() {
		return GenerationStep.Feature.SURFACE_STRUCTURES;
	}

	public BlockPos locateStructure(WorldView worldView, StructureAccessor structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureConfig structureConfig) {
		return locate(seed, blockPos, radius);
	}

	public StructureStart<?> method_28657(ChunkGenerator chunkGenerator, BiomeSource biomeSource, StructureManager structureManager, long l, ChunkPos chunkPos, Biome biome, int i, ChunkRandom chunkRandom, StructureConfig structureConfig, DefaultFeatureConfig featureConfig) {
		ChunkPos chunkPos2 = searchChunk(chunkRandom, l, chunkPos.x, chunkPos.z, 0, 0);
		if (chunkPos.x == chunkPos2.x && chunkPos.z == chunkPos2.z && this.shouldStartAt(chunkGenerator, biomeSource, l, chunkRandom, chunkPos.x, chunkPos.z, biome, chunkPos2, featureConfig)) {
			StructureStart<DefaultFeatureConfig> structureStart = this.getStructureStartFactory().create(this, chunkPos.x, chunkPos.z, BlockBox.empty(), i, l);
			structureStart.init(chunkGenerator, structureManager, chunkPos.x, chunkPos.z, biome, featureConfig);
			if (structureStart.hasChildren()) {
				return structureStart;
			}
		}

		return StructureStart.DEFAULT;
	}

	@Override
	public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
		return SkyExclaveStart::new;
	}

	public static class SkyExclaveStart extends StructureStart<DefaultFeatureConfig> {
		public SkyExclaveStart(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
			super(feature, chunkX, chunkZ, box, references, seed);
		}

		@Override
		public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig featureConfig) {
			StructurePiece startPiece = new SkyPortalPiece(0, this.random, chunkX * 16 + 8, chunkZ * 16 + 8);
			this.children.add(startPiece);
			startPiece.placeJigsaw(startPiece, this.children, this.random);
			this.setBoundingBoxFromChildren();
		}
	}

	public static BlockPos locate(long seed, BlockPos from, int radius) {
		int chunkX = from.getX() >> 4;
		int chunkZ = from.getZ() >> 4;
		ChunkRandom random = new ChunkRandom();

		for (int i = 0; i <= radius; ++i) {
			for (int dx = -i; dx <= i; ++dx) {
				boolean isXEdge = dx == -i || dx == i;

				for (int dz = -i; dz <= i; ++dz) {
					boolean isZEdge = dz == -i || dz == i;
					if (isXEdge || isZEdge) {
						ChunkPos chunkPos = searchChunk(random, seed, chunkX, chunkZ, dx, dz);
						return chunkPos.getCenterBlockPos();
					}
				}

				if (i == 0) {
					break;
				}
			}
		}
		return null;
	}

	public static ChunkPos searchChunk(ChunkRandom random, long seed, int centerX, int centerZ, int dX, int dZ) {
		int distance = 80;
		int separation = 20;
		int x = centerX + distance * dX;
		int z = centerZ + distance * dZ;
		int x2 = x < 0 ? x - distance + 1 : x;
		int z2 = z < 0 ? z - distance + 1 : z;
		int regionX = x2 / distance;
		int regionZ = z2 / distance;
		random.setRegionSeed(seed, regionX, regionZ, 10387319 * 31);
		int startX = regionX * distance + (random.nextInt(distance - separation) + random.nextInt(distance - separation)) / 2;
		int startZ = regionZ * distance + (random.nextInt(distance - separation) + random.nextInt(distance - separation)) / 2;
		return new ChunkPos(startX, startZ);
	}
}
