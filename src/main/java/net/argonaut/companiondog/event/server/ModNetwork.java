package net.argonaut.companiondog.event.server;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import static net.argonaut.companiondog.CompanionDog.MOD_ID;

public class ModNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;
        INSTANCE.registerMessage(id++, AnimationPacket.class, AnimationPacket::encode, AnimationPacket::decode, AnimationPacket::handle);
        INSTANCE.registerMessage(id++, OpenNamingScreenPacket.class, OpenNamingScreenPacket::encode, OpenNamingScreenPacket::decode, OpenNamingScreenPacket::handle);
        INSTANCE.registerMessage(id++, SetDogNamePacket.class, SetDogNamePacket::encode, SetDogNamePacket::decode, SetDogNamePacket::handle);

    }
}
