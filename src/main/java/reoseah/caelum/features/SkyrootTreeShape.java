package reoseah.caelum.features;

import java.util.Locale;
import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.util.StringIdentifiable;

public enum SkyrootTreeShape implements StringIdentifiable {
	NORMAL {
		@Override
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
		@Override
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
		@Override
		public int[] chooseShape(Random random) {
			switch (random.nextInt(4)) {
			case 0:
				return SMALL2;
			default:
				return SMALL1;
			}
		}
	},
	SHRUB {
		@Override
		public int[] chooseShape(Random random) {
			return SHRUB1;
		}
	};

	// Leaf shapes hard-coded from bottom to top
	public abstract int[] chooseShape(Random random);

	private static final int[] NORMAL1 = { 0, 0, 1, 2, 1, 2, 1, 0 };
	private static final int[] NORMAL2 = { 0, 0, 2, 2, 1, 2, 1, 0 };
	private static final int[] NORMAL3 = { 0, 0, 1, 2, 2, 1, 2, 1, 0 };

	private static final int[] TALL1 = { 0, 0, 1, 2, 1, 2, 1, 2, 1, 0 };
	private static final int[] TALL2 = { 0, 0, 1, 2, 2, 1, 2, 1, 2, 1, 0 };

	private static final int[] SMALL1 = { 0, 1, 2, 1, 2, 1, 0 };
	private static final int[] SMALL2 = { 0, 0, 1, 2, 1, 2, 1, 0 };

	private static final int[] SHRUB1 = { 2, 1, 0 };

	public static final Codec<SkyrootTreeShape> CODEC = StringIdentifiable.method_28140(SkyrootTreeShape::values, SkyrootTreeShape::fromString);

	@Override
	public String asString() {
		return this.name().toLowerCase(Locale.ROOT);
	}

	public static SkyrootTreeShape fromString(String name) {
		switch (name) {
		case "normal":
			return NORMAL;
		case "tall":
			return TALL;
		case "small":
			return SMALL;
		case "shrub":
			return SHRUB;
		default:
			return null;
		}
	}
}