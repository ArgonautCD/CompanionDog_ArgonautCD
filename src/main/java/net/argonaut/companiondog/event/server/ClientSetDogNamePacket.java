package net.argonaut.companiondog.event.server;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class ClientSetDogNamePacket {
    public static void handle(SetDogNamePacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // Aquí puedes agregar la lógica específica del cliente si es necesario
        });
        context.setPacketHandled(true);
    }
}
