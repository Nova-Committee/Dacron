package committee.nova.dacron.client.screen.base;

import com.mojang.blaze3d.platform.GlStateManager;
import committee.nova.dacron.common.container.base.ForgingContainer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerListener;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;

@Environment(value = EnvType.CLIENT)
public class ForgingScreen<T extends ForgingContainer> extends ContainerScreen<T> implements ContainerListener {
    private final Identifier texture;

    public ForgingScreen(T handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title);
        this.texture = texture;
    }

    protected void setup() {
        if (minecraft == null) return;
        minecraft.keyboard.enableRepeatEvents(true);
    }

    @Override
    protected void init() {
        super.init();
        this.setup();
        this.container.addListener(this);
    }

    @Override
    public void removed() {
        super.removed();
        this.container.removeListener(this);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (minecraft == null) return false;
        if (keyCode == 256) {
            minecraft.player.closeContainer();
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        super.render(mouseX, mouseY, delta);
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        this.renderForeground(mouseX, mouseY, delta);
        this.drawMouseoverTooltip(mouseX, mouseY);
    }

    protected void renderForeground(int mouseX, int mouseY, float delta) {
    }

    @Override
    protected void drawBackground(float delta, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        final MinecraftClient m = this.minecraft;
        if (m == null) return;
        m.getTextureManager().bindTexture(this.texture);
        int i = (this.width - this.containerWidth) / 2;
        int j = (this.height - this.containerHeight) / 2;
        this.blit(i, j, 0, 0, this.containerWidth, this.containerHeight);
        this.blit(i + 59, j + 20, 0, this.containerHeight + (this.container.getSlot(0).hasStack() ? 0 : 16), 110, 16);
        if ((this.container.getSlot(0).hasStack() || this.container.getSlot(1).hasStack()) && !this.container.getSlot(2).hasStack()) {
            this.blit(i + 99, j + 45, this.containerWidth, 0, 28, 21);
        }
    }

    @Override
    public void onContainerRegistered(Container handler, DefaultedList<ItemStack> stacks) {
        this.onContainerSlotUpdate(handler, 0, handler.getSlot(0).getStack());
    }

    @Override
    public void onContainerPropertyUpdate(Container handler, int property, int value) {
    }

    @Override
    public void onContainerSlotUpdate(Container handler, int slotId, ItemStack stack) {
    }
}
