package ellestuff.ellediscs.items;

import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.patterns.DiscPatterns;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

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
		// AudioPlayer support :)
		if (FabricLoader.getInstance().isModLoaded("audioplayer")) {tooltip.add(Text.translatable("item.ellediscs.discs.tip").formatted(Formatting.GRAY)); }
		else { tooltip.add(Text.translatable("item.ellediscs.discs.tip_noap").formatted(Formatting.DARK_RED)); }

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

		// AudioPlayer support :)
		if (nbtCompound != null && nbtCompound.contains("CustomSound")) { return "item.ellediscs.music_disc"; }

		return super.getTranslationKey(stack);
	}

	public static void dismantleHeldDisc(PlayerEntity player, Hand hand, ItemStack stack) {
		if (!(stack.getItem() instanceof CustomDiscItem)) { return; }

		NbtCompound nbtCompound = stack.getOrCreateSubNbt("colours");

		ItemStack record = ElleItems.DISC_RECORD.getDefaultStack().copy();
		if (nbtCompound.contains("RecordColour")) { ((CustomDyeableItem)record.getItem()).setColor(record,nbtCompound.getInt("RecordColour")); }

		ItemStack label = ElleItems.DISC_LABEL.getDefaultStack().copy();
		if (nbtCompound.contains("LabelColour")) { ((CustomDyeableItem)label.getItem()).setColor(label,nbtCompound.getInt("LabelColour")); }

		player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, record));
		player.giveItemStack(label);
		if (nbtCompound.contains("Pattern") || nbtCompound.contains("PatternColour")) {
			ItemStack pattern = ElleItems.LABEL_PATTERN.getDefaultStack().copy();
			if (nbtCompound.contains("Pattern")) { DiscPatternItem.setPattern(pattern, DiscPatterns.getById(nbtCompound.getString("Pattern"))); }
			if (nbtCompound.contains("PatternColour")) { ((DiscPatternItem)pattern.getItem()).setColor(pattern, nbtCompound.getInt("PatternColour")); }
			player.giveItemStack(pattern);
		}
	}
}
