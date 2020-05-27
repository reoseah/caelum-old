package reoseah.caelum.mixins;

import java.util.Map;
import java.util.concurrent.Executor;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.common.collect.ImmutableList;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.SaveProperties;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.border.WorldBorderListener;
import net.minecraft.world.dimension.DimensionTracker;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.level.UnmodifiableLevelProperties;
import net.minecraft.world.level.storage.LevelStorage;
import reoseah.caelum.common.dimension.CaelumBiomeSource;
import reoseah.caelum.common.dimension.CaelumChunkGenerator;
import reoseah.caelum.common.dimension.CaelumDimensionType;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Shadow
	protected @Final DimensionTracker.Modifiable field_25132;
	@Shadow
	private @Final Map<RegistryKey<DimensionType>, ServerWorld> worlds;
	@Shadow
	protected @Final SaveProperties field_24372;
	@Shadow
	protected @Final LevelStorage.Session session;
	@Shadow
	private @Final Executor workerExecutor;

	@Inject(method = "createWorlds", at = @At("RETURN"))
	protected void createWorlds(WorldGenerationProgressListener listener, CallbackInfo callback) {
		if (!this.field_25132.getRegistry().containsKey(CaelumDimensionType.REGISTRY_KEY)) {
			this.field_25132.add(CaelumDimensionType.REGISTRY_KEY, CaelumDimensionType.INSTANCE);
		}
		GeneratorOptions options = this.field_24372.method_28057(); // getGeneratorOptions
		long seed = options.getSeed();

		CaelumChunkGenerator generator = new CaelumChunkGenerator(new CaelumBiomeSource(seed), seed);
		UnmodifiableLevelProperties properties = new UnmodifiableLevelProperties(CaelumDimensionType.INSTANCE, this.field_24372, this.field_24372.getMainWorldProperties());
		ServerWorld serverWorld = new ServerWorld((MinecraftServer) (Object) this, this.workerExecutor, this.session, properties, CaelumDimensionType.INSTANCE, listener, generator, false, BiomeAccess.hashSeed(seed), ImmutableList.of(), false);

		WorldBorder worldBorder = this.worlds.get(DimensionType.OVERWORLD_REGISTRY_KEY).getWorldBorder();

		worldBorder.addListener(new WorldBorderListener.WorldBorderSyncer(serverWorld.getWorldBorder()));
		this.worlds.put(CaelumDimensionType.REGISTRY_KEY, serverWorld);
	}
}
