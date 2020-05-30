package reoseah.caelum.common.entities;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

public class DemonEntity extends PassiveEntity {
	public DemonEntity(EntityType<? extends PassiveEntity> type, World world) {
		super(type, world);
	}

	@Override
	public net.minecraft.entity.EntityData initialize(IWorld world, LocalDifficulty difficulty, SpawnType spawnType, /* @Nullable */ net.minecraft.entity.EntityData data, /* @Nullable */ CompoundTag tag) {
		if (data == null) {
			data = new PassiveEntity.EntityData();
			((PassiveEntity.EntityData) data).setBabyAllowed(false);
		}

		return super.initialize(world, difficulty, spawnType, data, tag);
	}

	@Override
	public PassiveEntity createChild(PassiveEntity mate) {
		return null;
	}

	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
		return 1.62F;
	}

}