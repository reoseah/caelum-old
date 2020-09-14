package reoseah.caelum.items;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import reoseah.caelum.registry.CaelumItems;

public class SkyrootBucketItem extends Item {
	protected final Fluid fluid;

	public SkyrootBucketItem(Fluid fluid, Item.Settings settings) {
		super(settings);
		this.fluid = fluid;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		HitResult hitResult = raycast(world, user, this.fluid == Fluids.EMPTY ? RaycastContext.FluidHandling.SOURCE_ONLY : RaycastContext.FluidHandling.NONE);
		if (hitResult.getType() == HitResult.Type.MISS) {
			return TypedActionResult.pass(stack);
		}
		if (hitResult.getType() != HitResult.Type.BLOCK) {
			return TypedActionResult.pass(stack);
		}
		BlockHitResult blockHitResult = (BlockHitResult) hitResult;
		BlockPos pos = blockHitResult.getBlockPos();
		Direction direction = blockHitResult.getSide();
		BlockPos pos2 = pos.offset(direction);
		if (world.canPlayerModifyAt(user, pos) && user.canPlaceOn(pos2, direction, stack)) {
			if (this.fluid == Fluids.EMPTY) {
				BlockState state = world.getBlockState(pos);
				if (state.getBlock() instanceof FluidDrainable && state.getFluidState().getFluid() == Fluids.WATER) {
					Fluid fluid = ((FluidDrainable) state.getBlock()).tryDrainFluid(world, pos, state);
					if (fluid != Fluids.EMPTY) {
						user.incrementStat(Stats.USED.getOrCreateStat(this));
						user.playSound(fluid.isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
						ItemStack stack2 = ItemUsage.method_30012(stack, user, new ItemStack(CaelumItems.SKYROOT_WATER_BUCKET));
						if (!world.isClient) {
							Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity) user, new ItemStack(CaelumItems.SKYROOT_WATER_BUCKET));
						}

						return TypedActionResult.method_29237(stack2, world.isClient());
					}
				}
				return TypedActionResult.fail(stack);
			} else {
				BlockState blockState = world.getBlockState(pos);
				BlockPos pos3 = blockState.getBlock() instanceof FluidFillable && this.fluid == Fluids.WATER ? pos : pos2;
				if (this.placeFluid(user, world, pos3, blockHitResult)) {
					this.onEmptied(world, stack, pos3);
					if (user instanceof ServerPlayerEntity) {
						Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) user, pos3, stack);
					}

					user.incrementStat(Stats.USED.getOrCreateStat(this));
					return TypedActionResult.method_29237(this.getEmptiedStack(stack, user), world.isClient());
				}
				return TypedActionResult.fail(stack);
			}
		}
		return TypedActionResult.fail(stack);
	}

	protected ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
		return !player.abilities.creativeMode ? new ItemStack(CaelumItems.SKYROOT_BUCKET) : stack;
	}

	public void onEmptied(World world, ItemStack stack, BlockPos pos) {
	}

	public boolean placeFluid(/* @Nullable */ PlayerEntity player, World world, BlockPos pos, /* @Nullable */ BlockHitResult blockHitResult) {
		if (!(this.fluid instanceof FlowableFluid)) {
			return false;
		}
		BlockState blockState = world.getBlockState(pos);
		Block block = blockState.getBlock();
		Material material = blockState.getMaterial();
		boolean bl = blockState.canBucketPlace(this.fluid);
		boolean bl2 = blockState.isAir() || bl || block instanceof FluidFillable && ((FluidFillable) block).canFillWithFluid(world, pos, blockState, this.fluid);
		if (!bl2) {
			return blockHitResult != null && this.placeFluid(player, world, blockHitResult.getBlockPos().offset(blockHitResult.getSide()), (BlockHitResult) null);
		}
		if (world.getDimension().isUltrawarm() && this.fluid.isIn(FluidTags.WATER)) {
			int i = pos.getX();
			int j = pos.getY();
			int k = pos.getZ();
			world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

			for (int l = 0; l < 8; ++l) {
				world.addParticle(ParticleTypes.LARGE_SMOKE, i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D);
			}

			return true;
		}
		if (block instanceof FluidFillable && this.fluid == Fluids.WATER) {
			((FluidFillable) block).tryFillWithFluid(world, pos, blockState, ((FlowableFluid) this.fluid).getStill(false));
			this.playEmptyingSound(player, world, pos);
			return true;
		}
		if (!world.isClient && bl && !material.isLiquid()) {
			world.breakBlock(pos, true);
		}

		if (!world.setBlockState(pos, this.fluid.getDefaultState().getBlockState(), 11) && !blockState.getFluidState().isStill()) {
			return false;
		}
		this.playEmptyingSound(player, world, pos);
		return true;
	}

	protected void playEmptyingSound(/* @Nullable */ PlayerEntity player, WorldAccess world, BlockPos pos) {
		SoundEvent soundEvent = this.fluid.isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
		world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}
}
