package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.PaneBlock;

@Mixin(PaneBlock.class)
public interface PaneBlockInvoker {
	@Invoker("<init>")
	public static PaneBlock create(AbstractBlock.Settings settings) {
		return null;
	}
}
