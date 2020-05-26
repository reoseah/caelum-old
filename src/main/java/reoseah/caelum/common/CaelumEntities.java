package reoseah.caelum.common;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.entities.DemonEntity;

public class CaelumEntities {
	public static final EntityType<DemonEntity> DEMON = new EntityType<>(DemonEntity::new, EntityCategory.MISC, true, true, true, true, 128, 32, EntityDimensions.fixed(0.6F, 1.95F));

	public static void register() {
		Registry.register(Registry.ENTITY_TYPE, "caelum:demon", DEMON);
	}
}
