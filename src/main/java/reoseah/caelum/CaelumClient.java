package reoseah.caelum;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import reoseah.caelum.common.CaelumBlocks;

public class CaelumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
				CaelumBlocks.SKYROOT_SAPLING,
				CaelumBlocks.SILVER_SKYROOT_SAPLING,
				CaelumBlocks.DWARF_SKYROOT_SAPLING,
				CaelumBlocks.CAELUM_SPROUTS,
				CaelumBlocks.CAELUM_FLOWERS,
				CaelumBlocks.SKY_BLUE_FLOWER,
				CaelumBlocks.BARLEY);
	}

}
