package dev.cryptic.riftrealmscore.api.networking.packet;

import dev.cryptic.riftrealmscore.api.capabilities.PlayerAspectEnergyProvider;
import dev.cryptic.riftrealmscore.api.client.ClientThirstData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ThirstDataSyncS2CPacket {
    //S2C (Server to Client)
    private final int energy;
    public ThirstDataSyncS2CPacket(int energy) {
        this.energy = energy;
    }

    public ThirstDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.energy = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(energy);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON CLIENT
            ClientThirstData.set(energy);

        });
        return true;
    }
}
