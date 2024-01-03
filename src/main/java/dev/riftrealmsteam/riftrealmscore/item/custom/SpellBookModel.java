package dev.riftrealmsteam.riftrealmscore.item.custom;

import dev.riftrealmsteam.riftrealmscore.RiftRealmsCore;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpellBookModel extends AnimatedGeoModel<SpellBook> {

    private static final Logger LOGGER = RiftRealmsCore.LOGGER;
    @Override
    public ResourceLocation getModelResource(SpellBook spellBook) {
        ResourceLocation modelResource = new ResourceLocation(RiftRealmsCore.MODID, "geo/spellbook.geo.json");
        //LOGGER.info("Loading model resource: " + modelResource);
        return modelResource;

    }

    @Override
    public ResourceLocation getTextureResource(SpellBook spellBook) {
        ResourceLocation textureResource = new ResourceLocation(RiftRealmsCore.MODID, "textures/item/spellbook.png");
        //LOGGER.info("Loading texture resource: " + textureResource);
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(SpellBook spellBook) {
        ResourceLocation animationResource = new ResourceLocation(RiftRealmsCore.MODID, "animations/spellbook.animation.json");
        //LOGGER.info("Loading animation resource: " + animationResource);
        return animationResource;
    }
}
