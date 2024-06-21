package net.argonaut.companiondog.entity.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.argonaut.companiondog.CompanionDog;
import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.argonaut.companiondog.event.server.ModNetwork;
import net.argonaut.companiondog.event.server.SetDogNamePacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class NamingDogScreen extends Screen implements GuiEventListener {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(CompanionDog.MOD_ID, "textures/gui/collar_gui.png");
    private EditBox nameEditBox;
    private final CompanionDogEntity dogEntity;
    public NamingDogScreen(Component pTitle, CompanionDogEntity dogEntity) {
        super(pTitle);
        this.dogEntity = dogEntity;
    }



    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new Button.Builder(CommonComponents.GUI_DONE, (p_251194) -> {
            this.onDone();}).bounds(this.width / 2 - 100, this.height / 4 + 144, 200, 20).build());
        this.nameEditBox = new EditBox(this.font, this.width / 2 - 23, this.height / 2 - 5, 75, 20, Component.literal(""));
        this.nameEditBox.setMaxLength(9);
        this.nameEditBox.setFocused(true);

        this.addRenderableWidget(this.nameEditBox);
        this.setInitialFocus(this.nameEditBox);
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);

        int textureWidth = 14 * 14;  // Ancho
        int textureHeight = 14 * 4;  // Alto
        int x = (this.width - textureWidth) / 2;
        int y = (this.height - textureHeight) / 2 + 2;

        pGuiGraphics.blit(BACKGROUND_TEXTURE, x, y, 0, 0, textureWidth, textureHeight, textureWidth, textureHeight);

        pGuiGraphics.drawCenteredString(this.font, Component.translatable("screen.name_dog_title"), this.width / 2, y - 10, 0xFFFFFF);

        this.nameEditBox.setBordered(false);
        this.nameEditBox.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void tick() {
        super.tick();
        this.nameEditBox.tick();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void onDone() {
        String name = this.nameEditBox.getValue().trim();
        dogEntity.name = name;
        if (!name.isEmpty()) {
            ModNetwork.INSTANCE.sendToServer(new SetDogNamePacket(dogEntity.getId(), name));
        }
        this.minecraft.setScreen(null);
    }
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.nameEditBox.keyPressed(keyCode, scanCode, modifiers) || this.nameEditBox.canConsumeInput()) {
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (this.nameEditBox.charTyped(codePoint, modifiers)) {
            return true;
        } else {
            return super.charTyped(codePoint, modifiers);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.nameEditBox.mouseClicked(mouseX, mouseY, button)) {
            return true;
        } else {
            return super.mouseClicked(mouseX, mouseY, button);
        }
    }
}
