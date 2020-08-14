package reoseah.caelum;

import net.fabricmc.api.ModInitializer;
import reoseah.caelum.registry.CaelumBiomes;
import reoseah.caelum.registry.CaelumBlocks;
import reoseah.caelum.registry.CaelumDimension;
import reoseah.caelum.registry.CaelumFeatures;
import reoseah.caelum.registry.CaelumItems;
import reoseah.caelum.registry.CaelumConfiguredFeatures;

public class Caelum implements ModInitializer {
	@Override
	public void onInitialize() {
		CaelumBlocks.register();
		CaelumItems.register();
		CaelumFeatures.register();
		CaelumDimension.register();
		CaelumConfiguredFeatures.register();
		CaelumBiomes.register();
	}
}
