package ellestuff.ellediscs;

import com.ibm.icu.text.Normalizer2;
import ellestuff.ellediscs.items.DiscPatternItem;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MatrixUtil;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class DiscPatternRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    @Override
    public void render(ItemStack stack, ModelTransformationMode renderMode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        System.out.println("Rendering item: " + stack.getItem().toString());
        matrices.push();

        BakedModel model = MinecraftClient.getInstance().getItemRenderer().getModel(stack, null, null, 0);

        renderLayer(matrices, vertexConsumers, light, overlay, stack, renderMode, model, "ellediscs:textures/item/label_pattern.png");
        renderLayer(matrices, vertexConsumers, light, overlay, stack, renderMode, model, ((DiscPatternItem)stack.getItem()).getPattern().getTexturePath());
        renderLayer(matrices, vertexConsumers, light, overlay, stack, renderMode, model, "ellediscs:textures/item/label_pattern_top.png");

        matrices.pop();
    }

    private void renderLayer(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, ItemStack stack, ModelTransformationMode renderMode, BakedModel model, String texture) {
        // Create a custom RenderLayer for this texture
        Identifier textureId = new Identifier(texture);
        RenderLayer renderLayer = RenderLayer.getCutoutMipped(); // Use Cutout Mipped Render Layer

        // Bind the texture
        MinecraftClient.getInstance().getTextureManager().bindTexture(textureId);

        // Render the item model with the custom texture layer
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, renderMode, false, matrices, vertexConsumers, light, overlay, model);
    }
}

