package committee.nova.dacron.common.mixin;

import committee.nova.dacron.common.container.init.ContainerInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SmithingTableBlock.class)
public abstract class MixinSmithingTable {
    @Inject(method = "activate", at = @At("HEAD"), cancellable = true)
    public void activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<Boolean> cir) {
        if (!world.isClient) ContainerInit.openContainer(ContainerInit.SMITHING_CONTAINER, player, pos);
        cir.setReturnValue(true);
    }
}
