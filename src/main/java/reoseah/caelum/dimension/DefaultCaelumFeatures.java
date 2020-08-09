package reoseah.caelum.dimension;

import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import reoseah.caelum.CaelumBlocks;
import reoseah.caelum.features.SkyrootConfig;
import reoseah.caelum.features.SkyrootTreeShape;

public class DefaultCaelumFeatures {
	private static final BlockStateProvider SKYROOT_LOG = new SimpleBlockStateProvider(CaelumBlocks.SKYROOT_LOG.getDefaultState());
	private static final BlockStateProvider SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.SKYROOT_LEAVES.getDefaultState());
	public static final SkyrootConfig SKYROOT_TREE = new SkyrootConfig(SKYROOT_LOG, SKYROOT_LEAVES, SkyrootTreeShape.NORMAL);
	public static final SkyrootConfig SKYROOT_TALL_TREE = new SkyrootConfig(SKYROOT_LOG, SKYROOT_LEAVES, SkyrootTreeShape.TALL);
	public static final SkyrootConfig SKYROOT_SHRUB = new SkyrootConfig(SKYROOT_LOG, SKYROOT_LEAVES, SkyrootTreeShape.SHRUB);

	private static final BlockStateProvider SILVER_SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.SILVER_SKYROOT_LEAVES.getDefaultState());
	public static final SkyrootConfig SILVER_SKYROOT_TREE = new SkyrootConfig(SKYROOT_LOG, SILVER_SKYROOT_LEAVES, SkyrootTreeShape.NORMAL);
	public static final SkyrootConfig SILVER_SKYROOT_SHRUB = new SkyrootConfig(SKYROOT_LOG, SILVER_SKYROOT_LEAVES, SkyrootTreeShape.SHRUB);

	private static final BlockStateProvider DWARF_SKYROOT_LEAVES = new SimpleBlockStateProvider(CaelumBlocks.DWARF_SKYROOT_LEAVES.getDefaultState());
	public static final SkyrootConfig DWARF_SKYROOT_SHRUB = new SkyrootConfig(SKYROOT_LOG, DWARF_SKYROOT_LEAVES, SkyrootTreeShape.SHRUB);

	private static final BlockStateProvider CAELUM_VEGETATION_PROVIDER = new WeightedBlockStateProvider().addState(CaelumBlocks.CAELUM_GRASS.getDefaultState(), 20).addState(CaelumBlocks.BLOSSOMING_CAELUM_GRASS.getDefaultState(), 1);
	public static final BlockPileFeatureConfig CAELUM_VEGETATION = new BlockPileFeatureConfig(CAELUM_VEGETATION_PROVIDER);
}
