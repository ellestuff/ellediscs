package ellestuff.ellediscs.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.NbtCompound;
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

    //public void setComparatorOutput(int comparatorOutput) { this.comparatorOutput = comparatorOutput; }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.ellediscs.discs.tip").formatted(Formatting.GRAY));

        if (context.isAdvanced()) {
            String record_hex = Integer.toHexString(this.getRecordColor(stack)).toUpperCase();
            String label_hex = Integer.toHexString(this.getLabelColor(stack)).toUpperCase();

            // This looks so dumb lol
            MutableText colour_tooltip = Text.translatable("item.ellediscs.discs.record")
                    .append(String.format(": #%s, ", record_hex))
                            .append(Text.translatable("item.ellediscs.discs.label"))
                                    .append(String.format(": #%s", label_hex));
            tooltip.add(colour_tooltip.formatted(Formatting.GRAY));
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        if (stack.hasNbt()) {
            NbtCompound nbt = stack.getNbt();
            if (nbt != null && nbt.contains("CustomSound")) {
                return "item.ellediscs.music_disc";
            }
        }

        return super.getTranslationKey();
    }
}
