package reoseah.caelum.common;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;
import reoseah.caelum.entities.DemonEntity;

public class CaelumEntities {
	public static final EntityType<DemonEntity> DEMON = new EntityType<>(DemonEntity::new, SpawnGroup.MISC, true, true, true, true, EntityDimensions.fixed(0.6F, 1.95F), 10, 3);

	public static void register() {
		Registry.register(Registry.ENTITY_TYPE, "caelum:demon", DEMON);
	}
}