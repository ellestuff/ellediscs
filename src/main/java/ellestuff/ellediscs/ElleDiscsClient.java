package ellestuff.ellediscs;

import ellestuff.ellediscs.items.CustomDiscItem;
import ellestuff.ellediscs.items.CustomDyeableItem;
import ellestuff.ellediscs.items.DiscPatternItem;
import ellestuff.ellediscs.patterns.DiscPatterns;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;

import static ellestuff.ellediscs.items.ElleItems.*;
import static ellestuff.ellediscs.render.CustomDiscRenderer.*;

public class ElleDiscsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		registerColouredItem(DISC_RECORD);
		registerColouredItem(DISC_LABEL);

		registerPatternModel(DiscPatterns.CENTER);
		registerPatternModel(DiscPatterns.CRACKED);
		registerPatternModel(DiscPatterns.DUAL);
		registerPatternModel(DiscPatterns.EDGES);
		registerPatternModel(DiscPatterns.GILDED);
		registerPatternModel(DiscPatterns.GRADIENT);
		registerPatternModel(DiscPatterns.NONE);
		registerPatternModel(DiscPatterns.SPIRAL);
		registerPatternModel(DiscPatterns.STREAKED);
		registerPatternModel(DiscPatterns.SLASHED);
		registerPatternModel(DiscPatterns.STRIPED);

		registerCustomDisc(DYED_MUSIC_DISC);
		registerCustomDisc(DYED_BROKEN_DISC);
		registerCustomDisc(DYED_ECHO_DISC);
		registerCustomDisc(DYED_GRADIENT_DISC);

		registerPatternItem(LABEL_PATTERN);
	}

	// Makes registering coloured stuff easier.
	public void registerColouredItem(Item item) {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((CustomDyeableItem)stack.getItem()).getColor(stack), item);
	}

	public void registerCustomDisc(Item item) {
		registerDiscRenderer(item);
		registerDiscModel(item);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			Item stackItem = stack.getItem();
			if (tintIndex == 0) {
				return ((CustomDiscItem)stackItem).getRecordColor(stack);
			} else if (tintIndex == 1) {
				return ((CustomDiscItem)stackItem).getLabelColor(stack);
			} else if (tintIndex == 2) {
				return ((CustomDiscItem)stackItem).getPatternColor(stack);
			}
			return 0xFFFFFF; // Fallback color in case of emergency
		}, item);

	}

	public void registerPatternItem(Item item) {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DiscPatternItem)stack.getItem()).getColor(stack), item);
	}
}