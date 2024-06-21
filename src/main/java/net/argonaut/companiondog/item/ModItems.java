package net.argonaut.companiondog.item;

import net.argonaut.companiondog.CompanionDog;
import net.argonaut.companiondog.entity.ModEntities;
import net.argonaut.companiondog.item.custom.DogCollarItem;
import net.argonaut.companiondog.item.custom.SoulOrb;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = CompanionDog.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS=
            DeferredRegister.create(ForgeRegistries.ITEMS, CompanionDog.MOD_ID);

    public static final RegistryObject<Item> DOG_COLLAR = ITEMS.register("dog_collar", ()-> new DogCollarItem(ModEntities.COMPANIONDOG,0xf6d774, 0xb0883c, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> COMPANIONDOG_SPAWNEGG = ITEMS.register("companiondog_spawnegg", ()-> new ForgeSpawnEggItem(ModEntities.COMPANIONDOG, 0xf6d774,
            0xb0883c, new Item.Properties()));

    public static final RegistryObject<SoulOrb> SOUL_ORB = ITEMS.register("soul_orb_3d", SoulOrb::new);


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
