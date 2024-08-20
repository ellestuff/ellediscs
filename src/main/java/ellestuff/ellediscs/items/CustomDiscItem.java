package ellestuff.ellediscs.items;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.NbtCompound;
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

    public int getRecordColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("RecordColour", 99) ? nbtCompound.getInt("RecordColour") : DEFAULT_RECORD_COLOR;
    }

    public int getLabelColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("LabelColour", 99) ? nbtCompound.getInt("LabelColour") : DEFAULT_LABEL_COLOR;
    }
}
