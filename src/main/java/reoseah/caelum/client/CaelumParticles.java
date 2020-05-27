package reoseah.caelum.client;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.registry.Registry;

public class CaelumParticles {
	public static final DefaultParticleType GLOWING_DUST = new DefaultParticleType(true) {
	};

	public static void register() {
		Registry.register(Registry.PARTICLE_TYPE, "caelum:glowing_dust", GLOWING_DUST);
	}
}
