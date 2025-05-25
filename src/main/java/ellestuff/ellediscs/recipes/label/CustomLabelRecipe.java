package ellestuff.ellediscs.recipes.label;

import ellestuff.ellediscs.items.CustomDyeableItem;
import ellestuff.ellediscs.items.DiscPatternItem;
import ellestuff.ellediscs.patterns.DiscPattern;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Optional;

public class CustomLabelRecipe extends SpecialCraftingRecipe {
	private final Ingredient coloured;
	private final Ingredient modifier;
	private final DiscPattern output;

	public CustomLabelRecipe(Ingredient coloured, Ingredient modifier, DiscPattern output, Identifier identifier, CraftingRecipeCategory craftingRecipeCategory) {
		super(identifier, craftingRecipeCategory);

		this.coloured = coloured;
		this.modifier = modifier;
		this.output = output;
	}

	public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
		boolean hasItem = false;
		boolean hasModifier = false;

		for(int i = 0; i < recipeInputInventory.size(); ++i) {
			ItemStack itemStack = recipeInputInventory.getStack(i);
			if (coloured.test(itemStack)) {
				if (hasItem) { return false; }
				hasItem = true;
			} else if (modifier.test(itemStack)) {
				if (hasModifier) { return false; }
				hasModifier = true;
			} else if (!itemStack.isEmpty()) {
				return false;
			};
		}
		return hasItem && hasModifier;
	}

	public boolean fits(int width, int height) {
		return width * height >= 2;
	}

	public Ingredient getItem() { return coloured; }
	public Ingredient getModifier() { return modifier; }
	public DiscPattern getOutput() { return output; }

	public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
		ItemStack item = ItemStack.EMPTY;

		for(int i = 0; i < recipeInputInventory.size(); ++i) {
			ItemStack itemStack = recipeInputInventory.getStack(i);
			if (coloured.test(itemStack)) {
				item = itemStack.copyWithCount(1);
				DiscPatternItem.setPattern(item, output);
			}
		}
		return item;
	}

	public LabelRecipeSerializer getSerializer() {
		return LabelRecipeSerializer.INSTANCE;
	}
}
