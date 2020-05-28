package reoseah.caelum.common.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class CaelumTreeFeatureConfig implements FeatureConfig {
	public static final Codec<CaelumTreeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(BlockStateProvider.field_24937.fieldOf("trunk_provider").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.trunk;
		}), BlockStateProvider.field_24937.fieldOf("leaves_provider").forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.leaves;
		}), CaelumTreeShape.CODEC.fieldOf("shape").withDefault(CaelumTreeShape.NORMAL).forGetter((treeFeatureConfig) -> {
			return treeFeatureConfig.shape;
		})).apply(instance, CaelumTreeFeatureConfig::new);
	});
	public final BlockStateProvider trunk;
	public final BlockStateProvider leaves;
	public final CaelumTreeShape shape;

	public CaelumTreeFeatureConfig(BlockStateProvider trunk,
			BlockStateProvider leaves,
			CaelumTreeShape shape) {
		this.trunk = trunk;
		this.leaves = leaves;
		this.shape = shape;
	}
}
