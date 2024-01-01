package dev.riftrealmsteam.riftrealmscore.setup;

import dev.riftrealmsteam.riftrealmscore.api.attribute.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModSetup {
    public static void registers(IEventBus modEventBus) {
        Attributes.ATTRIBUTES.register(modEventBus);
    }
}
