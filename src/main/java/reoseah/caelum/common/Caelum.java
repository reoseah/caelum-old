package reoseah.caelum.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import reoseah.caelum.client.CaelumParticles;
import reoseah.caelum.common.misc.TillingHelper;

public class Caelum implements ModInitializer {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier("caelum", "main"), () -> new ItemStack(CaelumBlocks.CAELUM_GRASS));

	@Override
	public void onInitialize() {
		CaelumBlocks.register();
		CaelumItems.register();
		CaelumFeatures.register();
		CaelumBiomes.register();
		CaelumEntities.register();
		CaelumParticles.register();

		//CaelumDimensionType.register();

		TillingHelper.registerTilling();
	}
}
