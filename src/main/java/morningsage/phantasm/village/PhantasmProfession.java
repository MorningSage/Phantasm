package morningsage.phantasm.village;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;

import static morningsage.phantasm.PhantasmCommon.createIdentifier;
import static morningsage.phantasm.village.PhantasmPointsOfInterestTypes.CARD_SHOWCASE;

public final class PhantasmProfession {
    public static final VillagerProfession CARD_TRADESMAN = register("card_tradesman", CARD_SHOWCASE, null);

    public static void init() {

    }

    public static VillagerProfession register(String id, PointOfInterestType workStation, @Nullable SoundEvent workSound) {
        return register(id, workStation, ImmutableSet.of(), ImmutableSet.of(), workSound);
    }

    public static VillagerProfession register(String id, PointOfInterestType workStation, ImmutableSet<Item> gatherableItems, ImmutableSet<Block> secondaryJobSites, @Nullable SoundEvent workSound) {
        Identifier identifier = createIdentifier(id);

        VillagerProfessionBuilder builder = VillagerProfessionBuilder.create().id(identifier);
        builder.workstation(workStation).secondaryJobSites(secondaryJobSites);
        builder.harvestableItems(gatherableItems).workSound(workSound);

        return Registry.register(Registry.VILLAGER_PROFESSION, id, builder.build());
    }
}
