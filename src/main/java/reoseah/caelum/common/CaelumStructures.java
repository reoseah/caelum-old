package reoseah.caelum.common;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import reoseah.caelum.Caelum;
import reoseah.caelum.common.world.PortalRuinFeature;
import reoseah.caelum.common.world.PortalRuinGenerator;

import java.util.Locale;

public class CaelumStructures {

    public static final StructurePieceType structurePieceType = Registry.register(Registry.STRUCTURE_PIECE, Caelum.id("portal_ruin_layer"), PortalRuinGenerator.Piece::new);
    public static final StructureFeature<DefaultFeatureConfig> PORTAL_RUIN_FEATURE = registerFeature("portal_ruin", new PortalRuinFeature());
    public static final StructureFeature<DefaultFeatureConfig> PORTAL_RUIN_STRUCTURE = registerStructureFeature("portal_ruin", PORTAL_RUIN_FEATURE);

    public static void register() {
        Feature.STRUCTURES.put("PortalRuin".toLowerCase(Locale.ROOT), PORTAL_RUIN_STRUCTURE);
    }

    private static <Config extends FeatureConfig, F extends Feature<Config>> F registerFeature(String name, F feature) {
        return Registry.register(Registry.FEATURE, Caelum.id(name), feature);
    }

    private static <Config extends FeatureConfig> StructureFeature<Config> registerStructureFeature(String name, StructureFeature<Config> structureFeature) {
        return Registry.register(Registry.STRUCTURE_FEATURE, Caelum.id(name), structureFeature);
    }
}
