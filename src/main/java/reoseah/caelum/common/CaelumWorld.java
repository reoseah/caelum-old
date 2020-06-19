package reoseah.caelum.common;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import reoseah.caelum.common.structures.SkyPortalStructure;
import reoseah.caelum.common.structures.pieces.PillarIslandPiece;
import reoseah.caelum.common.structures.pieces.SkyPortalPiece;

public class CaelumWorld {
	public static final SkyPortalStructure SKY_PORTAL_STRUCTURE = new SkyPortalStructure();
	public static final StructurePieceType SKY_PORTAL_PIECE = SkyPortalPiece::new;
	public static final StructurePieceType PILLAR_ISLAND_PIECE = PillarIslandPiece::new;

	public static void register() {
		Registry.register(Registry.STRUCTURE_FEATURE, "caelum:sky_portal", SKY_PORTAL_STRUCTURE);
		StructureFeature.STRUCTURES.put("caelum:sky_portal", SKY_PORTAL_STRUCTURE);

		Registry.register(Registry.STRUCTURE_PIECE, "caelum:sky_portal", SKY_PORTAL_PIECE);
		Registry.register(Registry.STRUCTURE_PIECE, "caelum:pillar_island", PILLAR_ISLAND_PIECE);

		for (Biome biome : Registry.BIOME) {
			addOverworldSkyPortal(biome);
		}
		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> {
			addOverworldSkyPortal(biome);
		});
	}

	private static void addOverworldSkyPortal(Biome biome) {
		Biome.Category category = biome.getCategory();
		if (category == Biome.Category.NONE
				|| category == Biome.Category.NETHER
				|| category == Biome.Category.THEEND) {
			return;
		}
		biome.addStructureFeature(SKY_PORTAL_STRUCTURE.configure(FeatureConfig.DEFAULT));
	}
}
