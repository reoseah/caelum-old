package reoseah.caelum.mixins;

import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import reoseah.caelum.common.CaelumBlocks;

@Mixin(AxeItem.class)
public class AxeItemMixin {
	@Shadow
	protected static @Final @Mutable Map<Block, Block> STRIPPED_BLOCKS;

	static {
		STRIPPED_BLOCKS = ImmutableMap.<Block, Block>builder().putAll(STRIPPED_BLOCKS)
				.put(CaelumBlocks.SKYROOT_LOG, CaelumBlocks.STRIPPED_SKYROOT_LOG)
				.build();
	}
}