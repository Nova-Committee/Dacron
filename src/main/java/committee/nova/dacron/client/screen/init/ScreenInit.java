package committee.nova.dacron.client.screen.init;

import committee.nova.dacron.client.screen.impl.SmithingScreen;
import committee.nova.dacron.common.container.impl.SmithingContainer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.text.TranslatableText;

import static committee.nova.dacron.common.container.init.ContainerInit.SMITHING_CONTAINER;

public class ScreenInit {
    public static void init() {
        ScreenProviderRegistry.INSTANCE.registerFactory(SMITHING_CONTAINER, (syncId, id, player, buf) -> new SmithingScreen(new SmithingContainer(syncId, player.inventory), player.inventory, new TranslatableText("gui.dacron.smithing.title")));
    }
}
