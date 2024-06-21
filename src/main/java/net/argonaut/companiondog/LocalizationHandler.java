package net.argonaut.companiondog;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Mod.EventBusSubscriber(modid = CompanionDog.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LocalizationHandler {

    private static final String DEFAULT_LANG = "en_us";
    private static final String LANG_PATH = "assets/companiondog/lang/";

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        String currentLang = Locale.getDefault().toString().toLowerCase(Locale.ROOT);
        if (!loadLanguage(currentLang)) {
            loadLanguage(DEFAULT_LANG);
        }
    }

    private static boolean loadLanguage(String lang) {
        String langFile = LANG_PATH + lang + ".json";
        try (InputStream inputStream = LocalizationHandler.class.getClassLoader().getResourceAsStream(langFile)) {
            if (inputStream != null) {
                String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                System.out.println("Loaded language: " + lang);
                return true;
            } else {
                System.out.println("Language file not found: " + langFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}