package reoseah.caelum.client.render;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import reoseah.caelum.common.blocks.entity.AerrackLanternBlockEntity;

public class AerrackLanternBlockEntityRenderer extends BlockEntityRenderer<AerrackLanternBlockEntity> {
	public static final ModelIdentifier AERRACK_LANTERN_OVERLAY_ID = new ModelIdentifier("caelum:aerrack_lantern_overlay", "");
	public static final Block CERUCLASE_LAMP_OVERLAY_DUMMY = new Block(FabricBlockSettings.of(Material.AIR).lightLevel(13));

	public AerrackLanternBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(AerrackLanternBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		MinecraftClient mc = MinecraftClient.getInstance();
		BakedModel model = mc.getBakedModelManager().getModel(AERRACK_LANTERN_OVERLAY_ID);
		BlockPos pos = entity.getPos();
		matrices.push();
		mc.getBlockRenderManager()
				.getModelRenderer()
				.render(entity.getWorld(), model, CERUCLASE_LAMP_OVERLAY_DUMMY.getDefaultState(), pos, matrices, vertexConsumers.getBuffer(RenderLayer.getCutout()), true, entity.getWorld().getRandom(), 1, 0xFFFFFF);
		matrices.pop();
	}
}
