package reoseah.empyrean.world.biomes;

public interface EmpyreanBiome {
	/**
	 * Returns a value that affects how much land a biome gets.
	 * 
	 * This value gets interpolated as to smoothen biome transitions and then added to noise.
	 */
	float getIslandsSize();
}
