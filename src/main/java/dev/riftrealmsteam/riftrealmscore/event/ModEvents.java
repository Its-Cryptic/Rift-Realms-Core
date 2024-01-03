package dev.riftrealmsteam.riftrealmscore.event;

import dev.riftrealmsteam.riftrealmscore.RiftRealmsCore;
import dev.riftrealmsteam.riftrealmscore.api.capabilities.PlayerAspectEnergy;
import dev.riftrealmsteam.riftrealmscore.api.capabilities.PlayerAspectEnergyProvider;
import dev.riftrealmsteam.riftrealmscore.api.networking.ModMessages;
import dev.riftrealmsteam.riftrealmscore.api.networking.packet.ThirstDataSyncS2CPacket;
import dev.riftrealmsteam.riftrealmscore.api.util.ClientInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = RiftRealmsCore.MODID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                if (!event.getObject().getCapability(PlayerAspectEnergyProvider.PLAYER_ASPECT_ENERGY).isPresent()) {
                    event.addCapability(new ResourceLocation(RiftRealmsCore.MODID, "properties"), new PlayerAspectEnergyProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerAspectEnergyProvider.PLAYER_ASPECT_ENERGY).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerAspectEnergyProvider.PLAYER_ASPECT_ENERGY).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(PlayerAspectEnergy.class);
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.side == LogicalSide.SERVER) {
                if (!event.player.isCreative()) {
                    event.player.getCapability(PlayerAspectEnergyProvider.PLAYER_ASPECT_ENERGY).ifPresent(energy -> {
                        if (energy.getEnergy() > 0
                                && event.player.tickCount % 40 == 0
                                && event.phase == TickEvent.Phase.START) {
                            energy.removeEnergy(1);
                            ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(energy.getEnergy()), ((ServerPlayer) event.player));
                            //event.player.sendSystemMessage(Component.literal("Subtracted Energy"));
                        }
                    });
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if (!event.getLevel().isClientSide()) {
                if (event.getEntity() instanceof ServerPlayer player) {
                    player.getCapability(PlayerAspectEnergyProvider.PLAYER_ASPECT_ENERGY).ifPresent(energy -> {
                        ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(energy.getEnergy()), player);
                    });
                }
            }
        }

        @SubscribeEvent
        public static void updateClientInfoTick(TickEvent.ServerTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                ClientInfo.setServerTime(event.getServer().getTickCount());
                ClientInfo.setLevelTime(event.getServer().getLevel(Level.OVERWORLD).getGameTime());
                event.getServer().getPlayerList().getPlayers().forEach(player -> {
                    //player.sendSystemMessage(Component.literal("Server Time: " + ClientInfo.serverTime));
                    //player.sendSystemMessage(Component.literal("Level Time: " + ClientInfo.levelTime));
                });
            }
        }

    }
}
