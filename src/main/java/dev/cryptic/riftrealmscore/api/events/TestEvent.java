package dev.cryptic.riftrealmscore.api.events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Cancelable;

public class TestEvent extends PlayerEvent {

    private final String testId;
    public TestEvent(Player player, String testId) {
        super(player);
        this.testId = testId;
    }


    @Override
    public boolean isCancellable() {
        return true;
    }
    public String getTestId() {
        return this.testId;
    }
}
