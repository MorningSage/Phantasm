package morningsage.phantasm.blocks;

import morningsage.phantasm.PhantasmCommon;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Collections;
import java.util.List;

import static morningsage.phantasm.PhantasmCommon.ITEM_GROUP;

public class CardShowcaseBlock extends Block {
    public static final Identifier IDENTIFIER = PhantasmCommon.createIdentifier("card_showcase");

    public CardShowcaseBlock() {
        super(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE));

        Registry.register(Registry.BLOCK, CardShowcaseBlock.IDENTIFIER, this);
        Registry.register(Registry.ITEM, IDENTIFIER, new BlockItem(this, new Item.Settings().group(ITEM_GROUP)));
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(PhantasmBlocks.CARD_SHOWCASE));
    }
}
