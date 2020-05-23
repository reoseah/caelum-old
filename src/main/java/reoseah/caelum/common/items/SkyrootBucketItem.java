package reoseah.caelum.common.items;

import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;
import reoseah.caelum.common.CaelumItems;

public class SkyrootBucketItem extends Item {
	protected final Fluid fluid;

	public SkyrootBucketItem(Fluid fluid, Item.Settings settings) {
		super(settings);
		this.fluid = fluid;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		HitResult hitResult = rayTrace(world, player, this.fluid == Fluids.EMPTY ? RayTraceContext.FluidHandling.SOURCE_ONLY : RayTraceContext.FluidHandling.NONE);
		if (hitResult.getType() == HitResult.Type.MISS) {
			return new TypedActionResult<>(ActionResult.PASS, stack);
		} else if (hitResult.getType() != HitResult.Type.BLOCK) {
			return new TypedActionResult<>(ActionResult.PASS, stack);
		} else {
			BlockHitResult blockHitResult = (BlockHitResult) hitResult;
			BlockPos pos = blockHitResult.getBlockPos();
			if (world.canPlayerModifyAt(player, pos)
					&& player.canPlaceOn(pos, blockHitResult.getSide(), stack)) {
				BlockState state;
				if (this.fluid == Fluids.EMPTY) {
					state = world.getBlockState(pos);
					if (state.getBlock() instanceof FluidDrainable) {
						Fluid fluid = ((FluidDrainable) state.getBlock()).tryDrainFluid(world, pos, state);
						if (fluid == Fluids.WATER) {
							player.incrementStat(Stats.USED.getOrCreateStat(this));
							player.playSound(FluidTags.LAVA.contains(fluid) ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
							ItemStack filled = this.getFilledStack(stack, player, CaelumItems.SKYROOT_WATER_BUCKET);
							if (!world.isClient) {
								Criterions.FILLED_BUCKET.trigger((ServerPlayerEntity) player, new ItemStack(fluid.getBucketItem()));
							}

							return new TypedActionResult<>(ActionResult.SUCCESS, filled);
						}
					}

					return new TypedActionResult<>(ActionResult.FAIL, stack);
				} else {
					state = world.getBlockState(pos);
					BlockPos pos2 = state.getBlock() instanceof FluidFillable && this.fluid == Fluids.WATER
							? pos
							: blockHitResult.getBlockPos().offset(blockHitResult.getSide());
					if (this.placeFluid(player, world, pos2, blockHitResult)) {
						this.onEmptied(world, stack, pos2);
						if (player instanceof ServerPlayerEntity) {
							Criterions.PLACED_BLOCK.trigger((ServerPlayerEntity) player, pos2, stack);
						}

						player.incrementStat(Stats.USED.getOrCreateStat(this));
						return new TypedActionResult<>(ActionResult.SUCCESS, this.getEmptiedStack(stack, player));
					} else {
						return new TypedActionResult<>(ActionResult.FAIL, stack);
					}
				}
			} else {
				return new TypedActionResult<>(ActionResult.FAIL, stack);
			}
		}
	}

	protected ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
		return !player.abilities.creativeMode ? new ItemStack(CaelumItems.SKYROOT_BUCKET) : stack;
	}

	public void onEmptied(World world, ItemStack stack, BlockPos pos) {
	}

	protected ItemStack getFilledStack(ItemStack stack, PlayerEntity player, Item item_1) {
		if (player.abilities.creativeMode) {
			return stack;
		} else {
			stack.decrement(1);
			if (stack.isEmpty()) {
				return new ItemStack(item_1);
			} else {
				if (!player.inventory.insertStack(new ItemStack(item_1))) {
					player.dropItem(new ItemStack(item_1), false);
				}

				return stack;
			}
		}
	}

	public boolean placeFluid(PlayerEntity player, World world, BlockPos pos, BlockHitResult hitResult) {
		if (!(this.fluid instanceof BaseFluid)) {
			return false;
		} else {
			BlockState state = world.getBlockState(pos);
			Material material = state.getMaterial();
			boolean notSolid = !material.isSolid();
			boolean replaceable = material.isReplaceable();
			if (world.isAir(pos) || notSolid || replaceable
					|| state.getBlock() instanceof FluidFillable && ((FluidFillable) state.getBlock()).canFillWithFluid(world, pos, state, this.fluid)) {
				if (world.dimension.doesWaterVaporize() && FluidTags.WATER.contains(this.fluid)) {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

					for (int i = 0; i < 8; ++i) {
						world.addParticle(ParticleTypes.LARGE_SMOKE, x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
					}
				} else if (state.getBlock() instanceof FluidFillable && this.fluid == Fluids.WATER) {
					if (((FluidFillable) state.getBlock()).tryFillWithFluid(world, pos, state, ((BaseFluid) this.fluid).getStill(false))) {
						this.playEmptyingSound(player, world, pos);
					}
				} else {
					if (!world.isClient && (notSolid || replaceable) && !material.isLiquid()) {
						world.breakBlock(pos, true);
					}

					this.playEmptyingSound(player, world, pos);
					world.setBlockState(pos, this.fluid.getDefaultState().getBlockState(), 11);
				}

				return true;
			} else {
				return hitResult == null ? false : this.placeFluid(player, world, hitResult.getBlockPos().offset(hitResult.getSide()), (BlockHitResult) null);
			}
		}
	}

	protected void playEmptyingSound(PlayerEntity player, IWorld world, BlockPos pos) {
		SoundEvent sound = FluidTags.LAVA.contains(this.fluid) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
		world.playSound(player, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}
}
