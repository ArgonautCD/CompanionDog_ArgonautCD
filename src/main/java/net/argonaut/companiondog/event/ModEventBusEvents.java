package net.argonaut.companiondog.event;

import net.argonaut.companiondog.CompanionDog;
import net.argonaut.companiondog.entity.ModEntities;
import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CompanionDog.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.COMPANIONDOG.get(), CompanionDogEntity.createAttributes().build());
    }


}
