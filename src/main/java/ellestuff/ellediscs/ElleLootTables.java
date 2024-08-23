package ellestuff.ellediscs;

import ellestuff.ellediscs.items.ElleItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ElleLootTables {
    public static final Identifier ANCIENT_CITY_ID = new Identifier("minecraft", "chests/ancient_city");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (ANCIENT_CITY_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(0.0f, 1.0f))
                        .with(ItemEntry.builder(ElleItems.ECHO_LABEL)
                                .weight(1)
                                .build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
