package reoseah.empyrean.items;

import java.util.function.Supplier;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import reoseah.empyrean.SkyBlocks;
import reoseah.empyrean.SkyItems;

public enum EmpyreanToolMaterials implements ToolMaterial {
	SKYROOT(0, 59, 2.0F, 0, 14, () -> Ingredient.ofItems(SkyBlocks.SKYROOT_PLANKS)),
//	AERRACK(1, 131, 4.0F, 1.0F, 8, () -> Ingredient.ofItems(SkyBlocks.AERRACK)),
	CERUCLASE(2, 550, 6.0F, 2.0F, 14, () -> Ingredient.ofItems(SkyItems.CERUCLASE));

	private final int miningLevel;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Lazy<Ingredient> repairIngredient;

	private EmpyreanToolMaterials(int miningLevel, int itemDurebility, float miningSpeed, float attackDamage, int enchantablity, Supplier<Ingredient> repairIngredient) {
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
