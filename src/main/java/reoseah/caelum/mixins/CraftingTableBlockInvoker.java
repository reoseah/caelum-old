package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CraftingTableBlock;

@Mixin(CraftingTableBlock.class)
public interface CraftingTableBlockInvoker {
	@Invoker("<init>")
	public static CraftingTableBlock create(AbstractBlock.Settings settings) {
		return null;
	}
}
