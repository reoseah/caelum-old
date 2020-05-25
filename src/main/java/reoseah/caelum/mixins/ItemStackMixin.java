package reoseah.caelum.mixins;

import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.common.CaelumItems;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Shadow
	private @Final Item item;

	@Inject(method = "getEnchantments", at = @At("RETURN"), cancellable = true)
	public void getEnchantments(CallbackInfoReturnable<ListTag> ci) {
		if (item == CaelumItems.CERUCLASE_ROCKCUTTER) {
			ListTag tag = ci.getReturnValue();
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(tag);
			if (!enchantments.containsKey(Enchantments.SILK_TOUCH)) {
				ListTag newtag = new ListTag();
				newtag.addAll(tag);
				CompoundTag silktouch = new CompoundTag();
				silktouch.putString("id", Registry.ENCHANTMENT.getId(Enchantments.SILK_TOUCH).toString());
				silktouch.putInt("lvl", 1);
				newtag.add(silktouch);
				ci.setReturnValue(newtag);
			}
		}
	}
}
