package reoseah.caelum.common.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;

public class AerrackOreConfig implements FeatureConfig {
	public static final Codec<AerrackOreConfig> CODEC = RecordCodecBuilder
			.create(instance -> {
				return instance.group(BlockState.field_24734.fieldOf("state").forGetter(config -> {
					return config.state;
				}), Codec.INT.fieldOf("size").withDefault(0).forGetter(config -> {
					return config.size;
				})).apply(instance, AerrackOreConfig::new);
			});

	public final int size;
	public final BlockState state;

	public AerrackOreConfig(BlockState state, int size) {
		this.size = size;
		this.state = state;
	}

}
