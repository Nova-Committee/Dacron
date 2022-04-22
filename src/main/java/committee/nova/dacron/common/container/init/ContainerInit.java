package committee.nova.dacron.common.container.init;

import committee.nova.dacron.Dacron;
import committee.nova.dacron.common.container.impl.SmithingContainer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ContainerInit {
    public static final Identifier SMITHING_CONTAINER = new Identifier(Dacron.MODID, "smithing");

    public static void init() {
        ContainerProviderRegistry.INSTANCE.registerFactory(SMITHING_CONTAINER, (syncId, id, player, buf) -> new SmithingContainer(syncId, player.inventory));
    }

    public static void openContainer(Identifier id, PlayerEntity player, BlockPos pos) {
        ContainerProviderRegistry.INSTANCE.openContainer(id, player, (it) -> it.writeBlockPos(pos));
    }
}
