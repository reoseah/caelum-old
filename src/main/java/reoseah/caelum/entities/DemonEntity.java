package reoseah.caelum.entities;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class DemonEntity extends PassiveEntity {
	public DemonEntity(EntityType<? extends PassiveEntity> type, World world) {
		super(type, world);
	}

	@Override
	public EntityData initialize(WorldAccess world, LocalDifficulty difficulty, SpawnReason reason, /* @Nullable */ EntityData data, /* @Nullable */ CompoundTag tag) {
		if (data == null) {
			data = new PassiveEntity.PassiveData();
			((PassiveEntity.PassiveData) data).setBabyAllowed(false);
		}

		return super.initialize(world, difficulty, reason, data, tag);
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