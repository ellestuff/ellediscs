package ellestuff.ellediscs.items;

import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.patterns.ElleDiscPatterns;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiscPatternItem extends CustomDyeableItem {
    public final DiscPattern DEFAULT_PATTERN;

    public DiscPatternItem(DiscPattern defaultPattern, int defaultColour, Item.Settings settings) {
        super(defaultColour, settings);
        this.DEFAULT_PATTERN = defaultPattern;
    }

    public DiscPattern getPattern(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        return nbtCompound != null && nbtCompound.contains("pattern") ? ElleDiscPatterns.getById(nbtCompound.getString("pattern")) : DEFAULT_PATTERN;
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(this.getPattern(stack).getTranslationKey()).formatted(Formatting.GRAY));
    }
}
