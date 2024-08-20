package ellestuff.ellediscs;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElleItems {
    // Disc Pieces
    public static final Item DISC_RECORD = registerItem("disc_record", new DiscIngredientItem(0x515151, new FabricItemSettings()));
    public static final Item DISC_LABEL = registerItem("disc_label", new DiscIngredientItem(0xffffff, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ElleDiscs.MODID, name), item);
    }

    public static void registerElleItems() {
        // Ingredients Group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            //content.addAfter(Items.EMERALD, GELATITE);
        });
    }
}
