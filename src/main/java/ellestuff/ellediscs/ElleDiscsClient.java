package ellestuff.ellediscs;

import ellestuff.ellediscs.items.CustomDiscItem;
import ellestuff.ellediscs.items.CustomDyeableItem;
import ellestuff.ellediscs.items.DiscPatternItem;
import ellestuff.ellediscs.patterns.DiscPattern;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.ItemRenderContext;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.Item;

import static ellestuff.ellediscs.items.ElleItems.*;
import static ellestuff.ellediscs.patterns.ElleDiscPatterns.*;

public class ElleDiscsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		registerColouredItem(DISC_RECORD);
		registerColouredItem(DISC_LABEL);

		registerColouredItem(BROKEN_RECORD);
		registerColouredItem(ECHO_LABEL);

		registerCustomDisc(DYED_MUSIC_DISC);
		registerCustomDisc(DYED_BROKEN_DISC);
		registerCustomDisc(DYED_ECHO_DISC);

		registerPatternItem(PATTERN_CENTER);
		registerPatternItem(PATTERN_CRACKED);
		registerPatternItem(PATTERN_DUAL);
		registerPatternItem(PATTERN_EDGES);
		registerPatternItem(PATTERN_GILDED);
		registerPatternItem(PATTERN_GRADIENT);
		registerPatternItem(PATTERN_SPIRAL);
		registerPatternItem(PATTERN_STREAKED);
		registerPatternItem(PATTERN_STRIPED);
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

	public void registerPatternItem(Item item) {
        BuiltinItemRendererRegistry.INSTANCE.register(item, new DiscPatternRenderer());

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			Item stackItem = stack.getItem();
			if (tintIndex != 2) {
				return ((DiscPatternItem)stackItem).getColor(stack);
			}
			return 0xFFFFFF; // Fallback color for other tint indices
		}, item);
	}
}