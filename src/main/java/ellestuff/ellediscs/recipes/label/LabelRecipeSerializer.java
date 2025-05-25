package ellestuff.ellediscs.recipes.label;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.recipes.label.CustomLabelRecipe;
import ellestuff.ellediscs.recipes.label.LabelRecipeJsonFormat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.Optional;

public class LabelRecipeSerializer implements RecipeSerializer<CustomLabelRecipe>{
	private LabelRecipeSerializer() { }

	public static final LabelRecipeSerializer INSTANCE = new LabelRecipeSerializer();

	public static final Identifier ID = new Identifier("ellediscs:label_pattern_modify");

	@Override
	// Turns json into Recipe
	public CustomLabelRecipe read(Identifier id, JsonObject json) {
		LabelRecipeJsonFormat recipeJson = new Gson().fromJson(json, LabelRecipeJsonFormat.class);

		Ingredient coloured = Ingredient.fromJson(recipeJson.input);
		Ingredient modifier = Ingredient.fromJson(recipeJson.modifier);

		DiscPattern output = new DiscPattern(new Identifier(recipeJson.output));

		return new CustomLabelRecipe(coloured, modifier, output, id, CraftingRecipeCategory.MISC);
	}
	@Override
	// Turns Recipe into PacketByteBuf
	public void write(PacketByteBuf packetData, CustomLabelRecipe recipe) {
		recipe.getItem().write(packetData);
		recipe.getModifier().write(packetData);
		packetData.writeIdentifier(recipe.getOutput().getIdentifier());
	}

	@Override
	// Turns PacketByteBuf into Recipe
	public CustomLabelRecipe read(Identifier id, PacketByteBuf packetData) {
		Ingredient coloured = Ingredient.fromPacket(packetData);
		Ingredient modifier = Ingredient.fromPacket(packetData);
		DiscPattern output = new DiscPattern(packetData.readIdentifier());

		return new CustomLabelRecipe(coloured, modifier, output, id, CraftingRecipeCategory.MISC);
	}
}