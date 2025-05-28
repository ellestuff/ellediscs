package ellestuff.ellediscs.recipes;

import ellestuff.ellediscs.recipes.convert.ConvertDiscRecipe;
import ellestuff.ellediscs.recipes.convert.ConvertDiscRecipeSerializer;
import ellestuff.ellediscs.recipes.disc.CustomDiscRecipe;
import ellestuff.ellediscs.recipes.disc.DiscRecipeSerializer;
import ellestuff.ellediscs.recipes.label.CustomLabelRecipe;
import ellestuff.ellediscs.recipes.label.LabelRecipeSerializer;
import ellestuff.ellediscs.recipes.label_cutting.LabelCuttingRecipe;
import ellestuff.ellediscs.recipes.label_cutting.LabelCuttingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface ElleRecipeSerializers {
	RecipeSerializer<ConvertDiscRecipe> CONVERT_DISC = Registry.register(Registries.RECIPE_SERIALIZER, ConvertDiscRecipeSerializer.ID, ConvertDiscRecipeSerializer.INSTANCE);
	RecipeSerializer<CustomDiscRecipe> DISC = Registry.register(Registries.RECIPE_SERIALIZER, DiscRecipeSerializer.ID, DiscRecipeSerializer.INSTANCE);
	RecipeSerializer<CustomLabelRecipe> LABEL = Registry.register(Registries.RECIPE_SERIALIZER, LabelRecipeSerializer.ID, LabelRecipeSerializer.INSTANCE);
	RecipeSerializer<LabelCuttingRecipe> LABEL_CUTTING = Registry.register(Registries.RECIPE_SERIALIZER, LabelCuttingRecipeSerializer.ID, LabelCuttingRecipeSerializer.INSTANCE);

	public static void registerElleRecipeSerializers() {}
}
