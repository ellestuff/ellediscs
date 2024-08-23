package ellestuff.ellediscs.items;

import ellestuff.ellediscs.patterns.DiscPattern;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiscPatternItem extends CustomDyeableItem {
    private final DiscPattern pattern;

    public DiscPatternItem(DiscPattern pattern, int defaultColour, Item.Settings settings) {
        super(defaultColour, settings);
        this.pattern = pattern;
    }

    public DiscPattern getPattern() { return pattern; };

    @Override
    public String getTranslationKey() {
        return "item.ellediscs.label_pattern";
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(this.pattern.getTranslationKey()).formatted(Formatting.GRAY));
    }
}
