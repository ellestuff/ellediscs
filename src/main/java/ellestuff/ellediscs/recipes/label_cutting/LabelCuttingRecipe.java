package ellestuff.ellediscs.recipes.label_cutting;

import ellestuff.ellediscs.ElleDiscs;
import ellestuff.ellediscs.items.DiscPatternItem;
import ellestuff.ellediscs.items.ElleItems;
import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.patterns.DiscPatterns;
import ellestuff.ellediscs.recipes.ElleRecipeSerializers;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.CuttingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Optional;

public class LabelCuttingRecipe extends StonecuttingRecipe {
	private final Ingredient input;
	private final DiscPattern pattern;

	public LabelCuttingRecipe(Ingredient input, DiscPattern pattern, Identifier id, ItemStack output) {
		super(id, "label_cutting", input, output);
		this.input = input;
		this.pattern = pattern;
	}

	public boolean fits(int width, int height) {
		return width * height >= 2;
	}

	public Ingredient getItem() { return input; }
	public DiscPattern getPattern() { return pattern; }

	@Override
	public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
		ItemStack item = inventory.getStack(0).copy();
		DiscPatternItem.setPattern(item,pattern);
		return item;
	}

	public LabelCuttingRecipeSerializer getSerializer() {
		return LabelCuttingRecipeSerializer.INSTANCE;
	}
}
