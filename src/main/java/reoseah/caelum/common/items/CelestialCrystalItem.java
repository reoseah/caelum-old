package reoseah.caelum.common.items;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import reoseah.caelum.common.misc.CelestialCrystalUser;

public class CelestialCrystalItem extends Item {
	public CelestialCrystalItem(Item.Settings settings) {
		super(settings);
	}

	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		if (!player.hasVehicle() && !player.hasPassengers() && player.canUsePortals()) {
			if (((CelestialCrystalUser) player).setUsingCelestialCrystal()) {
				player.setCurrentHand(hand);
				return new TypedActionResult<>(ActionResult.PASS, stack);
			}
		}
		return new TypedActionResult<>(ActionResult.SUCCESS, stack);
	}

	@Override
	public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
		if (user instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) user;
			if (!player.hasVehicle() && !player.hasPassengers() && player.canUsePortals()) {
				if (((CelestialCrystalUser) player).setUsingCelestialCrystal()) {
					for (int i = 0; i <= 2; i++) {
						double dx = world.random.nextGaussian();
						double dy = 2 * world.random.nextDouble();
						double dz = world.random.nextGaussian();
						world.addParticle(ParticleTypes.PORTAL, player.getX(), player.getY() + dy, player.getZ(), -dx, 0, -dz);
					}
				}
			}
		}
		super.usageTick(world, user, stack, remainingUseTicks);
	}

	@Override
	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		if (user instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) user;
			((CelestialCrystalUser) player).stopUsingCelestialCrystal();
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new LiteralText("Right-click to get to and from Caelum (temporary).").formatted(Formatting.GRAY));
	}
}
