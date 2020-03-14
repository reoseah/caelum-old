package reoseah.above.features;

import java.util.ArrayList;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import reoseah.above.features.SkyrootTreeFeature.Size;

public class SkyrootTreeFeatureConfig extends TreeFeatureConfig {
	public final SkyrootTreeFeature.Size size;

	public SkyrootTreeFeatureConfig(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, Size size) {
		super(trunkProvider, leavesProvider, new ArrayList<>(), size.getBaseHeight());
		this.size = size;
	}

	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		ImmutableMap<T, T> map = ImmutableMap.of(ops.createString("size"), ops.createInt(this.size.ordinal()));
		return new Dynamic<>(ops, ops.createMap(map)).merge(super.serialize(ops));
	}

	public static <T> SkyrootTreeFeatureConfig deserialize(Dynamic<T> dynamic) {
		TreeFeatureConfig treeFeatureConfig = TreeFeatureConfig.deserialize(dynamic);
		return new SkyrootTreeFeatureConfig(treeFeatureConfig.trunkProvider, treeFeatureConfig.leavesProvider, Size.values()[dynamic.get("size").asInt(0) % Size.values().length]);
	}
}
