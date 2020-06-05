package reoseah.caelum.common.blocks;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.StemBlock;
import reoseah.caelum.common.CaelumBlocks;

public class MuskmelonBlock extends GourdBlock {
	public MuskmelonBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public StemBlock getStem() {
		return (StemBlock) CaelumBlocks.MUSKMELON_STEM;
	}

	@Override
	public AttachedStemBlock getAttachedStem() {
		return (AttachedStemBlock) CaelumBlocks.ATTACHED_MUSKMELON_STEM;
	}

}
