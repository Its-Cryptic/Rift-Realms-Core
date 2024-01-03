package dev.riftrealmsteam.riftrealmscore.item.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.riftrealmsteam.riftrealmscore.RiftRealmsCore;
import dev.riftrealmsteam.riftrealmscore.api.util.ClientInfo;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class SpellBookRenderer extends GeoItemRenderer<SpellBook> {

    private static final Logger LOGGER = RiftRealmsCore.LOGGER;
    public SpellBookRenderer() {
        super(new SpellBookModel());
    }

    @Override
    public void render(GeoModel model, SpellBook animatable, float partialTick, RenderType type, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        //LOGGER.info("Rendering SpellBook");

//        IBone lPage = model.getBone("l_page").get();
//        IBone rPage = model.getBone("r_page").get();
//
//        float angle = (float) Math.toRadians(ClientInfo.serverTime + partialTick);
//
//        lPage.setRotationZ(-angle);
//        rPage.setRotationZ(angle);

        super.render(model, animatable, partialTick, type, poseStack, bufferSource, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public RenderType getRenderType(SpellBook animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        //LOGGER.info("Getting RenderType for SpellBook: " + texture);
        return RenderType.entityTranslucent(texture);
    }
}
