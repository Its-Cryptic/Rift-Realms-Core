package dev.riftrealmsteam.riftrealmscore.event;

import dev.riftrealmsteam.riftrealmscore.RiftRealmsCore;
import dev.riftrealmsteam.riftrealmscore.api.client.ThirstHudOverlay;
import dev.riftrealmsteam.riftrealmscore.api.networking.ModMessages;
import dev.riftrealmsteam.riftrealmscore.api.networking.packet.DrinkWaterC2SPacket;
import dev.riftrealmsteam.riftrealmscore.api.util.ICameraMixin;
import dev.riftrealmsteam.riftrealmscore.api.util.KeyBinding;
import dev.riftrealmsteam.riftrealmscore.item.custom.SpellBookRenderer;
import dev.riftrealmsteam.riftrealmscore.mixin.CameraMixin;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = RiftRealmsCore.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.DRINKING_KEY.consumeClick()) {
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed a Key!"));
                ModMessages.sendToServer(new DrinkWaterC2SPacket());
                Minecraft.getInstance().player.swing(InteractionHand.MAIN_HAND);
            }
        }

        @SubscribeEvent
        public static void clientTickEvent(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.START) {
                Minecraft minecraft = Minecraft.getInstance();
                Camera camera = minecraft.getEntityRenderDispatcher().camera;
                CameraMixin cameraMixin = (CameraMixin) camera;
                //cameraMixin.setDetached(true);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = RiftRealmsCore.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DRINKING_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("thirst", ThirstHudOverlay.HUD_THIRST);
        }

    }
}