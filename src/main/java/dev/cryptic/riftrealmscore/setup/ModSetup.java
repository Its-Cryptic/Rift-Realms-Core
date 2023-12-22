package dev.cryptic.riftrealmscore.setup;

import dev.cryptic.riftrealmscore.api.attribute.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModSetup {
    public static void registers(IEventBus modEventBus) {
        Attributes.ATTRIBUTES.register(modEventBus);
    }
}
