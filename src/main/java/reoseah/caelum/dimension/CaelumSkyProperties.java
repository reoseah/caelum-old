package reoseah.caelum.dimension;

import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.math.Vec3d;

public class CaelumSkyProperties extends SkyProperties {
	public CaelumSkyProperties() {
		super(4, true, SkyType.NORMAL, false, false);
	}

	@Override
	public Vec3d adjustSkyColor(Vec3d color, float sunHeight) {
		return color.multiply(sunHeight * 0.94F + 0.06F, sunHeight * 0.94F + 0.06F, sunHeight * 0.91F + 0.09F);
	}

	@Override
	public boolean useThickFog(int camX, int camY) {
		return false;
	}
}
