package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.TrapdoorBlock;

@Mixin(TrapdoorBlock.class)
public interface TrapdoorBlockInvoker {
	@Invoker("<init>")
	public static TrapdoorBlock create(AbstractBlock.Settings settings) {
		return null;
	}
}
