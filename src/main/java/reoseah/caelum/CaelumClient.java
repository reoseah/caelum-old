package reoseah.caelum;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import reoseah.caelum.client.render.AerrackLanternBlockEntityRenderer;
import reoseah.caelum.common.CaelumBlocks;

public class CaelumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
				CaelumBlocks.AERRACK_LANTERN,
				CaelumBlocks.SKYROOT_SAPLING,
				CaelumBlocks.SILVER_SKYROOT_SAPLING,
				CaelumBlocks.DWARF_SKYROOT_SAPLING,
				CaelumBlocks.BARLEY);

		ModelLoadingRegistry.INSTANCE.registerAppender((manager, out) -> {
			out.accept(AerrackLanternBlockEntityRenderer.AERRACK_LANTERN_OVERLAY_ID);
		});

		BlockEntityRendererRegistry.INSTANCE.register(CaelumBlocks.AERRACK_LANTERN_ENTITY, AerrackLanternBlockEntityRenderer::new);
	}
}
