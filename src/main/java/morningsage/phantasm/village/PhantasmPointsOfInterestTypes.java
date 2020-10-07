package morningsage.phantasm.village;

import morningsage.phantasm.blocks.CardShowcaseBlock;
import morningsage.phantasm.blocks.PhantasmBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public final class PhantasmPointsOfInterestTypes {
    public static final PointOfInterestType CARD_SHOWCASE = register(CardShowcaseBlock.IDENTIFIER, 1, 1, PhantasmBlocks.CARD_SHOWCASE);

    public static PointOfInterestType register(Identifier id, int ticketCount, int searchDistance, Block... blocks) {
        return PointOfInterestHelper.register(id, ticketCount, searchDistance, blocks);
    }

    public static void init() {

    }
}
