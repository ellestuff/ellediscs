package ellestuff.ellediscs.render;

import ellestuff.ellediscs.ElleDiscs;
import ellestuff.ellediscs.items.CustomDiscItem;
import ellestuff.ellediscs.items.DiscPatternItem;
import ellestuff.ellediscs.patterns.DiscPattern;
import ellestuff.ellediscs.patterns.DiscPatterns;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CustomDiscRenderer {
	public static void renderDisc(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

		ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
		var world = MinecraftClient.getInstance().world;
		var pattern = CustomDiscItem.getPattern(stack);
		var leftHanded = mode == ModelTransformationMode.FIRST_PERSON_LEFT_HAND || mode == ModelTransformationMode.THIRD_PERSON_LEFT_HAND;
		var baseModel = new ModelIdentifier(new Identifier(Registries.ITEM.getId(stack.getItem()).toString() + "_base") ,"inventory");
		var patternModel = new ModelIdentifier(pattern.getModelId(),"inventory");
		var loadedBaseModel = itemRenderer.getModels().getModelManager().getModel(baseModel);
		var loadedPatternModel = itemRenderer.getModels().getModelManager().getModel(patternModel);

		matrices.push();
		loadedBaseModel.getTransformation().getTransformation(mode).apply(leftHanded, matrices);
		loadedPatternModel.getTransformation().getTransformation(mode).apply(leftHanded, matrices);
		itemRenderer.renderItem(stack, ModelTransformationMode.NONE, false, matrices, vertexConsumers, light, overlay, loadedBaseModel);
		itemRenderer.renderItem(stack, ModelTransformationMode.NONE, false, matrices, vertexConsumers, light, overlay, loadedPatternModel);
		matrices.pop();
	}

	public static void registerDiscRenderer(Item item) {
		BuiltinItemRendererRegistry.INSTANCE.register(item, CustomDiscRenderer::renderDisc);
	}

	public static void registerPatternModel(DiscPattern pattern) {
		ModelLoadingPlugin.register(pluginContext -> pluginContext.addModels(new ModelIdentifier(pattern.getModelId(), "inventory")));
	}

	public static void registerDiscModel(Item item) {
		Identifier id = new Identifier(Registries.ITEM.getId(item).toString() + "_base");
		ModelLoadingPlugin.register(pluginContext -> pluginContext.addModels(new ModelIdentifier(id, "inventory")));
	}
}
