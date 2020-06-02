package reoseah.caelum.common;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CaelumSounds {
	public static final SoundEvent SHEAR_SOUND = new SoundEvent(new Identifier("caelum:item.garden_shears.stunt_sapling"));

	public static void register() {
		Registry.register(Registry.SOUND_EVENT, new Identifier("caelum:item.garden_shears.stunt_sapling"), SHEAR_SOUND);
	}
}
