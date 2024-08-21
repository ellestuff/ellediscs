package ellestuff.ellediscs.recipes;

import ellestuff.ellediscs.items.CustomDyeableItem;
import ellestuff.ellediscs.items.ElleItems;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CustomDiscRecipe extends SpecialCraftingRecipe {
    private final Ingredient record;
    private final Ingredient label;
    private final ItemStack output;
    private final boolean accents;

    public CustomDiscRecipe(Ingredient record, Ingredient label, ItemStack output, boolean allowsAccents, Identifier identifier, CraftingRecipeCategory craftingRecipeCategory) {
        super(identifier, craftingRecipeCategory);
        this.record = record;
        this.label = label;
        this.output = output;
        this.accents = allowsAccents;
    }

    public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
        boolean hasRecord = false;
        boolean hasLabel = false;
        int dust = 0;



        for(int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack = recipeInputInventory.getStack(i);
            if (itemStack.isEmpty()) { continue; }

            if (record.test(itemStack)) {
                if (hasRecord) {
                    return false;
                }

                hasRecord = true;
            } else if (label.test(itemStack)) {
                if (hasLabel) {
                    return false;
                }

                hasLabel = true;
            } else { return false; };
        }
        return hasRecord && hasLabel;
    }

    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public Ingredient getRecord() { return record; }
    public Ingredient getLabel() { return label; }
    public ItemStack getOutput() { return output; }
    public Boolean getAccentBool() { return accents; }

    public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack result = output.copy();
        NbtCompound nbtCompound = result.getOrCreateSubNbt("colours");

        for(int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack = recipeInputInventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (record.test(itemStack)) {
                    nbtCompound.putInt("RecordColour", ((CustomDyeableItem)itemStack.getItem()).getColor(itemStack));
                } else if (label.test(itemStack)) {
                    nbtCompound.putInt("LabelColour", ((CustomDyeableItem)itemStack.getItem()).getColor(itemStack));
                }
            }
        }
        return result;
    }

    public DiscRecipeSerializer getSerializer() {
        return DiscRecipeSerializer.INSTANCE;
    }
}
