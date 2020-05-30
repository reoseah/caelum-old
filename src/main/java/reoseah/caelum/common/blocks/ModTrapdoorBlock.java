package reoseah.caelum.common.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;

import java.util.Collections;
import java.util.List;

public class ModTrapdoorBlock extends TrapdoorBlock {
    public ModTrapdoorBlock(Block block) {
        super(FabricBlockSettings.copy(block).nonOpaque().build());
    }
}
