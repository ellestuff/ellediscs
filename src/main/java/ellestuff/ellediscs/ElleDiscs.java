package ellestuff.ellediscs;

import ellestuff.ellediscs.recipes.CustomDiscRecipe;
import ellestuff.ellediscs.recipes.DiscRecipeSerializer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ellestuff.ellediscs.items.ElleItems.registerElleItems;

public class ElleDiscs implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("ellediscs");
    public static final String MODID = "ellediscs";

	@Override
	public void onInitialize() {
		registerElleItems();

		Registry.register(Registries.RECIPE_SERIALIZER, DiscRecipeSerializer.ID,
				DiscRecipeSerializer.INSTANCE);
	}
}