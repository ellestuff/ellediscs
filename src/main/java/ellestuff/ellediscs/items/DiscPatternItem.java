package ellestuff.ellediscs.items;

import ellestuff.ellediscs.ElleDiscs;
import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.patterns.DiscPatterns;
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
	static DiscPattern DEFAULT_PATTERN;

	public DiscPatternItem(DiscPattern defaultPattern, int defaultColour, Item.Settings settings) {
		super(defaultColour, settings);
		DEFAULT_PATTERN = defaultPattern;
	}

	public static DiscPattern getPattern(ItemStack stack) {
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		if (!nbtCompound.contains("pattern")) { nbtCompound.putString("pattern",DEFAULT_PATTERN.getIdentifier().toString()); }

		if (DiscPatterns.getById(nbtCompound.getString("pattern")) == DEFAULT_PATTERN) { nbtCompound.remove("pattern"); }

		return DiscPatterns.getById(nbtCompound.getString("pattern"));
	}

	public static void setPattern(ItemStack stack, DiscPattern pattern) {
		NbtCompound nbtCompound = stack.getOrCreateNbt();
		assert nbtCompound != null;
		nbtCompound.putString("pattern",pattern.getIdentifier().toString());
	}

	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.translatable(getPattern(stack).getTranslationKey()).formatted(Formatting.GRAY));
	}
}
