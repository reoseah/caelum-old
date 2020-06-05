package reoseah.caelum.mixins.client;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockRenderView;
import reoseah.caelum.common.CaelumBlocks;

@Mixin(BlockModelRenderer.class)
public class BlockModelRendererMixin {
	@Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
	public void render(BlockRenderView world, BakedModel model, BlockState state, BlockPos pos, MatrixStack matrix, VertexConsumer vertexConsumer, boolean cull, Random random, long seed, int overlay, CallbackInfoReturnable<Boolean> ci) {
		if (state.getBlock() == CaelumBlocks.AERRACK_LIGHTSTONE) {
			boolean ao = MinecraftClient.isAmbientOcclusionEnabled();
			Vec3d offset = state.getOffsetPos(world, pos);
			matrix.translate(offset.x, offset.y, offset.z);

			try {
				BlockModelRenderer renderer = (BlockModelRenderer) (Object) this;
				ci.setReturnValue(ao ? renderer.renderSmooth(world, model, state, pos, matrix, vertexConsumer, cull, random, seed, overlay) : renderer.renderFlat(world, model, state, pos, matrix, vertexConsumer, cull, random, seed, overlay));
			} catch (Throwable throwable) {
				CrashReport report = CrashReport.create(throwable, "Tesselating block model");
				CrashReportSection section = report.addElement("Block model being tesselated");
				CrashReportSection.addBlockInfo(section, pos, state);
				section.add("Using AO", ao);
				throw new CrashException(report);
			}
		}
	}
}
