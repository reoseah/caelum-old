package reoseah.caelum.common.blocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

public class CeruclaseOreBlock extends OreBlock {
	public CeruclaseOreBlock(AbstractBlock.Settings settings) {
		super(settings);
	}

	@Override
	protected int getExperienceWhenMined(Random random) {
		return MathHelper.nextInt(random, 2, 5);
	}
}
