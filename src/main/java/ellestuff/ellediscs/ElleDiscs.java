package ellestuff.ellediscs;

import ellestuff.ellediscs.recipes.convert.ConvertDiscRecipe;
import ellestuff.ellediscs.recipes.convert.ConvertDiscRecipeSerializer;
import ellestuff.ellediscs.recipes.disc.CustomDiscRecipe;
import ellestuff.ellediscs.recipes.disc.DiscRecipeSerializer;
import ellestuff.ellediscs.recipes.label.CustomLabelRecipe;
import ellestuff.ellediscs.recipes.label.LabelRecipeSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SuspiciousStewRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ellestuff.ellediscs.ElleStats.registerElleStats;
import static ellestuff.ellediscs.items.ElleItems.registerElleItems;
import static ellestuff.ellediscs.recipes.ElleRecipeSerializers.registerElleRecipeSerializers;

public class ElleDiscs implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("ellediscs");
    public static final String MODID = "ellediscs";

	public static final RecipeType<ConvertDiscRecipe> CAULDRON_RECIPE_TYPE = RecipeType.register("ellediscs:cauldron");


	@Override
	public void onInitialize() {
		registerElleItems();
		registerElleStats();
		registerElleRecipeSerializers();
	}
}