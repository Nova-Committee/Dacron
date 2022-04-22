package committee.nova.dacron;

import committee.nova.dacron.common.container.init.ContainerInit;
import committee.nova.dacron.common.recipe.init.RecipeInit;
import net.fabricmc.api.ModInitializer;

public class Dacron implements ModInitializer {
    public static final String MODID = "dacron";

    @Override
    public void onInitialize() {
        RecipeInit.init();
        ContainerInit.init();
    }
}
