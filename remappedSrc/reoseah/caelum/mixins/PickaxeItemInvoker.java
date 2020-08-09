package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

@Mixin(PickaxeItem.class)
public interface PickaxeItemInvoker {
	@Invoker("<init>")
	public static PickaxeItem create(ToolMaterial material, int damage, float speed, Item.Settings settings) {
		return null;
	}
}
