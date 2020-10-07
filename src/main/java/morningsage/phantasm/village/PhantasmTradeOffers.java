package morningsage.phantasm.village;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

public final class PhantasmTradeOffers {
    public static void addTrade(VillagerProfession profession, int level, TradeOffers.Factory factory) {
        addTrades(profession, level, new TradeOffers.Factory[] { factory });
    }
    public static void addTrades(VillagerProfession profession, int level, TradeOffers.Factory[] factories) {
        // Get the current list of trades (if any)
        Int2ObjectMap<TradeOffers.Factory[]> trades = TradeOffers.PROFESSION_TO_LEVELED_TRADE.getOrDefault(profession, Int2ObjectMaps.emptyMap());

        // Will house all the trades -- the existing ones AND the one being added
        ImmutableMap.Builder<Integer, TradeOffers.Factory[]> builder = ImmutableMap.builder();

        // If the level doesn't exist, we don't need to worry about adding to the existing array
        if (!trades.containsKey(level)) {
            // Add the existing trades as well as the new level/trade
            builder.putAll(trades).put(level, factories);
        } else {
            // The easiest way, at this point, is to loop through the existing trades,
            // append the new one when applicable, and add everything to the builder
            trades.forEach((existingLevel, existingFactories) -> {
                TradeOffers.Factory[] newOffers;

                if (existingLevel == level) {
                    // Extend by one to include the new factory
                    newOffers = new TradeOffers.Factory[existingFactories.length + factories.length];
                    // Copy over
                    System.arraycopy(existingFactories, 0, newOffers, 0, existingFactories.length);
                    // Append the new trades
                    System.arraycopy(factories, 0, newOffers, existingFactories.length, factories.length);
                } else {
                    // No need to do anything special
                    newOffers = factories;
                }

                // Add whatever we have to the builder
                builder.put(existingLevel, newOffers);
            });
        }

        // At this point, everything has been copied over and the new trade should have been added
        TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(profession, new Int2ObjectOpenHashMap<>(builder.build()));
    }

    public static void init() {
        addTrades(PhantasmProfession.CARD_TRADESMAN, 1, new TradeOffers.Factory[] {
            (entity, random) -> new TradeOffer(
                new ItemStack(Items.CACTUS),
                new ItemStack(Items.ITEM_FRAME),
                50, 13, 0.697F
            ),
            (entity, random) -> new TradeOffer(
                new ItemStack(Items.COBBLESTONE, 2),
                new ItemStack(Items.COBBLESTONE_SLAB, 5),
                50, 13, 0.697F
            ),
            (entity, random) -> new TradeOffer(
                new ItemStack(Items.ACACIA_LEAVES),
                new ItemStack(Items.ITEM_FRAME),
                5, 13, 0.697F
            ),
            (entity, random) -> new TradeOffer(
                new ItemStack(Items.ITEM_FRAME, 2),
                new ItemStack(Items.ACACIA_LEAVES, 5),
                5, 13, 0.697F
            )
        });
    }
}
