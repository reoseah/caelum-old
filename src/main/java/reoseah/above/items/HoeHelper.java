package reoseah.above.items;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import reoseah.above.Above;

public final class HoeHelper extends HoeItem {
	protected HoeHelper(ToolMaterial material, float speed, Item.Settings settings) {
		super(material, 0, speed, settings);
	}

	// Needs to be here because TILLED_BLOCKS is protected
	public static final void registerTilling() {
		HoeItem.TILLED_BLOCKS.put(Above.SKY_SILT, Above.SKY_FARMLAND.getDefaultState());
		HoeItem.TILLED_BLOCKS.put(Above.SKY_GRASS, Above.SKY_FARMLAND.getDefaultState());
	}
}
