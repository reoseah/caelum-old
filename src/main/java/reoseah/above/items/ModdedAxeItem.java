package reoseah.above.items;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class ModdedAxeItem extends AxeItem {
	// Exposes constructor
	public ModdedAxeItem(ToolMaterial material, float damage, float speed, Item.Settings settings) {
		super(material, damage, speed, settings);
	}
}
