package ellestuff.ellediscs.items;

import ellestuff.ellediscs.ElleSoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.NbtCompound;

public class CustomDiscItem extends MusicDiscItem {
    int DEFAULT_RECORD_COLOR;
    int DEFAULT_LABEL_COLOR;
    int comparatorOutput;

    public CustomDiscItem(Item.Settings settings, int record_colour, int label_colour) {
        super(15, ElleSoundEvents.SILENCE, settings, 1);
        DEFAULT_RECORD_COLOR = record_colour;
        DEFAULT_LABEL_COLOR = label_colour;
    }

    public int getRecordColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("colours");
        return nbtCompound != null && nbtCompound.contains("RecordColour", 99) ? nbtCompound.getInt("RecordColour") : DEFAULT_RECORD_COLOR;
    }

    public int getLabelColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("colours");
        return nbtCompound != null && nbtCompound.contains("LabelColour", 99) ? nbtCompound.getInt("LabelColour") : DEFAULT_LABEL_COLOR;
    }

    public void setComparatorOutput(int comparatorOutput) {
        this.comparatorOutput = comparatorOutput;
    }
}
