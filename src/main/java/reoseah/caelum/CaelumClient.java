package reoseah.caelum;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import reoseah.caelum.registry.CaelumBlocks;

public class CaelumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
				CaelumBlocks.SKYROOT_SAPLING,
				CaelumBlocks.SILVER_SKYROOT_SAPLING,
				CaelumBlocks.DWARF_SKYROOT_SAPLING,
				CaelumBlocks.SKYROOT_DOOR,
				CaelumBlocks.SKYROOT_TRAPDOOR,
				CaelumBlocks.SKYROOT_FENCE,
				CaelumBlocks.CAELUM_LANTERN,
				CaelumBlocks.CAELUM_GRASS,
				CaelumBlocks.BLOSSOMING_CAELUM_GRASS,
				CaelumBlocks.BLUE_FLOWER,
				CaelumBlocks.PURPLE_FLOWER);
	}
}
