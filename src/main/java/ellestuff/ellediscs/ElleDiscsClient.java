package ellestuff.ellediscs;

import ellestuff.ellediscs.items.CustomDiscItem;
import ellestuff.ellediscs.items.CustomDyeableItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;

import static ellestuff.ellediscs.items.ElleItems.*;

public class ElleDiscsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		registerColouredItem(DISC_RECORD);
		registerColouredItem(DISC_LABEL);

		registerCustomDisc(DYED_MUSIC_DISC);
		registerCustomDisc(DYED_BROKEN_DISC);
		registerCustomDisc(DYED_ECHO_DISC);
	}

	// Makes registering coloured stuff easier.
	public void registerColouredItem(Item item) {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((CustomDyeableItem)stack.getItem()).getColor(stack), item);
	}

	public void registerCustomDisc(Item item) {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			Item stackItem = stack.getItem();
			if (tintIndex == 0) {
				return ((CustomDiscItem)stackItem).getRecordColor(stack);
			} else if (tintIndex == 1) {
				return ((CustomDiscItem)stackItem).getLabelColor(stack);
			}
			return 0xFFFFFF; // Fallback color for other tint indices
		}, item);
	}
}