package ellestuff.ellediscs.recipes;

import ellestuff.ellediscs.items.CustomDyeableItem;
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

public class CustomDiscRecipe extends SpecialCraftingRecipe {
    private final Ingredient record;
    private final Ingredient label;
    private final Ingredient modifier;
    private final ItemStack output;
    private final boolean accents;

    public CustomDiscRecipe(Ingredient record, Ingredient label, Ingredient modifier, ItemStack output, boolean allowsAccents, Identifier identifier, CraftingRecipeCategory craftingRecipeCategory) {
        super(identifier, craftingRecipeCategory);
        this.record = record;
        this.label = label;
        this.modifier = modifier;
        this.output = output;
        this.accents = allowsAccents;
    }

    public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
        boolean hasRecord = false;
        boolean hasLabel = false;
        boolean hasModifier = false;
        //int dust = 0;



        for(int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack = recipeInputInventory.getStack(i);
            if (itemStack.isEmpty()) { continue; }

            System.out.println(modifier.getMatchingStacks()[0]);

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
            } else if (modifier.test(itemStack)) {
                if (hasModifier) {
                    return false;
                }
                hasModifier = true;
            } else { return false; };
        }
        return hasRecord && hasLabel && (hasModifier || modifier.isEmpty());
    }

    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public Ingredient getRecord() { return record; }
    public Ingredient getLabel() { return label; }
    public Ingredient getModifier() { return modifier; }
    public ItemStack getOutput() { return output; }
    public Boolean getAccentBool() { return accents; }

    public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack result = output.copy();
        NbtCompound nbtCompound = result.getOrCreateSubNbt("colours");

        for(int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack = recipeInputInventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() instanceof DyeableItem && ((DyeableItem)itemStack.getItem()).hasColor(itemStack)) {
                    if (record.test(itemStack)) {
                        nbtCompound.putInt("RecordColour", ((CustomDyeableItem)itemStack.getItem()).getColor(itemStack));
                    } else if (label.test(itemStack)) {
                        nbtCompound.putInt("LabelColour", ((CustomDyeableItem)itemStack.getItem()).getColor(itemStack));
                    }
                }
            }
        }
        return result;
    }

    public DiscRecipeSerializer getSerializer() {
        return DiscRecipeSerializer.INSTANCE;
    }
}
