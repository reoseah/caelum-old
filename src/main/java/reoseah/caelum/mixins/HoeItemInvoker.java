package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

@Mixin(HoeItem.class)
public interface HoeItemInvoker {
	@Invoker("<init>")
	public static HoeItem create(ToolMaterial material, int damage, float speed, Item.Settings settings) {
		return null;
	}
}
