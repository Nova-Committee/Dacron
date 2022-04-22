package committee.nova.dacron.client.screen.impl;

import com.mojang.blaze3d.platform.GlStateManager;
import committee.nova.dacron.Dacron;
import committee.nova.dacron.client.screen.base.ForgingScreen;
import committee.nova.dacron.common.container.impl.SmithingContainer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(value = EnvType.CLIENT)
public class SmithingScreen extends ForgingScreen<SmithingContainer> {
    private static final Identifier TEXTURE = new Identifier(Dacron.MODID, "textures/gui/container/smithing.png");
    private final int x;
    private final int y;

    public SmithingScreen(SmithingContainer handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title, TEXTURE);
        this.x = 60;
        this.y = 18;
    }

    @Override
    protected void drawForeground(int mouseX, int mouseY) {
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        this.font.draw(this.title.asFormattedString(), this.x, this.y, 0x404040);
        GlStateManager.enableLighting();
    }
}
