package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

@Mixin(StairsBlock.class)
public interface StairsBlockInvoker {
	@Invoker("<init>")
	public static StairsBlock create(BlockState state, AbstractBlock.Settings settings) {
		return null;
	}
}
