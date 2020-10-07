package morningsage.phantasm.mixin;

import morningsage.phantasm.items.BlankCardItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	@Shadow @Final public PlayerAbilities abilities;

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(
		at = @At("HEAD"),
		method = "interact",
		cancellable = true
	)
	private void interact(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> info) {
		/*
		 * IF we decide to allow entities that players normally interact with to be captures
		 * this method would override the default behavior if the player is holding blank card.
		 *
		 * For now, this is disabled, but retained in the case it is implemented later.
		 */

		//ItemStack itemStack = this.getStackInHand(hand);

		//// Only capture when using the right tool
		//if (!(itemStack.getItem() instanceof BlankCardItem)) return;
		//// No Players for now...
		//if ((entity instanceof PlayerEntity)) return;
		//// Only living entities allowed
		//if (!(entity instanceof LivingEntity)) return;

		//ActionResult actionResult = itemStack.useOnEntity((PlayerEntity) (Object) this, (LivingEntity) entity, hand);

		//if (actionResult.isAccepted()) {
		//	if (itemStack.isEmpty() && !this.abilities.creativeMode) {
		//		this.setStackInHand(hand, ItemStack.EMPTY);
		//	}

		//	info.setReturnValue(actionResult);
		//}

		//info.setReturnValue(ActionResult.PASS);
	}
}
