package reoseah.caelum.mixins;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

@Mixin(HoeItem.class)
public interface HoeItemInvoker {
	@Accessor("TILLED_BLOCKS")
	public static Map<Block, BlockState> getTilledBlocks() {
		return null;
	}

	@Invoker("<init>")
	public static HoeItem create(ToolMaterial material, int damage, float speed, Item.Settings settings) {
		return null;
	}
}
