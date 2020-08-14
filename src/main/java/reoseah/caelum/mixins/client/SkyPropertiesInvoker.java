package reoseah.caelum.mixins.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.Identifier;

@Mixin(SkyProperties.class)
public interface SkyPropertiesInvoker {
	@Accessor("BY_IDENTIFIER")
	public static Object2ObjectMap<Identifier, SkyProperties> getBY_IDENTIFIER() {
		return null;
	}
}
