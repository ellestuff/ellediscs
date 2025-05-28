package ellestuff.ellediscs.items;

import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.patterns.DiscPatterns;
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
    DiscPattern DEFAULT_DISC_PATTERN;
    int DEFAULT_DISC_PATTERN_COLOR;
    int comparatorOutput;

    public CustomDiscItem(Item.Settings settings, int record_colour, int label_colour, DiscPattern default_pattern, int pattern_colour) {
        super(15, SoundEvents.INTENTIONALLY_EMPTY, settings, 1);
        this.DEFAULT_RECORD_COLOR = record_colour;
        this.DEFAULT_LABEL_COLOR = label_colour;
        this.DEFAULT_DISC_PATTERN = default_pattern;
        this.DEFAULT_DISC_PATTERN_COLOR = pattern_colour;
    }

    public int getRecordColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("colours");
        return nbtCompound != null && nbtCompound.contains("RecordColour", 99) ? nbtCompound.getInt("RecordColour") : DEFAULT_RECORD_COLOR;
    }

    public int getLabelColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("colours");
        return nbtCompound != null && nbtCompound.contains("LabelColour", 99) ? nbtCompound.getInt("LabelColour") : DEFAULT_LABEL_COLOR;
    }

    public int getPatternColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("colours");
        return nbtCompound != null && nbtCompound.contains("PatternColour", 99) ? nbtCompound.getInt("PatternColour") : DEFAULT_DISC_PATTERN_COLOR;
    }

    public DiscPattern getPattern(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("colours");
        return nbtCompound != null && nbtCompound.contains("Pattern") ? DiscPatterns.getById(nbtCompound.getString("Pattern")) : DEFAULT_DISC_PATTERN;
    }

    //public void setComparatorOutput(int comparatorOutput) { this.comparatorOutput = comparatorOutput; }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.ellediscs.discs.tip").formatted(Formatting.GRAY));

        if (context.isAdvanced()) {
            String record_hex = Integer.toHexString(getRecordColor(stack)).toUpperCase();
            String label_hex = Integer.toHexString(getLabelColor(stack)).toUpperCase();

            String pattern_hex = Integer.toHexString(getPatternColor(stack)).toUpperCase();
            String pattern = Text.translatable(getPattern(stack).getTranslationKey()).getString();

            MutableText colour_tooltip = Text.translatable("item.ellediscs.discs.disc_tooltip", record_hex, label_hex);
            tooltip.add(colour_tooltip.formatted(Formatting.GRAY));

            if (getPattern(stack) != DiscPatterns.NONE) {
                MutableText pattern_tooltip = Text.translatable("item.ellediscs.discs.pattern_tooltip", pattern, pattern_hex);
                tooltip.add(pattern_tooltip.formatted(Formatting.GRAY));
            }
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();

        if (nbtCompound != null && nbtCompound.contains("CustomSound")) { return "item.ellediscs.music_disc"; }

        return super.getTranslationKey(stack);
    }
}
