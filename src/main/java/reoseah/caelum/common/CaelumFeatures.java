package reoseah.caelum.common;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import reoseah.caelum.common.dimension.CaelumChunkGenerator;
import reoseah.caelum.common.dimension.CaelumPlacementHandler;
import reoseah.caelum.common.features.SkyrootConfig;
import reoseah.caelum.common.features.SkyrootShrubFeature;
import reoseah.caelum.common.features.SkyrootTreeFeature;
import reoseah.caelum.common.structures.SkyPortalStructure;
import reoseah.caelum.common.structures.pieces.LargeIslandPiece;
import reoseah.caelum.common.structures.pieces.PillarIslandPiece;
import reoseah.caelum.common.structures.pieces.SkyPortalPiece;

public class CaelumFeatures {
	public static final RegistryKey<World> CAELUM_WORLD = RegistryKey.of(Registry.DIMENSION, new Identifier("caelum:caelum"));

	public static final SkyPortalStructure SKY_PORTAL_STRUCTURE = new SkyPortalStructure();

	public static final StructurePieceType SKY_PORTAL_PIECE = SkyPortalPiece::new;
	public static final StructurePieceType PILLAR_ISLAND_PIECE = PillarIslandPiece::new;
	public static final StructurePieceType LARGE_ISLAND_PIECE = LargeIslandPiece::new;

	public static final Feature<SkyrootConfig> SKYROOT_TREE = new SkyrootTreeFeature(SkyrootConfig.CODEC);
	public static final Feature<SkyrootConfig> SKYROOT_SHRUB = new SkyrootShrubFeature(SkyrootConfig.CODEC);

	public static void register() {
		Registry.register(Registry.CHUNK_GENERATOR, new Identifier("caelum:caelum"), CaelumChunkGenerator.CODEC);

		Registry.register(Registry.STRUCTURE_FEATURE, "caelum:sky_portal", SKY_PORTAL_STRUCTURE);
		StructureFeature.STRUCTURES.put("caelum:sky_portal", SKY_PORTAL_STRUCTURE);

		Registry.register(Registry.STRUCTURE_PIECE, "caelum:sky_portal", SKY_PORTAL_PIECE);
		Registry.register(Registry.STRUCTURE_PIECE, "caelum:pillar_island", PILLAR_ISLAND_PIECE);
		Registry.register(Registry.STRUCTURE_PIECE, "caelum:large_island", LARGE_ISLAND_PIECE);

		for (Biome biome : Registry.BIOME) {
			addOverworldSkyPortal(biome);
		}
		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> {
			addOverworldSkyPortal(biome);
		});

		Registry.register(Registry.FEATURE, "caelum:skyroot_tree", SKYROOT_TREE);
		Registry.register(Registry.FEATURE, "caelum:skyroot_shrub", SKYROOT_SHRUB);

		FabricDimensions.registerDefaultPlacer(CAELUM_WORLD, CaelumPlacementHandler.enter(new BlockPos(0, 64, 0)));
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
