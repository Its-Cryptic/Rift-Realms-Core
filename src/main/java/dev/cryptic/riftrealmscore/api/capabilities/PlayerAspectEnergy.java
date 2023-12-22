package dev.cryptic.riftrealmscore.api.capabilities;

import net.minecraft.nbt.CompoundTag;

public class PlayerAspectEnergy {
    private int energy = 0;
    private final int MAX_ENERGY = 10;

    public int getEnergy() {
        return energy;
    }

    public void addEnergy(int energyAdded) {
        this.energy = Math.min(energy + energyAdded, MAX_ENERGY);
    }

    public void removeEnergy(int energyRemoved) {
        this.energy = Math.max(energy - energyRemoved, 0);
    }

    public void copyFrom(PlayerAspectEnergy source) {
        this.energy = source.energy;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("energy", energy);
    }

    public void loadNBTData(CompoundTag nbt) {
        energy = nbt.getInt("energy");
    }
}
