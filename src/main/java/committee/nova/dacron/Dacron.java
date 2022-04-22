package committee.nova.dacron;

import committee.nova.dacron.common.block.init.BlockInit;
import committee.nova.dacron.common.container.init.ContainerInit;
import committee.nova.dacron.common.item.init.ItemInit;
import committee.nova.dacron.common.recipe.init.RecipeInit;
import committee.nova.dacron.common.sound.init.SoundEventInit;
import committee.nova.dacron.common.world.init.WorldGenInit;
import net.fabricmc.api.ModInitializer;

public class Dacron implements ModInitializer {
    public static final String MODID = "dacron";

    @Override
    public void onInitialize() {
        SoundEventInit.init();
        RecipeInit.init();
        ContainerInit.init();
        BlockInit.init();
        ItemInit.init();
        WorldGenInit.initFeatures();
    }
}
