package reoseah.caelum.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import reoseah.caelum.common.CaelumBlocks;

public class CaelumFlowersBlock extends PlantBlock {
	protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D);

	public CaelumFlowersBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		Vec3d vec3d = state.getOffsetPos(view, pos);
		return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		Block block = floor.getBlock();
		return block == CaelumBlocks.CAELUM_GRASS || block == CaelumBlocks.CAELUM_DIRT || block == CaelumBlocks.CAELUM_FARMLAND;
	}
}
