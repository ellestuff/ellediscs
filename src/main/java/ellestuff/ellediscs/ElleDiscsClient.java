package ellestuff.ellediscs;

import ellestuff.ellediscs.items.DiscIngredientItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import static ellestuff.ellediscs.items.ElleItems.DISC_LABEL;
import static ellestuff.ellediscs.items.ElleItems.DISC_RECORD;

public class ElleDiscsClient implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DiscIngredientItem)stack.getItem()).getColor(stack), DISC_RECORD);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DiscIngredientItem)stack.getItem()).getColor(stack), DISC_LABEL);
	}
}