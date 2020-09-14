package reoseah.caelum.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class SkyrootConfig implements FeatureConfig {
	public static final Codec<SkyrootConfig> CODEC = RecordCodecBuilder.create(instance -> instance
			.group(
					BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter(config -> config.trunk),
					BlockStateProvider.TYPE_CODEC.fieldOf("leaves_provider").forGetter(config -> config.leaves),
					SkyrootTreeShape.CODEC.fieldOf("shape").orElse(SkyrootTreeShape.NORMAL).forGetter(config -> config.shape))
			.apply(instance, SkyrootConfig::new));

	public final BlockStateProvider trunk;
	public final BlockStateProvider leaves;
	public final SkyrootTreeShape shape;

	public SkyrootConfig(BlockStateProvider trunkProvider,
			BlockStateProvider leavesProvider,
			SkyrootTreeShape shape) {
		this.trunk = trunkProvider;
		this.leaves = leavesProvider;
		this.shape = shape;
	}
}
