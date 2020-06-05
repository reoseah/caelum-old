package reoseah.caelum.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import reoseah.caelum.common.misc.StrippingHelper;

public class DrawknifeItem extends ToolItem {
	public DrawknifeItem(ToolMaterial material, Item.Settings settings) {
		super(material, settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		return StrippingHelper.tryStripBlock(context);
	}
}
