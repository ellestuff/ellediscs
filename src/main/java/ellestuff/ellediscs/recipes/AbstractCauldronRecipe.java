package ellestuff.ellediscs.recipes;

import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public abstract class AbstractCauldronRecipe implements Recipe<Inventory> {
	protected final RecipeType<?> type;
	protected final Identifier id;
	protected final Ingredient input;
	protected final ItemStack output;

	public AbstractCauldronRecipe(RecipeType<?> type, Identifier id, Ingredient input, ItemStack output) {
		this.type = type;
		this.id = id;
		this.input = input;
		this.output = output;
	}

	public boolean matches(Inventory inventory, World world) {
		return this.input.test(inventory.getStack(0));
	}

	public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
		return this.output.copy();
	}

	public boolean fits(int width, int height) {
		return true;
	}

	public DefaultedList<Ingredient> getIngredients() {
		DefaultedList<Ingredient> defaultedList = DefaultedList.of();
		defaultedList.add(this.input);
		return defaultedList;
	}

	public ItemStack getOutput(DynamicRegistryManager registryManager) {
		return this.output;
	}

	public Identifier getId() {
		return this.id;
	}

	public RecipeType<?> getType() {
		return this.type;
	}
}
