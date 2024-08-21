package ellestuff.ellediscs.items;

import ellestuff.ellediscs.ElleDiscs;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ElleItems {
    // Disc Pieces
    public static final Item DISC_RECORD = registerItem("disc_record", new DiscIngredientItem(0x515151, new FabricItemSettings().group(ItemGroup.MATERIALS)));
    public static final Item DISC_LABEL = registerItem("disc_label", new DiscIngredientItem(0xffffff, new FabricItemSettings().group(ItemGroup.MATERIALS)));

    // Custom Discs
    public static final Item DYED_MUSIC_DISC = registerItem("custom_disc", new CustomDiscItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 0x515151, 0xffffff));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ElleDiscs.MODID, name), item);
    }
}
