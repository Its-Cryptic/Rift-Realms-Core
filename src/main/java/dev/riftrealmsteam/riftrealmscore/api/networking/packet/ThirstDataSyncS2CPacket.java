package dev.riftrealmsteam.riftrealmscore.api.networking.packet;

import dev.riftrealmsteam.riftrealmscore.api.client.ClientThirstData;
import net.minecraft.network.FriendlyByteBuf;
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
