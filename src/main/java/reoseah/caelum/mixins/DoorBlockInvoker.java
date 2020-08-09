package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.DoorBlock;

@Mixin(DoorBlock.class)
public interface DoorBlockInvoker {
	@Invoker("<init>")
	public static DoorBlock create(AbstractBlock.Settings settings) {
		return null;
	}
}
