package reoseah.empyrean;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class EmpyreanClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
				SkyBlocks.SKYROOT_SAPLING,
				SkyBlocks.SILVER_SKYROOT_SAPLING,
				SkyBlocks.DWARF_SKYROOT_SAPLING,
				SkyBlocks.BARLEY);
	}

}
