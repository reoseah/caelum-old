package reoseah.caelum.items;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import reoseah.caelum.registry.CaelumDimension;

public class MagicalCompassItem extends Item {
	public MagicalCompassItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (world instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld) world;
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
			if (world.getRegistryKey() == CaelumDimension.DIMENSION_KEY) {
				ServerWorld overworldWorld = serverWorld.getServer().getWorld(World.OVERWORLD);
				serverPlayer.teleport(overworldWorld, overworldWorld.getSpawnPos().getX(), overworldWorld.getSpawnPos().getY(), overworldWorld.getSpawnPos().getZ(), 0, 0);

			} else if (world.getRegistryKey() == World.OVERWORLD) {

				ServerWorld caelumWorld = serverWorld.getServer().getWorld(CaelumDimension.DIMENSION_KEY);
				serverPlayer.teleport(caelumWorld, 0, 61, 0, 0, 0);
			}
		}
		return super.use(world, user, hand);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
		tooltip.add(new TranslatableText(getTranslationKey() + ".desc"));
	}
}
