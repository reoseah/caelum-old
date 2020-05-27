package reoseah.caelum.common.dimension;

import java.util.Arrays;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.PrimitiveCodec;

import net.minecraft.class_5311;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

public class CaelumChunkGenerator extends ChunkGenerator {
	public static final Codec<CaelumChunkGenerator> CODEC = PrimitiveCodec.LONG.fieldOf("seed")
			.xmap(
					seed -> new CaelumChunkGenerator(new CaelumBiomeSource(seed), seed),
					generator -> generator.seed)
			.codec();

	protected final long seed;

	public CaelumChunkGenerator(BiomeSource biomeSource, long seed) {
		super(biomeSource, new class_5311(false));
		this.seed = seed;
	}

	@Override
	protected Codec<? extends ChunkGenerator> method_28506() {
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return new CaelumChunkGenerator(new CaelumBiomeSource(seed), seed);
	}

	@Override
	public void buildSurface(ChunkRegion region, Chunk chunk) {

	}

	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
		Heightmap oceanFloor = chunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap surface = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);

		BlockState state = Blocks.GRASS_BLOCK.getDefaultState();

		for (int dx = 0; dx < 16; dx++) {
			for (int dz = 0; dz < 16; dz++) {
				chunk.setBlockState(new BlockPos(dx, 1, dz), state, false);
				oceanFloor.trackUpdate(dx, 1, dz, state);
				surface.trackUpdate(dx, 1, dz, state);
			}
		}
	}

	@Override
	public int getHeight(int x, int z, Heightmap.Type type) {
		return 10;
	}

	@Override
	public BlockView getColumnSample(int x, int z) {
		BlockState[] blockStates = new BlockState[128];
		Arrays.fill(blockStates, Blocks.AIR.getDefaultState());
		blockStates[1] = Blocks.GRASS_BLOCK.getDefaultState();
		return new VerticalBlockSample(blockStates);
	}

}
