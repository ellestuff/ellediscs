package ellestuff.ellediscs.items;

import ellestuff.ellediscs.ElleDiscs;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ElleItems {
    // Disc Pieces
    public static final Item DISC_RECORD = registerItem("disc_record", new CustomDyeableItem(0x515151, new FabricItemSettings()));
    public static final Item DISC_LABEL = registerItem("disc_label", new CustomDyeableItem(0xffffff, new FabricItemSettings()));

    // Custom Discs
    public static final Item DYED_MUSIC_DISC = registerItem("custom_disc", new CustomDiscItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 0x515151, 0xffffff));
    public static final Item DYED_BROKEN_DISC = registerItem("custom_broken_disc", new CustomDiscItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 0x515151, 0xffffff));
    public static final Item DYED_ECHO_DISC = registerItem("custom_echo_disc", new CustomDiscItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 0x646464, 0x29dfeb));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ElleDiscs.MODID, name), item);
    }

    public static void registerElleItems() {
        // Ingredients Group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.addBefore(Items.DISC_FRAGMENT_5, DISC_RECORD);
            content.addAfter(DISC_RECORD, DISC_LABEL);
        });
    }
}
