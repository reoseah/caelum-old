package reoseah.caelum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import reoseah.caelum.blocks.entities.AerrackLanternBlockEntity;

public class AerrackLanternBlock extends Block implements BlockEntityProvider {
	public AerrackLanternBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new AerrackLanternBlockEntity();
	}
}