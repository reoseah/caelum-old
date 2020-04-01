package reoseah.skyland;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import reoseah.skyland.biomes.SkyBiomes;
import reoseah.skyland.blocks.SkyBlocks;
import reoseah.skyland.dimension.SkylandDimension;
import reoseah.skyland.dimension.chunk.SkylandConfig;
import reoseah.skyland.dimension.chunk.SkylandGeneratorType;
import reoseah.skyland.dimension.decorators.SkyDecorators;
import reoseah.skyland.dimension.features.SkyFeatures;
import reoseah.skyland.dimension.surfaces.SkySurfaceBuilders;
import reoseah.skyland.items.HoeHelper;
import reoseah.skyland.items.SkyItems;

public class Skyland implements ModInitializer {
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(makeID("main"), () -> new ItemStack(SkyBlocks.SKY_GRASS));

	public static final EntityPlacer TELEPORTING_PLACER = (entity, world, dim, offsetX, offsetZ) -> {
		BlockPos entityPos = entity.getSenseCenterPos();

		BlockPos structurePos = SkyFeatures.LARGE_ISLAND.locateStructure(
				world, world.getChunkManager().getChunkGenerator(),
				entityPos, 100, false);

		int surfaceY = world
				.getChunk(
						structurePos.getX() >> 4,
						structurePos.getZ() >> 4)
				.sampleHeightmap(
						Heightmap.Type.MOTION_BLOCKING,
						structurePos.getX() & 15,
						structurePos.getZ() & 15);

		return new BlockPattern.TeleportTarget(
				new Vec3d(
						structurePos.getX(),
						surfaceY + 2,
						structurePos.getZ()),
				entity.getVelocity(),
				(int) entity.yaw);
	};

	public static final DimensionType DIMENSION_TYPE = FabricDimensionType.builder()
			.factory(SkylandDimension::new)
			.defaultPlacer(TELEPORTING_PLACER)
			.biomeAccessStrategy(HorizontalVoronoiBiomeAccessType.INSTANCE)
			.buildAndRegister(makeID("sky"));

	public static Identifier makeID(String name) {
		return new Identifier("skyland", name);
	}

	@Override
	public void onInitialize() {
		SkyBlocks.register();
		SkyItems.register();

		SkyFeatures.register();
		SkyDecorators.register();
		SkySurfaceBuilders.register();

		SkyBiomes.register();

		Registry.register(Registry.CHUNK_GENERATOR_TYPE, makeID("sky"), new SkylandGeneratorType(false, SkylandConfig::new));

		HoeHelper.registerTilling();
	}
}
