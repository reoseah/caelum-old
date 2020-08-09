package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

@Mixin(AxeItem.class)
public interface AxeItemInvoker {
	@Invoker("<init>")
	public static AxeItem create(ToolMaterial material, float damage, float speed, Item.Settings settings) {
		return null;
	}
}
