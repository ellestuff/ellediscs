package ellestuff.ellediscs.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class DiscRecipeSerializer implements RecipeSerializer<CustomDiscRecipe>{
    private DiscRecipeSerializer() { }

    public static final DiscRecipeSerializer INSTANCE = new DiscRecipeSerializer();

    public static final Identifier ID = new Identifier("ellediscs:disc_create");

    @Override
    // Turns json into Recipe
    public CustomDiscRecipe read(Identifier id, JsonObject json) {
        DiscRecipeJsonFormat recipeJson = new Gson().fromJson(json, DiscRecipeJsonFormat.class);

        Ingredient record = Ingredient.fromJson(recipeJson.record);
        Ingredient label = Ingredient.fromJson(recipeJson.label);
        Item outputItem = Registries.ITEM.getOrEmpty(new Identifier(recipeJson.outputDisc)).get();
        ItemStack output = new ItemStack(outputItem, 1);

        return new CustomDiscRecipe(record, label, output, recipeJson.accents, id, CraftingRecipeCategory.MISC);
    }
    @Override
    // Turns Recipe into PacketByteBuf
    public void write(PacketByteBuf packetData, CustomDiscRecipe recipe) {
        recipe.getRecord().write(packetData);
        recipe.getLabel().write(packetData);
        packetData.writeItemStack(recipe.getOutput());
        packetData.writeBoolean(recipe.getAccentBool());
    }

    @Override
    // Turns PacketByteBuf into Recipe
    public CustomDiscRecipe read(Identifier id, PacketByteBuf packetData) {
        Ingredient record = Ingredient.fromPacket(packetData);
        Ingredient label = Ingredient.fromPacket(packetData);
        ItemStack output = packetData.readItemStack();
        boolean accents = packetData.readBoolean();

        return new CustomDiscRecipe(record, label, output, accents, id, CraftingRecipeCategory.MISC);
    }
}