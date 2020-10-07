package morningsage.phantasm;

import morningsage.phantasm.blocks.PhantasmBlocks;
import morningsage.phantasm.items.PhantasmItems;
import morningsage.phantasm.village.PhantasmPointsOfInterestTypes;
import morningsage.phantasm.village.PhantasmProfession;
import morningsage.phantasm.village.PhantasmTradeOffers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class PhantasmCommon implements ModInitializer {
    public static final String MOD_ID = "phantasm";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(createIdentifier("items")).build();

    @Override
    public void onInitialize() {
        PhantasmBlocks.init();
        PhantasmItems.init();
        PhantasmPointsOfInterestTypes.init();
        PhantasmProfession.init();
        PhantasmTradeOffers.init();
    }

    public static Identifier createIdentifier(String path) {
        return new Identifier(MOD_ID, path);
    }
}
