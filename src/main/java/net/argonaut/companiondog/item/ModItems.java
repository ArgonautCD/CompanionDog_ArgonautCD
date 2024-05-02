package net.argonaut.companiondog.item;

import net.argonaut.companiondog.CompanionDog;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS=
            DeferredRegister.create(ForgeRegistries.ITEMS, CompanionDog.MOD_ID);

    public static final RegistryObject<Item> DOG_COLLAR = ITEMS.register("dog_collar", ()-> new Item(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);

    }
}
