package reoseah.skyland.dimension.decorators;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import reoseah.skyland.Skyland;

public class SkyDecorators {
	public static final Decorator<CountExtraChanceDecoratorConfig> EXPOSED_AERRACK_DECORATOR = new ExposedAerrackDecorator(CountExtraChanceDecoratorConfig::deserialize);

	public static void register() {
		Registry.register(Registry.DECORATOR, Skyland.makeID("exposed_aerrack"), SkyDecorators.EXPOSED_AERRACK_DECORATOR);
	}
}
