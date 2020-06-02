package reoseah.caelum.common.world;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import reoseah.caelum.common.CaelumStructures;

import java.util.List;
import java.util.Random;

public class PortalRuinGenerator {
    private static final Identifier RUIN = new Identifier("caelum:portal_ruin/portal_ruin");

    static void addParts(StructureManager structureManager, BlockPos blockPos, BlockRotation rotation, List<StructurePiece> pieceList, Random random, DefaultFeatureConfig featureConfig) {
        pieceList.add(new PortalRuinGenerator.Piece(structureManager, RUIN, blockPos, rotation, false));
    }

    public static class Piece extends SimpleStructurePiece {
        private final Identifier template;
        private final BlockRotation rotation;

        public Piece(StructureManager structureManager, Identifier identifier, BlockPos blockPos, BlockRotation rotation, boolean ladderSide) {
            super(CaelumStructures.structurePieceType, 0);

            this.template = identifier;
            this.rotation = rotation;
            this.pos = blockPos;

            this.initializePlacementData(structureManager);
        }

        public Piece(StructureManager structureManager, CompoundTag compoundTag) {
            super(CaelumStructures.structurePieceType, compoundTag);

            this.template = new Identifier(compoundTag.getString("Template"));
            this.rotation = BlockRotation.valueOf(compoundTag.getString("Rot"));

            this.initializePlacementData(structureManager);
        }

        private void initializePlacementData(StructureManager structureManager_1) {
            Structure structure_1 = structureManager_1.getStructureOrBlank(this.template);
            StructurePlacementData structurePlacementData_1 = (new StructurePlacementData()).setRotation(this.rotation).setMirrored(BlockMirror.NONE).setPosition(pos).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(structure_1, this.pos, structurePlacementData_1);
        }

        @Override
        protected void toNbt(CompoundTag compoundTag_1) {
            super.toNbt(compoundTag_1);
            compoundTag_1.putString("Template", this.template.toString());
            compoundTag_1.putString("Rot", this.rotation.name());
        }

        @Override
        public boolean generate(IWorld world, ChunkGenerator<?> generator, Random random, BlockBox box, ChunkPos pos) {
            this.placementData.addProcessor(BlockIgnoreStructureProcessor.IGNORE_AIR_AND_STRUCTURE_BLOCKS);

            int yLevel = 0;

            for (int i = 255; i > 0; i--) {
                if (world.getBlockState(new BlockPos(pos.getStartX() + 7, i + this.pos.getY(), pos.getStartZ())).getBlock() != Blocks.AIR) {
                    yLevel = i;
                    break;
                }
            }

            this.pos = new BlockPos(this.pos.getX(), yLevel, this.pos.getZ());

            return super.generate(world, generator, random, box, pos);
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, IWorld world, Random random, BlockBox boundingBox) {
        }
    }
}
