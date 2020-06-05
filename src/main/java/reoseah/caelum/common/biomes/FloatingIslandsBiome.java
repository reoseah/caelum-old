package reoseah.caelum.common.biomes;

public interface FloatingIslandsBiome {
	/**
	 * Returns a value that affects how much land a biome gets.
	 *
	 * This value gets interpolated as to smoothen biome transitions and then simply added to noise.
	 */
	float getLandThresholdModifier();
}
