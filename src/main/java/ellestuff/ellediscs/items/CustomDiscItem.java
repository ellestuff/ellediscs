package ellestuff.ellediscs.items;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvents;

public class CustomDiscItem extends MusicDiscItem implements DyeableItem {
    int DEFAULT_RECORD_COLOR;
    int DEFAULT_LABEL_COLOR;

    public CustomDiscItem(Item.Settings settings, int record_colour, int label_colour) {
        super(15, SoundEvents.INTENTIONALLY_EMPTY, settings, 0);
        DEFAULT_RECORD_COLOR = record_colour;
        DEFAULT_LABEL_COLOR = label_colour;
    }
}
