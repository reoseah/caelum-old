package reoseah.caelum.common.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class SkyrootFeatureConfig implements FeatureConfig {
	public static final Codec<SkyrootFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(BlockStateProvider.field_24937.fieldOf("trunk_provider").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.trunk;
		}), BlockStateProvider.field_24937.fieldOf("leaves_provider").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.leaves;
		}), SkyrootTreeShape.CODEC.fieldOf("shape").withDefault(SkyrootTreeShape.NORMAL).forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.shape;
		})).apply(instance, SkyrootFeatureConfig::new);
	});
	public final BlockStateProvider trunk;
	public final BlockStateProvider leaves;
	public final SkyrootTreeShape shape;

	public SkyrootFeatureConfig(BlockStateProvider trunk,
			BlockStateProvider leaves,
			SkyrootTreeShape shape) {
		this.trunk = trunk;
		this.leaves = leaves;
		this.shape = shape;
	}
}
