package ellestuff.ellediscs.recipes.label_cutting;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ellestuff.ellediscs.items.DiscPatternItem;
import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.patterns.DiscPatterns;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.Optional;

public class LabelCuttingRecipeSerializer implements RecipeSerializer<LabelCuttingRecipe>{
	private LabelCuttingRecipeSerializer() { }

	public static final LabelCuttingRecipeSerializer INSTANCE = new LabelCuttingRecipeSerializer();

	public static final Identifier ID = new Identifier("ellediscs:label_cutting");

	@Override
	// Turns json into Recipe
	public LabelCuttingRecipe read(Identifier id, JsonObject json) {
		LabelCuttingRecipeJsonFormat recipeJson = new Gson().fromJson(json, LabelCuttingRecipeJsonFormat.class);

		Ingredient item = Ingredient.fromJson(recipeJson.input);
		DiscPattern pattern = DiscPatterns.getById(recipeJson.pattern);

		ItemStack output = item.getMatchingStacks()[0].copy();
		DiscPatternItem.setPattern(output,pattern);

		return new LabelCuttingRecipe(item,pattern, id, output);
	}

	@Override
	// Turns Recipe into PacketByteBuf
	public void write(PacketByteBuf packetData, LabelCuttingRecipe recipe) {
		recipe.getItem().write(packetData);
		packetData.writeString(recipe.getPattern().getIdentifier().toString());
		packetData.writeItemStack(recipe.getOutput());
	}

	@Override
	// Turns PacketByteBuf into Recipe
	public LabelCuttingRecipe read(Identifier id, PacketByteBuf packetData) {
		Ingredient item = Ingredient.fromPacket(packetData);
		DiscPattern pattern = DiscPatterns.getById(packetData.readString());
		ItemStack output = packetData.readItemStack();

		return new LabelCuttingRecipe(item,pattern, id, output);
	}
}