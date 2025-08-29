package ellestuff.ellediscs.recipes.convert;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.patterns.DiscPatterns;
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

public class ConvertDiscRecipeSerializer implements RecipeSerializer<ConvertDiscRecipe>{
	private ConvertDiscRecipeSerializer() { }

	public static final ConvertDiscRecipeSerializer INSTANCE = new ConvertDiscRecipeSerializer();

	public static final Identifier ID = new Identifier("ellediscs:convert_disc");

	@Override
	// Turns json into Recipe
	public ConvertDiscRecipe read(Identifier id, JsonObject json) {
		ConvertDiscRecipeJsonFormat recipeJson = new Gson().fromJson(json, ConvertDiscRecipeJsonFormat.class);

		Ingredient item = Ingredient.fromJson(recipeJson.input);

		ItemStack disc = new ItemStack(Registries.ITEM.getOrEmpty(new Identifier(recipeJson.disc)).get(), 1);

		Optional<Integer> record = JsonHelper.hasElement(json, "record") ? Optional.of(JsonHelper.getInt(json, "record")) : Optional.empty();
		Optional<Integer> label = JsonHelper.hasElement(json, "label") ? Optional.of(JsonHelper.getInt(json, "label")) : Optional.empty();
		Optional<Integer> pattern = JsonHelper.hasElement(json, "pattern") ? Optional.of(JsonHelper.getInt(json, "pattern")) : Optional.empty();
		Optional<DiscPattern> patternType = JsonHelper.hasElement(json, "pattern_type") ? Optional.of(DiscPatterns.getById(JsonHelper.getString(json, "pattern_type"))) : Optional.empty();

		return new ConvertDiscRecipe(item,disc,record,label,pattern,patternType, id);
	}
	@Override
	// Turns Recipe into PacketByteBuf
	public void write(PacketByteBuf packetData, ConvertDiscRecipe recipe) {
		recipe.getItem().write(packetData);
		packetData.writeItemStack(recipe.getDisc());
		packetData.writeInt(recipe.getRecord().get());
		packetData.writeInt(recipe.getLabel().get());
		packetData.writeInt(recipe.getPattern().get());
		packetData.writeIdentifier(recipe.getPatternType().get().getIdentifier());
	}

	@Override
	// Turns PacketByteBuf into Recipe
	public ConvertDiscRecipe read(Identifier id, PacketByteBuf packetData) {
		Ingredient item = Ingredient.fromPacket(packetData);
		ItemStack disc = packetData.readItemStack();
		Optional<Integer> record = Optional.of(packetData.readInt());
		Optional<Integer> label = Optional.of(packetData.readInt());
		Optional<Integer> pattern = Optional.of(packetData.readInt());
		Optional<DiscPattern> patternType = Optional.of(DiscPatterns.getById(packetData.readIdentifier()));

		return new ConvertDiscRecipe(item,disc,record,label,pattern,patternType, id);
	}
}