package reoseah.caelum.common.items;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import reoseah.caelum.common.dimension.CaelumDimensionType;

public class CelestialCrystalItem extends Item {
	public CelestialCrystalItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (!world.isClient) {
//			DimensionType destination = player.world.getDimension() == Caelum.DIMENSION_TYPE
//					? DimensionType.getDefaultDimensionType()
//					: Caelum.DIMENSION_TYPE;

//			FabricDimensions.teleport(player, destination);
			if (player.getEntityWorld().getDimension() == DimensionType.getDefaultDimensionType()) {
				player.changeDimension(CaelumDimensionType.REGISTRY_KEY);
			} else if (player.getEntityWorld().getDimension() == CaelumDimensionType.INSTANCE) {
				player.changeDimension(DimensionType.OVERWORLD_REGISTRY_KEY);
			}
		}
		return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new LiteralText("Right-click to get to and from Caelum (temporary).").formatted(Formatting.GRAY));
	}
}
