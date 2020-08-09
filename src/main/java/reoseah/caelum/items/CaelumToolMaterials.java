package reoseah.caelum.items;

import java.util.function.Supplier;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import reoseah.caelum.CaelumBlocks;
import reoseah.caelum.CaelumItems;

public enum CaelumToolMaterials implements ToolMaterial {
	SKYROOT(0, 25, 2.0F, 0, 24, () -> Ingredient.ofItems(CaelumBlocks.SKYROOT_PLANKS)),
	AERRACK(1, 150, 4.0F, 1.0F, 12, () -> Ingredient.ofItems(CaelumBlocks.AERRACK)),
	SKYGLASS(2, 250, 6.0F, 2.0F, 18, () -> Ingredient.ofItems(CaelumItems.SKYGLASS));

	private final int miningLevel;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Lazy<Ingredient> repairIngredient;

	private CaelumToolMaterials(int miningLevel, int itemDurebility, float miningSpeed, float attackDamage, int enchantablity, Supplier<Ingredient> repairIngredient) {
		this.miningLevel = miningLevel;
		this.itemDurability = itemDurebility;
		this.miningSpeed = miningSpeed;
		this.attackDamage = attackDamage;
		this.enchantability = enchantablity;
		this.repairIngredient = new Lazy<>(repairIngredient);
	}

	@Override
	public int getDurability() {
		return this.itemDurability;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return this.miningSpeed;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getMiningLevel() {
		return this.miningLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
