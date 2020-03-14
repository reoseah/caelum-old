package reoseah.above.features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

public class SkyrootTreeFeature extends AbstractTreeFeature<SkyrootTreeFeatureConfig> {
	private static final int[] NORMAL1 = { 0, 0, 1, 2, 1, 2, 1, 0 }; 
	private static final int[] NORMAL2 = { 0, 0, 2, 2, 1, 2, 1, 0 }; 
	private static final int[] NORMAL3 = { 0, 0, 1, 2, 2, 1, 2, 1, 0 }; 

	private static final int[] TALL1 = { 0, 0, 1, 2, 1, 2, 1, 2, 1, 0 }; 
	private static final int[] TALL2 = { 0, 0, 1, 2, 2, 1, 2, 1, 2, 1, 0 }; 

	private static final int[] SMALL1 = { 0, 1, 2, 1, 2, 1, 0 }; 
	private static final int[] SMALL2 = { 0, 0, 1, 2, 1, 2, 1, 0 }; 

	public enum Size {
		NORMAL {
			public Form getForm(Random random) {
				switch (random.nextInt(6)) {
				case 0:
					return new Form(6 + random.nextInt(2), NORMAL3);
				case 1:
					return new Form(5 + random.nextInt(2), NORMAL2);
				default:
					return new Form(5 + random.nextInt(2), NORMAL1);
				}
			}
		},
		TALL {
			public Form getForm(Random random) {
				switch (random.nextInt(2)) {
				case 0:
					return new Form(8 + random.nextInt(2), TALL2);
				default:
					return new Form(7 + random.nextInt(2), TALL1);
				}
			}
		},
		SMALL {
			public Form getForm(Random random) {
				switch (random.nextInt(4)) {
				case 0:
					return new Form(5 + random.nextInt(2), SMALL2);
				default:
					return new Form(4 + random.nextInt(2), SMALL1);
				}
			}
		};

		public abstract Form getForm(Random random);

		public int getBaseHeight() {
			switch (this) {
			case TALL:
				return 7;
			case SMALL:
				return 4;
			default:
				return 5;
			}
		}
	}

	public static class Form {
		public final int height;
		public final int[] pattern;

		public Form(int height, int[] pattern) {
			this.height = height;
			this.pattern = pattern;
		}
	}

	public SkyrootTreeFeature(Function<Dynamic<?>, ? extends SkyrootTreeFeatureConfig> function) {
		super(function);
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, SkyrootTreeFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			this.setLogBlockState(world, random, pos, logPositions, box, config);

			Form form = config.size.getForm(random);

			BlockPos.Mutable p = new BlockPos.Mutable();
			for (int dy = 0; dy < form.pattern.length; dy++) {
				int radius = form.pattern[dy];
				for (int dx = -radius; dx <= radius; dx++) {
					for (int dz = -radius; dz <= radius; dz++) {
						p.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
						if (dx == 0 && dz == 0 && dy < form.height) {
							this.setLogBlockState(world, random, p, logPositions, box, config);
						} else if (Math.abs(dx) != radius || Math.abs(dz) != radius || random.nextInt(2) == 0) {
							if (isAirOrLeaves(world, p)) {
								this.setLeavesBlockState(world, random, p, leavesPositions, box, config);
							}
						}
					}
				}
			}
		}

		return true;
	}
}
