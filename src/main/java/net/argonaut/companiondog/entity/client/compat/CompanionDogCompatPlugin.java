package net.argonaut.companiondog.entity.client.compat;

import com.legacy.lucent.api.EntityBrightness;
import com.legacy.lucent.api.plugin.ILucentPlugin;
import com.legacy.lucent.api.plugin.LucentPlugin;
import net.argonaut.companiondog.CompanionDog;
import net.argonaut.companiondog.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;

@LucentPlugin
public class CompanionDogCompatPlugin implements ILucentPlugin {

    @Override
    public String ownerModID() {
        return CompanionDog.MOD_ID;
    }
    @SuppressWarnings({"UnstableApiUsage", "removal"})
    public void getEntityLightLevel(EntityBrightness entityBrightness){
        Entity entity = entityBrightness.getEntity();

        if(entity instanceof Player){
            Player player = (Player)entity;
            if(ModList.get().isLoaded("curios") && ModList.get().isLoaded("lucent")){
                if(CuriosApi.getCuriosHelper().findFirstCurio(player, ModItems.SOUL_ORB.get()).isPresent()){
                    entityBrightness.setLightLevel(10);
                }

            }
        }

    }
}
