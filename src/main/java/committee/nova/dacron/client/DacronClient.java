package committee.nova.dacron.client;

import committee.nova.dacron.client.screen.init.ScreenInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DacronClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenInit.init();
    }
}
