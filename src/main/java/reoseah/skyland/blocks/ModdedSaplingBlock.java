package reoseah.skyland.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;


// makes constructor public
public class ModdedSaplingBlock extends SaplingBlock {
	public ModdedSaplingBlock(SaplingGenerator generator, Block.Settings settings) {
		super(generator, settings);
	}
}
