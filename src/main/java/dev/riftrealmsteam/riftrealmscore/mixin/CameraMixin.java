package dev.riftrealmsteam.riftrealmscore.mixin;

import dev.riftrealmsteam.riftrealmscore.api.util.ICameraMixin;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Camera.class)
public interface CameraMixin {
    @Accessor
    void setDetached(boolean detached);
}