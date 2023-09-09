package net.davey.originadditions.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.davey.originadditions.OriginAdditions;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


import java.util.List;

public class RevivalPylonScreen extends AbstractContainerScreen<RevivalPylonMenu> {
    private List<AbstractWidget> pylonButtons = Lists.newArrayList();
    private List<ServerPlayer> players;

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(OriginAdditions.MOD_ID, "textures/gui/revival_pylon.png");

    public RevivalPylonScreen(RevivalPylonMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        
        this.players = inventory.player.getServer().getPlayerList().getPlayers();
    }

    private <T extends AbstractWidget> void addRevivalPylonButton(T widget) {
        this.addRenderableWidget(widget);
        this.pylonButtons.add(widget);
    }

    @Override
    protected void init() {
        super.init();
        this.pylonButtons.clear();

        int xPos = this.leftPos + 10;

        for (ServerPlayer player : players) {
            this.addRevivalPylonButton(new RevivalPylonButtons(xPos, this.topPos + 12));

            xPos += 22;
        }

    }

    @Override
    protected void renderBg(PoseStack stack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }



     static class RevivalPylonButtons extends AbstractButton{
        int iconX = 176;
        int iconY = 0;

        public RevivalPylonButtons(int x, int y) {
            super(x, y, 22, 22, CommonComponents.EMPTY);
        }

        public RevivalPylonButtons(int x, int y, Component component) {
            super(x, y, 22, 22, component);

        }

        public void buttonRender(PoseStack pPoseStack) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, RevivalPylonScreen.TEXTURE);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            this.blit(pPoseStack, this.x, this.y, iconX, iconY, this.width, this.height);
        }

        @Override
        public void onPress() {
            
        }

        @Override
        public void updateNarration(NarrationElementOutput p_169152_) {

        }
        // blit(stack, locX, locY, iconX,  iconY, iconWidth, iconHeight)
    }
}
