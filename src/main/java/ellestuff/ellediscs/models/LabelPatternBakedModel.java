package ellestuff.ellediscs.models;

import ellestuff.ellediscs.items.DiscPatternItem;
import net.fabricmc.fabric.mixin.renderer.client.SpriteAtlasTextureMixin;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LabelPatternBakedModel implements BakedModel {
    private final BakedModel originalModel;
    private final ItemStack stack;

    public LabelPatternBakedModel(BakedModel originalModel, ItemStack stack) {
        this.originalModel = originalModel;
        this.stack = stack;
    }

    private boolean layerTextureEquals(BakedQuad quad, Identifier texture) {
        return quad.getSprite().getContents().getId().equals(texture);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
        // Delegate to the original model for most layers
        List<BakedQuad> quads = new ArrayList<>(originalModel.getQuads(state, face, random));
        // ((DiscPatternItem)stack.getItem()).getPattern(stack).getTexturePath()



        for (int i = 0; i < quads.size(); i++) {
            BakedQuad quad = quads.get(i);
            //System.out.println(quad.getSprite().getContents().toString() + ", " + quad.getSprite().getAtlasId());

            Identifier textureComparison = new Identifier("ellediscs:item/disc_patterns/none");

            if (layerTextureEquals(quad, textureComparison)) {

                Identifier customTexture = new Identifier(((DiscPatternItem)stack.getItem()).getPattern(stack).getTexturePath());
                //Identifier customTexture = new Identifier("ellediscs:item/disc_patterns/none");

                SpriteAtlasTexture atlas = MinecraftClient.getInstance().getBakedModelManager()
                        .getAtlas(new Identifier("textures/atlas/blocks.png"));

                Sprite sprite = atlas.getSprite(customTexture);

                //System.out.println(customTexture.getPath() + ", " + sprite.getContents().getId().getPath());
                //if (sprite.getContents().getId().getPath().equals("missingno")) { System.out.println("wtf"); }


                // what? why isn't this working?
                // i don't know if it's the newQuad or the quads.set(i, newQuad); either
                // since idk how to test this stuff-

                BakedQuad newQuad = new BakedQuad(
                        quad.getVertexData().clone(),
                        quad.getColorIndex(),
                        quad.getFace(),
                        sprite,
                        quad.hasShade()
                );

                quads.set(i, newQuad);
            }
        }


        return quads;
    }

    // ignore all this
    @Override
    public boolean useAmbientOcclusion() {
        return originalModel.useAmbientOcclusion();
    }

    @Override
    public boolean hasDepth() {
        return originalModel.hasDepth();
    }

    @Override
    public boolean isSideLit() {
        return originalModel.isSideLit();
    }

    @Override
    public boolean isBuiltin() {
        return originalModel.isBuiltin();
    }

    @Override
    public Sprite getParticleSprite() {
        return originalModel.getParticleSprite();
    }

    @Override
    public ModelTransformation getTransformation() {
        return originalModel.getTransformation();
    }

    @Override
    public ModelOverrideList getOverrides() {
        return originalModel.getOverrides();
    }
}