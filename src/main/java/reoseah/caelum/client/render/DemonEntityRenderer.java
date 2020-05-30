package reoseah.caelum.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import reoseah.caelum.entities.DemonEntity;

@Environment(EnvType.CLIENT)
public class DemonEntityRenderer extends MobEntityRenderer<DemonEntity, DemonModel> {
	private static final Identifier TEXTURE = new Identifier("caelum:textures/entity/demon.png");

	public DemonEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, new DemonModel(0.0F), 0.5F);
	}

	@Override
	public Identifier getTexture(DemonEntity entity) {
		return TEXTURE;
	}

	@Override
	protected void scale(DemonEntity entity, MatrixStack matrices, float f) {
	}
}
