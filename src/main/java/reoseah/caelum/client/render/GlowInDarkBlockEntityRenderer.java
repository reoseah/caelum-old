package reoseah.caelum.client.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import reoseah.caelum.CaelumClient;
import reoseah.caelum.common.blocks.entity.GlowInDarkBlockEntity;

public class GlowInDarkBlockEntityRenderer extends BlockEntityRenderer<GlowInDarkBlockEntity> {
	private static final ModelIdentifier CERUCLASE_LAMP_OVERLAY_ID = CaelumClient.CERUCLASE_LAMP_OVERLAY_ID;

	public GlowInDarkBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(GlowInDarkBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		MinecraftClient mc = MinecraftClient.getInstance();
		BakedModel model = mc.getBakedModelManager().getModel(CERUCLASE_LAMP_OVERLAY_ID);
		BlockPos pos = entity.getPos();
		matrices.push();
		mc.getBlockRenderManager()
				.getModelRenderer()
				.render(entity.getWorld(), model, CaelumClient.CERUCLASE_LAMP_OVERLAY_DUMMY.getDefaultState(), pos, matrices, vertexConsumers.getBuffer(RenderLayer.getCutout()), true, entity.getWorld().getRandom(), 1, 0xFFFFFF);
		matrices.pop();
	}
}
