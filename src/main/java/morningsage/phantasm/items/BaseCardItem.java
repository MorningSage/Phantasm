package morningsage.phantasm.items;

import morningsage.phantasm.PhantasmCommon;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

import static morningsage.phantasm.PhantasmCommon.createIdentifier;
import static morningsage.phantasm.items.PhantasmItems.BASE_CARD_ITEM;

public class BaseCardItem extends Item {
    public static final Identifier ID = createIdentifier("base_card");

    public BaseCardItem() {
        super(new Settings().group(PhantasmCommon.ITEM_GROUP).maxCount(1));

        Registry.register(Registry.ITEM, ID, this);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        if (stack.getItem() instanceof BaseCardItem) {
            CompoundTag tag = stack.getOrCreateTag();

            tooltip.add(new LiteralText("Card Base: " + tag.getString("entity")).formatted(Formatting.GRAY));
            tooltip.add(new LiteralText("Stat 1: " + (int) tag.getFloat("maxHealth")).formatted(Formatting.GRAY));
            tooltip.add(new LiteralText("Stat 2: " + (int) tag.getFloat("currentHealth")).formatted(Formatting.GRAY));
        }
    }

    public static ItemStack ofEntity(LivingEntity entity) {
        // Make sure we have a valid entity
        if (entity == null) return ItemStack.EMPTY;

        // ToDo: Perform checks to make sure the entity is valid?

        // If execution is here, that means the entity is valid
        ItemStack baseCard = new ItemStack(BASE_CARD_ITEM);
        CompoundTag tag = baseCard.getOrCreateTag();

        // Save the entity info
        tag.putString("entity", EntityType.getId(entity.getType()).toString());
        tag.putFloat("maxHealth", entity.getMaxHealth());
        tag.putFloat("currentHealth", entity.getHealth());

        return baseCard;
    }
}
