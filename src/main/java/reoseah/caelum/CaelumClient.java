package reoseah.caelum;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import reoseah.caelum.client.CaelumParticles;
import reoseah.caelum.client.particle.GlowingDustParticle;
import reoseah.caelum.client.render.DemonEntityRenderer;
import reoseah.caelum.common.CaelumBlocks;
import reoseah.caelum.common.CaelumEntities;

public class CaelumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
				CaelumBlocks.SKYROOT_SAPLING,
				CaelumBlocks.SILVER_SKYROOT_SAPLING,
				CaelumBlocks.DWARF_SKYROOT_SAPLING,
				CaelumBlocks.STUNTED_SKYROOT_SAPLING,
				CaelumBlocks.STUNTED_SILVER_SKYROOT_SAPLING,
				CaelumBlocks.CAELUM_SPROUTS,
				CaelumBlocks.BLOSSOMING_CAELUM_SPROUTS,
				CaelumBlocks.SKY_BLUE_FLOWER,
				CaelumBlocks.BARLEY,
				CaelumBlocks.MUSKMELON_STEM,
				CaelumBlocks.ATTACHED_MUSKMELON_STEM,
				CaelumBlocks.CAELUM_OYSTER_SHROOM,
				CaelumBlocks.CAELUM_OYSTER_SHROOM_PIN,
				CaelumBlocks.SKYROOT_DOOR,
				CaelumBlocks.SKYROOT_TRAPDOOR);

		EntityRendererRegistry.INSTANCE.register(CaelumEntities.DEMON, (manager, context) -> new DemonEntityRenderer(manager));

		ParticleFactoryRegistry.getInstance().register(CaelumParticles.GLOWING_DUST, GlowingDustParticle.Factory::new);
	}

}
