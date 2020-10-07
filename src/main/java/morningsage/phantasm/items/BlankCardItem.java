package morningsage.phantasm.items;

import morningsage.phantasm.PhantasmCommon;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static morningsage.phantasm.PhantasmCommon.createIdentifier;

public class BlankCardItem extends Item {
    public static final Identifier ID = createIdentifier("blank_card");

    public BlankCardItem() {
        super(new Item.Settings().group(PhantasmCommon.ITEM_GROUP));

        Registry.register(Registry.ITEM, ID, this);
    }

    /**
     * This method may not behave as normal!
     *
     * When the PlayerEntityMixin is enabled or unless another mod
     * calls this method directly, useOnEntity is only called from
     * the mixin which ignores whether or not you can normally
     * interact with the LivingEntity.  This allows for capturing
     * Villagers and the like.  We could override the "use" method,
     * but that would require another resource intensive raycast
     */
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // Create the new card version of the entity
        ItemStack newStack = BaseCardItem.ofEntity(entity);

        // Only do things if the card is valid
        if (newStack != ItemStack.EMPTY) {
            // Remove the entity from the game
            entity.remove();

            // Only use up items if the player is not in survival
            if (!user.abilities.creativeMode) stack.decrement(1);

            // Attempt to add the item in the Player's inventory
            if (!user.inventory.insertStack(newStack)) {
                // When all use fails, put it on the ground
                user.dropItem(newStack, false);
            }

            // If we get here, everything was a success
            return ActionResult.SUCCESS;
        }

        return super.useOnEntity(stack, user, entity, hand);
    }
}
