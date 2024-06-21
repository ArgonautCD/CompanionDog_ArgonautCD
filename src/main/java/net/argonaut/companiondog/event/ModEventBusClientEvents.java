package net.argonaut.companiondog.event;

import net.argonaut.companiondog.CompanionDog;
import net.argonaut.companiondog.entity.client.GoldenRetriever;
import net.argonaut.companiondog.entity.client.ModModelLayers;
import net.argonaut.companiondog.entity.client.SoulOrbParticle;
import net.argonaut.companiondog.entity.client.screen.NamingDogScreen;
import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.argonaut.companiondog.particles.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CompanionDog.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    private static CompanionDogEntity currentDogEntity;
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.COMPANIONDOG_LAYER, GoldenRetriever::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticlesFactories(final RegisterParticleProvidersEvent event){
        Minecraft.getInstance().particleEngine.register(ModParticles.SOUL_ORB.get(), SoulOrbParticle.Provider::new);
    }

    public static void openNamingDogScreen() {
        Minecraft.getInstance().setScreen(new NamingDogScreen(Component.literal(""), currentDogEntity));
    }

}
