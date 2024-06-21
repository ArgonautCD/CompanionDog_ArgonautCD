package net.argonaut.companiondog.event.server;

import net.argonaut.companiondog.entity.client.screen.NamingDogScreen;
import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenNamingScreenPacket {
    private final int entityId;

    public OpenNamingScreenPacket (int entityId){
        this.entityId = entityId;
    }
    public static void encode(OpenNamingScreenPacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.entityId);
    }

    public static OpenNamingScreenPacket decode(FriendlyByteBuf buffer) {
        return new OpenNamingScreenPacket(buffer.readInt());
    }

    public static void handle(OpenNamingScreenPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        if (contextSupplier.get().getDirection().getReceptionSide().isClient()) {
            handleClient(packet, contextSupplier);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void handleClient(OpenNamingScreenPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            Level level = Minecraft.getInstance().level;
            if (player != null && level != null) {
                Entity entity = level.getEntity(packet.entityId);
                if (entity instanceof CompanionDogEntity) {
                    CompanionDogEntity dogEntity = (CompanionDogEntity) entity;
                    Minecraft.getInstance().setScreen(new NamingDogScreen(Component.literal(""), dogEntity));
                }
            }
        });
        context.setPacketHandled(true);
    }
}