package dev.cryptic.riftrealmscore.api.attribute;

import dev.cryptic.riftrealmscore.RiftRealmsCore;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = RiftRealmsCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Attributes {
    public static final HashMap<RegistryObject<Attribute>, UUID> UUIDS = new HashMap<>();
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, RiftRealmsCore.MODID);

    public static final RegistryObject<Attribute> ASPECT_PROFICIENCY = registerAttribute("aspect_proficiency",(id) -> new RangedAttribute(id, 0.0D, 0, 10).setSyncable(true), "cd455036-75b4-4013-b9ea-ecccfe08f917");
    public static final RegistryObject<Attribute> ASPECT_POWER = registerAttribute("aspect_power",(id) -> new RangedAttribute(id, 0.0D, 0, 10).setSyncable(true), "53dd12dd-383d-41fa-b5e9-a0334ea07ffb");


    public static RegistryObject<Attribute> registerAttribute(String name, Function<String, Attribute> attribute, String uuid) {
        return registerAttribute(name, attribute, UUID.fromString(uuid));
    }

    public static RegistryObject<Attribute> registerAttribute(String name, Function<String, Attribute> attribute, UUID uuid) {
        RegistryObject<Attribute> registryObject = ATTRIBUTES.register(name, () -> attribute.apply(name));
        UUIDS.put(registryObject, uuid);
        return registryObject;
    }

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
//        event.getTypes().forEach(entity -> {
//            RiftRealmsCore.LOGGER.error(entity.toString());
//            event.add(entity, ASPECT_PROFICIENCY.get());
//            event.add(entity, ASPECT_POWER.get());
//        });
        event.add(EntityType.PLAYER, ASPECT_PROFICIENCY.get());
        event.add(EntityType.PLAYER, ASPECT_POWER.get());
    }

//    @SubscribeEvent
//    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
//        event.getTypes().stream().filter(e -> e == EntityType.PLAYER).forEach(e -> {
//            ATTRIBUTES.getEntries().forEach((v)->{
//                event.add(e, v.get());
//            });
//        });
//    }

//    @SubscribeEvent
//    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
//        for (EntityType<? extends LivingEntity> e : event.getTypes())
//            if (e == EntityType.PLAYER) for (RegistryObject<Attribute> v : ATTRIBUTES.getEntries())
//                event.add(e, v.get());
//    }

//    @SubscribeEvent
//    public void modifyEntityAttributes(EntityAttributeModificationEvent event) {
//        if (!event.has(EntityType.PLAYER, ASPECT_PROFICIENCY.get())) {
//            event.add(EntityType.PLAYER, ASPECT_PROFICIENCY.get());
//        }
//    }


}