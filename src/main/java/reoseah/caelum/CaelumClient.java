package reoseah.caelum;

import java.util.function.Consumer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelAppender;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.resource.ResourceManager;
import reoseah.caelum.client.render.GlowInDarkBlockEntityRenderer;
import reoseah.caelum.common.CaelumBlocks;

public class CaelumClient implements ClientModInitializer {
	public static final ModelIdentifier CERUCLASE_LAMP_OVERLAY_ID = new ModelIdentifier("caelum:ceruclase_lamp_overlay", "");
	public static final Block CERUCLASE_LAMP_OVERLAY_DUMMY = new Block(FabricBlockSettings.of(Material.AIR).lightLevel(13));

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
				CaelumBlocks.CERUCLASE_LAMP,
				CaelumBlocks.SKYROOT_SAPLING,
				CaelumBlocks.SILVER_SKYROOT_SAPLING,
				CaelumBlocks.DWARF_SKYROOT_SAPLING,
				CaelumBlocks.BARLEY);

		ModelLoadingRegistry.INSTANCE.registerAppender(new ModelAppender() {
			@Override
			public void appendAll(ResourceManager manager, Consumer<ModelIdentifier> out) {
				out.accept(CERUCLASE_LAMP_OVERLAY_ID);
			}
		});

		BlockEntityRendererRegistry.INSTANCE.register(CaelumBlocks.GLOW_IN_DARK_ENTITY, GlowInDarkBlockEntityRenderer::new);
	}
}
