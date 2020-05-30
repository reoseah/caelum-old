package reoseah.caelum.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GlowingDustParticle extends SpriteBillboardParticle {
	private final SpriteProvider spriteProvider;

	protected GlowingDustParticle(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
		super(world, x, y, z, velocityX, velocityY, velocityZ);
		this.spriteProvider = spriteProvider;

		this.velocityX *= 0.10000000149011612D;
		this.velocityY *= 0.10000000149011612D;
		this.velocityZ *= 0.10000000149011612D;
		this.scale *= 0.75F;
		this.maxAge = 30 + this.random.nextInt(12);
		this.setSpriteForAge(spriteProvider);

		float brightness = this.random.nextFloat();
		this.setColor(0.7F + brightness * 0.1F, 0.9F + brightness * 0.05F, 0.875F + brightness * 0.0525F);
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_LIT;
	}

	@Override
	public float getSize(float tickDelta) {
		return this.scale * MathHelper.clamp((this.age + tickDelta) / this.maxAge * 32.0F, 0.0F, 1.0F);
	}

	public int getColorMultiplier(float tint) {
		return 15728880;
	}

	public void tick() {
		this.prevPosX = this.x;
		this.prevPosY = this.y;
		this.prevPosZ = this.z;
		if (this.age++ >= this.maxAge) {
			this.markDead();
		} else {
			this.setSpriteForAge(this.spriteProvider);
			this.move(this.velocityX, this.velocityY, this.velocityZ);
			if (this.y == this.prevPosY) {
				this.velocityX *= 1.1D;
				this.velocityZ *= 1.1D;
			}

			this.velocityX *= 0.9599999785423279D;
			this.velocityY *= 0.9599999785423279D;
			this.velocityZ *= 0.9599999785423279D;
			if (this.onGround) {
				this.velocityX *= 0.699999988079071D;
				this.velocityZ *= 0.699999988079071D;
			}

		}
	}

	@Environment(EnvType.CLIENT)
	public static class Factory implements ParticleFactory<DefaultParticleType> {
		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public Particle createParticle(DefaultParticleType type, World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new GlowingDustParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
		}
	}
}