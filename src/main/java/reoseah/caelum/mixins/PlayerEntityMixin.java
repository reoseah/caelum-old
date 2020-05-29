package reoseah.caelum.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import reoseah.caelum.common.dimension.CaelumDimensionType;
import reoseah.caelum.common.misc.CelestialCrystalUser;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity implements CelestialCrystalUser {
	private PlayerEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	protected boolean usingCelestialCrystal = false;

	@Override
	public boolean setUsingCelestialCrystal() {
		Entity entity = (Entity) (Object) this;
		if (entity.netherPortalCooldown > 0) {
			entity.netherPortalCooldown = entity.getDefaultNetherPortalCooldown();
			return false;
		}
		this.usingCelestialCrystal = true;
		this.inNetherPortal = true;
		return true;
	}

	@Override
	public void stopUsingCelestialCrystal() {
		this.usingCelestialCrystal = false;
		this.inNetherPortal = false;
		this.netherPortalTime = 0;
	}

	@Override
	public boolean onNetherPortalTick() {
		PlayerEntity player = (PlayerEntity) (Object) this;
		int maxPortalTime = 79;
		if (this.usingCelestialCrystal) {
			if (!player.hasVehicle() && this.netherPortalTime++ >= maxPortalTime) {
				this.netherPortalTime = 0;
				player.netherPortalCooldown = player.getDefaultNetherPortalCooldown();
				this.inNetherPortal = false;
				this.usingCelestialCrystal = false;

				ItemStack stack = player.getActiveItem();

				player.stopUsingItem();
				player.getItemCooldownManager().set(stack.getItem(), 100);
				player.world.playSound((PlayerEntity) null,
						player.getX(), player.getY(), player.getZ(),
						SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL,
						0.5F, 0.4F / (player.getRandom().nextFloat() * 0.4F + 0.8F));
				player.world.getProfiler().push("portal");

				if (player.world instanceof ServerWorld) {
					player.changeDimension(CaelumDimensionType.REGISTRY_KEY);
				}
				player.world.getProfiler().push("portal");
			}
			this.usingCelestialCrystal = false;
			this.inNetherPortal = false;
			return true;
		}
		return false;
	}
}
