package net.argonaut.companiondog.event.server;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AnimationPacket {
    private final ItemStack itemStack;

    public AnimationPacket(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static void encode(AnimationPacket packet, FriendlyByteBuf buffer) {
        buffer.writeItem(packet.itemStack);
    }

    public static AnimationPacket decode(FriendlyByteBuf buffer) {
        return new AnimationPacket(buffer.readItem());
    }

    public static void handle(AnimationPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                Minecraft.getInstance().gameRenderer.displayItemActivation(packet.itemStack);
            });
        });
        context.setPacketHandled(true);
    }
}
