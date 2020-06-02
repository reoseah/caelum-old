package reoseah.caelum.common.world;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import reoseah.caelum.common.CaelumStructures;

public class PortalRuinFeature extends AbstractTempleFeature<DefaultFeatureConfig> {

    public PortalRuinFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    protected int getSeedModifier() {
        return 0;
    }

    @Override
    public StructureStartFactory getStructureStartFactory() {
        return PortalRuinStructureStart::new;
    }

    @Override
    public String getName() {
        return "portalruin";
    }

    @Override
    public int getRadius() {
        return 8;
    }

    public static class PortalRuinStructureStart extends StructureStart {

        public PortalRuinStructureStart(StructureFeature<?> feature, int chunkX, int chunkZ, BlockBox box, int references, long l) {
            super(feature, chunkX, chunkZ, box, references, l);
        }

        @Override
        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome) {
            DefaultFeatureConfig defaultFeatureConfig = chunkGenerator.getStructureConfig(biome, CaelumStructures.PORTAL_RUIN_FEATURE);

            int x = chunkX * 16;
            int z = chunkZ * 16;

            BlockPos startingPos = new BlockPos(x, 0, z);

            BlockRotation rotation = BlockRotation.NONE;

            PortalRuinGenerator.addParts(structureManager, startingPos, rotation, this.children, this.random, defaultFeatureConfig);
            this.setBoundingBoxFromChildren();
        }
    }
}
