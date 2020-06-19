package reoseah.caelum;

import net.fabricmc.api.ModInitializer;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumItems;
import reoseah.caelum.common.CaelumWorld;

public class Caelum implements ModInitializer {
	@Override
	public void onInitialize() {
		CaelumBlocks.register();
		CaelumItems.register();

		CaelumWorld.register();
	}
}
