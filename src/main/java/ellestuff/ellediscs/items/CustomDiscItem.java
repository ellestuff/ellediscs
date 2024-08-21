package ellestuff.ellediscs.items;

import ellestuff.ellediscs.ElleSoundEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(this.getDescription().formatted(Formatting.GRAY));

        if (context.isAdvanced()) {
            String record_hex = Integer.toHexString(this.getRecordColor(stack)).toUpperCase();
            String label_hex = Integer.toHexString(this.getLabelColor(stack)).toUpperCase();

            String colour_tooltip = String.format("Record: #%s, Label: #%s", record_hex, label_hex);
            tooltip.add(Text.literal(colour_tooltip).formatted(Formatting.GRAY));
        }
    }

    public MutableText getDescription() {
        return Text.translatable("item.ellediscs.discs.tip");
    }

}
