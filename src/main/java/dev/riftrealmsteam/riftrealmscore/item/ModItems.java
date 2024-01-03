package dev.riftrealmsteam.riftrealmscore.item;

import dev.riftrealmsteam.riftrealmscore.RiftRealmsCore;
import dev.riftrealmsteam.riftrealmscore.item.custom.SpellBook;
import dev.riftrealmsteam.riftrealmscore.item.custom.CustomSwordItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.Tiers;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RiftRealmsCore.MODID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    public static final RegistryObject<Item> MY_EVEN_BETTER_SWORD = ITEMS.register("my_best_sword",
            () -> new CustomSwordItem( 50, -2.4F, 0.5F,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    public static final RegistryObject<SpellBook> SPELL_BOOK = ITEMS.register("spell_book",
            () -> new SpellBook(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
}
}
