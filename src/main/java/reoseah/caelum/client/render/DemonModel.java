package reoseah.caelum.client.render;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.util.math.MathHelper;
import reoseah.caelum.entities.DemonEntity;

public class DemonModel extends CompositeEntityModel<DemonEntity> implements ModelWithHead {
	protected ModelPart head;
	protected final ModelPart torso;
	protected final ModelPart robe;
	protected final ModelPart arms;
	protected final ModelPart rightLeg;
	protected final ModelPart leftLeg;

	public DemonModel(float scale) {
		this(scale, 64, 64);
	}

	public DemonModel(float scale, int width, int height) {
		this.head = new ModelPart(this).setTextureSize(width, height);
		this.head.setPivot(0.0F, 0.0F, 0.0F);
		this.head.setTextureOffset(0, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, scale);
		this.torso = new ModelPart(this).setTextureSize(width, height);
		this.torso.setPivot(0.0F, 0.0F, 0.0F);
		this.torso.setTextureOffset(16, 16).addCuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, scale);
		this.robe = new ModelPart(this).setTextureSize(width, height);
		this.robe.setPivot(0.0F, 0.0F, 0.0F);
		this.robe.setTextureOffset(0, 34).addCuboid(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, scale + 0.5F);
		this.torso.addChild(this.robe);
		this.arms = new ModelPart(this).setTextureSize(width, height);
		this.arms.setPivot(0.0F, 2.0F, 0.0F);
		this.arms.setTextureOffset(44, 18).addCuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, scale);
		this.arms.setTextureOffset(44, 18).addCuboid(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, scale, true);
		this.arms.setTextureOffset(40, 34).addCuboid(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, scale);
		this.rightLeg = new ModelPart(this, 0, 18).setTextureSize(width, height);
		this.rightLeg.setPivot(-2.0F, 12.0F, 0.0F);
		this.rightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale);
		this.leftLeg = new ModelPart(this, 0, 18).setTextureSize(width, height);
		this.leftLeg.mirror = true;
		this.leftLeg.setPivot(2.0F, 12.0F, 0.0F);
		this.leftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale);
	}

	@Override
	public Iterable<ModelPart> getParts() {
		return ImmutableList.of(this.head, this.torso, this.rightLeg, this.leftLeg, this.arms);
	}

	@Override
	public void setAngles(DemonEntity entity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch) {
		boolean bl = false;

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
		if (bl) {
			this.head.roll = 0.3F * MathHelper.sin(0.45F * customAngle);
			this.head.pitch = 0.4F;
		} else {
			this.head.roll = 0.0F;
		}

		this.arms.pivotY = 3.0F;
		this.arms.pivotZ = -1.0F;
		this.arms.pitch = -0.75F;
		this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance * 0.5F;
		this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance * 0.5F;
		this.rightLeg.yaw = 0.0F;
		this.leftLeg.yaw = 0.0F;
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}
}
