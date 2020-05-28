package reoseah.caelum.common.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;

public class ModDoorBlock extends DoorBlock {
    public ModDoorBlock(Block block)
    {
        super(FabricBlockSettings.copy(block).nonOpaque().build());
    }
}