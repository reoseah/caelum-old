package reoseah.caelum;

import net.fabricmc.api.ModInitializer;

public class Caelum implements ModInitializer {
	@Override
	public void onInitialize() {
		CaelumBlocks.register();
		CaelumItems.register();
		CaelumFeatures.register();
	}
}
