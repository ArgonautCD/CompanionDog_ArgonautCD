package net.argonaut.companiondog.item.custom;


import net.argonaut.companiondog.entity.client.screen.NamingDogScreen;
import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.argonaut.companiondog.event.server.ModNetwork;
import net.argonaut.companiondog.event.server.OpenNamingScreenPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;


import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class DogCollarItem extends ForgeSpawnEggItem{
    protected final RandomSource randomSource;

    public DogCollarItem(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props);
        this.randomSource = RandomSource.create();
    }

   @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else if (!pContext.getLevel().isClientSide){
            ItemStack itemstack = pContext.getItemInHand();
            BlockPos blockpos = pContext.getClickedPos();
            Direction direction = pContext.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);

            if (blockstate.is(Blocks.SPAWNER)) {
                BlockEntity blockentity = level.getBlockEntity(blockpos);
                if (blockentity instanceof SpawnerBlockEntity) {
                    SpawnerBlockEntity spawnerblockentity = (SpawnerBlockEntity)blockentity;
                    EntityType<?> entitytype1 = this.getType(itemstack.getTag());
                    spawnerblockentity.setEntityId(entitytype1, level.getRandom());
                    blockentity.setChanged();
                    level.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                    level.gameEvent(pContext.getPlayer(), GameEvent.BLOCK_CHANGE, blockpos);
                    itemstack.shrink(1);
                    return InteractionResult.CONSUME;
                }
            }
            BlockPos blockpos1;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            EntityType<?> entityType = this.getType(itemstack.getTag());
            if (entityType != null) {
                Player player = pContext.getPlayer();
                if (player != null) {
                    boolean alreadyHasDog = level.getEntitiesOfClass(CompanionDogEntity.class, player.getBoundingBox().inflate(128))
                            .stream()
                            .anyMatch(dog -> dog.isOwnedBy(player));

                    if (alreadyHasDog) {
                        player.displayClientMessage(Component.translatable("dog.companiondog.alreadyhasone"), true);
                        return InteractionResult.FAIL;
                    }
                }
                Entity entity = entityType.spawn((ServerLevel) level, itemstack, pContext.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG,
                        true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
                if (entity != null && entity instanceof CompanionDogEntity) {
                    TamableAnimal tamableAnimal = (CompanionDogEntity) entity;
                    if (player != null) {
                        itemstack.shrink(1);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, blockpos);
                        tamableAnimal.tame(player);
                        if (tamableAnimal.isTame()) {
                            tamableAnimal.getNavigation().stop();
                            tamableAnimal.setTarget((LivingEntity) null);
                            tamableAnimal.setOrderedToSit(true);
                            tamableAnimal.level().broadcastEntityEvent(tamableAnimal, (byte) 7);

                            ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new OpenNamingScreenPacket(tamableAnimal.getId()));
                        }
                    }else {
                        tamableAnimal.level().broadcastEntityEvent(tamableAnimal, (byte)6);
                    }
                }
            }
            return InteractionResult.CONSUME;
        }
       return InteractionResult.SUCCESS;
    }




    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.companiondog.dog_collar_shift.tooltip").withStyle(ChatFormatting.AQUA));

        }else{
            pTooltipComponents.add(Component.translatable("tooltip.companiondog.noshift.tooltip").withStyle(ChatFormatting.YELLOW));

        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
