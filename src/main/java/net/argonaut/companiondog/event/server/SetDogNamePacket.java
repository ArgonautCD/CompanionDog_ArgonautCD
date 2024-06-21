package net.argonaut.companiondog.event.server;

import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetDogNamePacket {
    private final int entityId;
    private final String name;

    public SetDogNamePacket(int entityId, String name) {
        this.entityId = entityId;
        this.name = name;
    }

    public static void encode(SetDogNamePacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.entityId);
        buffer.writeUtf(msg.name, 32767);
    }

    public static SetDogNamePacket decode(FriendlyByteBuf buffer) {
        int entityId = buffer.readInt();
        String name = buffer.readUtf(32767);
        return new SetDogNamePacket(entityId, name);
    }

    public static void handle(SetDogNamePacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                ServerLevel level = player.getServer().getLevel(player.getCommandSenderWorld().dimension());
                Entity entity = level.getEntity(packet.entityId);
                if (entity instanceof CompanionDogEntity) {
                    CompanionDogEntity dogEntity = (CompanionDogEntity) entity;
                    dogEntity.name = packet.name;
                    dogEntity.setCustomName(Component.literal(packet.name));
                    dogEntity.setCustomNameVisible(true);
                    dogEntity.setPersistenceRequired();
                }
            }
        });
        context.setPacketHandled(true);
    }
}