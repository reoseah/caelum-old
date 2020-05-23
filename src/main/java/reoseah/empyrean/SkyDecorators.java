package reoseah.empyrean;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import reoseah.empyrean.world.decorators.ExposedAerrackDecorator;
import reoseah.empyrean.world.decorators.SkylandTreeDecorator;

public class SkyDecorators {
	public static final Decorator<CountExtraChanceDecoratorConfig> EXPOSED_AERRACK_DECORATOR = new ExposedAerrackDecorator(CountExtraChanceDecoratorConfig::deserialize);
	public static final Decorator<ChanceDecoratorConfig> SKYLAND_TREE_DECORATOR = new SkylandTreeDecorator(ChanceDecoratorConfig::deserialize);

	public static void register() {
		Registry.register(Registry.DECORATOR, Empyrean.makeID("exposed_aerrack"), SkyDecorators.EXPOSED_AERRACK_DECORATOR);
		Registry.register(Registry.DECORATOR, Empyrean.makeID("skyland_tree"), SkyDecorators.SKYLAND_TREE_DECORATOR);
	}
}
