package ellestuff.ellediscs;

import ellestuff.ellediscs.items.CustomDiscItem;
import ellestuff.ellediscs.items.DiscIngredientItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;

import static ellestuff.ellediscs.items.ElleItems.*;

public class ElleDiscsClient implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DiscIngredientItem)stack.getItem()).getColor(stack), DISC_RECORD);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DiscIngredientItem)stack.getItem()).getColor(stack), DISC_LABEL);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			Item item = stack.getItem();
			if (tintIndex == 0) {
				return ((CustomDiscItem)item).getRecordColor(stack);
			} else if (tintIndex == 1) {
				return ((CustomDiscItem)item).getLabelColor(stack);
			}
			return 0xFFFFFF; // Fallback color for other tint indices
		}, DYED_MUSIC_DISC);
	}
}