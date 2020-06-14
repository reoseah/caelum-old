package reoseah.caelum.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import reoseah.caelum.common.blocks.entity.GlowInDarkBlockEntity;

public class CeruclaseLampBlock extends Block implements BlockEntityProvider {
	public CeruclaseLampBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new GlowInDarkBlockEntity();
	}
}
