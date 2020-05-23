package reoseah.caelum.common.features;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.FeatureConfig;

public class AerrackOreConfig implements FeatureConfig {
	public final int size;
	public final BlockState state;

	public AerrackOreConfig(BlockState state, int size) {
		this.size = size;
		this.state = state;
	}

	@Override
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		return new Dynamic<>(ops,
				ops.createMap(ImmutableMap.of(
						ops.createString("size"), ops.createInt(this.size),
						ops.createString("state"), BlockState.serialize(ops, this.state).getValue())));
	}

	public static AerrackOreConfig deserialize(Dynamic<?> dynamic) {
		int size = dynamic.get("size").asInt(0);
		BlockState state = dynamic.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		return new AerrackOreConfig(state, size);
	}
}
