package ellestuff.ellediscs.items;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvents;

public class CustomDiscItem extends MusicDiscItem {
    int DEFAULT_RECORD_COLOR;
    int DEFAULT_LABEL_COLOR;
    int comparatorOutput;

    public CustomDiscItem(Item.Settings settings, int record_colour, int label_colour) {
        super(15, SoundEvents.INTENTIONALLY_EMPTY, settings, 1);
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

    @Override
    public String getTranslationKey(ItemStack stack) {
        String str = "item.ellediscs.custom_disc";
        NbtCompound nbtCompound = stack.getNbt();

        if (nbtCompound != null && nbtCompound.contains("CustomSound")) { str = "item.ellediscs.custom_disc_used"; }

        return str;
    }
}
