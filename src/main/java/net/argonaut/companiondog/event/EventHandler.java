package net.argonaut.companiondog.event;

import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.argonaut.companiondog.event.server.AnimationPacket;
import net.argonaut.companiondog.event.server.ModNetwork;
import net.argonaut.companiondog.event.server.SetDogNamePacket;
import net.argonaut.companiondog.item.ModItems;
import net.argonaut.companiondog.particles.ModParticles;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.UUID;

import static net.argonaut.companiondog.CompanionDog.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class EventHandler {

    @SuppressWarnings({"UnstableApiUsage", "removal"})
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            UUID playerUUID = player.getUUID();
            ImmutableTriple<String, Integer, ItemStack> equippedCurio = CuriosApi.getCuriosHelper().findEquippedCurio(ModItems.SOUL_ORB.get(), player).orElse(null);

            if (equippedCurio != null) {
                ItemStack soulOrbStack = equippedCurio.getRight();
                ItemStack soulOrbStackCopy = soulOrbStack.copy();

                if (!soulOrbStack.isEmpty()) {
                    event.setCanceled(true);
                    ServerPlayer serverPlayer = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(playerUUID);

                    if (serverPlayer != null) {
                        serverPlayer.setHealth(1.0F);
                        serverPlayer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                        serverPlayer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                        serverPlayer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));


                        ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new AnimationPacket(soulOrbStackCopy));
                        serverPlayer.level().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.TOTEM_USE, serverPlayer.getSoundSource(), 1.0F, 1.0F);
                        ServerLevel serverLevel = serverPlayer.serverLevel();
                        serverLevel.sendParticles(ModParticles.SOUL_ORB.get(), serverPlayer.getX()+0.0D, serverPlayer.getY() + 1.0D, serverPlayer.getZ(), 128, 0.5D, 1.0D, 0.5D, 0.0D);
                        //serverLevel.addParticle(ModParticles.SOUL_ORB.get(), serverPlayer.getX()+0.0D, serverPlayer.getY() + 1.0D, serverPlayer.getZ(), 64, 0.5D, 1.0D);
                        //ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new ParticlePacket(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 1, 1, 1));

                        soulOrbStack.hurtAndBreak(1, serverPlayer, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
                    }
                }
            }
        }
    }
}