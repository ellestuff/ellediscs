package ellestuff.ellediscs.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;

public class DiscRecipeSerializer implements RecipeSerializer<CustomDiscRecipe>{
    private DiscRecipeSerializer() {
    }

    public static final DiscRecipeSerializer INSTANCE = new DiscRecipeSerializer();

    // This will be the "type" field in the json
    public static final Identifier ID = new Identifier("example:example_recipe");

    @Override
    // Turns json into Recipe
    public CustomDiscRecipe read(Identifier id, JsonObject json) {
        return new CustomDiscRecipe(id, CraftingRecipeCategory.MISC);
    }
    @Override
    // Turns Recipe into PacketByteBuf
    public void write(PacketByteBuf packetData, CustomDiscRecipe recipe) {
    }

    @Override
    // Turns PacketByteBuf into Recipe
    public CustomDiscRecipe read(Identifier id, PacketByteBuf packetData) {
        // Make sure the read in the same order you have written!
        return new CustomDiscRecipe(id, CraftingRecipeCategory.MISC);
    }
}