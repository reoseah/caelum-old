package reoseah.skyland;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import reoseah.skyland.blocks.SkyBlocks;

public class SkylandClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
				SkyBlocks.SKYROOT_SAPLING,
				SkyBlocks.SILVER_SKYROOT_SAPLING,
				SkyBlocks.DWARF_SKYROOT_SAPLING,
				SkyBlocks.BARLEY,
				SkyBlocks.AERRACK_TRAPDOOR,
				SkyBlocks.AERRACK_DOOR);
	}

}
