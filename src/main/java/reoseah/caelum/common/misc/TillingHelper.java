package reoseah.caelum.common.misc;

import net.minecraft.item.HoeItem;
import reoseah.caelum.common.CaelumBlocks;

public abstract class TillingHelper extends HoeItem {
	private TillingHelper() {
		super(null, 0, 0, null);
		throw new UnsupportedOperationException();
	}

	// Needs to be here because TILLED_BLOCKS is protected
	public static final void registerTilling() {
		HoeItem.TILLED_BLOCKS.put(CaelumBlocks.CAELUM_DIRT, CaelumBlocks.CAELUM_FARMLAND.getDefaultState());
		HoeItem.TILLED_BLOCKS.put(CaelumBlocks.CAELUM_GRASS, CaelumBlocks.CAELUM_FARMLAND.getDefaultState());
	}
}
