package reoseah.caelum.structures;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class SkyPortalStructure extends StructureFeature<DefaultFeatureConfig> {
	public SkyPortalStructure() {
		super(DefaultFeatureConfig.CODEC);
	}

	@Override
	public GenerationStep.Feature method_28663() {
		return GenerationStep.Feature.SURFACE_STRUCTURES;
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
			StructurePiece startPiece = new SkyPortalGenerator.SkyPortal(0, this.random, chunkX * 16 + 8, chunkZ * 16 + 8);
			this.children.add(startPiece);
			this.setBoundingBoxFromChildren();
		}
	}
}
