package net.argonaut.companiondog.entity;

import net.argonaut.companiondog.CompanionDog;
import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CompanionDog.MOD_ID);

    public static final RegistryObject<EntityType<CompanionDogEntity>> COMPANIONDOG =
            ENTITY_TYPES.register("goldenretriever", ()-> EntityType.Builder.of(CompanionDogEntity::new, MobCategory.CREATURE)
                    .sized(0.8f,1.8f).build("goldenretriever"));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
