package reoseah.caelum.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import reoseah.caelum.client.render.DemonEntityRenderer;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumEntities;

public class CaelumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
				CaelumBlocks.SKYROOT_SAPLING,
				CaelumBlocks.SILVER_SKYROOT_SAPLING,
				CaelumBlocks.DWARF_SKYROOT_SAPLING,
				CaelumBlocks.CAELUM_SPROUTS,
				CaelumBlocks.BLOSSOMING_CAELUM_SPROUTS,
				CaelumBlocks.SKY_BLUE_FLOWER,
				CaelumBlocks.BARLEY);

		EntityRendererRegistry.INSTANCE.register(CaelumEntities.DEMON, (manager, context) -> new DemonEntityRenderer(manager));
	}

}
