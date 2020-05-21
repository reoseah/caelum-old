package reoseah.empyrean.world.features;

import java.util.Random;

public enum SkyrootTreeShape {
	NORMAL {
		public int[] chooseShape(Random random) {
			switch (random.nextInt(6)) {
			case 0:
				return NORMAL3;
			case 1:
				return NORMAL2;
			default:
				return NORMAL1;
			}
		}
	},
	TALL {
		public int[] chooseShape(Random random) {
			switch (random.nextInt(2)) {
			case 0:
				return TALL2;
			default:
				return TALL1;
			}
		}
	},
	SMALL {
		public int[] chooseShape(Random random) {
			switch (random.nextInt(4)) {
			case 0:
				return SMALL2;
			default:
				return SMALL1;
			}
		}
	};

	// Leaf shapes hard-coded from bottom to top
	private static final int[] NORMAL1 = { 0, 0, 1, 2, 1, 2, 1, 0 };
	private static final int[] NORMAL2 = { 0, 0, 2, 2, 1, 2, 1, 0 };
	private static final int[] NORMAL3 = { 0, 0, 1, 2, 2, 1, 2, 1, 0 };

	private static final int[] TALL1 = { 0, 0, 1, 2, 1, 2, 1, 2, 1, 0 };
	private static final int[] TALL2 = { 0, 0, 1, 2, 2, 1, 2, 1, 2, 1, 0 };

	private static final int[] SMALL1 = { 0, 1, 2, 1, 2, 1, 0 };
	private static final int[] SMALL2 = { 0, 0, 1, 2, 1, 2, 1, 0 };

	public abstract int[] chooseShape(Random random);

	public int toInteger() {
		return this.ordinal();
	}

	public static SkyrootTreeShape fromInteger(int value) {
		if (value < 0 || value >= values().length) {
			return NORMAL;
		}
		return values()[value];
	}
}