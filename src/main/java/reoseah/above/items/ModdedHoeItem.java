package reoseah.above.items;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class ModdedHoeItem extends HoeItem {
	// Exposes constructor
	public ModdedHoeItem(ToolMaterial material, int damage, float speed, Item.Settings settings) {
		super(material, damage, speed, settings);
	}
}
