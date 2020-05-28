package reoseah.caelum.common.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.DoorBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.common.Caelum;


public class ModDoorBlock extends DoorBlock {

    public ModDoorBlock(Settings block$Settings_1, String name) {
        super(block$Settings_1);
        Registry.register(Registry.BLOCK, new Identifier(Caelum.MOD_ID, name), this);
        Registry.register(Registry.ITEM, new Identifier(Caelum.MOD_ID, name), new TallBlockItem(this, new Item.Settings().group(Caelum.GROUP)));
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
    }
}