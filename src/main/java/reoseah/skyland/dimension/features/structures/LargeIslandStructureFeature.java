package reoseah.skyland.dimension.features.structures;

import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class LargeIslandStructureFeature extends SkylandStructureFeature {
	public LargeIslandStructureFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	@Override
	public StructureStartFactory getStructureStartFactory() {
		return new StructureStartFactory() {
			@Override
			public StructureStart create(StructureFeature<?> feature, int x, int z, BlockBox box, int references, long chunk) {
				return new StructureStart(feature, x, z, box, references, chunk) {
					@Override
					public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome) {
						System.out.println("initialize at " + x + " " + z);
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
