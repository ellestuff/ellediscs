package ellestuff.ellediscs.recipes;

import ellestuff.ellediscs.items.DiscIngredientItem;
import ellestuff.ellediscs.items.ElleItems;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.*;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CustomDiscRecipe extends SpecialCraftingRecipe {
    private static final Ingredient RECORD = Ingredient.ofItems(new ItemConvertible[]{ElleItems.DISC_RECORD});
    private static final Ingredient LABEL = Ingredient.ofItems(new ItemConvertible[]{ElleItems.DISC_LABEL});

    public CustomDiscRecipe(Identifier identifier) {
        super(identifier);
    }

    public boolean matches(CraftingInventory craftingInventory, World world) {
        boolean hasRecord = false;
        boolean hasLabel = false;
        int dust = 0;

        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack = craftingInventory.getStack(i);
            if (RECORD.test(itemStack)) {
                if (hasRecord) {
                    return false;
                }

                hasRecord = true;
            } else if (LABEL.test(itemStack)) {
                if (hasLabel) {
                    return false;
                }

                hasLabel = true;
            } else if (!itemStack.isEmpty()) { return false; };
        }
        return hasRecord && hasLabel;
    }

    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public ItemStack getOutput() {
        return new ItemStack(ElleItems.DYED_MUSIC_DISC);
    }

    public ItemStack craft(CraftingInventory craftingInventory) {
        ItemStack result = new ItemStack(ElleItems.DYED_MUSIC_DISC);
        NbtCompound nbtCompound = result.getOrCreateSubNbt("colours");

        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack = craftingInventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (RECORD.test(itemStack)) {
                    nbtCompound.putInt("RecordColour", ((DiscIngredientItem)itemStack.getItem()).getColor(itemStack));
                } else if (LABEL.test(itemStack)) {
                    nbtCompound.putInt("LabelColour", ((DiscIngredientItem)itemStack.getItem()).getColor(itemStack));
                }
            }
        }
        return result;
    }

    public DiscRecipeSerializer getSerializer() {
        return DiscRecipeSerializer.INSTANCE;
    }
}
