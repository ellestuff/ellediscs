package ellestuff.ellediscs.recipes.convert;

import ellestuff.ellediscs.ElleDiscs;
import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.patterns.DiscPatterns;
import ellestuff.ellediscs.recipes.AbstractCauldronRecipe;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ConvertDiscRecipe extends AbstractCauldronRecipe {
	private final Ingredient input;
	private final Optional<Integer> record;
	private final Optional<Integer> label;
	private final Optional<Integer> pattern;
	private final Optional<DiscPattern> patternType;
	private final ItemStack disc;

	public ConvertDiscRecipe(Ingredient input, ItemStack disc, Optional<Integer> record, Optional<Integer> label, Optional<Integer> pattern, Optional<DiscPattern> patternType, Identifier id) {
		super(ElleDiscs.CAULDRON_RECIPE_TYPE, id, input, disc);
		this.input = input;
		this.disc = disc;
		this.record = record;
		this.label = label;
		this.pattern = pattern;
		this.patternType = patternType;
	}

	public boolean fits(int width, int height) {
		return width * height >= 2;
	}

	public Ingredient getItem() { return input; }
	public ItemStack getDisc() { return disc; }
	public Optional<Integer> getRecord() { return record; }
	public Optional<Integer> getLabel() { return label; }
	public Optional<Integer> getPattern() { return pattern; }
	public Optional<DiscPattern> getPatternType() { return patternType; }

	@Override
	public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
		ItemStack item = disc.copy();
		NbtCompound nbtCompound = item.getOrCreateSubNbt("colours");

		record.ifPresent(integer -> nbtCompound.putInt("RecordColour", integer));
		label.ifPresent(integer -> nbtCompound.putInt("LabelColour", integer));

		if (patternType.orElse(DiscPatterns.NONE) != DiscPatterns.NONE || pattern.isPresent()) {
			nbtCompound.putString("Pattern", patternType.orElse(DiscPatterns.NONE).getIdentifier().toString());
			pattern.ifPresent(integer -> nbtCompound.putInt("PatternColour", integer));
		}

		return item;
	}

	public ConvertDiscRecipeSerializer getSerializer() {
		return ConvertDiscRecipeSerializer.INSTANCE;
	}
}
