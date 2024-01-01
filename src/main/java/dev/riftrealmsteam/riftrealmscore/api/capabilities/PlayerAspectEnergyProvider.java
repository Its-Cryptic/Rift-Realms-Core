package dev.riftrealmsteam.riftrealmscore.api.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerAspectEnergyProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerAspectEnergy> PLAYER_ASPECT_ENERGY = CapabilityManager.get(new CapabilityToken<PlayerAspectEnergy>() { });

    private PlayerAspectEnergy energy = null;
    private final LazyOptional<PlayerAspectEnergy> optional = LazyOptional.of(this::createPlayerAspectEnergy);

    private PlayerAspectEnergy createPlayerAspectEnergy() {
        if (this.energy == null) {
            this.energy = new PlayerAspectEnergy();
        }

        return this.energy;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_ASPECT_ENERGY) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerAspectEnergy().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerAspectEnergy().loadNBTData(nbt);
    }
}
