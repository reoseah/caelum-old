package reoseah.skyland.items;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class ModdedPickaxeItem extends PickaxeItem {
	// Exposes constructor
	public ModdedPickaxeItem(ToolMaterial material, int damage, float speed, Item.Settings settings) {
		super(material, damage, speed, settings);
	}
}
